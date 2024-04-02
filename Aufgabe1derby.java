import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aufgabe1derby {
    public static void main(String[] args) {

        String url = "jdbc:derby://localhost:1527/database-test;create=true";
        // Verbindung zur Datenbank erstellen
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            //SQL-Statement zum Erstellen der Tabelle
            //stmt.executeUpdate("CREATE TABLE Fahrzeuge (ID INT PRIMARY KEY, Hersteller VARCHAR(255), Modell VARCHAR(255))");        
            
            stmt.executeUpdate("Delete FROM Fahrzeuge WHERE ID = 6");

            //SQL-Statement zum Einfügen von Datensätzen
            stmt.executeUpdate("INSERT INTO Fahrzeuge  VALUES (6, 'Porsche', '911')");
            stmt.executeUpdate("INSERT INTO Fahrzeuge  VALUES (7, 'Audi', 'Q7')");

            //Rückgabewerte ausgeben
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " " + rs.getString("Hersteller") + " " + rs.getString("Modell"));
            }

            while (rs.next()) {
                int ID = rs.getInt(1);
                String Hersteller = rs.getString(2);
                String Modell = rs.getString(3);
                System.out.println(ID + " " + Hersteller + " " + Modell);

            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen der Fahrzeuge: " + e.getMessage());
        }

    }

}