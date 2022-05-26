package cipo;

import java.util.TimerTask;
import java.util.Date;

public class ScheduledTask extends TimerTask {

		
		
		public void run() 
		{
			Date now = new Date(); // to display current time
			//now.wait(10000);
			System.out.println("Time is :" + now); // Display current time
		}

}
	
	
	
