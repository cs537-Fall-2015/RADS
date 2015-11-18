package RADS;

import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Rad {
    
    private final static double POWER_LEVEL_ON = 4.2;
    
    private final static double POWER_LEVEL_SLEEP = 1.2;
    
    private final static double POWER_LEVEL_OFF = 0.0;
    
    public final static double MIN_RADIATION = 0.1;
    
    public final static double MAX_RADIATION = Math.pow(10, 3);
    
    private double powerLevel;
    
    ArrayList<Double> elements = new ArrayList<Double>();
    
    public HashMap<String, String> data = new HashMap<String, String>();
    
    private String state = "RAD_OFF";
    
<<<<<<< HEAD:src/RADS/Rad.java
    private JSONArray heJson;
    
    public JSONArray getHeJson() {
		return heJson;
	}

	@SuppressWarnings("unchecked")
	public void setHeJson(Double radLevel) {
		
		heJson.add(radLevel);
=======
    JSONArray jarray = new JSONArray();
    
    public JSONArray getJarray() {
		return jarray;
	}

	@SuppressWarnings({ "unchecked" })
	public void setJarray(String particle, String radlevel ) {
		JSONObject obj = new JSONObject();
		obj.put(particle, radlevel);
		obj.put("Time", Calendar.getInstance().getTimeInMillis());
		jarray.add(obj);
>>>>>>> Rads-shrey:src/RADS/Rad.java
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
    
    public void addMeasurement(String particle, String radiationLevel) {
    	//elements.add(radiationLevel);
    	
        data.put(particle, radiationLevel);
        System.out.println("in add measurement");
        System.out.println(particle + radiationLevel);
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
    
}
