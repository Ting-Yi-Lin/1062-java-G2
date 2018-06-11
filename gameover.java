package menu;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import mysql.InsertData;
import mysql.InsertSelections;
import mysql.ReadData;
import mysql.UpdateData;

public class gameover extends JFrame implements ActionListener{
		private JButton PlayAgain;
		private JButton Menu;
		private gameoverPanel gameoverPanel;
		private JLabel background;
		private gameoverPanel rank;
		private JTextField field;
		private JButton submit;
		private UpdateData up = new UpdateData();
		private ReadData to;
		private InsertData in = new InsertData();
		private int score;
		private InsertSelections sel = new InsertSelections();
		
		public void in(int score){
			this.score = score;
		}
		
		public gameover() {
			gameoverPanel = new gameoverPanel();
			rank = new gameoverPanel();
			
			PlayAgain = new JButton("PlayAgain");
			PlayAgain.addActionListener(this);
			gameoverPanel.add(PlayAgain);
			
			Menu = new JButton("Menu");
			Menu.addActionListener(this);
			gameoverPanel.add(Menu);
			
			field = new JTextField(10);
			field.setText("Enter your name");
			field.selectAll();
			rank.add(field);
			
			submit = new JButton("submit");
			submit.addActionListener(this);
			rank.add(submit);
			
			add(gameoverPanel, BorderLayout.SOUTH);
			add(rank, BorderLayout.NORTH);
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
			
			else if(e.getSource()==submit && field.getText() != null) {
				if(sel.select(field.getText(),score)==true){
					up.update(field.getText(),score);
					to = new ReadData();
					to.read();
				}
				else if(sel.select(field.getText())==true){
					to = new ReadData();
					to.read();
				}
				else{
					in.insert(field.getText(),score);
					to = new ReadData();
					to.read();
				}
			}
		}
		
		public void add(){
			background=new JLabel(new ImageIcon("resources/image/game_over.png"));
			add(background);
			background.setLayout(new GridBagLayout());
			
		}

	
}
