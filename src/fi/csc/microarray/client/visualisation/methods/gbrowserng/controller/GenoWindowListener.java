package fi.csc.microarray.client.visualisation.methods.gbrowserng.controller;

import java.util.concurrent.BlockingQueue;

import com.jogamp.newt.event.NEWTEvent;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;

public class GenoWindowListener implements WindowListener {
	
	private BlockingQueue<NEWTEvent> eventQueue;
	
	public GenoWindowListener(BlockingQueue<NEWTEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
	}

	public void windowDestroyNotify(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDestroyed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowGainedFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowLostFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowMoved(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowRepaint(WindowUpdateEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowResized(WindowEvent e) {
		try {
			this.eventQueue.put(e);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
