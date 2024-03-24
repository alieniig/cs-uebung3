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
            //stmt.executeUpdate("INSERT INTO Kunden VALUES ('André', 'Lienig', '2004-07-07')");
            //Ausgabe Fahrzeugtabelle
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM Fahrzeuge");
            System.out.println("Ausgabe Fahrzeuge");
            while (rs1.next()) {
                System.out.println(
                        rs1.getString("Hersteller") + " " + rs1.getString("Modell") + " " + rs1.getInt("Preis pro Tag"));
            }
            //Ausgabe Fahrzeugtabelle
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Kunden");
            System.out.println("Ausgabe Kunden");
            while (rs2.next()) {
                System.out.println(
                        rs2.getString("Vorname") + " " + rs2.getString("Nachname") + " " + rs2.getDate("Geburtsdatum"));
            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
