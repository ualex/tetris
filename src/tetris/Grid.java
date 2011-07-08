package tetris;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

public class Grid extends JPanel {

	private static final long serialVersionUID = 2077176596376777249L;	
	List<Peca> pecas = new ArrayList<Peca>();
	
	int contador = 0;
	int direcao  = 220;
	int rotacao  = 0;
	int auxTempo = 0;
	private Peca peca;
	
	public Grid() {
		
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while(true) {
					if (contador == 0) {
						Random random = new Random(System.currentTimeMillis());
						int pecaSorteada = random.nextInt(6);
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
					
					desenhar();
					
					try {
						Thread.sleep(500);
						auxTempo++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					contador+=20;
					
					if (parar() || bateu()) {
						contador = 0;
						pecas.add(peca);
					}
				}
			}


		}).start();
	}
	private boolean parar() {
		if (contador  == 440)
			return true;
		else
			return false;
	}
	private boolean bateu() {
		for(Peca antiga : pecas) {
			if (antiga.getY() <= peca.getY()+80) {
				if (Math.abs(antiga.getX() - peca.getX()) < 80) {
					return !antiga.encaixa(peca);
				}
			}
		}
		return false;
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
		//desenhar pecas antigas
		for(Peca peca2 : pecas) {
			peca2.desenhar(g);
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
		if (parar() || bateu()) {
			contador = 0;
			pecas.add(peca);
		} else {
			contador+=20;
		}
	}
	public void rotacionar() {
		
		peca.rotacionar();
	}

	
}
