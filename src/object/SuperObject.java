package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision=false;
	public int worldX, worldY;
	public Rectangle solidArea=new Rectangle(0,0,48,48); //So like Player each object also have this solidArea 
	public int solidAreaDefaultX=0;
	public int solidAreaDefaultY=0;
	
	public void draw(Graphics2D g2,GamePanel gp)
	{
		int screenX=worldX-gp.player.x + gp.player.screenX;
		int screenY=worldY-gp.player.y + gp.player.screenY;
		
		if(worldX + gp.tileSize>gp.player.x-gp.player.screenX && worldX-gp.tileSize<gp.player.x+gp.player.screenX 
				&& worldY+gp.tileSize>gp.player.y-gp.player.screenY && worldY-gp.tileSize<gp.player.y+gp.player.screenY)
		{
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
			
		}
	}
}