package menu;

import java.io.FileInputStream;


import javax.swing.JFrame;

import javazoom.jl.player.Player;



public class main {
	public static FileInputStream fileinputstream;
	public static menu a;

	public static void main(String[] args) {
		show();
		try {
			fileinputstream = new  FileInputStream("resources/audio/gwyn.mp3");
			Player player = new Player(fileinputstream);
			while(true){
			player.play();
			System.out.println("Song is playing...");
			}
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public static void show(){
		a = new menu();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setSize(12*26+20, 26*23+50);
		a.add();
		a.setVisible(true);
	}

}
