import java.sql.*;

public class Aufgabe3 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693896";
        String user = "sql11693896";
        String password = "bdIfjnDgeA";
        // JDBC-Verbindungsparameter

        // JDBC-Verbindungsobjekt
        Connection connection = null;

        // Verbindung herstellen
        try {

            connection = DriverManager.getConnection(url, user, password); // Instanz des JDBC-Treibers
            
            // Vorkompilierte Abfrageanweisung erstellen
            String selectSql = "SELECT * FROM Fahrzeuge WHERE Hersteller = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectSql);
            selectStmt.setString(1, "BMW"); // Parameter setzen
            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getString("Hersteller") + " " + rs.getString("Modell") + " " + rs.getInt("Preis pro Tag"));
            }

            // Vorkompilierte Änderungsanweisung erstellen
            String updateSql = "UPDATE Fahrzeuge SET Preis pro Tag = ? WHERE Hersteller = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setInt(1, 120); // Preis pro Tag setzen
            updateStmt.setString(2, "Audi"); // Hersteller setzen
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println(rowsUpdated + " Zeilen aktualisiert.");

            countRecords();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // Methode aus der Vorlesung angepasst
    public static void countRecords() throws SQLException {
        String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693896";
        String user = "sql11693896";
        String password = "bdIfjnDgeA";
        Connection conn = DriverManager.getConnection(url, user, password); // Instanz des JDBC-Treibers
        String[] aTables = { "Fahrzeuge", "Kunden" };
        for (int i = 0; i < aTables.length; i++) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) FROM " + aTables[i]);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("SQL: no result");
            }
            System.out.println(aTables[i] + ": " + rs.getInt(1));
            pstmt.close();
        }
    }
}
