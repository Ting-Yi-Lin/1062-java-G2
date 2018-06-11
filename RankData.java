package mysql;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RankData {
	
	static JLabel L[] = new JLabel[5];
	public void read() {
		
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

			ResultSet rs = stmt.executeQuery("select  user_name, user_score" + " from user_table s");
			
			
			
		
			int i=0;
			//JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			JFrame f = new JFrame("JLabel1");
			f.setSize(300, 200);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			Container cp = f.getContentPane();
			cp.setLayout(null);
			JLabel label1 = new JLabel();
			String[] str=new String[100];
			while (rs.next()) {
				L[i] = new JLabel();
				System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
				
				str[i]=String.format(" %s   %d",rs.getString(1),rs.getInt(2));
				L[i].setText(str[i]);
				cp.add(L[i]);
				L[i].setBounds(20, 20 + i*30, 100, 40);
				i++;
			}
			
			
			
			
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
}