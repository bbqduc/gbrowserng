package fi.csc.microarray.client.visualisation.methods.gbrowserng.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.NEWTEvent;
import com.jogamp.newt.event.WindowEvent;

public class EventHandler {

	private BlockingQueue<NEWTEvent> eventQueue = new ArrayBlockingQueue<NEWTEvent>(10);
	private GenoEvent genoEvent;

	public EventHandler() {
	}

	public void toggleFullscreen() {
	}

	public void handleEvents() throws InterruptedException {
		for (;;) {
			NEWTEvent event = this.eventQueue.take();
			this.genoEvent.event = event;

			if (event instanceof KeyEvent) {
				KeyEvent keyEvent = (KeyEvent) event;
				if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE) {
					return;
				} else if (keyEvent.getKeyChar() == 'f') {
					toggleFullscreen();
				}
			} else if (event instanceof WindowEvent) {
			}
		}
	}
}
