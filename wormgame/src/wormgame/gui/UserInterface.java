package wormgame.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import wormgame.game.WormGame;

public class UserInterface implements Runnable{
	private JFrame frame;
	private WormGame wg;
	private DrawingBoard board;
	private int sides;
	public UserInterface(WormGame wg, int sides) {
		this.wg = wg;
		this.sides = sides;
	}
	
	@Override
	public void run() {
		this.frame = new JFrame("Worm Game");
		int w = (this.wg.getWidth() + 1) * this.sides + 10;
		int h = (this.wg.getWidth() + 2) * this.sides + 10;
		this.frame.setPreferredSize(new Dimension(w,h));
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createComponents(this.frame.getContentPane());
		this.frame.pack();
		this.frame.setVisible(true);
	}
	public void createComponents(Container container) {
		this.board = new DrawingBoard(this.wg,this.sides);
		container.add(board);
		KeyboardListener kl = new KeyboardListener(this.wg.getWorm());
		this.frame.addKeyListener(kl);
	}
	public Updatable getUpdatable() {
		return this.board;
	}
}
