package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class L extends Peca {

	@Override
	public void desenhar(Graphics g) {		
		g.setColor(Color.orange);	
		g.fillRect(getX()+1, getY()+1, 19, 19);
		g.fillRect(getX()+1, 20+getY()+1, 19, 19);
		g.fillRect(getX()+1, 40+getY()+1, 19, 19);
		g.fillRect(20 + getX()+1, 40+getY()+1, 19, 19);
	}
}
