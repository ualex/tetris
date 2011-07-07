package tetris;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Tela extends JFrame {
	private static final long serialVersionUID = 5368903090787342329L;
	public Tela() {		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		final Grid grid = new Grid();
		grid.setBackground(Color.white);
		this.add(grid);
		this.pack();
		this.setVisible(true);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent evento) {
				
				if (evento.getKeyCode() == 37) {
					grid.moverEsquerda();
				} else if (evento.getKeyCode() == 39) {
					grid.moverDireita();
				} else if(evento.getKeyCode() == 40) {
					grid.moverBaixo();
				} else if(evento.getKeyCode() == 38) {
					grid.rotacionar();
				}
					
				
			}
		});
	}
	public static void main(String [] args) {
		SwingUtilities.invokeLater(
				new Runnable() {				
					@Override
					public void run() {
						SwingUtilities.isEventDispatchThread();
						new Tela();						
					}
				}
		);
		
	}
}
