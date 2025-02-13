package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp)
	{
		this.gp=gp;
	}
	public void checkTile(Entity entity) //ye keval check karke batata hai ki collision ho raha hai ki nahi aur collisionOn ki value change kar deta hai ussi ke hisab se hum player class mei player ki movement control karte hai
	{
		int entityLeftWorldX=entity.x +entity.solidArea.x;
		int entityRightWorldX=entity.x+entity.solidArea.x+entity.solidArea.width;
		int entityTopWorldY=entity.y+entity.solidArea.y;
		int entityBottomWorldY=entity.y+entity.solidArea.y+entity.solidArea.height;
		
		int entityLeftCol=entityLeftWorldX/gp.tileSize;
		int entityRightCol=entityRightWorldX/gp.tileSize;
		
		int entityTopRow=entityTopWorldY/gp.tileSize;
		int entityBottomRow=entityBottomWorldY/gp.tileSize;
		
		int tileNum1,tileNum2;
		
		switch(entity.direction)
		{
		case "up":
			
			entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;//to predict where player will be after he moves so we subtract this player speed from player's solidArea world Y
			tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
			{
				entity.collisionOn=true;
			}
			break;

		case "down":
			entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;//to predict where player will be after he moves so we subtract this player speed from player's solidArea world Y
			tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
			{
				entity.collisionOn=true;
			}
			break;
			
		case "left":
			entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;//to predict where player will be after he moves so we subtract this player speed from player's solidArea world Y
			tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
			{
				entity.collisionOn=true;
			}
			break;

		case "right":
			entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;//to predict where player will be after he moves so we subtract this player speed from player's solidArea world Y
			tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
			{
				entity.collisionOn=true;
			}
			break;
		}
	}
	public int checkObject(Entity entity,boolean Player) //we check if player is hitting any object and if he is we return the index of the object
	{
		int index=999;
		for(int i=0;i<gp.obj.length;i++)
		{
			//we scan object array and check if its null or not if not null then we need to know entity's solid area ,position and also we need to get the object's solid area,position
			if(gp.obj[i]!=null)
			{
				entity.solidArea.x=entity.x+entity.solidArea.x;
				entity.solidArea.y=entity.y+entity.solidArea.y;
				
				//get objecct's solid area position
				gp.obj[i].solidArea.x=gp.obj[i].worldX+gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y=gp.obj[i].worldY+gp.obj[i].solidArea.y;
				
				switch(entity.direction)
				{
				case "up":
					entity.solidArea.y-=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea))//checks if these 2 rectangles are colliding or intersecting or not we did not use intersect before because then we would have to check for each and every tile using this intersect which would take time while currently we are scanning only 2 tiles of each direction
					{
						if(gp.obj[i].collision==true)
						{
							entity.collisionOn=true;
						}
						if(Player==true) //If player is colliding then he will take key NPC and monster cannot do that
						{
							index=i;
						}
					}
					break;
				case "down":
					entity.solidArea.y+=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea))//checks if these 2 rectangles are colliding or intersecting or not
					{
						if(gp.obj[i].collision==true)
						{
							entity.collisionOn=true;
						}
						if(Player==true) //If player is colliding then he will take key NPC and monster cannot do that
						{
							index=i;
						}
					}
					break;
				case "left":
					entity.solidArea.x-=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea))//checks if these 2 rectangles are colliding or intersecting or not
					{
						if(gp.obj[i].collision==true)
						{
							entity.collisionOn=true;
						}
						if(Player==true) //If player is colliding then he will take key NPC and monster cannot do that
						{
							index=i;
						}
					}
					break;
				case "right":
					entity.solidArea.x+=entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea))//checks if these 2 rectangles are colliding or intersecting or not
					{
						if(gp.obj[i].collision==true)
						{
							entity.collisionOn=true;
						}
						if(Player==true) //If player is colliding then he will take key NPC and monster cannot do that
						{
							index=i;
						}
					}
					break;
				}
				entity.solidArea.x=entity.solidAreaDefaultX;
				entity.solidArea.y=entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}
