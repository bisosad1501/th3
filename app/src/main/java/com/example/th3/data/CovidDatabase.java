package com.example.th3.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Covid19.class}, version = 1)
public abstract class CovidDatabase extends RoomDatabase {
    public abstract CovidDao covidDao();

    private static CovidDatabase INSTANCE;

    public static CovidDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CovidDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CovidDatabase.class, "covid19_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}