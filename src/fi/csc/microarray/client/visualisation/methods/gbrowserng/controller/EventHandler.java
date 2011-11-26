package fi.csc.microarray.client.visualisation.methods.gbrowserng.controller;

import java.util.concurrent.BlockingQueue;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.NEWTEvent;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.Camera;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.SessionView;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud.Hud;

public class EventHandler {

	private BlockingQueue<NEWTEvent> eventQueue;
	private GenoEvent genoEvent;

	private Hud hud;
	private SessionView worldView;
	private GLWindow glWindow;
	private Camera camera;

	public EventHandler(BlockingQueue<NEWTEvent> eventQueue, Hud hud,
			SessionView worldView, GLWindow glWindow, Camera camera, int width,
			int height) {
		this.eventQueue = eventQueue;
		this.hud = hud;
		this.worldView = worldView;
		this.glWindow = glWindow;
		this.camera = camera;
		this.genoEvent = new GenoEvent(width, height);
	}

	public void toggleFullscreen() {
		if (this.glWindow.isFullscreen()) {
			this.glWindow.setFullscreen(false);
		} else {
			this.glWindow.setFullscreen(true);
		}
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
				this.genoEvent.setScreenSize(this.glWindow.getWidth(),
						this.glWindow.getHeight());

			}
			this.camera.update(this.genoEvent);
			this.hud.update(this.genoEvent);
			this.worldView.update(this.genoEvent);
		}
	}
}
