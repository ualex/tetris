package tetris;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Peca {

	private int x;
	private int y;
	private int xReal = 0;
	private int yReal = 0;
	
	private int rotacao;
	public abstract byte[][] getKernel();
	public abstract void setKernel(byte[][] newKernel);
	
	public void desenhar(Graphics g) {
		g.setColor(Color.cyan);
		byte [][] kernel = getKernel();
		xReal = yReal = 0;
		int auxRow = 0;
		int auxCol = 0;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if(kernel[row][col] == 1) {					
					g.fillRect((col *20) +getX()+1, (row * 20) + getY()+1, 19, 19);
					if (xReal == 0 && yReal == 0){
						xReal = (col *20) +getX();
						yReal =  (row * 20) + getY();
					}
				}// else {
				//	g.setColor(Color.yellow);
				//	g.fillRect((col *20) +getX()+1, (row * 20) + getY()+1, 19, 19);
				//	g.setColor(Color.cyan);
				
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

	public int getRotacao() {
		return rotacao;
	}	

	public void setRotacao(int rotacao) {
		this.rotacao = rotacao;
	}

	public void rotacionar() {
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
	public boolean encaixa(Peca peca) {
		
			for(int row = 0; row < getKernel().length; row++) {
				
				for(int col = 0; col < getKernel()[row].length; col++) {
					//System.out.println("Antiga:"+peca.getX()+"-"+peca.getY()+"atual:"+getX()+"-"+getY());
					if (peca.getKernel()[row][col] == 1 && getKernel()[row][col] == 1) {
						return false;
					}
				}
				
			}
		

		return true;
	}
	public int getXReal() {
		return xReal;
	}
	public int getYReal() {
		return yReal;
	}
	public int getHight() {
		int hight  = 0;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if (getKernel()[row][col] == 1) {
					hight +=1;
					break;
				}
			}
		}
		return hight;			
	}
	public int getWidth() {
		int width  = 0;
		
		for(int col = 0; col < 4; col++) {
			for(int row = 0; row < 4; row++) {
				if (getKernel()[row][col] == 1) {
					width += 1;
					break;
				}
			}
		}
		return width;
	}
		
}
