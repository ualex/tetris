package tetris;

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

	@Override
	public byte getCor() {		
		return 1;
	}
	


}
