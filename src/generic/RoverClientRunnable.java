package generic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class RoverClientRunnable implements Runnable{
	
	private RoverSocket roverSocket;
	
	public RoverClientRunnable(int port, InetAddress host) throws UnknownHostException{
		setRoverSocket(port, host);
	}

	private void setRoverSocket(int port, InetAddress host) throws UnknownHostException{		
		this.roverSocket = new RoverSocket(port, host);		
	}
	//gets the rover socket program running
	public RoverSocket getRoverSocket(){		
		return roverSocket;		
	}
	//if the exception shown it will close all socket programs running
	public void closeAll() throws IOException{
		if(roverSocket != null)
			roverSocket.closeAll();
	}
}
