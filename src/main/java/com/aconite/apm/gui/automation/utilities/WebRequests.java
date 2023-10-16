package com.aconite.apm.gui.automation.utilities;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

public class WebRequests
{

    public static String dataload(String xml) {
        String url;
        String username;
        String password;
        StringBuilder response = new StringBuilder();

        url = ConfigUtils.cfgProperty("data_url");
        username = ConfigUtils.cfgProperty("data_username");
        password = ConfigUtils.cfgProperty("data_password");

        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);

            String s = username + ":" + password;
            String basicAuth = "Basic " + Base64.getEncoder().encodeToString(s.getBytes());

            con.setRequestProperty("Authorization", basicAuth);
            con.setRequestProperty("Accept-Encoding", "gzip,deflate");
            con.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
            con.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.5 (Java/16.0.1)");
            con.setRequestProperty("Connection","Keep-Alive");
            con.setRequestProperty("SOAPAction","");
            con.setRequestProperty("Host",ConfigUtils.cfgProperty("host_ip") + ":" + ConfigUtils.cfgProperty("host_port"));
            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Length",String.valueOf(xml.length()));

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return(response.toString());

    }

    public static String XMLtoString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    public static HashMap<Integer, HashMap<String, String>> getResponseData(String rspxml){

        NodeList nodes;
        Document doc;

        HashMap <Integer, HashMap<String, String>> responseData = new HashMap<>();

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(rspxml));
            doc = db.parse(is);
            nodes = doc.getElementsByTagName("record");


            // iterate the records
            for (int i = 0; i < nodes.getLength(); i++) {

                HashMap <String, String> recordData = new HashMap<>();

                NodeList childNodes = nodes.item(i).getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++){

                    recordData.put(childNodes.item(j).getNodeName(), childNodes.item(j).getTextContent());
                    NodeList grandchildNodes = childNodes.item(j).getChildNodes();

                    for (int k = 0; k < grandchildNodes.getLength(); k++){

                        recordData.put(grandchildNodes.item(k).getNodeName(), grandchildNodes.item(k).getTextContent());

                    }

                }

                responseData.put(i, recordData);
            }

            if(nodes.getLength()==0) {

                HashMap<String, String> recordData = new HashMap<>();

                nodes = doc.getElementsByTagName("soapenv:Body");
                NodeList childNodes = nodes.item(0).getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {

                    recordData.put(childNodes.item(j).getNodeName(), childNodes.item(j).getTextContent());
                    NodeList grandchildNodes = childNodes.item(j).getChildNodes();

                    for (int k = 0; k < grandchildNodes.getLength(); k++) {
                        recordData.put(grandchildNodes.item(k).getNodeName(), grandchildNodes.item(k).getTextContent());
                    }

                    responseData.put(j, recordData);
                }

            }



        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * Need to add code here to deal with xml responses that don't have records
         */

        return(responseData);

    }

//    public String getResponseCode(rspxml){
//
//        try {
//
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(rspxml));
//
//            Document doc = db.parse(is);
//            NodeList nodes = doc.getElementsByTagName("ResponseCode");
//
//    }


}

