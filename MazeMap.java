import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JPanel;


public class MazeMap extends JPanel implements ActionListener {

	int Width;
	int Height;
	Image wallI;
	Image pacI;
	Image goalI;
	Map m;
	Timer t;
	char[][] theMaze;
	@Override
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
	}
	public MazeMap() throws IOException
	{
		m = new Map();
		Height = m.count;
		Width = m.size;
		theMaze = m.getMap();
		
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		for(int k =0;k<Width;k++)
		{
			for(int j = 0;j<Height;j++)
			{
				System.out.print(theMaze[k][j]);
			}
			System.out.println();
		}
		for(int i = 0; i<Height;i++)
		{
			for(int j = 0; j<Width;j++)
			{
				if(theMaze[i][j] == '%')
				{
					g.drawImage(wallI,j*50,i*50,null);
				}
				if(theMaze[i][j] == 'P')
				{
					g.drawImage(pacI,j*50,i*50,null);
				}

				if(theMaze[i][j] == '.')
				{
					g.drawImage(goalI,j*50,i*50,null);
				}
			}
		}
	}
}
