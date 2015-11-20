package RADS;

import generic.RoverServerRunnable;

import JSON.MyWriter;
import JSON.GlobalReader;
import JSON.MyWriter;
import JSON.GlobalReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class RadServer extends RoverServerRunnable {

	private Rad rad = new Rad();
	String path = "5.json";
	
	String particle;
	
	String[] elementslist1 = {"positron","electrons","gamma-rays"};
	
	String[] elementslist2 = {"electrons","gamma-rays"};
	
	String[] elementslist3 = {"neutrons" ,"electrons" , "ions(Li-O)" , "p-He"};
	
	String[] elementslist4 = {"ions(Li-O)" , "ions(Mg-Fe)" , "p-He"};
	
	String[] elementslist5 = {"ions(Li-O)" , "ions(Mg-Fe)"};

	public RadServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		try {
			while (true) {

				System.out.println("RAD Server: Waiting for client request");

<<<<<<< HEAD:src/RADS/RadServer.java
				// read the JSON file
				//readJson();

				// do work
				doWork();
				
				writeJson();

				// creating socket and waiting for client connection
=======
				
				doWork();
				
				
>>>>>>> Rads-shrey:src/RADS/RadServer.java
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(
						getRoverServerSocket().getSocket().getInputStream());

				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out
						.println("RAD Server: Message Received from Client - "
								+ message.toUpperCase());

				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());

				if (message.equals("RAD_BOOTUP")) {

					message = "RAD: Booting up -- Please wait";

					rad.bootup();
					rad.science();

				} else if (message.equals("RAD_CHECKOUT")) {

					// sends back data
					// and clears it
					rad.checkout();
					message = "Rad Data:" + rad.getData();

					//rad.clearData();

				} else if (message.equals("RAD_SHUTDOWN")) {

					// loads sleep timer and initiates sleep state
					rad.sleep(); // 45 mins then bootup again

					message = "Sleeping for 45 minutes.";

				} else if (message.equals("RAD_IS_ON")) {
					// return state

					message = "RAD is currently: "
							+ (rad.isOn() ? "On" : "Off");
				} else if (message.equals("RAD_OFF")) {
					rad.off();

					message = "Turning RAD off.";
				} else if (message.equals("RAD_GET_POWER")) {

					message = "RAD power consumption is: "
							+ rad.getPowerConsumption();
<<<<<<< HEAD:src/RADS/RadServer.java
				
=======
					
>>>>>>> Rads-shrey:src/RADS/RadServer.java
				}
				outputToAnotherObject.writeObject("Rad Server response - "
						+ message);
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
				if (message.equalsIgnoreCase("exit"))
					break;
			}
			
			System.out.println("Server: Shutting down Socket server 1!!");
<<<<<<< HEAD:src/RADS/RadServer.java
=======
			writeJson();
>>>>>>> Rads-shrey:src/RADS/RadServer.java
			closeAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		}
		

	}

	String doWork() throws InterruptedException {

		String message = "";

		if (rad.isScience()) {

<<<<<<< HEAD:src/RADS/RadServer.java
			message += "\nRAD: Now in SCIENCE mode. Reading data for 15 mins.";
			message += "\nRAD: Adding measurements from the environment.";
			message += "\nRAD: Adding radiation and particle data to json file";
			
			for (int i = 0; i < 300; i++) {

<<<<<<< HEAD:src/RADS/RadServer.java
			for (int i = 0; i < 15; i++) {

=======
>>>>>>> Rads-shrey:src/RADS/RadServer.java
=======
			//message += "\nRAD: Now in SCIENCE mode. Reading data for 15 mins.";
			System.out.println("Adding measurements from the environment.");
			System.out.println("Adding radiation and particle data to json file");
			
			for (int i = 0; i < 200; i++) {

>>>>>>> Rads-shrey:src/RADS/RadServer.java
				Double calc = Rad.MIN_RADIATION
						+ (Math.random() * ((Rad.MAX_RADIATION - Rad.MIN_RADIATION) + 1));
						
				if(calc <= 1.00){
					int index = Randomidx(elementslist1);
					particle = elementslist1[index];
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
				}
				
				else if(calc > 1.00 && calc < 7.00){
					int index = Randomidx(elementslist2);
					particle = elementslist2[index];
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
				}
				
				else if(calc > 7.00 && calc < 10.00){
					int index = Randomidx(elementslist3);
					particle = elementslist3[index];					
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
				}
				
				else if(calc > 10.00 && calc < 100.00){
					int index = Randomidx(elementslist4);
					particle = elementslist4[index];
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					
				}
				
				else if(calc > 100.00 && calc < 1000.00){
					int index = Randomidx(elementslist5);
					particle = elementslist5[index];
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					
				}
				
				

<<<<<<< HEAD:src/RADS/RadServer.java
<<<<<<< HEAD:src/RADS/RadServer.java
				rad.addMeasurement(calc);
				
//				if(calc > 1000) {
//					rad.setHeJson(calc);
//				}
			}
			//System.out.println(rad.getHeJson());
=======
=======
>>>>>>> Rads-shrey:src/RADS/RadServer.java
				
			}
			
			

>>>>>>> Rads-shrey:src/RADS/RadServer.java
			message += "\nRAD: Collection completed.\nGoing to sleep for 45 mins";

			Thread.sleep(15000); // sleep for 15 seconds

			rad.sleep();

		} else if (rad.isSleeping()) {

			message += "\nRAD: Now in SCIENCE mode. Reading data for 15 mins.";

			rad.science();

		}

		return message;
	}

	void writeJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
<<<<<<< HEAD:src/RADS/RadServer.java
<<<<<<< HEAD:src/RADS/RadServer.java

		// Instantiate the writer since we're writing to a JSON file.
		//FileWriter writer = null;
//		try {
//			writer = new FileWriter(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// Object is converted to a JSON String
		//String jsonString = gson.toJson(rad);
		

		new JSON.MyWriter(rad, 3); 

		// Write the file
//		try {
//			writer.write(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// Close the Writer
//		try {
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
=======
		
		new JSON.MyWriter(rad.jarray, 3);
	
>>>>>>> Rads-shrey:src/RADS/RadServer.java
=======
		
		new JSON.MyWriter(rad.jarray, 5);
	
>>>>>>> Rads-shrey:src/RADS/RadServer.java
	}

	@SuppressWarnings("unchecked")
	void readJson() {
<<<<<<< HEAD:src/RADS/RadServer.java
<<<<<<< HEAD:src/RADS/RadServer.java
		//JSONParser parser = new JSONParser();
		//JSONObject obj = null;
//		try {
//
//			Object obj = parser.parse(new FileReader(path));
//			JSONObject json = (JSONObject) obj;
//
//			rad.setState((String) json.get("state"));
//
//			JSONObject j = (JSONObject) json.get("data");
//
//			rad.setData(j);
//
//		} catch (FileNotFoundException e) {
//			System.out.println("No file found. " + e.getMessage());
//			// e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("I/O exception found.");
//			e.printStackTrace();
//		} catch (ParseException e) {
//			System.out.println("Parse exception found.");
//			e.printStackTrace();
//		}
		
		//json.GlobalReader greader = new JSON.GlobalReader(3);
=======
	
>>>>>>> Rads-shrey:src/RADS/RadServer.java
=======
	
>>>>>>> Rads-shrey:src/RADS/RadServer.java
		JSON.GlobalReader greader = new JSON.GlobalReader(3);
		JSONObject obj = greader.getJSONObject();
		rad.setData(obj);

	}
	
	
	public int Randomidx(String[] data){
		int index = new java.util.Random().nextInt(data.length);
		return index;
	}
}
