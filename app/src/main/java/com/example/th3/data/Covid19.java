package com.example.th3.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "covid19")
public class Covid19 {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String virusName;
    private boolean hasARN;       // Sửa tên thành hasARN
    private boolean hasProteinS;
    private boolean hasProteinN;
    private String discoveredDate; // Tên thuộc tính đã được sử dụng
    private boolean hasVaccine;
    private int worldwideCases;
    private int vietnamCases;

    // Constructor
    public Covid19(String virusName, boolean hasARN, boolean hasProteinS, boolean hasProteinN,
                   String discoveredDate, boolean hasVaccine, int worldwideCases, int vietnamCases) {
        this.virusName = virusName;
        this.hasARN = hasARN;
        this.hasProteinS = hasProteinS;
        this.hasProteinN = hasProteinN;
        this.discoveredDate = discoveredDate;
        this.hasVaccine = hasVaccine;
        this.worldwideCases = worldwideCases;
        this.vietnamCases = vietnamCases;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVirusName() {
        return virusName;
    }

    public void setVirusName(String virusName) {
        this.virusName = virusName;
    }

    public boolean isHasARN() {
        return hasARN; // Đảm bảo phương thức này có sẵn
    }

    public void setHasARN(boolean hasARN) {
        this.hasARN = hasARN;
    }

    public boolean isHasProteinS() {
        return hasProteinS; // Đảm bảo phương thức này có sẵn
    }

    public void setHasProteinS(boolean hasProteinS) {
        this.hasProteinS = hasProteinS;
    }

    public boolean isHasProteinN() {
        return hasProteinN; // Đảm bảo phương thức này có sẵn
    }

    public void setHasProteinN(boolean hasProteinN) {
        this.hasProteinN = hasProteinN;
    }

    public String getDiscoveredDate() {
        return discoveredDate; // Sử dụng phương thức getter đúng
    }

    public void setDiscoveredDate(String discoveredDate) {
        this.discoveredDate = discoveredDate;
    }

    public boolean isHasVaccine() {
        return hasVaccine; // Đảm bảo phương thức này có sẵn
    }

    public void setHasVaccine(boolean hasVaccine) {
        this.hasVaccine = hasVaccine;
    }

    public int getWorldwideCases() {
        return worldwideCases; // Đảm bảo phương thức này có sẵn
    }

    public void setWorldwideCases(int worldwideCases) {
        this.worldwideCases = worldwideCases;
    }

    public int getVietnamCases() {
        return vietnamCases; // Đảm bảo phương thức này có sẵn
    }

    public void setVietnamCases(int vietnamCases) {
        this.vietnamCases = vietnamCases;
    }
}