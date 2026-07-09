package com.wouti.zoom_academia.entities;

public class SmsModel {

    private String telNumber;
    private String messageBody;

    public SmsModel(String telNumber, String messageBody) {
        this.telNumber = telNumber;
        this.messageBody = messageBody;
    }

    public String getTelNumber() {
        return telNumber;
    }
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
    public String getMessageBody() {
        return messageBody;
    }
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }


}
