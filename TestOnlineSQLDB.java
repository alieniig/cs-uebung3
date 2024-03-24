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

            //Kunden einfügen - INSERT INTO
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('Max', 'Mustermann', '1980-01-01')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('Erika', 'Musterfrau', '1985-02-02')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('John', 'Doe', '1990-03-03')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('Jane', 'Doe', '1995-04-04')");

            // Ausgabe Fahrzeugtabelle - SELECT
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM Fahrzeuge");
            System.out.println("Ausgabe Fahrzeuge");
            while (rs1.next()) {
                System.out.println(
                        rs1.getString("Hersteller") + " " + rs1.getString("Modell") + " "
                                + rs1.getInt("Preis pro Tag"));
            }
            // Ausgabe Fahrzeugtabelle - SELECT
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Kunden");
            System.out.println("Ausgabe Kunden");
            while (rs2.next()) {
                System.out.println(
                        rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getDate(3));
            }
            //Kunden löschen - DELETE
            stmt.executeUpdate("DELETE FROM Kunden WHERE Vorname = 'Max'");

            //Fahrzeug ändern - UPDATE
            stmt.executeUpdate("UPDATE Fahrzeuge SET `Preis pro Tag` = 100 WHERE Hersteller = 'BMW' AND Modell = 'X5'");

            // Ausgabe Fahrzeugtabelle - SELECT
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM Fahrzeuge");
            System.out.println("Ausgabe Fahrzeuge");
            while (rs3.next()) {
                System.out.println(
                        rs3.getString("Hersteller") + " " + rs3.getString("Modell") + " "
                                + rs3.getInt("Preis pro Tag"));
            }
            // Ausgabe Fahrzeugtabelle - SELECT
            ResultSet rs4 = stmt.executeQuery("SELECT * FROM Kunden");
            System.out.println("Ausgabe Kunden");
            while (rs4.next()) {
                System.out.println(
                        rs4.getString("Vorname") + " " + rs4.getString("Nachname") + " " + rs4.getDate("Geburtsdatum"));
            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
