package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class chest extends SuperObject{

	public chest()
	{
		name="chest";
		try
		{
			image=ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
