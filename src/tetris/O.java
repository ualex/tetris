package tetris;



public class O extends Peca {

	byte [][] kernel = {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}};

	@Override
	public byte[][] getKernel() {		
		return kernel;
	}
	@Override
	public void setKernel(byte[][] newKernel) {
		this.kernel = newKernel;
		
	}
}
