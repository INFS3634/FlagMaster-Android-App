package au.edu.unsw.infs3634.unswlearning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('1','South America', 'What country is this?', Brazil, Tanzania,'Nigeria','Rwanda','Brazil');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('2','South America', 'What country is this?', Paraguay, Suriname,'Peru','Chile','Chile');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('3','South America', 'What country is this?', Argentina, Uruguay,'Escuador','Paraguay','Uruguay');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('4','South America', 'What country is this?', Venezuela, Escuador,'Paraguay','Columbia','Columbia');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('5','South America', 'What country is this?', Peru, Brazil,'Vanezuela','Argentina','Suriname');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('6','Africa', 'What country is this?', South Africa, Zimbabwe,'Angola','Tanzania','South Africa');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('7','Africa', 'What country is this?', Mauritius, Botswana,'Tanzania','Rwanda','Botswana');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('8','Africa', 'What country is this?', Nigeria, South Africa,'Zimbabwe','Ivory Coast','Nigeria');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('9','Africa', 'What country is this?', Botswana, Ivory Coast,'Angola','Egypt','Egypt');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('10','Africa', 'What country is this?', Ivory Coast, Mauritius,'Rwanada','Tanzania','Mauritius');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('11','Oceania', 'What country is this?', Palau, Fiji,'Tuvalu','Solomon Islands','Tuvalu');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('12','Oceania', 'What country is this?', Nauru, Vanatu,'Tonga','Marshall Islands','Nauru');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('13','Oceania', 'What country is this?', Papua New Guinea, Tuvalu,'Samoa','Fiji','Papua New Guinea');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('14','Oceania', 'What country is this?', Tonga, Solomon Islands,'Palau','Palau','Solomon Islands');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('15','Oceania', 'What country is this?', Vanatu, Samoa,'Marshall Islands','Tonga','Samoa');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('16','Europe', 'What country is this?', Poland, Luxembourg,'Denmark','Netherlands','Denmark');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('17','Europe', 'What country is this?', Netherlands, France,'Italy','Romania','France');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('18','Europe', 'What country is this?', Sweden, Germany,'Luxembourg','Poland','Sweden');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('19','Europe', 'What country is this?', UK, Greece,'Romania','Italy','UK');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('20','Europe', 'What country is this?', Greece, Poland,'Netherlands','Denmark','Netherlands');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('21','Asia', 'What country is this?', Iran, Pakistan,'Myanmar','Vietnam','Vietnam');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('22','Asia', 'What country is this?', Philippines, Japan,'South Korea','Uzbekistan','Japan');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('23','Asia', 'What country is this?', Vietnam, India,'Singapore','Iran','Iran');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('24','Asia', 'What country is this?', Japan, Myanmar,'Pakistan','Phillippines','Pakistan');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('25','Asia', 'What country is this?', South Korea, Japan,'Iran','Uzbekistan','South Korea');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('26','North America', 'What country is this?', Puerto Ricco, Panama,'United States','Canada','United States');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('27','North America', 'What country is this?', Costa Rica, Cuba,'Panama','Jamaica','Cuba');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('28','North America', 'What country is this?', Jamaica, Mexico,'Puerto Rico','The Bahamas','The Bahamas');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('29','North America', 'What country is this?', Panama, Honduras,'Canada','Cuba','Honduras');");
        insertStatements.add("INSERT INTO MCQ_TABLE(id,Region,textQuestion,ChoiceA,ChoiceB,ChoiceC,ChoiceD,Answer) VALUES ('30','North America', 'What country is this?', The Bahamas, Puerto Ricco,'Mexico','Costa Rica','Costa Rica');");


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
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8)));
            }
            st.close();
            closeConnection();
            return MCQList;
        }
    public static ArrayList<MultipleChoiceQuestion> getQuestionsByRegion(String regionChosen) throws SQLException {
        openConnection();

        Statement st = conn.createStatement();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM MCQ_TABLE WHERE Region = ?");
        ps.setString(1, regionChosen);

        //Execute the query and get ResultSet of all orders that exist in the database
        ResultSet rs = ps.executeQuery();
        ArrayList<MultipleChoiceQuestion> questionByRegion = new ArrayList<>();

        //Add each row in the ResultSet to the countryList
        while (rs.next()) {
            questionByRegion.add((MultipleChoiceQuestion) new au.edu.unsw.infs3634.unswlearning.MultipleChoiceQuestion(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8)));
        }
        st.close();
        closeConnection();
        return questionByRegion;
    }
    }
