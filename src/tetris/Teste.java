package tetris;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
			byte [][] kernel = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
			byte [][] transposta = new byte[4][4];
			
			System.out.println("Matriz");
			imprimir(kernel);
			
			for(int row = 0; row < kernel.length; row++) {
				for(int col = 0; col < kernel[row].length; col++) {
					transposta[col][row] = kernel[row][col];
				}
			}
			System.out.println("Transposta");
			imprimir(transposta);
			for(byte col = 0; col < 2; col++) {
				for(byte row = 0; row < 4; row++) {
					byte current_value = transposta[row][col];
					byte newCol = (byte) (3 - col);				
					transposta[row][col] = transposta[row][newCol];
					transposta[row][newCol] = current_value;					
				}			
			}
			System.out.println("Girada em 90");
			imprimir(transposta);			
			//kernel = removeRow(transposta, 1);
			System.out.println("Sem linha");
			imprimir(kernel);
			
	}


	public static void imprimir(byte[][] transposta) {
		for(byte row = 0; row < transposta.length; row++) {
			for(byte col = 0; col < transposta[row].length; col++) {
				System.out.print("	"+transposta[row][col]);
			}
			System.out.println("");
		}
	}
}
