package com.example.th3.data;

public class CovidStatistics {
    public int total;        // Tổng số ca
    public int rna;          // Số lượng chủng RNA
    public int proteinS;     // Số lượng chủng Protein S
    public int proteinN;     // Số lượng chủng Protein N

    // Constructor
    public CovidStatistics(int total, int rna, int proteinS, int proteinN) {
        this.total = total;
        this.rna = rna;
        this.proteinS = proteinS;
        this.proteinN = proteinN;
    }

    // Getters and Setters
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRna() {
        return rna;
    }

    public void setRna(int rna) {
        this.rna = rna;
    }

    public int getProteinS() {
        return proteinS;
    }

    public void setProteinS(int proteinS) {
        this.proteinS = proteinS;
    }

    public int getProteinN() {
        return proteinN;
    }

    public void setProteinN(int proteinN) {
        this.proteinN = proteinN;
    }
}