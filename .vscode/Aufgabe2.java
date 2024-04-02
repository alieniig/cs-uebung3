
import java.sql.*;

public class Aufgabe2 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693896";
        String user = "sql11693896";
        String password = "bdIfjnDgeA";

        try {

            Connection connection = DriverManager.getConnection(url, user, password);
            // prepared Statment
            updatePreisProTag(connection, 200, "Audi");

            // Vergleich prep statement und ohne prep statement
            System.out.println("INSERT INTO -------------------");
            

            // verbindung schließen
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    

    public static void updatePreisProTag(Connection connection, int preisProTag, String hersteller)
            throws SQLException {
        String updateSql = "UPDATE Fahrzeuge SET `Preis pro Tag` = ? WHERE Hersteller = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setInt(1, preisProTag); // Preis pro Tag setzen
        updateStmt.setString(2, hersteller); // Hersteller setzen
        int rowsUpdated = updateStmt.executeUpdate();
        System.out.println(rowsUpdated + " Zeilen aktualisiert.");
    }

    
}