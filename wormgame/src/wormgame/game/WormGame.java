package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import wormgame.domain.Apple;
import wormgame.domain.Direction;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener{
	
	private Worm worm;
	private Apple apple;
	private Random rand;
	private int width;
	private int height;
	private boolean cont;
	private Updatable updatable;
	public WormGame(int width, int height) {
		super(1000,null);
		this.worm = new Worm(width/2,height/2,Direction.DOWN);
		this.rand = new Random();
		this.apple = new Apple(this.rand.nextInt(width),this.rand.nextInt(height));
		this.width = width;
		this.height = height;
		this.cont = true;
		addActionListener(this);
		setInitialDelay(2000);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(!this.cont) {
			System.exit(0);
		}
		this.worm.move();
		if(this.worm.runsInto(this.apple)) {
			this.worm.grow();
			while(true) {
				this.apple = new Apple(this.rand.nextInt(this.width),this.rand.nextInt(this.height));
				if(!this.worm.runsInto(this.apple)) {
					break;
				}
			}
		}
		if(this.worm.runsIntoItself()) {
			this.cont = false;
		}
		for(Piece piece : this.worm.getPieces()) {
			if(piece.getX()<0||piece.getX()>=width||piece.getY()<0||piece.getY()>=height) {
				this.cont = false;
			}
		}
		updatable.update();
		setDelay(1000/this.worm.getLength());
		
	}
	public Worm getWorm() {
		return this.worm;
	}
	public void setWorm(Worm worm) {
		this.worm = worm;
	}
	public Apple getApple() {
		return this.apple;
	}
	public void setApple(Apple apple) {
		this.apple = apple;
	}
	public boolean getCont() {
		return this.cont;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setUpdatable(Updatable updatable) {
		this.updatable = updatable;
	}
}
