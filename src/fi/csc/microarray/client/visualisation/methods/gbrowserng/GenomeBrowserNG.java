package fi.csc.microarray.client.visualisation.methods.gbrowserng;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.jogamp.newt.event.NEWTEvent;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.EventHandler;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.GenoWindowListener;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.Keyboard;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.Mouse;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.Camera;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.GenoGLListener;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.GenoWindow;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.SessionView;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud.Hud;

public class GenomeBrowserNG {
	
	BlockingQueue<NEWTEvent> eventQueue;
	Mouse mouse;
	Keyboard keyboard;
	Session session;
	Camera camera;
	SessionView sessionView;
	Hud hud;
	GenoWindowListener windowListener;
	GenoGLListener glListener;
	GenoWindow genoWindow;
	EventHandler eventHandler;
	
	public GenomeBrowserNG(int width, int height) {
		this.eventQueue = new LinkedBlockingQueue<NEWTEvent>();
		this.mouse = new Mouse(eventQueue);
		this.keyboard = new Keyboard(eventQueue);
		this.session = new Session();
		this.camera = new Camera(0.0f, 0.0f, 2.0f);
		this.sessionView = new SessionView(session);
		this.hud = new Hud();
		this.windowListener = new GenoWindowListener(eventQueue);
		this.glListener = new GenoGLListener(camera, hud, sessionView);
		this.genoWindow = new GenoWindow(width, height);
		this.eventHandler = new EventHandler(eventQueue, hud,
				sessionView, genoWindow.window, camera, width, height);

		this.genoWindow.window.addKeyListener(keyboard);
		this.genoWindow.window.addMouseListener(mouse);
		this.genoWindow.window.addGLEventListener(glListener);
		this.genoWindow.window.addWindowListener(windowListener);
	}
	
	public void run() throws InterruptedException {
		this.genoWindow.open();

		this.eventHandler.handleEvents();

		this.genoWindow.close();
	}
	
	public static void main(String[] s) throws InterruptedException {
		new GenomeBrowserNG(800, 600).run();
	}
}
