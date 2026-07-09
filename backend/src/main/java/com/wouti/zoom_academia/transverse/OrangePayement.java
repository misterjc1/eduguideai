package com.wouti.zoom_academia.transverse;

public class OrangePayement {

    private String TYPE;
    private String customer_msisdn;
    private String merchant_msisdn;
    private String api_username;
    private String api_password;
    private String amount;
    private String PROVIDER;
    private String PROVIDER2;
    private String PAYID;
    private String PAYID2;
    private String otp;
    private String reference_number;
    private String ext_txn_id;

    public String getTYPE() {
        return TYPE;
    }
    public void setTYPE(String tYPE) {
        TYPE = tYPE;
    }
    public String getCustomer_msisdn() {
        return customer_msisdn;
    }
    public void setCustomer_msisdn(String customer_msisdn) {
        this.customer_msisdn = customer_msisdn;
    }
    public String getMerchant_msisdn() {
        return merchant_msisdn;
    }
    public void setMerchant_msisdn(String merchant_msisdn) {
        this.merchant_msisdn = merchant_msisdn;
    }
    public String getApi_username() {
        return api_username;
    }
    public void setApi_username(String api_username) {
        this.api_username = api_username;
    }
    public String getApi_password() {
        return api_password;
    }
    public void setApi_password(String api_password) {
        this.api_password = api_password;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getPROVIDER() {
        return PROVIDER;
    }
    public void setPROVIDER(String pROVIDER) {
        PROVIDER = pROVIDER;
    }
    public String getPROVIDER2() {
        return PROVIDER2;
    }
    public void setPROVIDER2(String pROVIDER2) {
        PROVIDER2 = pROVIDER2;
    }
    public String getPAYID() {
        return PAYID;
    }
    public void setPAYID(String pAYID) {
        PAYID = pAYID;
    }
    public String getPAYID2() {
        return PAYID2;
    }
    public void setPAYID2(String pAYID2) {
        PAYID2 = pAYID2;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    public String getReference_number() {
        return reference_number;
    }
    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }
    public String getExt_txn_id() {
        return ext_txn_id;
    }
    public void setExt_txn_id(String ext_txn_id) {
        this.ext_txn_id = ext_txn_id;
    }



}
