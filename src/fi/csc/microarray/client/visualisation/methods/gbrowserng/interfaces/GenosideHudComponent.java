package fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

public interface GenosideHudComponent {
    public boolean handle(MouseEvent event, float screen_x, float screen_y);
    public boolean handle(KeyEvent event);
}
