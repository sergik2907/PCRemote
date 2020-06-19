package com.pccontroll.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.pccontroll.model.Button;

@Database(entities = {Button.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

   public static final String dbName = "pc_remote";

	public abstract ButtonDao buttonDao();
}