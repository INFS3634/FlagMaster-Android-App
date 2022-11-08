package au.edu.unsw.infs3634.unswlearning;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MCQDao {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(MCQ mcq);

    //Delete query
    @Delete
    void delete(MCQ mcq);

    //Delete all query
    @Delete
    void reset(List<MCQ> mcq);

    //Update query
    @Query("UPDATE MCQ " +
            "SET region = :sRegion, choiceA = :sChoiceA, choiceB = :sChoiceB, choiceC = :sChoiceC, choiceD = :sChoiceD, answer = :sAnswer " +
            "WHERE id = :sID")
    void update(int sID, String sRegion, String sChoiceA, String sChoiceB, String sChoiceC, String sChoiceD, String sAnswer);

    //Get all data query
    @Query("SELECT * FROM MCQ")
    List<MCQ> getAll();

    //Get question by region
    @Query("SELECT * FROM MCQ WHERE region = :sRegion")
    List<MCQ> getQuestionByRegion(String sRegion);
}


