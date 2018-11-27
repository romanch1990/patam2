package Server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class My_Cache_Manager implements Cache_Manager {

	@Override
	public void newFile(long hash, String solution) {
		FileOutputStream sol;
		try {
			sol = new FileOutputStream(hash+".txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sol));
			String[] temp = solution.split("\\r?\\n");
			for (String string : temp) 
			{
				bw.write(string);
				bw.newLine();
			}
			bw.flush();
			sol.close();
			bw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public FileInputStream findFile(long hash) {
		FileInputStream solution = null;
		File f = new File(hash+".txt");

		if(f.exists())
		{
			try {
				solution = new FileInputStream(hash+".txt");

			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			}
		}
		else {
			System.out.println(hash+".txt not found");
		}
		return solution;
	}

}
