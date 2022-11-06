package au.edu.unsw.infs3634.unswlearning;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Database(entities = {ShortAnswer.class}, version = 1)
public abstract class ShortAnswerDatabase extends RoomDatabase {

    //Create Dao
    public abstract ShortAnswerDao shortAnswerDao();

    //Define database name
    private static final String DATABASE_NAME = "Short_Answer_Database";
    //Create database instance
    private static ShortAnswerDatabase database;

    public synchronized static ShortAnswerDatabase getInstance(Context context) {
        //Check condition
        if (database == null) {
            //When database is null, initialize database
            database = Room.databaseBuilder(context.getApplicationContext(), ShortAnswerDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        //Return database
        return database;
    }



}
