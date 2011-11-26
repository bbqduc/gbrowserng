package fi.csc.microarray.client.visualisation.methods.gbrowserng.view;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import javax.media.opengl.GL2;

public class TextureHandler
{
	private static TreeMap<String, Texture> textures = new TreeMap<String, Texture>();
	
	public static void loadTexture(String texName, String fileName)
	{
		try
		{
			Texture t = TextureIO.newTexture(new File(fileName), false);
			textures.put(texName, t);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void bindTexture(GL2 gl, String s)
	{
		textures.get(s).bind(gl);
	}
}
