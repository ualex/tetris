package tetris;

import java.awt.Color;
import java.awt.Graphics;
//TODO Redesenhar com base em uma matriz
//TODO Rotacionar uma matriz
//TODO encaixe
//TODO pontuação
public class I extends Peca {
	private byte [][] kernel = {{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
	@Override
	public void desenhar(Graphics g) {
		g.setColor(Color.cyan);	
		for(int i = 0; i < 4; i++)
			for(int x = 0; x < 4; x++)
				if(kernel[i][x] == 1) {					
					g.fillRect(getX()+1, getY()+1, 19, 19);
					g.fillRect(getX()+1, 20+getY()+1, 19, 19);
					g.fillRect(getX()+1, 40+getY()+1, 19, 19);
					g.fillRect(getX()+1, 60+getY()+1, 19, 19);					
				}
		/*			
		if (getRotacao() == 0 || getRotacao() == 2) {			
		} else if (getRotacao() == 1 || getRotacao() == 3) {
			g.setColor(Color.cyan);	
			g.fillRect(getX()-20+1, getY()+1, 19, 19);
			g.fillRect(getX()+1, getY()+1, 19, 19);
			g.fillRect(getX()+20+1, getY()+1, 19, 19);
			g.fillRect(getX()+40+1, getY()+1, 19, 19);
		}*/
		
	}

}
