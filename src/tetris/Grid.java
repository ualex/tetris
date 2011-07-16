package tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Grid extends JPanel {

	private static final long serialVersionUID = 2077176596376777249L;
	private boolean desenhar = true;
	private boolean encerrar = false;
	int pontuacao = 0;
	int contador = 0;
	int direcao = 120;

	private Peca peca;
	private byte[][] mapa = new byte[16][16];

	public Grid() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				int auxTempo = 0;
				while (!encerrar) {
					if (contador == 0) {
						direcao = 120;

						Random random    = new Random(System.currentTimeMillis());
						int pecaSorteada = random.nextInt(6);

						switch (pecaSorteada) {
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
						desenhar = true;
					}

					if (desenhar) {
						if (parar() || bateu()) {
							acumulaPeca();
							pontuar();
							perdeu();
							contador = 0;
						} else {
							desenhar();
							contador += 20;
						}
						desenhar = false;						
					}

					if (auxTempo > 150) {
						auxTempo = 0;
						desenhar = true;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					auxTempo++;
				}

			}
		}).start();
	}
	private void perdeu() {
		if (peca.getY() == 0) {
			System.out.println("perdeu o jogo!!!");
			encerrar = true;
		}
	}
	private void acumulaPeca() {
		int x = peca.getX() / 20;
		int y = peca.getY() / 20;
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (peca.getKernel()[row][col] > 0) {
					mapa[y + row][x + col] = peca.getCor();
				}
			}
		}

	}

	private void pontuar() {
		int aux = 0;
		int auxContador = 0;
		byte[] rows = new byte[4];
		for (byte row = 0; row < mapa.length; row++) {
			aux = 0;
			for (byte col = 0; col < mapa[row].length; col++) {
				if (mapa[row][col] > 0)
					aux++;
			}
			if (aux == mapa[row].length) {
				pontuacao += 10;
				System.out.println("Pontuou!" + pontuacao);
				rows[auxContador] = row;
				auxContador++;
			}
		}
		if (auxContador > 0) {// redesenha a tela
			for (byte row : rows)
				mapa = removeRow(mapa, row);

			desenhar();
		}
	}

	private byte[][] removeRow(byte[][] kernel, byte i) {
		byte[][] newMatrix = new byte[kernel.length][kernel[0].length];

		int aux = kernel.length - 1;
		if (i == 0) {// copia sem a primeira
			for (int row = 1; row < kernel.length; row++) {
				for (int col = 0; col < kernel[row].length; col++) {
					newMatrix[row][col] = kernel[row][col];
				}
			}
		} else {
			for (int row = kernel.length - 1; row >= 0; row--) {

				if (aux < 0)
					break;

				if (row == i) // Pula a linha
					aux--;

				for (int col = 0; col < kernel[row].length; col++) {
					newMatrix[row][col] = kernel[aux][col];
				}

				aux--;
			}
		}
		return newMatrix;
	}

	private boolean bateu() {
		boolean bateu = false;
		int x = peca.getX() / 20;
		int y = contador / 20;

		if (x == 16)
			x = 15;
		if (y == 16)
			y = 15;

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (peca.getKernel()[row][col] > 0
						&& mapa[y + row][x + col] > 0) {
					return true;
				}
			}

		}
		return bateu;
	}

	private boolean parar() {
		if (contador + (peca.getAltura() * 20) > 320) {
			return true;
		} else
			return false;
	}

	private void desenhar() {
		peca.setY(contador);
		peca.setX(direcao);
		repaint();
	}

	public Dimension getPreferredSize() {
		return new Dimension(320, 320);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int grid = 20;
		for (int i = 0; i <= 24; i++) {
			g.drawLine((i * grid), 0, (i * grid), 480); // vertical
			g.drawLine(0, (i * grid), 480, (i * grid));// horizontal
		}

		for (int i = 0; i < 24; i++) {
			g.drawString(i + " ", (i * 20), 20);
		}
		for (int x = 1; x < 24; x++) {
			g.drawString(x + " ", 3, (x * 20) + 20);
		}

		for (int row = 0; row < mapa.length; row++) {
			for (int col = 0; col < mapa[row].length; col++) {
				if (mapa[row][col] > 0) {
					g.setColor(Peca.getColor(mapa[row][col]));
					g.fillRect((col * 20) + getX() + 1,
							(row * 20) + getY() + 1, 19, 19);
				}
			}
		}
		peca.desenhar(g);
	}

	public void moverEsquerda() {
		direcao -= 20;
		desenhar = true;
	}

	public void moverDireita() {
		direcao += 20;		
		desenhar = true;
	}

	public void moverBaixo() {
		desenhar = true;
	}

	public boolean excedeLimiteLateralDireito() {
		if (direcao + (peca.getLargura() * 20) <= 300) {
			if (!parar() || !bateu()) {
				return false;
			}
		}
		return true;
	}

	public void rotacionar() {
		if (!excedeLimiteLateralDireito()) {
			peca.rotacionar();
		}
	}
}
