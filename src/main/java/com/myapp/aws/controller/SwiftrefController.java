package com.myapp.aws.controller;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.myapp.aws.dynamodb.model.AccountNumberFormat;
import com.myapp.aws.dynamodb.model.IbanPlus;
import com.myapp.aws.dynamodb.model.IbanStructure;
import com.myapp.aws.dynamodb.repositories.AccountNumberFormatRepository;
import com.myapp.aws.dynamodb.repositories.IbanPlusRepository;
import com.myapp.aws.dynamodb.repositories.IbanStructureRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/swiftref")
public class SwiftrefController {
    @Autowired
    AccountNumberFormatRepository accountNumberFormatRepository;

    @Autowired
    IbanStructureRepository ibanStructureRepository;

    @Autowired
    IbanPlusRepository ibanPlusRepository;

    @Autowired
    AmazonDynamoDB dynamoDB;

    public static String itemAccountNumberFormat = "AccountNumberFormat";
    public static String itemIbanStructure = "IbanStructure";
    public static String itemIbanPlus = "IbanPlus";



    /*@PostMapping("/accountnumberformat")
    public ResponseEntity<HttpResponse> saveTask(@RequestBody Task newTask,
                                                 @RequestParam(value = "version", required = false) String apiVersion) {
        return taskletService.saveTask(newTask);
    }*/

    @GetMapping("/accountnumberformat/{isoCountryCode}")
    public AccountNumberFormat getAccountNumberFormatByIsoCountryCode(@PathVariable("isoCountryCode") String isoCountryCode,
                                                                      @RequestParam(value = "version", required = false) String apiVersion) {
        System.out.println("Received request: /accountnumberformat/" + isoCountryCode);
        return  getAccountNumberFormat(isoCountryCode);
    }

    @GetMapping("/accountnumberformat")
    public Iterable<AccountNumberFormat> getAllAccountNumberFormats(@RequestParam(value = "version", required = false) String apiVersion) {
        System.out.println("Received request: /accountnumberformat/" );
        return  getAccountNumberFormat();
    }

    @GetMapping("/ibanstructure/{isoCountryCode}")
    public IbanStructure getIbanStructureByIsoCountryCode(@PathVariable("isoCountryCode") String isoCountryCode,
                                                                      @RequestParam(value = "version", required = false) String apiVersion) {
        System.out.println("Received request: /ibanstructure/" + isoCountryCode);

        return getIbanStructure(isoCountryCode);
    }

    @GetMapping("/ibanstructure")
    public Iterable<IbanStructure> getAllIbanStructures() {
        System.out.println("Received request: /ibanstructure/");
        return getIbanStructure();
    }

    @GetMapping("/ibanplus/{iban}")
    public IbanPlus getIbanPlusByIban(@PathVariable("iban") String iban) {
        System.out.println("Received request: /ibanplus/" + iban);
        return getIbanPlus(iban);
    }

    @GetMapping("/processfile")
    public void processFile( @RequestParam(value = "fileName", required = true) String fileName) {
        System.out.println("Received request for processing fileName: " + fileName);
        processFileWithFileName(fileName);
    }


    private AccountNumberFormat getAccountNumberFormat(String isoCountryCode){
        AccountNumberFormat accountNumberFormat = new AccountNumberFormat();
        Optional<AccountNumberFormat> optional = accountNumberFormatRepository.findById(isoCountryCode);
        if(optional != null && optional.isPresent()){
            accountNumberFormat = optional.get();
        }

        return  accountNumberFormat;
    }

    private Iterable<AccountNumberFormat> getAccountNumberFormat() {
        AccountNumberFormat accountNumberFormat = new AccountNumberFormat();

        return accountNumberFormatRepository.findAll();
    }

    private IbanStructure getIbanStructure(String isoCountryCode){
        IbanStructure ibanStructure = new IbanStructure();
        Optional<IbanStructure> optional = ibanStructureRepository.findById(isoCountryCode);
        if(optional != null && optional.isPresent()){
            ibanStructure = optional.get();
        }

        return  ibanStructure;
    }

    private Iterable<IbanStructure> getIbanStructure(){
        return ibanStructureRepository.findAll();
    }

    private IbanPlus getIbanPlus(String iban){
        String isoCountryCode = iban.substring(0,2);
        AccountNumberFormat accountNumberFormat = getAccountNumberFormat(isoCountryCode);
        IbanStructure ibanStructure = getIbanStructure(isoCountryCode);

        int bankIdentifierPosition = ibanStructure.getBankIdentifierPosition();
        int ibanNationalIdLength = ibanStructure.getIbanNationalIdLength();

        String ibanNationalId = iban.substring(bankIdentifierPosition-1, bankIdentifierPosition + ibanNationalIdLength-1);
        String nationalIdCountry = ibanNationalId + "-" + isoCountryCode;
        IbanPlus ibanPlus = new IbanPlus();
        Optional<IbanPlus> optional = ibanPlusRepository.findById(nationalIdCountry);
        if(optional != null && optional.isPresent()){
            ibanPlus = optional.get();
        }

        return  ibanPlus;
    }

    private void processFileWithFileName(String fileName){
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        Instant start = Instant.now();
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            CSVParser parser = CSVParser.parse(inputStream, Charset.defaultCharset(), CSVFormat.TDF.withHeader());

            AccountNumberFormat accountNumberFormat;
            IbanStructure ibanStructure;
            IbanPlus ibanPlus;

            List items = new ArrayList<>();
            int batchSize = 500;
            int totalItems = 0;
            String itemName = getItemName(fileName);

            for (CSVRecord csvRecord : parser) {
                totalItems++;
                System.out.print(totalItems + ",");
                switch (itemName){
                    case ("AccountNumberFormat"):
                        accountNumberFormat = new AccountNumberFormat(csvRecord.toMap());
                        if(accountNumberFormat.getAccountNbType().equalsIgnoreCase("IBAN - Standard")){
                            items.add(accountNumberFormat);
                        }
                        break;

                    case("IbanStructure"):
                        ibanStructure = new IbanStructure(csvRecord.toMap());
                        items.add(ibanStructure);
                        break;

                    case("IbanPlus"):
                        ibanPlus = new IbanPlus(csvRecord.toMap());
                        items.add(ibanPlus);
                        break;

                    default:
                        break;
                }

                if(items.size() == batchSize){
                    //saveAll(itemName, items);

                    mapper.batchSave(items);
                    items = new ArrayList<>();
                }

            }
            if(items.size() > 0){
                //saveAll(itemName, items);
                mapper.batchSave(items);
            }
            System.out.println("Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long timeElapsed = Duration.between(start, Instant.now()).toMillis();  //in millis
        System.out.println("Time taken: " + timeElapsed);
    }

    private void saveAll(String itemName, List items){
        switch (itemName){
            case ("AccountNumberFormat"):
                accountNumberFormatRepository.saveAll(items);
                break;

            case("IbanStructure"):
                ibanStructureRepository.saveAll(items);
                break;

            case("IbanPlus"):
                ibanPlusRepository.saveAll(items);
                break;

            default:
                break;
        }
    }

    private String getItemName(String fileName){
        String itemName = "";
        if(fileName.contains("ACCOUNTNBFORMAT")) {
            itemName = itemAccountNumberFormat;
        }else if(fileName.contains("IBANSTRUCTURE")){
            itemName = itemIbanStructure;
        }else if(fileName.contains("IBANPLUS")){
            itemName = itemIbanPlus;
        }
        return  itemName;
    }
}
