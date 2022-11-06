package au.edu.unsw.infs3634.unswlearning;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Define table name
@Entity
public class MCQ {
    @PrimaryKey
    public int id;

    //Create region column
    @ColumnInfo(name = "region")
    public String region;

    //Create choiceA column
    @ColumnInfo(name = "choiceA")
    public String choiceA;

    //Create choiceB column
    @ColumnInfo(name = "choiceB")
    public String choiceB;

    //Create choiceC column
    @ColumnInfo(name = "choiceC")
    public String choiceC;

    //Create choiceD column
    @ColumnInfo(name = "choiceD")
    public String choiceD;

    //Create answer
    @ColumnInfo(name = "answer")
    public String answer;

    MCQ(int id, String region, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        this.id = id;
        this.region = region;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.answer = answer;
    }

    //Generate getter and setter
    //Setter methods
    public void setID() {
        this.id = id;
    }

    public void setRegion() {
        this.region = region;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //Getter methods
    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public int getID() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getAnswer() {
        return answer;
    }

}
