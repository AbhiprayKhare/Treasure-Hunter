package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_key extends SuperObject{
	public OBJ_key()
	{
		name="key";
		try
		{
			image=ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
