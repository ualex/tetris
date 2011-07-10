package tetris;

//TODO Redesenhar com base em uma matriz
//TODO Rotacionar uma matriz
//TODO encaixe
//TODO pontuação
public class I extends Peca {
	byte [][] kernel = {{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};

	@Override
	public byte[][] getKernel() {
		
		return kernel;
	}

	@Override
	public void setKernel(byte[][] newKernel) {
		this.kernel = newKernel;
		
	}
	


}
