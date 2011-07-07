package tetris;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Grid extends JPanel {

	private static final long serialVersionUID = 2077176596376777249L;
	
	int contador = 0;
	int direcao  = 220;
	int rotacao  = 0;
	private Peca peca;
	
	public Grid() {
		
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while(true) {
					System.out.println("Contador:"+contador);
					if (contador == 0) {
						Random random = new Random(System.currentTimeMillis());
						int pecaSorteada = random.nextInt(6);
						System.out.println("Peca sorteada:"+pecaSorteada);
						switch(pecaSorteada) {
							case 0:
								peca = new I();
								break;
							case 1:
								peca = new Z();
								break;
							case 2:
								peca = new J();
								break;
							case 3:
								peca = new S();
								break;
							case 4:
								peca = new T();
								break;
							case 5:
								peca = new O();
								break;
							case 6:
								peca = new L();
								break;
						}
					}
					//peca = new I();
					desenhar();					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					contador+=20;
					if (contador == 480)
						contador = 0;					
				}
			}
		}).start();
	}
	private void desenhar() {					
		peca.setY(contador);
		peca.setX(direcao);	
		peca.setRotacao(rotacao);
		repaint();						
	}	
	public Dimension getPreferredSize() {
		return new Dimension(480,480);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int grid = 20;				
		for(int i = 0; i <= 24; i++) {
			g.drawLine((i*grid), 0, (i*grid), 480); //vertical
			g.drawLine(0, (i*grid), 480, (i*grid));//horizontal
		}		
				
		peca.desenhar(g);
	}
	public void moverEsquerda() {
		if (direcao == 0)
			return;
		direcao-=20;		
	}
	public void moverDireita() {
		if (direcao == 460)
			return;
		direcao+=20;		
	}
	public void moverBaixo() {
		if (contador == 460)
			return;
		direcao+=20;		
	}
	public void rotacionar() {
		rotacao++;
		if (rotacao == 4)
			rotacao = 0;
		
		peca.rotacionar();
	}

	
}
