package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.ids;

import gles.SoulGL2;
import gles.shaders.ShaderID;
import managers.ShaderManager;

public class GenoShaders {

    public enum GenoShaderID implements ShaderID {
        GENE_CIRCLE,
        TORRENT
    }

    public static void createShaders(SoulGL2 gl) {
        ShaderManager.createProgram(gl, GenoShaderID.TORRENT, "torrent.vert", "torrent.frag");
        ShaderManager.createProgram(gl, GenoShaderID.GENE_CIRCLE, "genecircle.vert", "genecircle.frag");
    }
}
