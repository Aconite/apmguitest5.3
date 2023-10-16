package com.aconite.apm.gui.automation.records;

public class PersoBureauDataRecord {

    private String id;
    private String name;
    private String code;
    private String destinationDirectory;
    private String encryptionZone;
    private String extractPan;
    private String extractPanDisplay;
    private String testOutput;

    public PersoBureauDataRecord(){
        testOutput = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public String getEncryptionZone() {
        return encryptionZone;
    }

    public void setEncryptionZone(String encryptionZone) {
        this.encryptionZone = encryptionZone;
    }

//    public String getExtractPan() {
//        return extractPan;
//    }
//
//    public void setExtractPan(String extractPan) {
//        this.extractPan = extractPan;
//    }

    public String getExtractPanDisplay() {
        return extractPanDisplay;
    }

    public void setExtractPanDisplay(String extractPanDisplay) {
        this.extractPanDisplay = extractPanDisplay;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    @Override
    public String toString() {
        return "Record: [" +
                "ID=" + getId() +
                ", NAME=" + getName() +
                ", CODE=" + getCode() +
                ", DESTINATIONDIRECTORY=" + getDestinationDirectory() +
                ", ENCRYPTIONZONE=" + getEncryptionZone() +
//                ", EXTRACTPAN=" + getExtractPan() +
                ", EXTRACTPANDISPLAY=" + getExtractPanDisplay() +
                "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final PersoBureauDataRecord other = (PersoBureauDataRecord) obj;


        if (!this.getId().equals(other.getId())) {
            return false;
        }

        if (!this.getName().equals(other.getName())) {
            return false;
        }

        if (!this.getCode().equals(other.getCode())) {
            return false;
        }

        if (!this.getDestinationDirectory().equals(other.getDestinationDirectory())) {
            return false;
        }

        if (!this.getEncryptionZone().equals(other.getEncryptionZone())) {
            return false;
        }

//        if (!this.getExtractPan().equals(other.getExtractPan())) {
//            return false;
//        }

        if (!this.getExtractPanDisplay().equals(other.getExtractPanDisplay())) {
            return false;
        }

        return true;
    }

}
