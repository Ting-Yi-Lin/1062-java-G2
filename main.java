package menu;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		
		menu a = new menu();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setSize(12*26+20, 26*23+50);
		a.add();
		a.setVisible(true);
		
	}

}
