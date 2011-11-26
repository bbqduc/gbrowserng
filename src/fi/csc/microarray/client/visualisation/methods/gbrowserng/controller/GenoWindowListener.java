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

	@Override
	public void windowDestroyNotify(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDestroyed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowMoved(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowRepaint(WindowUpdateEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowResized(WindowEvent e) {
		try {
			this.eventQueue.put(e);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
