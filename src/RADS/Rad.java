package RADS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
=======
import java.util.Iterator;
>>>>>>> develop

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
<<<<<<< HEAD
=======


>>>>>>> Rads-shrey

public class Rad {
    
    private final static double POWER_LEVEL_ON = 4.2;
    
    private final static double POWER_LEVEL_SLEEP = 1.2;
    
    private final static double POWER_LEVEL_OFF = 0.0;
    
    public final static double MIN_RADIATION = 0.1;
    
    public final static double MAX_RADIATION = Math.pow(10, 3);
    
    private double powerLevel;
    
<<<<<<< HEAD
    public static ArrayList<String> part = new ArrayList<String>();
    
    
    
    public static ArrayList<String> radiation = new ArrayList<String>();
    
    
    
=======
>>>>>>> develop
    public HashMap<String, String> data = new HashMap<String, String>();
    
    private String state = "RAD_OFF";

    JSONArray jarray = new JSONArray();
    
<<<<<<< HEAD
    JSONArray jarray = new JSONArray();
    
=======
>>>>>>> develop
    public JSONArray getJarray() {
		return jarray;
	}

	@SuppressWarnings({ "unchecked" })
	public void setJarray(String particle, String radlevel ) {
		JSONObject obj = new JSONObject();
		obj.put("particle", particle);
		obj.put("Radiation",radlevel);
<<<<<<< HEAD
		part.add(particle);
		radiation.add(radlevel);
=======
>>>>>>> develop
		jarray.add(obj);
	}


	public Rad() {
    }
    
    // Set the state to RAD_OFF
    
    public void off() {
        setState("RAD_OFF");
    }
    // Set the state to RAD_BOOTUP
    public void bootup() {
        setState("RAD_BOOTUP");
    }
    // Set the state to RAD_Science
    public void science() {
        setState("RAD_SCIENCE");
    }
    // Set the state to RAD_CHECKOUT
    public void checkout() {
        setState("RAD_CHECKOUT");
    }
    // Set the state to RAD_SHUTDOWN
    public void shutdown() {
        setState("RAD_SHUTDOWN");
    }
    // Set the state to RAD_SLEEP
    public void sleep() {
        setState("RAD_SLEEP");
    }
    
    // Rover interaction
    
    public boolean isOn() {
        return !state.equals("RAD_OFF");
    }
    
    public boolean isSleeping() {
        return state.equals("RAD_SLEEP");
    }
    
    public boolean isScience() {
        return state.equals("RAD_SCIENCE");
    }
    
    public double getPowerConsumption() {
        if (state.equals("RAD_OFF")) {
            return POWER_LEVEL_OFF;
        }
        if (state.equals("RAD_SLEEP")) {
            return POWER_LEVEL_SLEEP;
        } else {
            return POWER_LEVEL_ON;
        }
    }
    
<<<<<<< HEAD
=======
//    public void addMeasurement(Double radiationLevel) {
//        data.put(Calendar.getInstance().getTimeInMillis(), radiationLevel);
//    }
//    
    
>>>>>>> develop
    public void addMeasurement(String particle, String radiationLevel) {
    	//elements.add(radiationLevel);
    	
        data.put(particle, radiationLevel);
  
    }
    
    public void clearData() {
        data.clear();
    }
    
    // Getters/Setters
    
    public HashMap<String, String> getData() {
        if (state.equals("RAD_CHECKOUT")) {
            return data;
        } else {
            return null;
        }
    }
    
    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
        
        setPowerLevel(getPowerConsumption());
    }
    
    public double getPowerLevel() {
        return powerLevel;
    }
    
    public void setPowerLevel(double powerLevel) {
        this.powerLevel = powerLevel;
    }
    
<<<<<<< HEAD
=======
    
>>>>>>> Rads-shrey
    public void readJSONData () {
    	String filePath = "RADS/data.json";
    	JSONParser parser = new JSONParser();
    	try {
    		JSONArray obj = (JSONArray) parser.parse(new FileReader(filePath));
    		for(Object o : obj) {
    			JSONObject jsonObject = (JSONObject) o;
    			String dratestr = (String) jsonObject.get("Dose rates");
    			Double drates = Double.parseDouble(dratestr);
    			String time = (String) jsonObject.get("Time");
    			if(drates > 290.0) {
    				System.out.println("Heavy ions events getting detected at " + time + " with a dose rate " + drates);
    			}
    		}
    	}  catch (FileNotFoundException e) {
			System.out.println("No file found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O exception found.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Parse exception found.");
			e.printStackTrace();
		}
    		
    	}
    
}
