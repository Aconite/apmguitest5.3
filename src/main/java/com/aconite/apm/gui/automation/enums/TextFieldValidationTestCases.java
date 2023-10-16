package com.aconite.apm.gui.automation.enums;

import java.util.stream.Stream;

public enum TextFieldValidationTestCases
{
    DECIMAL_VALUE("12"),
    STRING_VALUE("aa"),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    BACK_SLASH("\\"),
    SPACES("  "),
    HEXADECIMAL_VALUE("6e4afe23"),
    ACUTE_E_HEXADECIMAL_VALUE("é562"),
    APOSTROPHE_STRING("O' Connor"),
    AMPERSAND_STRING("Bunkum & Balderdash"),
    FINNISH_CHARACTER_STRING("ÄÅŠŽÖäåšž"),
    EURO_CHARACTER_STRING("€ Character"),
    TILDE_CHARACTER_STRING("Señor Chips"),
    GRAVE_AND_ACUTE_E_STRING("Élène"),
    BLANK(""),
    PUNCTUATION_STRING("!\"£$%^&*()_-~#@':;|,./");

    String testCaseInput;

    TextFieldValidationTestCases(String testCaseInput)
    {
        this.testCaseInput = testCaseInput;
    }

    public static Stream<TextFieldValidationTestCases> stream() {
        return Stream.of(TextFieldValidationTestCases.values());
    }

    public String getTestCaseInput() {
        return testCaseInput;
    }

    public void setTestCaseInput(String testCaseInput) {
        this.testCaseInput = testCaseInput;
    }

    @Override
    public String toString()
    {
        return testCaseInput;
    }
}
