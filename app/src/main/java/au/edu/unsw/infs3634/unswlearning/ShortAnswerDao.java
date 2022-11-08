package au.edu.unsw.infs3634.unswlearning;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ShortAnswerDao {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(ShortAnswer shortAnswer);

    //Delete query
    @Delete
    void delete(ShortAnswer shortAnswer);

    //Delete all query
    @Delete
    void reset(ArrayList<ShortAnswer> shortAnswerList);

    //Get all data query
    @Query("SELECT * FROM short_answer_question")
    List<ShortAnswer> getAll();

    //Get question by region
    @Query("SELECT * FROM short_answer_question WHERE region = :sRegion")
    List<ShortAnswer> getQuestionByRegion(String sRegion);
}
