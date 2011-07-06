package tetris;

import java.awt.Graphics;

public abstract class Peca {
	private int x;
	private int y;
	private int rotacao;
	
	public abstract void desenhar(Graphics g);

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
		
}
