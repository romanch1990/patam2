package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Server.Client_Handler;

public class My_Server extends Thread implements Server {

	protected int 				port;
	protected Client_Handler 	handler;
	protected volatile boolean 	stop;
	protected ServerSocket		serverSocket = null;
	
	public My_Server(int port) {
		this.port = port;
		this.stop = false;
	}
	
	public void start(Client_Handler ch, Solver<?> sol) {
		this.handler= ch;
		new Thread(()->{
			try {
				runServer(sol);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void runServer(Solver<?> sol) throws IOException {

		
		while (!isStopped())
		{
			Socket aClient = null;
			try {
				aClient = serverSocket.accept();
			} catch(IOException e) {
				if(isStopped()) {
					System.out.println("Server Stopped.");
					return;
				} 
				throw new RuntimeException("Error accepting client connection", e);
			}
			

				try {
					handler = new My_Client_Handler(sol);
					handler.hc(aClient.getInputStream(), aClient.getOutputStream());
					aClient.getInputStream().close();
					aClient.close();
				}

					catch (Exception e) {
						System.out.println("Was not able to handle the request");
						System.out.println(e.getMessage());
						}

		}

	}

	@Override
	public void stop() {
		this.stop = true;
		System.out.println("Server Stopped.");
		try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
	}

	private synchronized boolean isStopped() {
		return this.stop;
	}
	
	public synchronized void stop() {
		this.stop = true;
		try {
			this.serverSocket.close();
		}
	}
	
	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
			serverSocket.setSoTimeout(1000);
		} catch(IOException e) {
			throw new RuntimeException("Cannot open port " + this.port, e);
		}
	}
}
