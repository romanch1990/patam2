package Server;

import Server.Client_Handler;
import Server.Solver;

public interface Server {
	void start(Client_Handler ch, Solver<?> sol);
	void stop();
}
