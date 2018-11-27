package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


import Searcher.Board_Searchable;
import Server.Pipe_Game_Board;
import Server.My_Cache_Manager;
import Server.Normalizer;

public class My_Client_Handler implements Client_Handler {
	
	private Solver<?> sol;
	private Cache_Manager cm;
	
	public My_Client_Handler(Solver<?> sol) {
		this.sol = sol;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void hc(InputStream input, OutputStream output) {
		BufferedReader serverInput=new BufferedReader(new InputStreamReader(input));
		BufferedWriter serverOutput = new BufferedWriter(new OutputStreamWriter(output));	
		String solution="";
		
		try {
			int row = 0;
			int col = 0;
			String getClient = "";
			String[] checker = {"",""};
			getClient = serverInput.readLine();
			col = getClient.length();
			
			while (!getClient.equals("done"))
		    {
				row++;
				checker[0] = checker[0] + getClient;
				getClient = serverInput.readLine();
		    }
			
			Pipe_Game_Board board=new Pipe_Game_Board(col, row, checker[0]);
			Normalizer n = new Normalizer();
			long hash = n.normalizer(checker, col, row).hashCode();
			FileInputStream checkSol = new My_Cache_Manager().findFile(hash);
			if(checkSol != null) {
				ArrayList<String> savedSol = new ArrayList<String>();
				BufferedReader bf = new BufferedReader(new InputStreamReader(checkSol));
				while ((getClient=bf.readLine()) != null)
					savedSol.add(getClient); 					
				int rowSol=0,colSol=0,turns=0;

				for (String result: savedSol) {
					
					rowSol = Character.getNumericValue(result.charAt(0));
					colSol = Character.getNumericValue(result.charAt(2));
					turns = Character.getNumericValue(result.charAt(4)) + (Character.getNumericValue(checker[1].charAt((rowSol*col)+colSol)));
					char type = (char) checker[0].charAt(rowSol*col+colSol);

					if (turns>=4)
						turns-=4;
					if (turns>=2 && (type == '|' || type == '-'))
						turns-=2;
					if(turns !=0)
						{
							serverOutput.write(rowSol + "," + colSol + "," + turns);
							serverOutput.newLine();

						}
					
					}
				serverOutput.write("done");
				serverOutput.flush();
				checkSol.close();
			}
			else {
				solution = sol.solve(new Board_Searchable(board));
				serverOutput.write(solution);
				serverOutput.write("done");
				serverOutput.flush();
				
				int rowSol=0,colSol=0,turns=0;
				String[] temp = solution.split("\\r?\\n");
				solution = "";		
				for (String result: temp) {
					
					rowSol = Character.getNumericValue(result.charAt(0));
					colSol = Character.getNumericValue(result.charAt(2));
					turns = Character.getNumericValue(result.charAt(4)) + (Character.getNumericValue(checker[1].charAt((rowSol*col)+colSol)));
					char type = (char) checker[0].charAt(rowSol * col + colSol);
					if (turns>=4)
						turns-=4;
					if (turns>=2 && (type == '|' || type == '-'))
						turns-=2;
					solution += rowSol + "," + colSol + "," + turns + "\n";
					
				}
				
				cm = new My_Cache_Manager();
				cm.newFile(hash, solution);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
