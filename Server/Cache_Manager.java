package Server;

import java.io.FileInputStream;

public interface Cache_Manager {
	public void newFile(long hash,String solution);
	public FileInputStream findFile(long hash);
}
