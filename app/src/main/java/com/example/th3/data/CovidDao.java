package com.example.th3.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CovidDao {
    @Insert
    void insert(Covid19 covid19);

    @Update
    void update(Covid19 covid19);

    @Delete
    void delete(Covid19 covid19);

    @Query("SELECT * FROM covid19")
    List<Covid19> getAllCovidStrains();

    @Query("SELECT * FROM covid19 WHERE id = :id")
    Covid19 getCovidStrainById(int id);

    @Query("SELECT * FROM covid19 WHERE discoveredDate BETWEEN :startDate AND :endDate")
    List<Covid19> getCovidStrainsByAppearanceDate(String startDate, String endDate);

    @Query("SELECT COUNT(*) AS total, SUM(CASE WHEN hasARN = 1 THEN 1 ELSE 0 END) AS rna, " +
            "SUM(CASE WHEN hasProteinS = 1 THEN 1 ELSE 0 END) AS proteinS, " +
            "SUM(CASE WHEN hasProteinN = 1 THEN 1 ELSE 0 END) AS proteinN " +
            "FROM covid19 WHERE vietnamCases > 0")
    CovidStatistics getCovidStatisticsForVietnam();

    @Query("SELECT COUNT(*) AS total, SUM(CASE WHEN hasARN = 1 THEN 1 ELSE 0 END) AS rna, " +
            "SUM(CASE WHEN hasProteinS = 1 THEN 1 ELSE 0 END) AS proteinS, " +
            "SUM(CASE WHEN hasProteinN = 1 THEN 1 ELSE 0 END) AS proteinN " +
            "FROM covid19")
    CovidStatistics getCovidStatisticsGlobal();
}