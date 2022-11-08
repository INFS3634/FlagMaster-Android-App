package au.edu.unsw.infs3634.unswlearning;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;

//Add database entities
@Database(entities = {MCQ.class}, version = 1, exportSchema = false)
public abstract class MCQDatabase extends RoomDatabase {

    //Define database name
    private static final String DATABASE_NAME = "MCQ_Database";
    //Create database instance
    private static MCQDatabase database;

    //Create Dao
    public abstract MCQDao mainDao();

    public ArrayList<MCQ> insertMCQ() {
        ArrayList<MCQ> mcqList = new ArrayList<>();
        mcqList.add(new MCQ(1,"South America", "Brazil", "Tanzania","Nigeria","Rwanda","Brazil"));
        mcqList.add(new MCQ(2,"South America", "Paraguay", "Suriname","Peru","Chile","Chile"));
        mcqList.add(new MCQ(3,"South America", "Argentina", "Uruguay","Escuador","Paraguay","Uruguay"));
        mcqList.add(new MCQ(4,"South America", "Venezuela", "Escuador","Paraguay","Columbia","Columbia"));
        mcqList.add(new MCQ(5,"South America", "Peru", "Brazil","Vanezuela","Argentina","Suriname"));
        mcqList.add(new MCQ(6,"Africa", "South Africa", "Zimbabwe","Angola","Tanzania","South Africa"));
        mcqList.add(new MCQ(7,"Africa", "Mauritius", "Botswana","Tanzania","Rwanda","Botswana"));
        mcqList.add(new MCQ(8,"Africa", "Nigeria", "South Africa","Zimbabwe","Ivory Coast","Nigeria"));
        mcqList.add(new MCQ(9,"Africa", "Botswana", "Ivory Coast","Angola","Egypt","Egypt"));
        mcqList.add(new MCQ(10,"Africa", "Ivory Coast", "Mauritius","Rwanada","Tanzania","Mauritius"));
        mcqList.add(new MCQ(11,"Oceania", "Palau", "Fiji","Tuvalu","Solomon Islands","Tuvalu"));
        mcqList.add(new MCQ(12,"Oceania", "Nauru", "Vanatu","Tonga","Marshall Islands","Nauru"));
        mcqList.add(new MCQ(13,"Oceania", "Papua New Guinea", "Tuvalu","Samoa","Fiji","Papua New Guinea"));
        mcqList.add(new MCQ(14,"Oceania", "Tonga", "Solomon Islands","Palau","Palau","Solomon Islands"));
        mcqList.add(new MCQ(15,"Oceania", "Vanatu", "Samoa","Marshall Islands","Tonga","Samoa"));
        mcqList.add(new MCQ(16,"Europe", "Poland", "Luxembourg","Denmark","Netherlands","Denmark"));
        mcqList.add(new MCQ(17,"Europe", "Netherlands", "France","Italy","Romania","France"));
        mcqList.add(new MCQ(18,"Europe", "Sweden", "Germany","Luxembourg","Poland","Sweden"));
        mcqList.add(new MCQ(19,"Europe", "UK", "Greece","Romania","Italy","UK"));
        mcqList.add(new MCQ(20,"Europe", "Greece", "Poland","Netherlands","Denmark","Netherlands"));
        mcqList.add(new MCQ(21,"Asia", "Iran", "Pakistan","Myanmar","Vietnam","Vietnam"));
        mcqList.add(new MCQ(22,"Asia", "Philippines", "Japan","South Korea","Uzbekistan","Japan"));
        mcqList.add(new MCQ(23,"Asia", "Vietnam", "India","Singapore","Iran","Iran"));
        mcqList.add(new MCQ(24,"Asia", "Japan", "Myanmar","Pakistan","Phillippines","Pakistan"));
        mcqList.add(new MCQ(25,"Asia", "South Korea", "Japan","Iran","Uzbekistan","South Korea"));
        mcqList.add(new MCQ(26,"North America", "Puerto Ricco", "Panama","United States","Canada","United States"));
        mcqList.add(new MCQ(27,"North America", "Costa Rica", "Cuba","Panama","Jamaica","Cuba"));
        mcqList.add(new MCQ(28,"North America", "Jamaica", "Mexico","Puerto Rico","The Bahamas","The Bahamas"));
        mcqList.add(new MCQ(29,"North America", "Panama", "Honduras","Canada","Cuba","Honduras"));
        mcqList.add(new MCQ(30,"North America", "The Bahamas", "Puerto Ricco","Mexico","Costa Rica","Costa Rica"));

        return mcqList;
    }



}
