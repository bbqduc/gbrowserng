package fi.csc.microarray.client.visualisation.methods.gbrowserng.controller;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.NEWTEvent;

public class GenoEvent {

	public NEWTEvent event;
	public int screenHalfWidth;
	public int screenHalfHeight;

	public GenoEvent(int width, int height)
	{
		setScreenSize(width, height);
	}
	
	public void setScreenSize(int width, int height) {
		this.screenHalfWidth = width / 2;
		this.screenHalfHeight = height / 2;
	}

	public float getMouseGLX() {
		return -(this.screenHalfWidth - (float) ((MouseEvent) event).getX())
				/ this.screenHalfWidth;
	}

	public float getMouseGLY() {
		return (this.screenHalfHeight - (float) ((MouseEvent) event).getY())
				/ this.screenHalfHeight;
	}
}
