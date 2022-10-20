package au.edu.unsw.infs3634.unswlearning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryDatabase {

        // Database string
    private static final String COUNTRY_DB_PATH = "jdbc:sqlite:country.db";

    private Connection conn = null;

    public CountryDatabase() throws SQLException {

        // Connect to Database
        openConnection();
        Statement st = conn.createStatement();

        // Create COUNTRY table
        String createCountryTable = "DROP TABLE IF EXISTS COUNTRY"
                + "CREATE TABLE COUNTRY ("
                + "name TEXT PRIMARY KEY"
                + ", region TEXT NOT NULL"
                + ", capital TEXT NOT NULL"
                + ", area FLOAT NOT NULL"
                + ", population INT NOT NULL)";
        st.execute(createCountryTable);

        //Insert data
        insertCountry();

        // Close connections and statements
        closeConnection();
        }



    private void insertCountry() throws SQLException {
        openConnection();
        Statement st = conn.createStatement();

        ArrayList<String> insertStatements = new ArrayList<>();

        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Denmark', 'Copenhagen', 43094, 5920527);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Germany', 'Berlin', 357022, 83695430);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','UK', 'London', 242495, 67326569);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Poland', 'Warsaw', 312696, 38179800);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Sweden', 'Stockholm', 450295, 10481937);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','France', 'Paris', 643801, 67897000);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Luxembourg', 'Luxembourg', 2586.4, 645397);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Greece', 'Athens', 131957, 10432481);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Romania', 'Bucharest', 238397, 19038098);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Italy', 'Rome', 301230, 58983000);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Europe','Netherlands', 'Amsterdam', 41850, 17752300);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Vietnam', 'Hanoi', 331699, 103808319);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Japan', 'Tokyo', 377975, 124214766);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','India', 'New Delhi', 3287263, 1375586000);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Singapore', 'Singapore', 733.1, 5637000);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','South Korea', 'Seoul', 100363, 51844834);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Iran', 'Tehran', 1648195, 86758304);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Myanmar', 'Nay Pyi Daw', 676570, 57526449);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Pakistan', 'Islamabad', 881913, 242923845);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Uzbekistan', 'Tashkent', 448978, 35819689);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Asia','Philippines', 'Manila', 300000, 110000000);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Brazil', 'Brasilia', 8515767, 217240060);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Paraguay', 'Asunción', 406752, 7356409);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Uruguay', 'Montevideo', 181034, 3407213);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Colombia', 'Bogotá', 1414748, 49059221);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Suriname', 'Paramaribo', 163820, 632638);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Venezuela', 'Caracas', 916445, 29789730);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Chile', 'Santiago', 756950, 18430408);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Argentina', 'Buenos Aires', 75417, 2780400);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Ecuador', 'Quito', 13943, 18048628);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('South America','Peru', 'Lima', 1285220, 32275736);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','South Africa', 'Cape Town, Pretoria, Bloemfontein', 1221037, 60142978);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Botswana', 'Gaborone', 581730, 2346179);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Zimbabwe', 'Harare', 181034, 3407213);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Tanzania', 'Dodoma', 947303, 63852892);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Rwanda', 'Kigali', 26338, 10515973);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Ivory Coast', 'Yamoussoukro', 322463, 29389150);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Egypt', 'Cairo', 1010408, 107770524);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Mauritius', 'Buenos Aires', 2040, 1265475);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Angola', 'Luanda', 1246700, 34795287);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Africa','Nigeria', 'Lagos', 923769, 218541212);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Papua New Guinea', 'Port Moresby', 18270, 9949437);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Fiji', 'Suva', 581730, 924610);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Vanuatu', 'Port Vila', 12200, 42050);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Marshall Islands', 'Majuro', 181, 63852892);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Tonga', 'Nuku’Alofa', 748, 106017);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Tuvalu', 'Funafuti', 26, 11204);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Solomon Islands', 'Honiara', 28450, 707851);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Nauru', 'Yaren', 21, 12511);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Palau', 'Ngerulmud', 458, 18024);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('Oceania','Samoa', 'Apia', 2944, 218764);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','United States', 'Washington, D.C', 9629091, 336997624);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Canada', 'Ottawa', 9984670, 38155012);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Cuba', 'Havana', 109886, 11256372);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Honduras', 'Tegucigalpa', 112492, 10278345);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Mexico', 'Mexico City', 1964375, 126705138);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Costa Rica', 'San José', 51100, 5153957);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Jamaica', 'Kingston', 10991, 2827695);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Panama', 'Panama City', 75417, 4351267);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','The Bahamas', 'Nassau', 13943, 407906);");
        insertStatements.add("INSERT INTO COUNTRY(Region,Name,Capital,Area,Population) " +
                    "VALUES ('North America','Puerto Rico', 'San Juan', 8870, 3256028);");

        for (String thisStatement: insertStatements) {
            st.execute(thisStatement);
        }

        st.close();
        conn.close();
    }

    private void openConnection() throws SQLException {
        conn = DriverManager.getConnection(COUNTRY_DB_PATH);
    }

    private void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    //Return list of countries
    public ArrayList<Country> getCountry() throws SQLException {
        openConnection();
        Statement st = conn.createStatement();

        String query = "SELECT name, region, capital, area, population FROM COUNTRY";

        //Execute the query and get ResultSet of all orders that exist in the database
        ResultSet rs = st.executeQuery(query);
        ArrayList<Country> countryList = new ArrayList<>();

        //Add each row in the ResultSet to the countryList
        while (rs.next()) {
            countryList.add(new Country(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getFloat(4), rs.getInt(5)));
        }
        st.close();
        closeConnection();
        return countryList;
    }
}

