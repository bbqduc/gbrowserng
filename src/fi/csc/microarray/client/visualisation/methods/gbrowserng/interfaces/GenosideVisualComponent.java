package fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces;

import com.soulaim.tech.gles.SoulGL2;

public interface GenosideVisualComponent {
    public void draw(SoulGL2 gl);
    public void tick(float dt);
}
