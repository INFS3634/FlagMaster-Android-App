package au.edu.unsw.infs3634.unswlearning;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "short_answer_question")
public class ShortAnswer {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public String region;

    @ColumnInfo
    public String country;

    @ColumnInfo
    public String textQuestion;

    @ColumnInfo
    public String answer;

//Constructor
    ShortAnswer(int id, String region, String country, String textQuestion, String answer) {
        this.id = id;
        this.region = region;
        this.country = country;
        this.textQuestion = textQuestion;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
