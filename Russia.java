package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Russia extends JPanel {

	private final Point[][][] Square = { { { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
			{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
			{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } },

			{ { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } },

			{ { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } },

			{ { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } },

			{ { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
					{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } },

			{ { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
					{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
					{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } },

			{ { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
					{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
					{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
					{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } } };

	private final Color[] tetraminoColors = { Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green,
			Color.pink, Color.red };

	private Point pieceOrigin;
	private int currentPiece;
	private int rotation;
	public boolean gg = false;

	long score;
	private Color[][] well;

	protected JLabel gameover;

	void init() {
		well = new Color[12][24];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 23; j++) {
				if (i == 0 || i == 11 || j == 22 || j == 0) {
					well[i][j] = Color.GRAY;
				} else {
					well[i][j] = Color.BLACK;
				}
			}
		}
		newPiece();
	}

	public void newPiece() {

		Random rand = new Random();
		pieceOrigin = new Point(rand.nextInt(6) + 2, 1);
		rotation = 0;
		currentPiece = rand.nextInt(7);
		if (check(pieceOrigin.x, pieceOrigin.y, rotation))
			gg = true;
		else
			gg = false;
	}

	private boolean check(int x, int y, int rotation) {
		for (Point p : Square[currentPiece][rotation]) {
			if (well[p.x + x][p.y + y] != Color.BLACK) {
				return true;
			}
		}
		return false;
	}

	public void rotate(int i) {
		int newRotation = (rotation + i) % 4;
		if (newRotation < 0) {
			newRotation = 3;
		}
		if (!check(pieceOrigin.x, pieceOrigin.y, newRotation)) {
			rotation = newRotation;
		}
		repaint();
	}

	public void move(int i) {
		if (!check(pieceOrigin.x + i, pieceOrigin.y, rotation)) {
			pieceOrigin.x += i;
		}
		repaint();
	}

	public void dropDown() {
		if (!check(pieceOrigin.x, pieceOrigin.y + 1, rotation)) {
			pieceOrigin.y += 1;
		} else {
			fixToWell();
		}
		repaint();
	}

	public void fixToWell() {
		for (Point p : Square[currentPiece][rotation]) {
			well[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = tetraminoColors[currentPiece];
		}
		clearRows();
		if (gg == false)
			newPiece();
		
	}
	
	public void deleteRow(int row) {
		for (int j = row - 1; j > 0; j--) {
			for (int i = 1; i < 11; i++) {
				well[i][j + 1] = well[i][j];
			}
		}
	}

	public void clearRows() {
		boolean gap;
		int numClears = 0;

		for (int j = 21; j > 0; j--) {
			gap = false;
			for (int i = 1; i < 11; i++) {
				if (well[i][j] == Color.BLACK) {
					gap = true;
					break;
				}
			}
			if (!gap) {
				deleteRow(j);
				j += 1;
				numClears += 1;
			}
		}

		switch (numClears) {
		case 1:
			score += 100;
			break;
		case 2:
			score += 300;
			break;
		case 3:
			score += 500;
			break;
		case 4:
			score += 800;
			break;
		}
	}

	private void drawPiece(Graphics g) {
		g.setColor(tetraminoColors[currentPiece]);
		for (Point p : Square[currentPiece][rotation]) {
			g.fillRect((p.x + pieceOrigin.x) * 26, (p.y + pieceOrigin.y) * 26, 25, 25);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, 26 * 12, 26 * 23);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 23; j++) {
				g.setColor(well[i][j]);
				g.fillRect(26 * i, 26 * j, 25, 25);
			}
		}

		g.setColor(Color.WHITE);
		g.drawString("" + score, 19 * 12, 25);

		drawPiece(g);
	}

	public void mm() {
		JFrame f = new JFrame("Tetris");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(12 * 26 + 10, 26 * 23 + 50);
		f.setVisible(true);

		final Russia game = new Russia();
		game.init();
		f.add(game);

		f.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (gg == false) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						game.rotate(-1);
						break;
					case KeyEvent.VK_DOWN:
						game.dropDown();
						break;
					case KeyEvent.VK_LEFT:
						game.move(-1);
						break;
					case KeyEvent.VK_RIGHT:
						game.move(+1);
						break;
					case KeyEvent.VK_SPACE:
						game.dropDown();
						break;
					}
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});
		
		new Thread() {
			@Override
			public void run() {
				while (game.gg == false) {
					try {
						Thread.sleep(1000);
						game.dropDown();
						System.out.println(gg);
						if(game.gg==true){
							Thread.interrupted();
							f.setVisible(false);
							gameover ggg = new gameover();
							
							ggg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							ggg.setSize(12 * 26 + 10, 26 * 23 + 50);
							ggg.add();
							ggg.setVisible(true);
							
						}
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}
}