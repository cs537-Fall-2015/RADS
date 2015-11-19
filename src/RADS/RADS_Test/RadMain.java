package RADS.RADS_Test;

import generic.RoverThreadHandler;

import java.io.IOException;
<<<<<<< HEAD:src/RADS/RADS_Test/RadMain.java

import RADS.RadServer;
=======
import RADS.RadServer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
>>>>>>> Rads-shrey:src/RADS/RADS_Test/RadMain.java

public class RadMain {

	public static void main(String[] args) {

		// Each module has its own port

<<<<<<< HEAD:src/RADS/RADS_Test/RadMain.java

		int port_one = 9026;

	

=======
		int port_one = 9020;
>>>>>>> Rads-shrey:src/RADS/RADS_Test/RadMain.java


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
<<<<<<< HEAD:src/RADS/RADS_Test/RadMain.java

=======
	
>>>>>>> Rads-shrey:src/RADS/RADS_Test/RadMain.java
}
