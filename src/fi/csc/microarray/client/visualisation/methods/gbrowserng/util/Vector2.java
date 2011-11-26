package fi.csc.microarray.client.visualisation.methods.gbrowserng.util;


public class Vector2
{
	public float x;
	public float y;
	
	public Vector2()
	{
		x = y = 0.0f;
	}
	
	public Vector2(float sx, float sy)
	{
		x = sx;
		y = sy;
	}
	
	public void rotate(float rad, Vector2 working_point)
	{
		/*
		cos a,  -sin a    x
		sin a,   cos a    y
		*/
		
		working_point.x = (float) (x * Math.cos(rad) - y * Math.sin(rad));
		working_point.y = (float) (x * Math.sin(rad) + y * Math.cos(rad));
	}

	public void add(Vector2 v)
	{
		x += v.x;
		y += v.y;
	}

	public float sqrLength()
	{
		return x * x + y * y;
	}

	public void copy(Vector2 a)
	{
		x = a.x;
		y = a.y;
	}
}
