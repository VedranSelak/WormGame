package wormgame.domain;

import java.util.ArrayList;
import java.util.List;

public class Worm {
	private int originalX;
	private int originalY;
	private Direction originalDirection;
	private ArrayList<Piece> list;
	private boolean grow;
	public Worm(int originalX, int originalY, Direction originalDirection) {
		this.originalX = originalX;
		this.originalY = originalY;
		this.originalDirection = originalDirection;
		this.list = new ArrayList<Piece>();
		this.list.add(new Piece(this.originalX,this.originalY));
		this.grow = false;
	}
	public Direction getDirection() {
		return this.originalDirection;
	}
	public void setDirection(Direction newDirection) {
		this.originalDirection = newDirection;
	}
	public List<Piece> getPieces(){
		return this.list;
	}
	public int getLength() {
		return getPieces().size();
	}
	public void move() {
		if(getLength() < 3 || this.grow == true) {
			if(this.originalDirection == Direction.RIGHT) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX() + 1,this.list.get(this.list.size()-1).getY()));
				this.grow = false;
			}else if(this.originalDirection == Direction.LEFT) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX() - 1,this.list.get(this.list.size()-1).getY()));
				this.grow = false;
			}else if(this.originalDirection == Direction.UP) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX(),this.list.get(this.list.size()-1).getY()-1));
				this.grow = false;
			}
			else if(this.originalDirection == Direction.DOWN) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX(),this.list.get(this.list.size()-1).getY()+1));
				this.grow = false;
			}
		}else {
			if(this.originalDirection == Direction.RIGHT) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX() + 1,this.list.get(this.list.size()-1).getY()));
				this.list.remove(0);
			}else if(this.originalDirection == Direction.LEFT) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX() - 1,this.list.get(this.list.size()-1).getY()));
				this.list.remove(0);
			}else if(this.originalDirection == Direction.UP) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX(),this.list.get(this.list.size()-1).getY()-1));
				this.list.remove(0);
			}
			else if(this.originalDirection == Direction.DOWN) {
				this.list.add(new Piece(this.list.get(this.list.size()-1).getX(),this.list.get(this.list.size()-1).getY()+1));
				this.list.remove(0);
			}
		}
	}
	public void grow() {
		this.grow = true;
	}
	public boolean runsInto(Piece piece) {
		for(Piece p : this.list) {
			if(p.getX() == piece.getX() && p.getY() == piece.getY()) {
				return true;
			}
		}
		return false;
	}
	public boolean runsIntoItself() {
		for(Piece p : this.list) {
			for(Piece p2 : this.list) {
				if(p.getX() == p2.getX() && p.getY() == p2.getY() && p != p2) {
					return true;
				}
			}
		}
		return false;
	}
}
