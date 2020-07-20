package com.myapp.aws.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Date;
import java.util.Map;

@DynamoDBTable(tableName = "IbanStructure")
public class IbanStructure{
    private String modificationFlag;
    private String isoCountryCode;
    private String recordKey;
    private Integer ibanCountryCodePosition;
    private Integer ibanCountryCodeLength;
    private Integer ibanCheckDigitsPosition;
    private Integer ibanCheckDigitsLength;
    private Integer bankIdentifierPosition;
    private Integer bankIdentifierLength;
    private Integer branchIdentifierPosition;
    private Integer branchIdentifierLength;
    private Integer ibanNationalIdLength;
    private Integer accountNumberPosition;
    private Integer accountNumberLength;
    private Integer ibanTotalLength;
    private String sepa;
    private Date lastUpdatedTime;

    @DynamoDBAttribute(attributeName="modificationFlag")
    public String getModificationFlag() {
        return modificationFlag;
    }

    public void setModificationFlag(String modificationFlag) {
        this.modificationFlag = modificationFlag;
    }

    @DynamoDBHashKey(attributeName="isoCountryCode")
    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    @DynamoDBAttribute(attributeName = "recordKey")
    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    @DynamoDBAttribute(attributeName = "ibanCountryCodePosition")
    public Integer getIbanCountryCodePosition() {
        return ibanCountryCodePosition;
    }

    public void setIbanCountryCodePosition(Integer ibanCountryCodePosition) {
        this.ibanCountryCodePosition = ibanCountryCodePosition;
    }

    public void setIbanCountryCodePosition(String ibanCountryCodePosition) {
        if(ibanCountryCodePosition != null && !ibanCountryCodePosition.isEmpty()) {
            this.ibanCountryCodePosition = Integer.valueOf(ibanCountryCodePosition);
        }
    }

    @DynamoDBAttribute(attributeName = "ibanCountryCodeLength")
    public Integer getIbanCountryCodeLength() {
        return ibanCountryCodeLength;
    }

    public void setIbanCountryCodeLength(Integer ibanCountryCodeLength) {
        this.ibanCountryCodeLength = ibanCountryCodeLength;
    }

    public void setIbanCountryCodeLength(String ibanCountryCodeLength) {
        if(ibanCountryCodeLength != null && !ibanCountryCodeLength.isEmpty()) {
            this.ibanCountryCodeLength = Integer.valueOf(ibanCountryCodeLength);
        }
    }

    @DynamoDBAttribute(attributeName = "ibanCheckDigitsPosition")
    public Integer getIbanCheckDigitsPosition() {
        return ibanCheckDigitsPosition;
    }

    public void setIbanCheckDigitsPosition(Integer ibanCheckDigitsPosition) {
        this.ibanCheckDigitsPosition = ibanCheckDigitsPosition;
    }

    public void setIbanCheckDigitsPosition(String ibanCheckDigitsPosition) {
        if(ibanCheckDigitsPosition != null && !ibanCheckDigitsPosition.isEmpty()) {
            this.ibanCheckDigitsPosition = Integer.valueOf(ibanCheckDigitsPosition);
        }
    }

    @DynamoDBAttribute(attributeName = "ibanCheckDigitsLength")
    public Integer getIbanCheckDigitsLength() {
        return ibanCheckDigitsLength;
    }

    public void setIbanCheckDigitsLength(Integer ibanCheckDigitsLength) {
        this.ibanCheckDigitsLength = ibanCheckDigitsLength;
    }

    public void setIbanCheckDigitsLength(String ibanCheckDigitsLength) {
        if(ibanCheckDigitsLength != null && !ibanCheckDigitsLength.isEmpty()) {
            this.ibanCheckDigitsLength = Integer.valueOf(ibanCheckDigitsLength);
        }
    }

    @DynamoDBAttribute(attributeName = "bankIdentifierPosition")
    public Integer getBankIdentifierPosition() {
        return bankIdentifierPosition;
    }

    public void setBankIdentifierPosition(Integer bankIdentifierPosition) {
        this.bankIdentifierPosition = bankIdentifierPosition;
    }

    public void setBankIdentifierPosition(String bankIdentifierPosition) {
        if(bankIdentifierPosition != null && !bankIdentifierPosition.isEmpty()) {
            this.bankIdentifierPosition = Integer.valueOf(bankIdentifierPosition);
        }
    }

    @DynamoDBAttribute(attributeName = "bankIdentifierLength")
    public Integer getBankIdentifierLength() {
        return bankIdentifierLength;
    }

    public void setBankIdentifierLength(Integer bankIdentifierLength) {
        this.bankIdentifierLength = bankIdentifierLength;
    }
    public void setBankIdentifierLength(String bankIdentifierLength) {
        if(bankIdentifierLength != null && !bankIdentifierLength.isEmpty()) {
            this.bankIdentifierLength = Integer.valueOf(bankIdentifierLength);
        }
    }

    @DynamoDBAttribute(attributeName = "branchIdentifierPosition")
    public Integer getBranchIdentifierPosition() {
        return branchIdentifierPosition;
    }

