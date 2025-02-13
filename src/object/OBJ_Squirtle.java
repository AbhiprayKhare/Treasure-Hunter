package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Squirtle extends SuperObject{
	public OBJ_Squirtle()
	{
		name="squirtle";
		try
		{
			image=ImageIO.read(getClass().getResourceAsStream("/objects/Squirtle.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}