package com.aconite.apm.gui.automation.records;

@SuppressWarnings("Annotation")
public class SmsTemplateTextDataRecord
{
    private String id;
    private String institutionName;
    private String template;
    private String templateType;
    private String languageCode;
    private String abbreviatedLanguageCode;
    private String templateText;
    private String testOutput;

    public SmsTemplateTextDataRecord(){
        testOutput = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getAbbreviatedLanguageCode() {
        return abbreviatedLanguageCode;
    }

    public void setAbbreviatedLanguageCode(String abbreviatedLanguageCode) {
        this.abbreviatedLanguageCode = abbreviatedLanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    @Override
    public String toString() {
        return "SMSTEMPLATETEXTDATARECORD{" +
                "ID='" + id + '\'' +
                ", INSTITUTIONNAME='" + institutionName + '\'' +
                ", TEMPLATE='" + template + '\'' +
                ", TEMPLATETYPE='" + templateType + '\'' +
                ", LANGUAGECODE='" + languageCode + '\'' +
                ", TEMPLATETEXT='" + templateText + '\'' +
                '}';
    }

    public boolean equalsTable(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        final SmsTemplateTextDataRecord other = (SmsTemplateTextDataRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getInstitutionName().equals(other.getInstitutionName()))
        {
            return false;
        }

        if (!this.getTemplate().equals(other.getTemplate()))
        {
            return false;
        }

        if (!this.getTemplateType().equals(other.getTemplateType()))
        {
            return false;
        }

        if (!this.getAbbreviatedLanguageCode().equals(other.getAbbreviatedLanguageCode()))
        {
            return false;
        }

        return true;

    }

    public String toStringTable(){

        return "Record: [ID=" + getId() +
                ", INSTITUTION=" + getInstitutionName() +
                ", TEMPLATE=" + getTemplate() +
                ", TEMPLATETYPE=" + getTemplateType() +
                ", LANGUAGECODE=" + getAbbreviatedLanguageCode() +
                "]";

    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        final SmsTemplateTextDataRecord other = (SmsTemplateTextDataRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getInstitutionName().equals(other.getInstitutionName()))
        {
            return false;
        }

        if (!this.getTemplate().equals(other.getTemplate()))
        {
            return false;
        }

        if (!this.getTemplateType().equals(other.getTemplateType()))
        {
            return false;
        }

        if (!this.getAbbreviatedLanguageCode().equals(other.getLanguageCode()))
        {
            return false;
        }

//        if (!this.getLanguageCode().equals(other.getLanguageCode()))
//        {
//            return false;
//        }
//
//        if (!this.getAbbreviatedLanguageCode().equals(other.getAbbreviatedLanguageCode()))
//        {
//            return false;
//        }

        if (!this.getTemplateText().equals(other.getTemplateText()))
        {
            return false;
        }

        return true;
    }
}