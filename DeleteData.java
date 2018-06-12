package mysql;

import java.sql.Connection;


import java.sql.DriverManager;


import java.sql.PreparedStatement;


import java.sql.ResultSet;


import java.sql.SQLException;


import java.sql.Statement;

public class DeleteData {
	
	private String name;
	
	public DeleteData(String name,int score){
		this.name = name;
	}

	public void Delete() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			
			
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb?" + "user=root&password=kyle880821&serverTimezone=UTC&useSSL=false");

			
			
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_table WHERE user_name = ?;");
			
			
			stmt.setString(1, name);
			
			stmt.executeUpdate();

			ResultSet rs = stmt.executeQuery("select s.user_id, user_name, user_score"
					+ " from user_table s");

			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) );
			
			}

		} catch (SQLException ex) {
			
			
			// handle any errors
			
			System.out.println("SQLException: " + ex.getMessage());
			
			System.out.println("SQLState: " + ex.getSQLState());
			
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
}
