package main;

import object.OBJ_Boots;
import object.OBJ_Squirtle;
import object.OBJ_door;
import object.OBJ_key;
import object.chest;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp)
	{
		this.gp=gp;
	}
	public void setObject()
	{
		gp.obj[0]=new OBJ_key();
		gp.obj[0].worldX=23*gp.tileSize;
		gp.obj[0].worldY=7*gp.tileSize;
		
		gp.obj[1]=new OBJ_key();
		gp.obj[1].worldX=23*gp.tileSize;
		gp.obj[1].worldY=40*gp.tileSize;
		
		gp.obj[2]=new OBJ_door();
		gp.obj[2].worldX=37*gp.tileSize;
		gp.obj[2].worldY=7*gp.tileSize;
		
		gp.obj[3]=new OBJ_door();
		gp.obj[3].worldX=10*gp.tileSize;
		gp.obj[3].worldY=11*gp.tileSize;
		
		gp.obj[4]=new OBJ_door();
		gp.obj[4].worldX=12*gp.tileSize;
		gp.obj[4].worldY=22*gp.tileSize;
		
		gp.obj[5]=new chest();
		gp.obj[5].worldX=10*gp.tileSize;
		gp.obj[5].worldY=7*gp.tileSize;
		
		gp.obj[6]=new OBJ_key();
		gp.obj[6].worldX=36*gp.tileSize;
		gp.obj[6].worldY=30*gp.tileSize;
		
		gp.obj[7]=new OBJ_Boots();
		gp.obj[7].worldX=37*gp.tileSize;
		gp.obj[7].worldY=42*gp.tileSize;
		
		gp.obj[8]=new OBJ_Squirtle();
		gp.obj[8].worldX=37*gp.tileSize;
		gp.obj[8].worldY=40*gp.tileSize;
		
		gp.obj[9]=new OBJ_Squirtle();
		gp.obj[9].worldX=19*gp.tileSize;
		gp.obj[9].worldY=21*gp.tileSize;
		
		
	}
}
