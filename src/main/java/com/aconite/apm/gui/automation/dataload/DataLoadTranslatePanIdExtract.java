package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadTranslatePanIdExtract
 * This will call all the functions to put enough data in for a translatepaniddataextract test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTranslate;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadTranslatePanIdExtract
{

    public static String runDataLoadTranslatePanIdExtract()  {

        HashMap<Integer, HashMap<String, String>> responseData;
        StringBuilder out = new StringBuilder();
        String res="";
        String xmlrsp;
        String pinEncrypted;
        String pinVerificationValue;
        String pvvKeyIndex;
        String panId;
        String panClear;
        String panSeqNum;
        String panExpDate;
        String issuerPanAlias;

        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail
         * Added to ensure there is a token application in the database
         * when starting from an empty database
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail(5));
        responseData = WebRequests.getResponseData(xmlrsp);

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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID=");
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
                out.append("TOWPD_OT1_Mail FAIL: ");
                out.append( responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
        * TranslatePanId
        *--------------------------------------------------------------*/

        responseData = DataLoadTranslate.runTranslatePanId(5);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {

                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");

                out.append("TRX_TYPE_ID 16 - TRANSLATE PAN ID: PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append( panExpDate);
            }
            else{
                out.append("TRX_TYPE_ID 16 - TRANSLATE PAN ID FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

       /*--------------------------------------------------------------
        * TranslatePanId
        *--------------------------------------------------------------*/

        responseData = DataLoadTranslate.runTranslatePan(5);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {

                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");

                out.append("TRX_TYPE_ID 19 - TRANSLATE PAN: PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append( panExpDate);
            }
            else{
                out.append("TRX_TYPE_ID 19 - TRANSLATE PAN FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TranslatePanAlias
         *--------------------------------------------------------------*/

        responseData = DataLoadTranslate.runTranslatePanAlias(5);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {

                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");

                out.append("TRX_TYPE_ID 23 - TRANSLATE PAN ALIAS: PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append( panExpDate);
            }
            else{
                out.append("TRX_TYPE_ID 23 - TRANSLATE PAN ALIAS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }




        return(out.toString());

    }

}