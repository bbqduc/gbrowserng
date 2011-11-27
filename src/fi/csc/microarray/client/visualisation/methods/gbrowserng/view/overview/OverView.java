package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.math.Vector2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideHudComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideVisualComponent;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OverView implements GenosideVisualComponent, GenosideHudComponent {

    private int mouseState = 0;

    public boolean handle(MouseEvent event, float x, float y) {

        if(event.getButton() == 1 && mouseState == 0) {
            mouseState = 1;
            Vector2 pos = new Vector2(x, y);
            float angle = (((float)Math.PI * -0.5f) + (float) (Math.atan2(pos.y, pos.x))) / (float)(-Math.PI * 2.0f);
            long position = (long) (info.getTotalLength() * angle);
            this.sessions.add(new SessionDescriptor(position, info));
        }
        else if(event.getButton() != 1) {
            mouseState = 0;
        }

        return false;
    }

    public boolean handle(KeyEvent event) {
        return false;
    }

    public static class GeneInfo {
        ArrayList<Long> chromosomeLengths = new ArrayList<Long>();
        String geneSource = "BogusGene";

        public GeneInfo() {
            chromosomeLengths.add(5000l);
            chromosomeLengths.add(10000l);
            chromosomeLengths.add(100l);
        }

        public long getTotalLength() {
            long total = 0;
            for(Long length : chromosomeLengths)
                total += length;
            return total;
        }
    }

    public static class SessionDescriptor {

        public SessionDescriptor(long genePosition, GeneInfo info) {
            this.genePosition = genePosition;
            this.zoom = 1000.0f;

            Vector2 direction = this.getDirection(info);
            this.screenPositionX = direction.x;
            this.screenPositionY = direction.y;
            this.screenScale = 1.0f;
        }

        public int getCurrentGene(GeneInfo info) {
            long pos = genePosition;
            int chromosome = -1;
            for(Long length : info.chromosomeLengths) {
                pos -= length;
                ++chromosome;
                if(pos < 0) return chromosome;
            }

            // this shouldn't happen
            return -1;
        }

        public Vector2 getDirection(GeneInfo info) {
            long total = info.getTotalLength();
            Vector2 result = new Vector2(0, 1);
            result.rotate((float)(-2.0f * Math.PI * genePosition / total));
            return result;
        }

        public void updateForce(SessionDescriptor sd) {
            float x_diff = this.screenPositionX - sd.screenPositionX;
            float y_diff = this.screenPositionY - sd.screenPositionY;
            float dist = x_diff * x_diff + y_diff * y_diff;

            this.screenForceX += x_diff / (dist + 1.2f) * 0.1f;
            this.screenForceY += y_diff / (dist + 1.2f) * 0.1f;
            sd.screenForceX   -= x_diff / (dist + 1.2f) * 0.1f;
            sd.screenForceY   -= y_diff / (dist + 1.2f) * 0.1f;
        }

        long genePosition;
        float zoom;

        float screenForceX;
        float screenForceY;
        float screenPositionX;
        float screenPositionY;
        float screenScale;

        public void tick(float dt) {
            screenPositionX += screenForceX * dt;
            screenPositionY += screenForceY * dt;
        }
    }

    ConcurrentLinkedQueue<SessionDescriptor> sessions = new ConcurrentLinkedQueue<SessionDescriptor>();
    GeneInfo info = new GeneInfo();


    public void draw(SoulGL2 gl) {
        for(SessionDescriptor descriptor : sessions) {
            PrimitiveRenderer.drawLine(descriptor.screenPositionX, descriptor.screenPositionY, 0.0f, 0.0f, gl, Color.GREEN);
        }

        PrimitiveRenderer.drawCircle(0, 0, 0.40f, gl, Color.MAGENTA);
        PrimitiveRenderer.drawCircle(0, 0, 0.38f, gl, Color.BLACK);

        for(SessionDescriptor descriptor : sessions) {
            PrimitiveRenderer.drawRectangle(descriptor.screenPositionX, descriptor.screenPositionY, 0.2f, 0.1f, gl, Color.CYAN);
        }
    }

    public void tick(float dt) {
         // update forces
        for(SessionDescriptor descriptor : sessions) {
            Vector2 pos = new Vector2(descriptor.screenPositionX, descriptor.screenPositionY);
            float dist = pos.lengthSquared();
            pos.normalize();
            descriptor.screenForceX = descriptor.screenPositionX / dist  - pos.x;
            descriptor.screenForceY = descriptor.screenPositionY / dist - pos.y;
        }
        for(SessionDescriptor descriptor1 : sessions) {
            for(SessionDescriptor descriptor2 : sessions) {
                descriptor1.updateForce(descriptor2);
            }
        }
        for(SessionDescriptor descriptor : sessions) {
            descriptor.tick(dt);
        }
    }

}
