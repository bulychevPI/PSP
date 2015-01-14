package serverBulychevPI;

import java.io.Serializable;
import javax.swing.JTextField;

// Class to represent the timer.

public class Timer extends Thread implements Serializable{
	
	private int seconds, minutes, hours;
	private boolean stop, pause;
	private JTextField timeField;
	
	public Timer( JTextField timeField ) {
		seconds = minutes = hours = 0;
		this.timeField = timeField;
	}

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }
        
	public void SetTime(int h,int m,int s){
            this.hours=h;
            this.minutes=m;
            this.seconds=s;
            
        }
	public void stopTimer() {
		stop = true;
		pause = true;
	}
	public void pauseTimer() {
		pause = true;
	}
	public void resumeTimer() {
		pause = false;
	}
	
	public void resetTimer() {
		seconds = minutes = hours = 0;
		stop = false;
		pause = false;
	}
	
	public void run() {
		while ( !stop ) {
			while ( !pause ) {
				if ( stop )
					break;
				waitTime( 1000 );
				seconds++;
				if ( seconds == 60 ) {
					minutes++;
					seconds = 0;
					if ( minutes == 60 ) {
						hours++;
						minutes = 0;
					}
				}
				String secondsString, minutesString, hoursString;
				if ( seconds < 10 )
					secondsString = "0" + Integer.toString( seconds );
				else
					secondsString = Integer.toString( seconds );
				if ( minutes < 10 )
					minutesString = "0" + Integer.toString( minutes );
				else
					minutesString = Integer.toString( minutes );
				if ( hours < 10 )
					hoursString = "0" + Integer.toString( hours );
				else
					hoursString = Integer.toString( hours );
				
				timeField.setText( hoursString + ":" + minutesString + ":" + secondsString );
			}
			waitTime( 100 );
		}
	}
	
	/** Causes this thread to sleep for a given amount of time. */
	private void waitTime( int ms ) {
		try {
			Timer.sleep( ms );
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
