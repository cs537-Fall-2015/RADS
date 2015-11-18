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
	String path = "3.json";
	
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

				// read the JSON file
				//readJson();

				// do work
				doWork();
				
				writeJson();

				// creating socket and waiting for client connection
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
					//doWork();
//					System.out.println("In hashmap");
//					for (String key : rad.data.keySet()) {
//						System.out.println("Key = " + key);
//						}
					//writeJson();
				}

				
				

				// write object to Socket
				outputToAnotherObject.writeObject("Rad Server response - "
						+ message);

				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
			}
			System.out.println("Server: Shutting down Socket server 1!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		}

	}

	String doWork() throws InterruptedException {

		String message = "";

		if (rad.isScience()) {

			message += "\nRAD: Now in SCIENCE mode. Reading data for 15 mins.";
			message += "\nRAD: Adding measurements from the environment.";
			
			for (int i = 0; i < 300; i++) {

				Double calc = Rad.MIN_RADIATION
						+ (Math.random() * ((Rad.MAX_RADIATION - Rad.MIN_RADIATION) + 1));
						
				if(calc <= 1.00){
					int index = Randomidx(elementslist1);
					particle = elementslist1[index];
//					Rad.data.put("Name", particle);
//					Rad.data.put("Value", calc.toString());
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					//System.out.println(particle + " " + calc );
				}
				
				else if(calc > 1.00 && calc < 7.00){
					int index = Randomidx(elementslist2);
					particle = elementslist2[index];
//					Rad.data.put("Name", particle);
//					Rad.data.put("Value", calc.toString());
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					//System.out.println(particle + " " + calc );
				}
				
				else if(calc > 7.00 && calc < 10.00){
					int index = Randomidx(elementslist3);
					particle = elementslist3[index];
//					Rad.data.put("Name", particle);
//					Rad.data.put("Value", calc.toString());
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					//System.out.println(particle + " " + calc );
				}
				
				else if(calc > 10.00 && calc < 100.00){
					int index = Randomidx(elementslist4);
					particle = elementslist4[index];
//					Rad.data.put("Name", particle);
//					Rad.data.put("Value", calc.toString());
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					//System.out.println(particle + " " + calc );
				}
				
				else if(calc > 100.00 && calc < 100.00){
					int index = Randomidx(elementslist5);
					particle = elementslist5[index];
//					Rad.data.put("Name", particle);
//					Rad.data.put("Value", calc.toString());
					rad.addMeasurement(particle, calc.toString());
					rad.setJarray(particle, calc.toString());
					//System.out.println(particle + " " + calc );
				}
				
				

				//rad.addMeasurement(calc);
			}
			
			

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

		// Instantiate the writer since we're writing to a JSON file.
		//FileWriter writer = null;
//		try {
//			writer = new FileWriter(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// Object is converted to a JSON String
		//String jsonString = gson.toJson(rad);
		

		new JSON.MyWriter(rad.jarray, 3);
		System.out.println("In to JSON");
		System.out.println(rad.data);
//		new JSON.MyWriter(rad.elements, 9015);

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
	}

	@SuppressWarnings("unchecked")
	void readJson() {
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
		JSON.GlobalReader greader = new JSON.GlobalReader(3);
		JSONObject obj = greader.getJSONObject();
		rad.setData(obj);

	}
	
	
	public int Randomidx(String[] data){
		int index = new java.util.Random().nextInt(data.length);
		return index;
	}
}
