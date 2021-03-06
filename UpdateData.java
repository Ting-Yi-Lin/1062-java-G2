package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData {
	
	public UpdateData(){
		
	}
	
	

	public boolean update(String name,int score) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb?" + "user=root&password=kyle880821&serverTimezone=UTC&useSSL=false");

			
			
			
			PreparedStatement stmt = conn.prepareStatement("UPDATE user_table SET  user_score= ? WHERE  user_name= ?;");
			stmt.setString(1, name);
			stmt.setInt(2, score);
			stmt.executeUpdate();
			
			

			ResultSet rs = stmt.executeQuery("select  user_name, user_score"
					+ " from user_table s");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2)  );
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;

	}
}