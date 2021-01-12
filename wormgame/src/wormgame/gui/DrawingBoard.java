package wormgame.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import wormgame.domain.Piece;
import wormgame.game.WormGame;

public class DrawingBoard extends JPanel implements Updatable{
	private WormGame wg;
	private int pieceLength;
	public DrawingBoard(WormGame wg, int pieceLength) {
		this.wg = wg;
		this.pieceLength = pieceLength;
		super.setBackground(Color.GRAY);
	}
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        for(Piece p:wg.getWorm().getPieces()){
            graphics.fill3DRect(p.getX() * this.pieceLength, p.getY() * this.pieceLength, this.pieceLength, this.pieceLength, true);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(this.wg.getApple().getX() * this.pieceLength, this.wg.getApple().getY() * this.pieceLength,this.pieceLength,this.pieceLength);
	}
	@Override
	public void update() {
		super.repaint();
	}
}
