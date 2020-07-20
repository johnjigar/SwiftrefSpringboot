package com.myapp.aws.controller;


import com.myapp.aws.dynamodb.model.AccountNumberFormat;
import com.myapp.aws.dynamodb.model.IbanPlus;
import com.myapp.aws.dynamodb.model.IbanStructure;
import com.myapp.aws.dynamodb.repositories.AccountNumberFormatRepository;
import com.myapp.aws.dynamodb.repositories.IbanPlusRepository;
import com.myapp.aws.dynamodb.repositories.IbanStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/swiftref")
public class SwiftrefController {
    @Autowired
    AccountNumberFormatRepository accountNumberFormatRepository;

    @Autowired
    IbanStructureRepository ibanStructureRepositoryRepository;

    @Autowired
    IbanPlusRepository ibanPlusRepository;
    /*@PostMapping("/accountnumberformat")
    public ResponseEntity<HttpResponse> saveTask(@RequestBody Task newTask,
                                                 @RequestParam(value = "version", required = false) String apiVersion) {
        return taskletService.saveTask(newTask);
    }*/

    @GetMapping("/accountnumberformat/{isoCountryCode}")
    public AccountNumberFormat getAccountNumberFormatByIsoCountryCode(@PathVariable("isoCountryCode") String isoCountryCode,
                                                                      @RequestParam(value = "version", required = false) String apiVersion) {
        return  getAccountNumberFormat(isoCountryCode);
    }

    @GetMapping("/accountnumberformat")
    public Iterable<AccountNumberFormat> getAllAccountNumberFormats(@RequestParam(value = "version", required = false) String apiVersion) {
        return  getAccountNumberFormat();
    }

    @GetMapping("/ibanstructure/{isoCountryCode}")
    public IbanStructure getIbanStructureByIsoCountryCode(@PathVariable("isoCountryCode") String isoCountryCode,
                                                                      @RequestParam(value = "version", required = false) String apiVersion) {
        return getIbanStructure(isoCountryCode);
    }

    @GetMapping("/ibanstructure")
    public Iterable<IbanStructure> getAllIbanStructures() {
        return getIbanStructure();
    }

    @GetMapping("/ibanplus/{iban}")
    public IbanPlus getIbanPlusByIban(@PathVariable("iban") String iban,
                                                                      @RequestParam(value = "version", required = false) String apiVersion) {
        return getIbanPlus(iban);
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
        Optional<IbanStructure> optional = ibanStructureRepositoryRepository.findById(isoCountryCode);
        if(optional != null && optional.isPresent()){
            ibanStructure = optional.get();
        }

        return  ibanStructure;
    }

    private Iterable<IbanStructure> getIbanStructure(){
        return ibanStructureRepositoryRepository.findAll();
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
}
