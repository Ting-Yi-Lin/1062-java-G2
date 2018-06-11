package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

public class InsertSelections {

	public InsertSelections() {

	}

	static JLabel L[] = new JLabel[10];

	public boolean select(String name, int score) {

		boolean action = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb?" + "user=root&password=kyle880821&serverTimezone=UTC&useSSL=false");
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("select  user_name, user_score" + " from user_table " + "order by user_score DESC");

			int i = 0;
			String[] str = new String[100];
			while (rs.next()) {
				if (rs.getString(1) == name && rs.getInt(2) < score) {
					action = true;
				}
				i++;
			}

			for (int gg = 0; gg <= i; gg++) {
				System.out.println(str[i]);
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return action;

	}

	public boolean select(String name) {

		boolean action = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb?" + "user=root&password=kyle880821&serverTimezone=UTC&useSSL=false");
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("select  user_name, user_score" + " from user_table " + "order by user_score DESC");

			int i = 0;
			String[] str = new String[100];
			while (rs.next()) {
				if (rs.getString(1) == name) {
					action = true;
				}
				i++;
			}

			for (int gg = 0; gg <= i; gg++) {
				System.out.println(str[i]);
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return action;

	}
}