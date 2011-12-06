package fi.csc.microarray.client.visualisation.methods.gbrowserng.controller;

import java.util.concurrent.BlockingQueue;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.NEWTEvent;
import com.jogamp.newt.event.WindowEvent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideHudComponent;

public class EventHandler {

	private BlockingQueue<NEWTEvent> eventQueue = null;
	private GenoEvent genoEvent = new GenoEvent(800, 600);

    private final GenosideHudComponent client;

	public EventHandler(GenosideHudComponent client, BlockingQueue<NEWTEvent> eventQueue) {
	    this.client = client;
        this.eventQueue = eventQueue;
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
			} else if(genoEvent.event instanceof MouseEvent)  {
                client.handle((MouseEvent)(genoEvent.event), genoEvent.getMouseGLX(), genoEvent.getMouseGLY());
            } else if (event instanceof WindowEvent) {
			}
		}
	}
}
