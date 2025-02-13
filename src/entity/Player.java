package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey=0;
	
	public Player(GamePanel gp,KeyHandler keyH)
	{
		this.gp=gp;
		this.keyH=keyH;
	
		
		screenX=gp.screenwidth/2 - (gp.tileSize/2);
		screenY=gp.screenheight/2 - (gp.tileSize/2);
		
		solidArea=new Rectangle(8,16,32,32);
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues()
	{
		x=gp.tileSize*23;
		y=gp.tileSize*21;
		speed=4;
		direction="down";
	}
	public void getPlayerImage()
	{
		try {
			
			up1=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void update()
	{
		if(keyH.up==true||keyH.down==true||keyH.left==true||keyH.right==true)
		{
			if(keyH.up==true)
			{
				direction="up";
				
			}if(keyH.down==true)
			{
				direction="down";
				
			}
			if(keyH.right==true)
			{
				direction="right";
				
			}
			if(keyH.left==true)
			{
				direction="left";
				
			}
			
			collisionOn=false;
			gp.cChecker.checkTile(this);
			//Check object collision
			int objIndex=gp.cChecker.checkObject(this, true); //based on this index number we will decide what will happen if player catches object
			pickUpObject(objIndex);
			//if Collision is False ,Player can move
			
			if(collisionOn==false)
			{
				switch(direction)
				{
				case "up":
					y-=speed;
					break;
					
				case "down":
					y+=speed;
					break;
					
				case "left":
					x-=speed;
					break;
				
				case "right":
					x+=speed;
					break;
				}
			}
			
			spriteCounter++;  //update gets called 100 times per second so every time it increases the counter and as soon as 10 frames are reached player image will be changed
			if(spriteCounter>15)
			{
				if(spriteNum==1)
				{
					spriteNum=2;
				}
				else if(spriteNum==2)
				{
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
		
		
	}
	
	public void pickUpObject(int i)
	{
		if(i!=999)
		{
			String objectName=gp.obj[i].name;
			switch(objectName)
			{
			case "key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i]=null;
				gp.ui.showMessage("Crazy dude you got a key!Open those doors");
				break;
			case "door":
				gp.playSE(3);
				if(hasKey==0)
				{
					gp.ui.showMessage("HAHAHA no entry!!Loner");
				}
				if(hasKey>0)
				{
					gp.obj[i]=null;
					hasKey--;
					gp.ui.showMessage("Oh somebody brought the key");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed+=2;
				gp.obj[i]=null;
				gp.ui.showMessage("Flash mode on!!");
				break;
			case "chest":
				gp.ui.gamefinish=true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			case "squirtle":
				gp.playSE(5);
				gp.ui.showMessage("Hey Kid Moving so fast huh!! I am squirtle!!Splash");
				speed=2;
				gp.obj[i]=null;
				break;
			}
		}
	}
	public void draw(Graphics2D g2)
	{

		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image=null;
		switch(direction)
		{
		case "up":
			if(spriteNum==1)
			{
				image=up1;
			}
			if(spriteNum==2)
			{
				image=up2;
			}
			break;
		case "down":
			if(spriteNum==1)
			{
				image=down1;
			}
			if(spriteNum==2)
			{
				image=down2;
			}
			break;
		case "left":
			if(spriteNum==1)
			{
				image=left1;
			}
			if(spriteNum==2)
			{
				image=left2;
			}
			break;
		case "right":
			if(spriteNum==1)
			{
				image=right1;
			}
			if(spriteNum==2)
			{
				image=right2;
			}
			break;
		}
		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);//null is image observer here
	}
}
