package Server;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Searcher.BFS;

public class Main {

	public static void main(String[] args) {
	My_Server server = new My_Server(6411);
	server.start(new My_Client_Handler(new My_Solver<Character>(new BFS<Character>())), new My_Solver<Character>(new BFS<Character>()));
	try {
		System.out.println("Server Started!");
		TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		Scanner sc = new Scanner(System.in);
		while(!sc.nextLine().equals("stop"))
			{
			
			
			}
		sc.close();	
		server.stop();
	}
}
