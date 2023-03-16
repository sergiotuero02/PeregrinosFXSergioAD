package com.example.PeregrinosFX.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Carnets Backup")
public class CarnetBackup {
    public String fileName;
    public String[] carnetsBackup;

    public CarnetBackup(String fileName, String[] carnetsBackup) {
        this.fileName = fileName;
        this.carnetsBackup = carnetsBackup;
    }

    public CarnetBackup() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getCarnetsBackup() {
        return carnetsBackup;
    }

    public void setCarnetsBackup(String[] carnetsBackup) {
        this.carnetsBackup = carnetsBackup;
    }
}
