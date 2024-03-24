import java.sql.*;

public class TestOnlineSQLDB {
    public static void main(String[] args) {
        // JDBC-Verbindungsparameter

        // JDBC-Verbindungsobjekt
        Connection connection = null;

        // Verbindung herstellen
        try {
            String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693896";
            String user = "sql11693896";
            String password = "bdIfjnDgeA";
            connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
           

            // Fahrzeug 2
            stmt.executeUpdate("INSERT INTO Fahrzeuge VALUES ('BMW', 'X7', 220)");


            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge");
            while (rs.next()) {
                System.out.println(
                        rs.getString("Hersteller") + " " + rs.getString("Modell") + " " + rs.getInt("Preis pro Tag"));
            }

            connection.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
