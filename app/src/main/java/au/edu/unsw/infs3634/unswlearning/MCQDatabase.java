package au.edu.unsw.infs3634.unswlearning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MCQDatabase {

    private static final String MCQ_DB_PATH = "jdbc:sqlite:mcq.db";

    private static Connection conn;

    public MCQDatabase() throws SQLException {

        // Connect to Database
        openConnection();
        Statement st = conn.createStatement();

        // Create COUNTRY table
        String createMCQTable = "DROP TABLE IF EXISTS MCQ"
                + "CREATE TABLE MCQ_TABLE ("
                + "id INT PRIMARY KEY"
                + ", region TEXT NOT NULL"
                + ", level TEXT NOT NULL"
                + ", textQuestion TEXT NOT NULL"
                + ", choiceA TEXT NOT NULL"
                + ", choiceB TEXT NOT NULL)"
                + ", choiceC TEXT NOT NULL"
                + ", choiceD TEXT NOT NULL"
                + ", answer TEXT NOT NULL";
        st.execute(createMCQTable);

        //Insert data
        insertMCQ();

        // Close connections and statements
        closeConnection();
    }


    private void insertMCQ() throws SQLException {
        openConnection();
        Statement st = conn.createStatement();

        ArrayList<String> insertStatements = new ArrayList<>();

        //Add insertStatements here


        for (String thisStatement : insertStatements) {
            st.execute(thisStatement);
        }

        st.close();
        conn.close();
    }


    private static void openConnection() throws SQLException {
        conn = DriverManager.getConnection(MCQ_DB_PATH);
    }

    private static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static ArrayList<MultipleChoiceQuestion> getAllQuestions() throws SQLException {
            openConnection();

            Statement st = conn.createStatement();

            String query = "SELECT * FROM MCQ_TABLE";

            //Execute the query and get ResultSet of all orders that exist in the database
            ResultSet rs = st.executeQuery(query);
            ArrayList<MultipleChoiceQuestion> MCQList = new ArrayList<>();

            //Add each row in the ResultSet to the countryList
            while (rs.next()) {
                MCQList.add(new MultipleChoiceQuestion(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9)));
            }
            st.close();
            closeConnection();
            return MCQList;
        }
    }
