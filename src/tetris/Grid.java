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
	private byte[][] mapa = new byte[24][24];
	
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
					
		
					
					
					if (parar() || bateu()) {

						contador = 0;	
						pecas.add(peca);
						int x = peca.getX() / 20;
						int y = peca.getY() / 20;
						for(int row = 0; row < 4; row++) {
							for(int col = 0; col < 4; col++) {
								if(peca.getKernel()[row][col] == 1) {					
									mapa[y+row][x+col] = 1;
								}
							}
						}
						imprimirMapa();
					}else {
						desenhar();
						contador+=20;
						try {
							Thread.sleep(500);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					
				}
			}

		}).start();
	}
	private void imprimirMapa() {
		
		for(int row = 0; row < 24; row++) {
			if (row == 0) {
				for(int col = 0; col < 24; col++) {
					System.out.print("	["+col+"]");
				}
				System.out.println("\n");
			}
			System.out.print("["+row+"]");
			for(int col = 0; col < 24; col++) {
				System.out.print((mapa[row][col] == 1 ? "*	" : "	")+mapa[row][col]);
				//System.out.print("	"+mapa[row][col]);
			}
			System.out.println("\n");
		}
	}
	private boolean bateu() {
		boolean bateu = false;
		int x = peca.getX() / 20;
		int y = contador / 20;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				//System.out.println(row+"-"+col+"-"+(y+row)+"-"+(x+col));
				if(peca.getKernel()[row][col] == 1 && mapa[y+row][x+col] == 1) {					
					 return true;
				}
			}
			
		}
		return bateu;
	}
	private boolean parar() {
		if (contador + (peca.getHight() * 20)  > 480) {			
			return true;
		} else
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
		for(int i = 0; i <24; i++) {
			g.drawString(i+" ", (i * 20), 20);
			
		}
		for(int x = 1; x <24; x++) {
			g.drawString(x+" ", 3, (x * 20)+20);
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
		if (parar()) {
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
