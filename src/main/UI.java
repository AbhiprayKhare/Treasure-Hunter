package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_key;

public class UI {

	GamePanel gp;
	Font arial_40,arial_80b;
	BufferedImage keyimage;
	public boolean messageOn=false;
	public String message="";
	int messagecounter=0;
	public boolean gamefinish=false;
	
	
	public double playtime;
	DecimalFormat dformat=new DecimalFormat("0.00");//to display up to 2 places of decimal
	
	public UI(GamePanel gp)
	{
		this.gp=gp;
		arial_40=new Font("Arial",Font.PLAIN,30);
		arial_80b=new Font("Arial",Font.BOLD,80);
		OBJ_key key=new OBJ_key();
		keyimage=key.image;	
	}
	public void showMessage(String text)
	{
		message=text;
		messageOn=true;
		
	}
	public void draw(Graphics2D g2)
	{
		if(gamefinish==true)
		{

			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			String text;
			int textlength;
			int x;
			int y;
			text="You found my treasure. You are rich now";
			textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x=gp.screenwidth/2-textlength/2;
			y=gp.screenheight/2-(gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text="You took "+dformat.format(playtime)+" to find my treasure!!!";
			textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x=gp.screenwidth/2-textlength/2;
			y=gp.screenheight/2+(gp.tileSize*4);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80b);
			g2.setColor(Color.RED);
			text="Congratulations";
			textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x=gp.screenwidth/2-textlength/2;
			y=gp.screenheight/2+(gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread=null;
			
		}
		else
		{
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			g2.drawImage(keyimage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize,null);
			g2.drawString("x ="+gp.player.hasKey, 74, 65); //when you use drawString then x and y don't function normally and show baseline
			//Time
			playtime+=(double)1/75;
			g2.drawString("Time: "+dformat.format(playtime), gp.tileSize*11, 65);
			
			
			//Message
			if(messageOn==true)
			{
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*3);
				messagecounter++;
				if(messagecounter>150) //75 frames per second so text will disappear after 2 seconds
				{
					messagecounter=0;
					messageOn=false;
				}
			}
		}
	}
	
}
