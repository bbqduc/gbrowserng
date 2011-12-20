package fi.csc.microarray.client.visualisation.methods.gbrowserng;

import com.soulaim.tech.gles.Color;

public class GlobalVariables {
    public static float aspectRatio = 1.0f;

    // TODO: Lots of if statements could be replaced by one memory look-up.
    public static Color genomeColor(char c) {
		if (c == 'A')
			return Color.BLUE;
		else if (c == 'G')
			return Color.CYAN;
		else if (c == 'C')
			return Color.ORANGE;
		else
			// (c == 'T')
			return Color.MAGENTA;
    }
}
