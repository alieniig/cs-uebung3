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
            connection = DriverManager.getConnection(url, user, password); // Instanz des JDBC-Treibers
            Statement stmt = connection.createStatement();

            // Kunden einfügen - INSERT INTO
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('Max', 'Mustermann', '1980-01-01')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('Erika', 'Musterfrau', '1985-02-02')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('John', 'Doe', '1990-03-03')");
            stmt.executeUpdate("INSERT INTO Kunden VALUES ('Jane', 'Doe', '1995-04-04')");

            // Ausgabe Fahrzeugtabelle - SELECT
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM Fahrzeuge");
            System.out.println("Ausgabe Fahrzeuge");
            while (rs3.next()) {
                System.out.println(
                        rs3.getString("Hersteller") + " " + rs3.getString("Modell") + " "
                                + rs3.getInt("Preis pro Tag"));
                System.out.println("Row number: " + rs3.getRow());
                System.out.println("Column count: " + rs3.getMetaData().getColumnCount());
                System.out.println("Column name of index 1: " + rs3.getMetaData().getColumnName(1));
                System.out.println("Column type of index 1: " + rs3.getMetaData().getColumnType(1));
                System.out.println("Is the cursor on the last row? " + rs3.isLast());
                System.out.println("Is the cursor on the first row? " + rs3.isFirst());
                System.out.println("Is the cursor on a valid row? " + rs3.isBeforeFirst());
                System.out.println("Is the cursor on a valid row? " + rs3.isAfterLast());

            }
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
