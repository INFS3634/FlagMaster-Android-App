package au.edu.unsw.infs3634.unswlearning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShortAnswerDatabase {

    private static final String SHORT_ANSWER_DB_PATH = "jdbc:sqlite:shortanswer.db";

    private Connection conn = null;

    public ShortAnswerDatabase() throws SQLException {
        // Connect to Database
        openConnection();
        Statement st = conn.createStatement();

        // Create SHORT_ANSWER_QUIZ table
        String createShortAnswerQuizTable = "DROP TABLE IF EXISTS SHORT_ANSWER_QUIZ"
                + "CREATE TABLE SHORT_ANSWER_QUIZ ("
                + "region TEXT PRIMARY KEY"
                + ", country TEXT NOT NULL"
                + ", question TEXT NOT NULL"
                + ", answer TEXT NOT NULL"
                + ", image VARBINARY NOT NULL)";
        st.execute(createShortAnswerQuizTable);

        //Insert data
        insertQuestionTextAndAnswer();
        insertQuestionImage();

        // Close connections and statements
        closeConnection();
    }

    public void insertQuestionTextAndAnswer() throws SQLException {
        openConnection();
        Statement st = conn.createStatement();
        ArrayList<String> insertStatements = new ArrayList<>();

        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Asia','Iran','What country is this?','Iran');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Asia','Japan','What is the capital of this country?','Tokyo');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Asia','India','What country is this?','India');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Asia','Vietnam','What is the capital of this country?','Hanoi');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Asia','Pakistan','What country is this?','Pakistan');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Europe','France','What country is this?','France');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Europe','Netherlands','What is the capital of this country?'" +
                ",'Amsterdam');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Europe','Denmark','What country is this?','Denmark');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Europe','Greece','What is the capital of this country?','Athens');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Europe','Luxembourg','What country is this?','Luxembourg');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('North America','Mexico','What country is this?','Mexico');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('North America','Canada','What is the capital of this country?'" +
                ",'Ottawa');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('North America','Cuba','What country is this?','Cuba');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('North America','Jamaica','What is the capital of this country?'" +
                ",'Kingston');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('North America','The Bahamas','What country is this?','The Bahamas');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('South America','Colombia','What country is this?','Colombia');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('South America','Brazil','What is the capital of this country?'" +
                ",'Brasilia');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('South America','Venezuela','What country is this?','Venezuela');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('South America','Ecuador','What is the capital of this country?'" +
                ",'Quito');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('South America','Argentina','What country is this?','Argentina');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Ocenia','Fiji','What country is this?','Fiji');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Ocenia','Fiji','What is the capital of this country?','Suva');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Ocenia','Solomon Islands','What country is this?','Solomon Islands');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Ocenia','Marshall Islands','What country is this?','Marshall Islands');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Ocenia','Vanuatu','What is the capital of this country?','Port Vila');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Africa','South Africa','What country is this?','South Africa');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Africa','Nigeria','What is the capital of this country?','Lagos');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Africa','Zimbabwe','What country is this?','Zimbabwe');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Africa','Egypt','What is the capital of this country?','Cairo');");
        insertStatements.add("INSERT INTO HARD_QUIZ(Region,Country,Question,Answer) " +
                "VALUES ('Africa','Botswana','What country is this?','Botswana');");

        for (String thisStatement: insertStatements) {
            st.execute(thisStatement);
        }

        st.close();
        conn.close();
    }

    public void insertQuestionImage() throws SQLException {

    }

    public void openConnection() throws SQLException {
        conn = DriverManager.getConnection(SHORT_ANSWER_DB_PATH);
    }

    private void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    
    public ArrayList<ShortAnswer> getShortAnswers() throws SQLException {
        openConnection();
        Statement st = conn.createStatement();
        String query = "SELECT question, answer, image FROM SHORT_ANSWER_QUIZ";

        //Execute the query and get ResultSet of all orders that exist in the database
        ResultSet rs = st.executeQuery(query);
        ArrayList<ShortAnswer> shortAnswerQuizList = new ArrayList<>();

        //Add each row in the ResultSet to the list
//        while (rs.next()) {
//            shortAnswerQuizList.add(new ShortAnswer(rs.getString(1), rs.getString(2),
//                    rs.getImageView(3)));
//        }
        
        st.close();
        closeConnection();
        return shortAnswerQuizList;
    }

}
