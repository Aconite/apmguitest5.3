package com.aconite.apm.gui.automation.testcases;

import java.util.HashMap;
import java.util.Map;

public class ValidationTestcases {

    Map<String, String> testCases = new HashMap<>();

    public ValidationTestcases() {



        testCases.put("Decimal_Value","12");
        testCases.put("Three_Digit_Decimal_Value","123");
        testCases.put("Zero_Value","0");
        testCases.put("Negative_Integer_Value","-1");
        testCases.put("String_Value","gg");
        testCases.put("Less_Than","<");
        testCases.put("Greater_Than",">");
        testCases.put("Back_Slash","\\");
        testCases.put("Spaces","  ");
        testCases.put("Hexadecimal_Value","6e4afe23");
        testCases.put("Acute_e_Hexadecimal_Value","6"+"\u00e9"+"4af"+"\u00e9"+"23");
        testCases.put("Spaced_String","Hello world");
        testCases.put("Apostrophe_String","O' Connor");
        testCases.put("Ampersand_String","Bunkum & Balderdash");
        testCases.put("Finnish_Character_String","\u00c4"+"\u00c5"+"\u00d6"+"\u00f6"+"\u00e4"+
                "\u00e5"+"\u0160"+"\u0161"+"\u017d"+"\u017e");
        testCases.put("Euro_Character_String","\u20AC"+" Character");
        testCases.put("Tilde_Character_String","Se"+"\u00f1"+"or Chips");
        testCases.put("Grave_and_Acute_e_String",""+"\u00c9"+"l"+"\u00e8"+"ne");
        testCases.put("Blank","");
        testCases.put("Excluded_Pins","1111,2222");
        testCases.put("Punctuation_String","\u0021"+"\u00a3"+"\u0024"+"\u0025"+"\u005e"+"\u0026"+
                "\u002a"+"\u0028"+"\u0029"+"\u005f"+"\u002d"+"\u007e"+
                "\u0040"+"\u0027"+"\u003a"+"\u003b"+"\u007c"+"\u002c"+
                "\u002e"+"\u002f"+"\u0023");

    }

    public Map<String, String> getTestcases()
    {
        return testCases;
    }


}
