package tetris;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Peca {

	private int x;
	private int y;
	private int auxEncaixe = 0;

	
	private int rotacao;
	public abstract byte[][] getKernel();
	public abstract void setKernel(byte[][] newKernel);
	
	public void desenhar(Graphics g) {
		g.setColor(Color.cyan);
		byte [][] kernel = getKernel();


		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if(kernel[row][col] == 1) {					
					g.fillRect((col *20) +getX()+1, (row * 20) + getY()+1, 19, 19);
				}
				 else {
				//	g.setColor(Color.yellow);
				//	g.fillRect((col *20) +getX()+1, (row * 20) + getY()+1, 19, 19);
				//	g.setColor(Color.cyan);
				
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
			
			if (peca.auxEncaixe < 3) {
				for(int row = 0; row <= peca.auxEncaixe; row++) {				
					for(int col = 0; col < getKernel()[row].length; col++) {
						if (peca.getKernel()[3-row][col] == 1 && getKernel()[row][col] == 1) {
							return false;
						}
					}
				
				}
			} else if(peca.auxEncaixe == 3) { //encaixe total
				for(int row = 0; row < getKernel().length; row++) {				
					for(int col = 0; col < getKernel()[row].length; col++) {
						if (peca.getKernel()[row][col] == 1 && getKernel()[row][col] == 1) {
							return false;
						}
					}
				
				}				
			}
		

		return true;
	}

	public int getHight() {
		int hight  = 0;
		boolean emBranco=true;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if (getKernel()[row][col] == 1) {
					emBranco = false;
					break;
				}
			}
			if (!emBranco || hight == 0) {
				hight++;
				emBranco=true;
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
	public int getAuxEncaixe() {
		return auxEncaixe;
	}
	public void setAuxEncaixe(int auxEncaixe) {
		this.auxEncaixe = auxEncaixe;
	}
	
	
}
