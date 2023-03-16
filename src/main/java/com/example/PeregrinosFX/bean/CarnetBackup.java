package com.example.PeregrinosFX.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Carnets Backup")
public class CarnetBackup {
    public String fileName;
    public ArrayList<Carnet> carnetsBackup = new ArrayList<Carnet>();

    public CarnetBackup(String fileName, ArrayList<Carnet> carnetsBackup) {
        this.fileName = fileName;
        this.carnetsBackup = carnetsBackup;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Carnet> getCarnetsBackup() {
        return carnetsBackup;
    }

    public void setCarnetsBackup(ArrayList<Carnet> carnetsBackup) {
        this.carnetsBackup = carnetsBackup;
    }
}
