package menu;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class menu extends JFrame implements ActionListener{
		private JLabel background;
		private JButton Play;
		private JButton Rank;
		private menuPanel menuPanel;
		private boolean vis = true;
		
		public menu() {
			
			
			
			menuPanel = new menuPanel();
			
			Play = new JButton("Play");
			Play.addActionListener(this);
			menuPanel.add(Play);
			
			Rank = new JButton("Rank");
			Rank.addActionListener(this);
			menuPanel.add(Rank);
			
			add(menuPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			if(e.getSource()==Play) {
				vis = false;
				setVisible(vis);
				Russia tt = new Russia();
				tt.mm();
				
			}else if(e.getSource()==Rank) {
				ReadData rd = new ReadData();
				rd.show();
			}
		}

		public void add() {
			background=new JLabel(new ImageIcon("resources/image/final.jpg"));
			add(background);
			background.setLayout(new GridBagLayout());
		}
}
