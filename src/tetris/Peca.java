package tetris;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Peca {

	private int x;
	private int y;
	private boolean recalcularAltura = true;
	private int altura  = 0;
	public abstract byte[][] getKernel();
	public abstract byte getCor();
	public abstract void setKernel(byte[][] newKernel);
	
	public void desenhar(Graphics g) {
		g.setColor(getColor(getCor()));
		byte [][] kernel = getKernel();		
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if(kernel[row][col] == 1) {					
					g.fillRect((col *20) +getX()+1, (row * 20) + getY()+1, 19, 19);
				} else {
				//	g.setColor(Color.yellow);
				//	g.fillRect((col *20) +getX()+1, (row * 20) + getY()+1, 19, 19);
				//	g.setColor(getColor(getCor()));
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void rotacionar() {
		recalcularAltura = true;
		byte [][] kernel = getKernel();
		byte [][] transposta = new byte[4][4];
		
		for(int row = 0; row < kernel.length; row++) {
			for(int col = 0; col < kernel[row].length; col++) {
				transposta[col][row] = kernel[row][col];
			}
		}

		for(byte col = 0; col < 2; col++) {
			for(byte row = 0; row < 4; row++) {
				byte current_value = transposta[row][col];
				byte newCol = (byte) (3 - col);	
				
				transposta[row][col] = transposta[row][newCol];
				transposta[row][newCol] = current_value;
				
			}			
		}

		setKernel(transposta);
	}
	/**
	 * calcula a altura real da peca
	 * @return altura em unidades
	 */
	public int getAltura() {
		if (!recalcularAltura)
			return altura; 
		
		altura  = 0;
		boolean emBranco=true;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if (getKernel()[row][col] == 1) {
					emBranco = false;
					break;
				}
			}
			if (!emBranco || altura == 0) {
				altura++;
				emBranco=true;
			}
		}
		return altura;			
	}
	/**
	 * calcula o espço horizontal ocupado pela peca
	 * @return largura
	 */
	public int getLargura() {
		for(int row = getKernel().length-1; row >= 0; row--) {
			for(int col = getKernel()[row].length-1; col >=0; col--) {
				if (getKernel()[row][col] > 0) {
					return col+1;
				}
			}
		}
		return 0;
	}
		
	public static Color getColor(byte indiceCor) {
		switch(indiceCor) {
			case 1:
				return Color.cyan;
			case 2:
				return Color.yellow;
			case 3:
				return Color.green;
			case 4:
				return Color.red;
			case 5:
				return Color.blue;
			case 6:
				return Color.pink;
			case 7:
				return Color.magenta;
								
		}
		return Color.white;
	}
	
}