    public void setBranchIdentifierPosition(Integer branchIdentifierPosition) {
        this.branchIdentifierPosition = branchIdentifierPosition;
    }

    public void setBranchIdentifierPosition(String branchIdentifierPosition) {
        if(branchIdentifierPosition != null && !branchIdentifierPosition.isEmpty()) {
            this.branchIdentifierPosition = Integer.valueOf(branchIdentifierPosition);
        }
    }

    @DynamoDBAttribute(attributeName = "branchIdentifierLength")
    public Integer getBranchIdentifierLength() {
        return branchIdentifierLength;
    }

    public void setBranchIdentifierLength(Integer branchIdentifierLength) {
        this.branchIdentifierLength = branchIdentifierLength;
    }

    public void setBranchIdentifierLength(String branchIdentifierLength) {
        if(branchIdentifierLength != null && !branchIdentifierLength.isEmpty()) {
            this.branchIdentifierLength = Integer.valueOf(branchIdentifierLength);
        }
    }

    @DynamoDBAttribute(attributeName = "ibanNationalIdLength")
    public Integer getIbanNationalIdLength() {
        return ibanNationalIdLength;
    }

    public void setIbanNationalIdLength(Integer ibanNationalIdLength) {
        this.ibanNationalIdLength = ibanNationalIdLength;
    }

    public void setIbanNationalIdLength(String ibanNationalIdLength) {
        if(ibanNationalIdLength != null && !ibanNationalIdLength.isEmpty()) {
            this.ibanNationalIdLength = Integer.valueOf(ibanNationalIdLength);
        }
    }

    @DynamoDBAttribute(attributeName = "accountNumberPosition")
    public Integer getAccountNumberPosition() {
        return accountNumberPosition;
    }

    public void setAccountNumberPosition(Integer accountNumberPosition) {
        this.accountNumberPosition = accountNumberPosition;
    }
    public void setAccountNumberPosition(String accountNumberPosition) {
        if(accountNumberPosition != null && !accountNumberPosition.isEmpty()) {
            this.accountNumberPosition = Integer.valueOf(accountNumberPosition);
        }
    }

    @DynamoDBAttribute(attributeName = "accountNumberLength")
    public Integer getAccountNumberLength() {
        return accountNumberLength;
    }

    public void setAccountNumberLength(Integer accountNumberLength) {
        this.accountNumberLength = accountNumberLength;
    }
    public void setAccountNumberLength(String accountNumberLength) {
        if(accountNumberLength != null && !accountNumberLength.isEmpty()) {
            this.accountNumberLength = Integer.valueOf(accountNumberLength);
        }
    }

    @DynamoDBAttribute(attributeName = "ibanTotalLength")
    public Integer getIbanTotalLength() {
        return ibanTotalLength;
    }

    public void setIbanTotalLength(Integer ibanTotalLength) {
        this.ibanTotalLength = ibanTotalLength;
    }
    public void setIbanTotalLength(String ibanTotalLength) {
        if(ibanTotalLength != null && !ibanTotalLength.isEmpty()) {
            this.ibanTotalLength = Integer.valueOf(ibanTotalLength);
        }
    }

    @DynamoDBAttribute(attributeName = "sepa")
    public String getSepa() {
        return sepa;
    }

    public void setSepa(String sepa) {
        this.sepa = sepa;
    }

    public IbanStructure(){

    }

    @DynamoDBAttribute(attributeName = "lastUpdatedTime")
    @DynamoDBAutoGeneratedTimestamp(strategy= DynamoDBAutoGenerateStrategy.ALWAYS)
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public IbanStructure(Map<String, String> records){
        setModificationFlag(records.get("MODIFICATION FLAG"));
        setIsoCountryCode(records.get("IBAN COUNTRY CODE"));
        setRecordKey(records.get("RECORD KEY"));
        setSepa(records.get("SEPA"));
        setIbanCountryCodePosition(records.get("IBAN COUNTRY CODE POSITION"));
        setIbanCountryCodeLength(records.get("IBAN COUNTRY CODE LENGTH"));
        setIbanCheckDigitsPosition(records.get("IBAN CHECK DIGITS POSITION"));
        setIbanCheckDigitsLength(records.get("IBAN CHECK DIGITS LENGTH"));
        setBankIdentifierPosition(records.get("BANK IDENTIFIER POSITION"));
        setBankIdentifierLength(records.get("BANK IDENTIFIER LENGTH"));
        setBranchIdentifierPosition(records.get("BRANCH IDENTIFIER POSITION"));
        setBranchIdentifierLength(records.get("BRANCH IDENTIFIER LENGTH"));
        setIbanNationalIdLength(records.get("IBAN NATIONAL ID LENGTH"));
        setAccountNumberPosition(records.get("ACCOUNT NUMBER POSITION"));
        setAccountNumberLength(records.get("ACCOUNT NUMBER LENGTH"));
        setIbanTotalLength(records.get("IBAN TOTAL LENGTH"));
    }
}