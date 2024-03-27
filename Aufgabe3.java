import java.sql.*;

public class Aufgabe3 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693896";
        String user = "sql11693896";
        String password = "bdIfjnDgeA";
        
        try {

            Connection connection = DriverManager.getConnection(url, user, password);
            //prepared Statment
            updatePreisProTag(connection, 200, "Audi");

            //Vergleich prep statement und ohne prep statement
            System.out.println("INSERT INTO -------------------");
            insertWithoutPreparedStatements(connection, "X1", "BMW", 100);
            insertWithPreparedStatements(connection, "X2", "BMW", 100);
            System.out.println("SELECT -------------------");
            selectWithPreparedStatements(connection, "BMW");
            selectWithoutPreparedStatements(connection, "BMW");
            System.err.println("DELETE -------------------");
            deleteWithoutPreparedStatements(connection, "BMW", "X1");
            deleteWithPreparedStatements(connection, "BMW", "X2");

            //methode aus der vorlesung
            countRecords();
            
            //verbindung schließen
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
    public static void updatePreisProTag(Connection connection, int preisProTag, String hersteller) throws SQLException {
        String updateSql = "UPDATE Fahrzeuge SET `Preis pro Tag` = ? WHERE Hersteller = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setInt(1, preisProTag); // Preis pro Tag setzen
        updateStmt.setString(2, hersteller); // Hersteller setzen
        int rowsUpdated = updateStmt.executeUpdate();
        System.out.println(rowsUpdated + " Zeilen aktualisiert.");
    }

    public static void selectWithoutPreparedStatements(Connection connection, String hersteller) throws SQLException {
        long startTime = System.currentTimeMillis();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fahrzeuge WHERE Hersteller = '" + hersteller + "'");
        while (rs.next()) {
            System.out.println(
                    rs.getString("Modell") + " " + rs.getString("Hersteller") + " " + rs.getInt("Preis pro Tag"));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Zeit ohne Prepared Statements: " + (endTime - startTime) + "ms");
    }

    public static void selectWithPreparedStatements(Connection connection, String hersteller) throws SQLException {
        long startTime = System.currentTimeMillis();
        String selectSql = "SELECT * FROM Fahrzeuge WHERE Hersteller = ?";
        PreparedStatement selectStmt = connection.prepareStatement(selectSql);
        selectStmt.setString(1, hersteller); // Parameter setzen
        ResultSet rs = selectStmt.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString("Modell") + " " + rs.getString("Hersteller") + " " + rs.getInt("Preis pro Tag"));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Zeit mit Prepared Statements: " + (endTime - startTime) + "ms");
    }

    public static void insertWithoutPreparedStatements(Connection connection, String modell, String hersteller,
            int preisProTag) throws SQLException {
        long startTime = System.currentTimeMillis();
        Statement stmt = connection.createStatement();
        int rowsAffected = stmt.executeUpdate(
                "INSERT INTO Fahrzeuge (Modell, Hersteller, `Preis pro Tag`) VALUES ('" + modell + "', '" + hersteller
                        + "', " + preisProTag + ")");
        System.out.println("Eingefügte Zeilen: " + rowsAffected);
        long endTime = System.currentTimeMillis();
        System.out.println("Zeit ohne Prepared Statements: " + (endTime - startTime) + "ms");
    }

    public static void insertWithPreparedStatements(Connection connection, String modell, String hersteller,
            int preisProTag) throws SQLException {
        long startTime = System.currentTimeMillis();
        String insertSql = "INSERT INTO Fahrzeuge (Modell, Hersteller, `Preis pro Tag`) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertSql);
        insertStmt.setString(1, modell); // Parameter setzen
        insertStmt.setString(2, hersteller);
        insertStmt.setInt(3, preisProTag);
        int rowsAffected = insertStmt.executeUpdate();
        System.out.println("Eingefügte Zeilen: " + rowsAffected);
        long endTime = System.currentTimeMillis();
        System.out.println("Zeit mit Prepared Statements: " + (endTime - startTime) + "ms");
    }

    public static void deleteWithoutPreparedStatements(Connection connection, String hersteller, String modell) throws SQLException {
        long startTime = System.currentTimeMillis();
        Statement stmt = connection.createStatement();
        int rowsAffected = stmt.executeUpdate("DELETE FROM Fahrzeuge WHERE Hersteller = '" + hersteller + "' AND Modell = '" + modell + "'");
        System.out.println("Gelöschte Zeilen: " + rowsAffected);
        long endTime = System.currentTimeMillis();
        System.out.println("Zeit ohne Prepared Statements: " + (endTime - startTime) + "ms");
    }
    
    public static void deleteWithPreparedStatements(Connection connection, String hersteller, String modell) throws SQLException {
        long startTime = System.currentTimeMillis();
        String deleteSql = "DELETE FROM Fahrzeuge WHERE Hersteller = ? AND Modell = ?";
        PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
        deleteStmt.setString(1, hersteller); // Parameter setzen
        deleteStmt.setString(2, modell);
        int rowsAffected = deleteStmt.executeUpdate();
        System.out.println("Gelöschte Zeilen: " + rowsAffected);
        long endTime = System.currentTimeMillis();
        System.out.println("Zeit mit Prepared Statements: " + (endTime - startTime) + "ms");
    }
}