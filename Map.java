import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;


public class Map {
	private Scanner m;
	char [][] maze;
	Image wall,
	pacman,
	walekdNode,
	walkedPath,goal;
	int size;
	int count;
	public Map() throws IOException
	{

		ImageIcon wallImg = new ImageIcon("/User/ziyaowang/Documents/workspace/440Project1/src/wall.png");
		wall = wallImg.getImage();
		ImageIcon pacImg = new ImageIcon("/User/ziyaowang/Documents/workspace/440Project1/src/position.png");
		pacman = pacImg.getImage();
		ImageIcon nodeImg = new ImageIcon("/User/ziyaowang/Documents/workspace/440Project1/src/walkedNode.png");
		walekdNode = nodeImg.getImage();
		ImageIcon pathImg = new ImageIcon("/User/ziyaowang/Documents/workspace/440Project1/src/walkedPath.png");
		walkedPath = pathImg.getImage();
		ImageIcon goalImg = new ImageIcon("/User/ziyaowang/Documents/workspace/440Project1/src/goal.png");
		goal = goalImg.getImage();
		openFile();
		readFile();
		closeFile();
	}
	public char[][] getMap()
	{
		return maze;
	}
	public void print()
	{
		for(int k =0;k<count;k++)
		{	
			for(int j = 0;j<size;j++)
			{
				System.out.print(maze[k][j]);
			}
			System.out.println();
		}

	}


	public void openFile()
	{
		try{
		m = new Scanner(new File("/Users/ziyaowang/Documents/workspace/440Project1/src/mediumMaze.txt"));
		}
		catch(Exception e)
		{
			
		}
	}
	public void readFile() throws IOException
	{
		
		String fileName = "/Users/ziyaowang/Documents/workspace/440Project1/src/mediumMaze.txt";
		
		FileReader fr = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader (fr);

		char [][] maze = null;
		String line;
		int x = 0;
		size = 0;
		count = 0;
		while((line = buffer.readLine())!=null)
		{
	
			count++;
		}
		System.out.println(count);
		fr.close();
		FileReader fr2 = new FileReader(fileName);
		BufferedReader bufferNew = new BufferedReader (fr2);
		while((line = bufferNew.readLine())!=null)
		{
			System.out.println("Entered!");
			char [] vals = line.toCharArray();
            if (maze == null) {
                size = vals.length;
          //      System.out.println("total columns: "+size);
                maze = new char[count][size];
            }
  
            for (int col = 0; col < size; col++) {
            	
                maze[x][col] = vals[col];
                System.out.print(maze[x][col]);
            }

            x++;
            System.out.println();
		}
		
	}
	public void closeFile()
	{
		m.close();
	}
}
