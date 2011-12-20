package fi.csc.microarray.client.visualisation.methods.gbrowserng.controller;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.NEWTEvent;

import java.util.concurrent.BlockingQueue;

public class Keyboard implements KeyListener {
	private BlockingQueue<NEWTEvent> eventQueue;

	public Keyboard(BlockingQueue<NEWTEvent> eventQueue) {
		this.eventQueue = eventQueue;
	}

	public void keyPressed(KeyEvent keyEvent) {
		try {
			this.eventQueue.put(keyEvent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void keyReleased(KeyEvent keyEvent) {
		try {
			this.eventQueue.put(keyEvent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void keyTyped(KeyEvent keyEvent) {
		try {
			this.eventQueue.put(keyEvent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
