package com.aconite.apm.gui.automation.data;

import java.util.HashMap;

public class CoreErrorMessages {

    private HashMap<String, String> errorCodes = new HashMap<>();

    public CoreErrorMessages(){

        errorCodes.put("35","Application uses shared PIN");
        errorCodes.put("47","Configuration error");
        errorCodes.put("31","Dynamic keys are not supported for this operation");
        errorCodes.put("6","Encryption error");
        errorCodes.put("38","Failed to generate a PIN offset");
        errorCodes.put("39","Failed to generate a PVV");
        errorCodes.put("9","Incorrect PAN");
        errorCodes.put("14","Incorrect PVV");
        errorCodes.put("24","Incorrect SMS password");
        errorCodes.put("37","Invalid encrypted PIN block");
        errorCodes.put("54","Invalid institution ID");
        errorCodes.put("57","Invalid order type");
        errorCodes.put("50","Invalid PIN generation method");
        errorCodes.put("41","Invalid PIN length");
        errorCodes.put("40","Invalid PIN verification method");
        errorCodes.put("53","Invalid PIN verification method");
        errorCodes.put("45","Invalid token identification");
        errorCodes.put("11","Message format error");
        errorCodes.put("60","Message not delivered");
        errorCodes.put("61","Missing message template");
        errorCodes.put("52","Multiple token applications found");
        errorCodes.put("42","No PIN required");
        errorCodes.put("48","No PIN verfication key");
        errorCodes.put("16","No PIN/PUK expected");
        errorCodes.put("55","No previous PIN");
        errorCodes.put("33","No VPP PIN ID exists");
        errorCodes.put("13","Not a default token application");
        errorCodes.put("34","Not a PIN");
        errorCodes.put("43","Online PIN change not permitted");
        errorCodes.put("44","Online viewing not permitted");
        errorCodes.put("1","Operation completed successfully");
        errorCodes.put("5","Operation failed");
        errorCodes.put("3","PAN not found");
        errorCodes.put("36","PIN change not permitted");
        errorCodes.put("4","PIN delivery failed");
        errorCodes.put("49","PIN excluded");
        errorCodes.put("59","PIN failed validation");
        errorCodes.put("17","PIN in use");
        errorCodes.put("58","PIN temporarily locked out");
        errorCodes.put("15","PIN/PUK expected");
        errorCodes.put("2","PIN/PUK not found");
        errorCodes.put("27","PIN/PUK with the same issuer pin id already exists");
        errorCodes.put("46","Request not found");
        errorCodes.put("21","SMS not delivered");
        errorCodes.put("25","Sms password does not exist");
        errorCodes.put("26","Sms password expired");
        errorCodes.put("29","Template not found");
        errorCodes.put("51","The operation cannot be performed on both PIN and PUK");
        errorCodes.put("20","Token application already exists");
        errorCodes.put("19","Token application already has PIN/PUK");
        errorCodes.put("8","Token application not found");
        errorCodes.put("28","Token Application Profile not found");
        errorCodes.put("18","Token in use");
        errorCodes.put("7","Token not found");
        errorCodes.put("12","Token product not found");
        errorCodes.put("10","Unexpected value in a data element");
        errorCodes.put("23","Valid sms password already exists");
        errorCodes.put("32","VPP PIN already exists");

    }

    public String getErrorMessageByCode(String errorCode){

        return errorCodes.get(errorCode);

    }
}
