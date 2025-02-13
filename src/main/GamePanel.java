package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int tileSize=48;
	public final int maxcol=16;
	public final int maxrow=12;
	public final int screenwidth=tileSize*maxcol;
	public final int screenheight=tileSize*maxrow;
	
	
	
	//world Settings
	
	public final int maxworldCol=50;
	public final int maxworldRow=50;
	
	
	int fps=75;
	TileManager tileM=new TileManager(this);
	KeyHandler keyH=new KeyHandler();
	Sound sound=new Sound();
	
	Thread gameThread;
	public CollisionChecker cChecker=new CollisionChecker(this);
	public AssetSetter aSetter=new AssetSetter(this);
	public UI ui=new UI(this);
	
	public Player player=new Player(this,keyH);
	public SuperObject obj[]=new SuperObject[10];
	
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenwidth,screenheight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);

	}
	public void setupGame()
	{
		aSetter.setObject();
		playMusic(0);
	}
	public void startGameThread()
	{
		gameThread=new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() { //game loop
		
		double drawinterval=1000000000/fps;//0.01666 seconds
		double nextdrawinterval=System.nanoTime() + drawinterval;
		
		
		while(gameThread!=null)
		{
			update();
			
			repaint();
			
			try
			{
				double remainingtime=nextdrawinterval -System.nanoTime();
				remainingtime=remainingtime/500000; //converting nano time to milli time
				if(remainingtime<0)
				{
					remainingtime=0;
				}
				Thread.sleep((long)remainingtime);
				
				nextdrawinterval+=drawinterval;
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	public void update()
	{
		player.update();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		//Tile
		tileM.draw(g2);
		
		//Object
		for(int i=0;i<obj.length;i++)
		{
			if(obj[i]!=null)
			{
				obj[i].draw(g2,this);
			}
		}
		
		//Player
		player.draw(g2);
		
		//UI
		ui.draw(g2);
		
		g2.dispose();
	}
	public void playMusic(int i)
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic()
	{
		sound.stop();
	}
	public void playSE(int i)
	{
		sound.setFile(i);
		sound.play();
	}
}