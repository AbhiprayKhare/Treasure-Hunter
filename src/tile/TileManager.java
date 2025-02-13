package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager 
{
	GamePanel gp;
	public Tile[] tile;
	
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp=gp;
		tile=new Tile[10];
		mapTileNum=new int[gp.maxworldCol][gp.maxworldRow];
		getTileImage();
		loadMap("/maps/gamemap.txt");
	}
	public void loadMap(String Filepath)
	{
		try {
			InputStream is=getClass().getResourceAsStream(Filepath);
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			
			int col=0;
			int row=0;
			while(col<gp.maxworldCol && row<gp.maxworldRow)
			{
				String line=br.readLine();
				while(col<gp.maxworldCol)
				{
					String numbers[]=line.split(" ");
					int num=Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row]=num;
					col++;
				}
				if(col==gp.maxworldCol)
				{
					col=0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getTileImage()
	{
		try {
			tile[0]=new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			

			tile[1]=new Tile();
			tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision=true;
			

			tile[2]=new Tile();
			tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision=true;
			
			tile[3]=new Tile();
			tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4]=new Tile();
			tile[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision=true;
			
			tile[5]=new Tile();
			tile[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2)
	{	
		int worldCol=0;
		int worldRow=0;
		while(worldCol<gp.maxworldCol && worldRow<gp.maxworldRow)
		{
			
			int tileNum=mapTileNum[worldCol][worldRow];
			
			int worldX=worldCol*gp.tileSize;
			int worldY=worldRow*gp.tileSize;
			int screenX=worldX-gp.player.x + gp.player.screenX;
			int screenY=worldY-gp.player.y + gp.player.screenY;
			
			if(worldX + gp.tileSize>gp.player.x-gp.player.screenX && worldX-gp.tileSize<gp.player.x+gp.player.screenX 
					&& worldY+gp.tileSize>gp.player.y-gp.player.screenY && worldY-gp.tileSize<gp.player.y+gp.player.screenY)
			{
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
				
			}
			
			//g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
			worldCol++;
			if(worldCol==gp.maxworldCol)
			{
				worldCol=0;
				worldRow++;
			}
		}
	}
}
