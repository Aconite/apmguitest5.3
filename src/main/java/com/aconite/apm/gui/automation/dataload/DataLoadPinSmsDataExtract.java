package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadPinSmsDataExtract
 * This will call all the functions to put enough data in for a pinsmsdataextract test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinAdvice;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import static com.aconite.apm.gui.automation.utilities.WebRequests.*;

import java.util.HashMap;



public class DataLoadPinSmsDataExtract
{
    public static String runDataLoadPinSmsDataExtract() {

        StringBuilder out = new StringBuilder();
        String xmlrsp;
        String pinEncrypted;
        String pinVerificationValue;
        String pvvKeyIndex;
        String panId;
        String panClear;
        String panSeqNum;
        String panExpDate;
        String issuerPanAlias;


        HashMap<Integer, HashMap<String, String>> responseData;

        /*===============================================================
         * TOWPD - SMS
         *===============================================================*/

        /*--------------------------------------------------------------
         * TOWPD_OT1_SMS
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT1_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_SMS
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT2_SMS(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT2_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_SMS
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT3_SMS(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT3_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_SMS
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT4_SMS(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT4_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_SMS
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT6_SMS(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT6_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*===============================================================
         * TOWPD OT 1 - Delivery Method 1 - PIN Advice
         *===============================================================*/
        /*--------------------------------------------------------------
         * TOWPD_OT1_SMS_DM1
         * PIN Advice By Pan Id
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS_DM1(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT1_SMS_DM1
         *  PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS_DM1(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT1_SMS_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS_DM1(5));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }

            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_SMS_DM1
         * PIN Advice By Pan Id
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT2_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_SMS_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT2_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_SMS_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT2_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }

            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_SMS_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT3_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_SMS_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT3_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_SMS_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT3_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_SMS_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT4_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_SMS_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT4_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_SMS_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT4_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_SMS_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT6_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_SMS_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT6_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_SMS_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = dataload(DataLoadTOWPD.dataloadTOWPD_OT6_SMS_DM1(1));
        responseData = getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS_DM1: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
                out.append("\n");

                /* PIN Advice */
                xmlrsp = dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        return(out.toString());

    }
}


