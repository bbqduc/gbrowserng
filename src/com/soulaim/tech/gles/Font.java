package com.soulaim.tech.gles;

public class Font {

    private static float[] charWidths;

    static {
        charWidths = new float[256];
        for (int i = 0; i < charWidths.length; ++i) {
            charWidths[i] = 1.0f;
        }
        
        for(char symbol = 'A'; symbol <= 'Z'; ++symbol) {
            charWidths[symbol] = 0.22f;
        }
        for(char symbol = 'a'; symbol <= 'z'; ++symbol) {
            charWidths[symbol] = 0.19f;
        }
        for(char symbol = '0'; symbol <= '9'; ++symbol) {
            charWidths[symbol] = 0.16f;
        }

        charWidths['9'] = 0.20f;
        charWidths['8'] = 0.20f;
        charWidths['4'] = 0.20f;
        charWidths['0'] = 0.23f;
        charWidths['l'] = 0.1f;
        charWidths['r'] = 0.14f;
        charWidths['f'] = 0.12f;
        charWidths['!'] = 0.1f;
        charWidths['t'] = 0.15f;
        charWidths['>'] = 0.15f;
        charWidths['<'] = 0.15f;
        charWidths['i'] = 0.1f;
        charWidths['w'] = 0.26f;
        charWidths['m'] = 0.26f;
        charWidths['j'] = 0.12f;
        charWidths['o'] = 0.19f;
        charWidths['s'] = 0.13f;
        charWidths['I'] = 0.1f;
        charWidths['J'] = 0.12f;
        charWidths['.'] = 0.09f;
        charWidths[','] = 0.1f;
        charWidths[':'] = 0.1f;
        charWidths['?'] = 0.15f;
        charWidths[' '] = 0.1f;
        charWidths[']'] = 0.1f;
        charWidths['['] = 0.1f;
        charWidths[')'] = 0.1f;
        charWidths['('] = 0.1f;
        charWidths['\''] = 0.1f;
        charWidths['-'] = 0.1f;
        charWidths['+'] = 0.1f;
        charWidths['|'] = 0.1f;
        charWidths['/'] = 0.1f;
        charWidths['_'] = 0.1f;
        charWidths['%'] = 0.13f;
        charWidths['&'] = 0.12f;
        charWidths['='] = 0.13f;
        charWidths['"'] = 0.1f;

        charWidths['S'] = 0.17f;
        charWidths['T'] = 0.20f;
        charWidths['W'] = 0.24f;
        charWidths['Q'] = 0.24f;
        charWidths['O'] = 0.3f;
        charWidths['Z'] = 0.1f;
        charWidths['M'] = 0.3f;

        charWidths['n'] = 0.20f;
        charWidths['e'] = 0.20f;
        charWidths['h'] = 0.20f;
        charWidths['p'] = 0.20f;
    }

    public static float width(char c) {
        return charWidths[c]  * 2.6f;
    }

    public static float width(String text) {
        float sum = 0.0f;
        for(int i = 0; i < text.length(); ++i) {
            sum += width(text.charAt(i));
        }
        return sum;
    }

    public static int getRow(char c) {
        return (c - 1) / 16;
    }

    public static int getColumn(char c) {
        return (c - 1) % 16;
    }

    public static int getColumns() {
        return 16;
    }

    public static int getRows() {
        return 16;
    }
}
