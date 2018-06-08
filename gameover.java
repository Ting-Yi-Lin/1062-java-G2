package menu;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameover extends JFrame implements ActionListener{
		private JButton PlayAgain;
		private JButton Menu;
		private gameoverPanel gameoverPanel;
		private JLabel background;
		
		public gameover() {
			gameoverPanel = new gameoverPanel();
			
			PlayAgain = new JButton("PlayAgain");
			PlayAgain.addActionListener(this);
			gameoverPanel.add(PlayAgain);
			
			Menu = new JButton("Menu");
			Menu.addActionListener(this);
			gameoverPanel.add(Menu);
			
			
			add(gameoverPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			if(e.getSource()==PlayAgain) {
				setVisible(false);
				Russia tt = new Russia();
				tt.mm();
			}
			
			else if(e.getSource()==Menu) {
				setVisible(false);
				main.show();
				
			}
		}
		
		public void add(){
			background=new JLabel(new ImageIcon("resources/image/game_over.png"));
			add(background);
			background.setLayout(new GridBagLayout());

		}

	
}
