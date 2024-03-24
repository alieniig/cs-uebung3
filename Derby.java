import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Derby {
    public static void main(String[] args) {

        String url = "jdbc:derby://localhost:1527/database-test;create=true";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            //stmt.executeUpdate("Delete FROM Fahrzeuge WHERE ID = 6");

            stmt.executeUpdate("INSERT INTO Fahrzeuge  VALUES (6, 'Porsche', '911')");

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