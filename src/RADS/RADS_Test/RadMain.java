package RADS.RADS_Test;

import generic.RoverThreadHandler;

import java.io.IOException;
import RADS.RadServer;


public class RadMain {

	public static void main(String[] args) {

		// Each module has its own port

		int port_one = 9003;


		try {

			// create a thread for module one
			RadServer serverOne = new RadServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler()
					.getNewThread(serverOne);

			// server begins listening
			server_1.start();

			// client one server sending messages to server
			RadClient clientOne = new RadClient(port_one, null); // notice
																	// port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler()
					.getNewThread(clientOne);

			// start the thread which communicates through sockets initiates it
			client_1.start();
			System.out.println("Client process initiated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void test () {
		System.out.println("testing the module");
	}
	
}
