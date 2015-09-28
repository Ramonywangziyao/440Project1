import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;



public class Maze {
	public static void main(String [] args) throws IOException, InterruptedException
	{
		new Maze();
	}
	public Maze() throws IOException, InterruptedException
	{
		JFrame f = new JFrame();
		ArrayList<Node> solution = new ArrayList<Node>();
		MazeBoard mm = new MazeBoard();
		f.setTitle("Maze Ghost");
		f.add(mm);
		f.setSize(mm.colCount*10, mm.rowCount*10+20);
		f.setBackground(Color.WHITE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//1.1  
		boolean [][] trace = new boolean[mm.rowCount][mm.colCount];
		int stepsTotal = 0;
		int totalNode = 0;
		
		
		//******DFS
		//mm.depthFirst(solution, mm.theMaze, mm.sN.getX(), mm.sN.getY(), trace, mm.colCount, mm.rowCount, stepsTotal, totalNode, mm.fN);
		
		
		//******BFS
		//mm.breadth(mm.theMaze,mm.sN, mm.colCount, mm.rowCount);
		
		
		
		//******Greedy
		//mm.greedyBest(mm.theMaze, mm.sN, mm.fN, mm.colCount, mm.rowCount);
		
		//*******choose A*
		//mm.aStar(mm.theMaze, mm.sN, mm.fN);
		
		
		//1.2
		mm.aStarTurnCostManhatton(mm.theMaze, mm.sN, mm.fN, 2);
		
		//mm.aStarTurnCostOurHeuristic(mm.theMaze, mm.sN, mm.fN, 1);
		//*******A* with Ghost 1.3
		//mm.aStarwGhost(mm.theMaze, mm.sN,mm.fN);
		
		//mm.repaint();
	}
}
