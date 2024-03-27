// Einfaches Beispiel mit Ausgabe in einem Editor
// Schalter und ComboBox vereinfachen die Aktionen

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.*;

import java.sql.*;

// schalter
import java.awt.event.*;

public class JDBCTestClientGUI extends JFrame {

	JToolBar toolbar = new JToolBar();
	JTextArea editor = new JTextArea("");
	JButton BnSQL;
	JButton BnESC;

	JLabel lFormel;
	JComboBox TSql;

	/** Creates a new instance of Main */
	public JDBCTestClientGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400);
		setGUI();
	}

	private void setGUI() {
		this.getContentPane().setLayout(new BorderLayout());

		JScrollPane sc = new JScrollPane(editor);
		this.getContentPane().add(sc, BorderLayout.CENTER);

		editor.setFont(new Font("Courier New", Font.BOLD, 18));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());

		BnSQL = new JButton("Starte SQL");
		BnESC = new JButton("Ende");
		BnSQL.setFont(new Font("Arial", Font.BOLD, 18));

		BnSQL.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BnSQL_Click();
			}
		});
		BnESC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BnESC_Click();
			}
		});

		lFormel = new JLabel("SQL-Befehl");
		lFormel.setFont(new Font("Arial", Font.BOLD, 18));
		panel1.add(lFormel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 20, 0, 0), 0, 6));

		String sTable = "employee";
		// ComboBox mit Eingabe
		String[] items = new String[4];
		items[0] = "SELECT EMP_NO, Full_name, DEPT_NO, SALARY FROM " + sTable;
		items[1] = "SELECT *  FROM " + sTable;
		items[2] = "SELECT *  FROM " + sTable + "  WHERE SALARY > 70000.0";
		items[3] = "SELECT EMP_NO, Last_name, First_name, DEPT_NO, SALARY FROM "
				+ sTable + "  WHERE SALARY > 70000.0";

		TSql = new JComboBox(items);
		TSql.setFont(new Font("Arial", Font.PLAIN, 18));
		panel1.add(TSql, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(20, 40, 0, 20), 00, 0));
		TSql.setEditable(true);

		panel1.add(BnSQL, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						20, 20, 0, 0), 0, 6));

		this.getContentPane().add(panel1, BorderLayout.NORTH);

	} // setGUI

	private void BnESC_Click() {
		System.exit(0);
	}

	public void BnSQL_Click() { // throws Exception {
		String sDbDrv;
		String sDbUrl;

		String sTable = "employee";
		String sUsr = "sysdba";
		String sPwd = "masterkey";

		sDbDrv = "org.firebirdsql.jdbc.FBDriver";
		sDbUrl = "jdbc:firebirdsql:localhost/3050:C:/Programme/Firebird/Firebird_1_5/examples/employee.fdb";

		// Verbindung zur Datenbank, Auslese Variablen
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		editor.setText("");
		try {

			// Treiber INIT, Verbinden und SQL-Statement ausl�sen
			// Returns the Class object associated with the class or interface
			// with the given string name.
			// class c = Class.forName( sDbDrv );
			Class.forName(sDbDrv);

			cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);
			st = cn.createStatement();

			String sql = (String) TSql.getSelectedItem();
			rs = st.executeQuery(sql);

			// Get meta data:
			ResultSetMetaData rsmd = rs.getMetaData();
			int i, AnzCols;
			AnzCols = rsmd.getColumnCount();
			// Ausgabe der Tabelle:
			// Oberste Zeile
			String s = "";
			for (i = 0; i < AnzCols; i++) {
				s = s + "+-----------------";
			}
			s += "+\n";
			editor.append(s);

			s = "";
			for (i = 1; i <= AnzCols; i++) { // Attention: first column with 1
												// instead of 0
				s = s + "| " + extendStringToN(16, rsmd.getColumnName(i));
			}
			s = s + "|\n";
			editor.append(s);

			s = "";
			for (i = 0; i < AnzCols; i++) {
				s = s + "+-----------------";
			}
			s = s + "+\n";
			editor.append(s);

			// Schleife �ber alle Tupel
			while (rs.next()) {
				s = "";
				for (i = 1; i <= AnzCols; i++) { // Attention: first column with
													// 1 instead of 0
					s = s + "| " + extendStringToN(16, rs.getString(i));
				}
				s = s + "|\n"; // Tupel-Ende
				editor.append(s);
			}

			// Unterste Zeile
			s = "";
			for (i = 0; i < AnzCols; i++) {
				s = s + "+-----------------";
			}
			s = s + "+\n";
			editor.append(s);

		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (null != rs)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (null != st)
					st.close();
			} catch (Exception ex) {
			}
			try {
				if (null != cn)
					cn.close();
			} catch (Exception ex) {
			}
		}
	} // sql

	// Extend String to length of 14 characters
	private static final String extendStringToN(int iWantLen, String s) {
		if (null == s)
			s = "";
		while (s.length() < iWantLen) {
			s += " ";
		}
		if (s.length() > iWantLen)
			return s.substring(0, iWantLen);
		else
			return s;
	}

	public static void main(String[] args) {
		JDBCTestClientGUI frame = new JDBCTestClientGUI();
		frame.setVisible(true);
	}

}