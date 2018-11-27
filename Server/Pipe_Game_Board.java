package Server;

public class Pipe_Game_Board {

	private int col,row;
	private char[][] board;
	
	public Pipe_Game_Board(int col, int row, String s)
	{
		this.col=col;
		this.row=row;
		this.board=new char[row][col];
		for (int i=0; i<row; i++)
		{
			for (int j=0; j<col; j++)
				this.board[i][j]=s.charAt(col*i + j);		
		}
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public Character[][] getBoard(){
		Character[][] temp = new Character[board.length][board[0].length];
		for (int i=0; i<row; i++)
		{
			for (int j=0; j<col; j++)
				temp[i][j]=this.board[i][j];		
		}	
		return temp;
	}
	
	public char getIndex(int row, int col) {
		return board[row][col];
	}
	
	
}
