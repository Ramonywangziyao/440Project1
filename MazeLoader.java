import java.util.*;
import java.io.*;

public class MazeLoader {
	//initialize variables here
	
	
	boolean [][] trace;
	
	//building maze : scan file and build the maze 
/*	public static char [][] theMaze (String filename) throws Exception {
		char [][] maze = null;
		
		InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
		BufferedReader buffer = new BufferedReader (new InputStreamReader(stream));
		
		
		String line;
		int x = 0;
		int size = 0;
		
		while((line = buffer.readLine())!=null)
		{
			char [] vals = line.toCharArray();
            if (maze == null) {
                size = vals.length;
                maze = new char[size][size];
            }

            for (int col = 0; col < size; col++) {
                maze[x][col] = vals[col];
            }

            x++;
		}
		trace = new boolean[maze.Height][maze.Width];  //give Maze size to it
		return maze;
		
	}
	*/
	//run the maze
	public static void main(String [] args) throws IOException, InterruptedException
	{
		String fileName;
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter the file name");
		fileName = scanner.next();
		scanner.close();
		FileReader fr = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader (fr);

		char [][] maze = null;
		String line;
		int x = 0;
		int size = 0;
		int count = 0;
		while((line = buffer.readLine())!=null)
		{
	
			count++;
		}
		fr.close();
		System.out.println(count);
		FileReader fr2 = new FileReader(fileName);
		BufferedReader bufferNew = new BufferedReader (fr2);
		while((line = bufferNew.readLine())!=null)
		{
		
			char [] vals = line.toCharArray();
            if (maze == null) {
                size = vals.length;
                System.out.println("total columns: "+size);
                maze = new char[count][size];
            }

            for (int col = 0; col < size; col++) {
                maze[x][col] = vals[col];
                System.out.print(maze[x][col]);
            }

            x++;
            System.out.println();
		}
		
		int [] startPacman = searchForPacman(maze,size,count);
		int [] goalofPacman = searchForGoal(maze,size,count);
		Node startNode = new Node();
		Node endNode = new Node();
		System.out.println(startPacman[0]+" "+startPacman[1]);
		startNode.setX(startPacman[0]);
		startNode.setY(startPacman[1]);
		endNode.setX(goalofPacman[0]);
		endNode.setY(goalofPacman[1]);
		System.out.println("AlgoCount: "+count);
	//	int result = depthFirst(maze,size,count,startNode.getX(),startNode.getY());
		//int result = breadth(maze,startNode,size,count);
		//int result = greedyBest(maze,startNode,endNode,size,count);
		//System.out.println("\n"+result);
		for(int i =0;i<count;i++)
		{
			for(int j = 0;j<size;j++)
			{
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		//count height
		
		
		//give Maze size to trace
		//trace = new boolean[maze.Height][maze.Width];  
		//if ()select which method to use
		
		aStar(maze,startNode,endNode);
		
		
	}
	public static int[] searchForPacman(char[][]mazeContext, int width, int height)
	{
		
		for(int i = 0; i<height;i++)
		{
			for(int j = 0; j<width;j++)
			{
				if(mazeContext[i][j] == 'P')
				{
					int[] pacmanPosition = new int[2];
					pacmanPosition[0] = i;
					pacmanPosition[1] = j;
				
					return pacmanPosition;
				}
			}
		}
		
		
		return null;
		
	}
	
	public static int[] searchForGoal(char[][]mazeContext, int width, int height)
	{
		
		
		for(int i = 0; i<height;i++)
		{
			for(int j = 0; j<width;j++)
			{
				if(mazeContext[i][j] == '.')
				{
					int[] goalPosition = new int[2];
					goalPosition[0] = i;
					goalPosition[1] = j;
					return goalPosition;
				}
			}
		}
		
		
		return null;
		
	}
	
	
	//algorithms  already known the position of pacman as x,y
	public static int depthFirst(char[][]mazeContext, int width, int height, int x,int y)
	{
		//baseCase
		if(x<0 || x>width-1 || y>height-1 || y <0 || mazeContext[x][y]=='.')
		{	
			return 0;
		}
	
		if(mazeContext[x][y]=='%' || mazeContext[x][y] == '.')
		{
			return 0;
		}
	
		//recursive	
		
		if(depthFirst(mazeContext,width,height,x,y+1)==1)
		{
			//trace[x][y] = true;
			mazeContext[x][y] = '.';
			
			return 1;
		}
		if(depthFirst(mazeContext,width,height,x,y-1)==1)
		{
			//trace[x][y] = true;
			mazeContext[x][y] = '.';
			
			return 1;
		}
		if(depthFirst(mazeContext,width,height,x+1,y)==1)
		{
			//trace[x][y] = true;
			mazeContext[x][y] = '.';
			
			return 1;
		}
		if(depthFirst(mazeContext,width,height,x-1,y)==1)
		{
		//	trace[x][y] = true;
			mazeContext[x][y] = '.';
			
			return 1;
		}
			
			
	
		return 0;

		
		
		
		
	}
	
	public static int breadth(char[][]mazeContext, Node newNode, int Height, int Width) throws InterruptedException
	{
		//baseCase
		int numberOfSteps = 0;
		int nodeExpanded = 0;
		//System.out.println(Height);
		Queue<Node> mazeLocaQue = new LinkedList<Node>();
		List<String> isChecked = new ArrayList<String>();
		mazeLocaQue.add(newNode);
		isChecked.add(newNode.getX()+"/"+newNode.getY());
		//add starting node
		//traverse the surrounding nodes
		//System.out.println("start POINT: "+newNode.getX()+" "+newNode.getY());
		while(!mazeLocaQue.isEmpty())
		{
			//System.out.println("entered");
			Node currentNode = mazeLocaQue.poll();
			int currentX = currentNode.getX();
			int currentY = currentNode.getY();
			if(currentNode.lastNode!=null)
			{
				Node CurrentlastNode = currentNode.lastNode;
			}
		//	System.out.println("currentX: "+currentX+" currentY: "+currentY+" Height: "+Height);
			
			if(currentX>0 && currentY+1>0 && currentX<Width-1 && currentY+1<Height-1)
			{
			//	System.out.println("entered1");
				
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX);
				theNewNode.setY(currentY+1);
				
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX][currentY+1] == ' '||mazeContext[currentX][currentY+1] == '.')
				{
					numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX][currentY+1] == '.')
					{
						numberOfSteps++;
						System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
					
					
				}
				
			}
			if(currentX-1>0 && currentY+1>0 && currentX-1<Width-1 && currentY+1<Height-1)
			{
			//	System.out.println("entered2");
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX-1);
				theNewNode.setY(currentY+1);
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX-1][currentY+1] == ' '||mazeContext[currentX-1][currentY+1] == '.')
				{
					nodeExpanded++;
					numberOfSteps++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX-1][currentY+1] == '.')
					{
						numberOfSteps++;
						System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
			}
			if(currentX-1>0 && currentY>0 && currentX-1<Width-1 && currentY<Height-1)
			{
			//	System.out.println("entered3");
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX-1);
				theNewNode.setY(currentY);
				theNewNode.lastNode = currentNode;
			//	System.out.println("the NEW node X: "+theNewNode.getX()+" newY: "+theNewNode.getY());
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX-1][currentY] == ' ' || mazeContext[currentX-1][currentY]== '.')
				{
					numberOfSteps++;
					nodeExpanded++;
				//	System.out.println("RightTrack!");
					if(mazeContext[currentX-1][currentY] == '.')
					{
						numberOfSteps++;
				//		System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
			}
			if(currentX-1>0 && currentY-1>0 && currentX-1<Width-1 && currentY-1<Height-1)
			{
		//		System.out.println("entered4");
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX-1);
				theNewNode.setY(currentY-1);
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX-1][currentY-1] == ' '||mazeContext[currentX-1][currentY-1]== '.')
				{
					nodeExpanded++;
					numberOfSteps++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX-1][currentY-1] == '.')
					{
						numberOfSteps++;
				//		System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
			}
			if(currentX>0 && currentY-1>0 && currentX<Width-1 && currentY-1<Height-1)
			{
			//	System.out.println("entered5");
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX);
				theNewNode.setY(currentY-1);
			//	System.out.println("the NEW node X: "+theNewNode.getX()+" newY: "+theNewNode.getY());
			
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX][currentY-1] == ' '||mazeContext[currentX][currentY-1]== '.')
				{
				//	System.out.println("RightTrack!");
					numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX][currentY-1] == '.')
					{
						numberOfSteps++;
				//		System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
			}
			if(currentX+1>0 && currentY-1>0 && currentX+1<Width-1 && currentY-1<Height-1)
			{
		//		System.out.println("entered6");
				Node theNewNode = new Node();
				numberOfSteps++;
				theNewNode.setX(currentX+1);
				theNewNode.setY(currentY-1);
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX+1][currentY-1] == ' ' || mazeContext[currentX+1][currentY-1]== '.')
				{
					numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX+1][currentY-1] == '.')
					{
						numberOfSteps++;
			//			System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
				
			}
			if(currentX+1>0 && currentY>0 && currentX+1<Width-1 && currentY<Height-1)
			{
			//	System.out.println("entered7");
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX+1);
				theNewNode.setY(currentY);
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX+1][currentY] == ' '||mazeContext[currentX+1][currentY]== '.')
				{
					numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX+1][currentY] == '.')
					{
						numberOfSteps++;
			//			System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
			}
			if(currentX+1>0 && currentY+1>0 && currentX+1<Width-1 && currentY+1<Height-1)
			{
			//	System.out.println("entered8");
				Node theNewNode = new Node();
				numberOfSteps++;
				
				theNewNode.setX(currentX+1);
				theNewNode.setY(currentY+1);
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX+1][currentY+1] == ' '||mazeContext[currentX+1][currentY+1]== '.')
				{
					nodeExpanded++;
					numberOfSteps++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX+1][currentY+1] == '.')
					{
						numberOfSteps++;
				//		System.out.println("Number of steps: "+numberOfSteps+"    Node expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = ',';
				}
			}
			for(int k =0;k<Width;k++)
			{
				for(int j = 0;j<Height;j++)
				{
					System.out.print(mazeContext[k][j]);
				}
				System.out.println();
			}
			Thread.sleep(100);

		}
		
		
		return 0;
	}
	
	public static int greedyBest(char[][]mazeContext, Node newNode,Node endNode, int Width, int Height) throws InterruptedException
	{
		int numberOfSteps = 0;
		int nodeExpanded = 0;
		//System.out.println(Height);
		
		//Open state
		PriorityQueue<Node> mazeLocaQue = new PriorityQueue<Node>();
		
		//checked state
		List<String> isChecked = new ArrayList<String>();
		mazeLocaQue.add(newNode);
		int [] xM = {0,0,-1,-1,-1,1,1,1};
		int [] yM = {-1,1,-1,0,1,-1,0,1};
		
		while(!mazeLocaQue.isEmpty())
		{
		
			Node currentNode = mazeLocaQue.poll();
			isChecked.add(currentNode.getX()+"/"+currentNode.getY());
			
			if(mazeContext[currentNode.getX()][currentNode.getY()] == '.')
			{
				return 1;
			}else
			{
	
			//int h = calcuManhattonDistance(endNode.getX(),currentNode.getX(),endNode.getY(),currentNode.getY());
			//starting counter
			//applicable actions
				for(int i = 0;i<8;i++ )//not finished.  > starting counter
				{
				
					if(mazeContext[currentNode.getX()+xM[i]][currentNode.getY()+yM[i]] == ' ' ||mazeContext[currentNode.getX()+xM[i]][currentNode.getY()+yM[i]] == '.' && currentNode.getX()+xM[i]>0 && currentNode.getY()+yM[i]>0 && currentNode.getX()+xM[i]<Width-1 && currentNode.getY()+yM[i]<Height-1)
					{
					
				//current_action = applicable action
				//successor state= currentstate apply
						Node expandedNode = new Node();
						expandedNode.setX(currentNode.getX()+xM[i]);
						expandedNode.setY(currentNode.getY()+yM[i]);
						if(mazeContext[expandedNode.getX()][expandedNode.getY()] == '.')
						{
							return 1;
						}
				//end if
						expandedNode.setH(calcuManhattonDistance(endNode.getX(),expandedNode.getX(),endNode.getY(),expandedNode.getY()));
						
						if(isChecked.indexOf(expandedNode.getX()+"/"+expandedNode.getY())<0)
						{
						
							expandedNode.lastNode = currentNode;
							mazeLocaQue.add(expandedNode);//insert current state
							isChecked.add(expandedNode.getX()+"/"+expandedNode.getY());//insert successor state
							mazeContext[expandedNode.getX()][expandedNode.getY()] = ',';
							
						}
						for(int k =0;k<Height;k++)
						{
							for(int j = 0;j<Width;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
					Thread.sleep(100);
					}
				}
			}
		}
		
		return 0;
	
	}
	public static int calcuManhattonDistance(int x0,int x1, int y0, int y1)
	{
		int distance = Math.abs(x1-x0) + Math.abs(y1-y0);
		return distance;
	}
	
	public static void aStar(char[][]mazeContext, Node startNode, final Node endNode) throws InterruptedException
	{
		ArrayList<Node> iniList = extractNode(mazeContext,startNode);
		Comparator<Node> nodeCom = new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				if(node1.getCost()+calcuManhattonDistance(node1.getX(),endNode.getX(),node1.getY(),endNode.getY())<node2.getCost()+calcuManhattonDistance(node2.getX(),endNode.getX(),node2.getY(),endNode.getY())) return -1;
				else if(node1.getCost()+calcuManhattonDistance(node1.getX(),endNode.getX(),node1.getY(),endNode.getY())>node2.getCost()+calcuManhattonDistance(node2.getX(),endNode.getX(),node2.getY(),endNode.getY())) return 1;
				//else if(node1.getCost()<node2.getCost()) return -1;
				//else if(node1.getCost()>node2.getCost() return 1;
				return 0;
			}
		};
		PriorityQueue<Node> mazeLocaQue = new PriorityQueue<Node>(iniList.size(),nodeCom);
		for(Node node:iniList)
			mazeLocaQue.offer(node);
		Node result = mazeLocaQue.poll();
		while(calcuManhattonDistance(result.getX(),endNode.getX(),result.getY(),endNode.getY())>1){
			mazeContext[result.getX()][result.getY()] = '.';
			System.out.println((result.getX()+1)+" "+(result.getY()+1));
			/*
			for(int k =0;k<mazeContext.length;k++) {
				for(int j = 0;j<mazeContext[k].length;j++){
					System.out.print(mazeContext[k][j]);
				}
				System.out.println();
			}
			*/
			//Thread.sleep(200);
			ArrayList<Node> cnodes = extractNode(mazeContext,result);
			for(Node node:cnodes)
				mazeLocaQue.offer(node);
			result = mazeLocaQue.poll();
		}
		mazeContext[result.getX()][result.getY()] = '.';
		System.out.println((result.getX()+1)+" "+(result.getY()+1));
		
		for(int k =0;k<mazeContext.length;k++) {
			for(int j = 0;j<mazeContext[k].length;j++){
				System.out.print(mazeContext[k][j]);
			}
			System.out.println();
		}
		
		while(result.lastNode != null){
			System.out.println((result.lastNode.getX()+1)+","+(result.lastNode.getY()+1));
			result = result.lastNode;
		}
	}

	public static ArrayList<Node> extractNode(char[][]mazeContext, Node node){
		ArrayList<Node> resultList = new ArrayList<>();
		/*
		if (mazeContext[node.getX()+1][node.getY()]!='%'&&(!node.exist(node.getX()+1, node.getY()))) resultList.add(new Node(node.getX()+1,node.getY(),node.getCost()+1,node));
		if (mazeContext[node.getX()-1][node.getY()]!='%'&&(!node.exist(node.getX()-1, node.getY()))) resultList.add(new Node(node.getX()-1,node.getY(),node.getCost()+1,node));
		if (mazeContext[node.getX()][node.getY()+1]!='%'&&(!node.exist(node.getX(), node.getY()+1))) resultList.add(new Node(node.getX(),node.getY()+1,node.getCost()+1,node));
		if (mazeContext[node.getX()][node.getY()-1]!='%'&&(!node.exist(node.getX(), node.getY()-1))) resultList.add(new Node(node.getX(),node.getY()-1,node.getCost()+1,node));
		*/
		if (mazeContext[node.getX()+1][node.getY()]==' ') resultList.add(new Node(node.getX()+1,node.getY(),node.getCost()+1,node));
		if (mazeContext[node.getX()-1][node.getY()]==' ') resultList.add(new Node(node.getX()-1,node.getY(),node.getCost()+1,node));
		if (mazeContext[node.getX()][node.getY()+1]==' ') resultList.add(new Node(node.getX(),node.getY()+1,node.getCost()+1,node));
		if (mazeContext[node.getX()][node.getY()-1]==' ') resultList.add(new Node(node.getX(),node.getY()-1,node.getCost()+1,node));
		return resultList;
	}
	
	public static ArrayList<DirectedNode> extractNodeTurnCost(char[][]mazeContext, DirectedNode node){
		ArrayList<DirectedNode> resultList = new ArrayList<>();
		if (mazeContext[node.getX()+1][node.getY()]==' ') resultList.add(new DirectedNode(node.getX()+1,node.getY(),node.getCost()+directionCostCase1('e',node.getDirection()),node,'e'));
		if (mazeContext[node.getX()-1][node.getY()]==' ') resultList.add(new DirectedNode(node.getX()-1,node.getY(),node.getCost()+directionCostCase1('w',node.getDirection()),node,'w'));
		if (mazeContext[node.getX()][node.getY()+1]==' ') resultList.add(new DirectedNode(node.getX(),node.getY()+1,node.getCost()+directionCostCase1('s',node.getDirection()),node,'s'));
		if (mazeContext[node.getX()][node.getY()-1]==' ') resultList.add(new DirectedNode(node.getX(),node.getY()-1,node.getCost()+directionCostCase1('n',node.getDirection()),node,'n'));
		return resultList;
	}
	
	public static int directionCostCase1(char dir1, char dir2){
		if(dir1==dir2) return 2;
		return 1;
	}
	
	public static void aStarwGhost(char[][] mazeContext,Node startNode,final Node endNode) throws InterruptedException{
		char[][] mazecopy = mazeContext.clone();
		ArrayList<int[]> ghostPath = new ArrayList<>();
		int currentGPos = 0;
		for(int i=0;i<mazeContext.length;i++)
			for(int j=0;j<mazeContext[i].length;j++){
				if (mazeContext[i][j]=='g'){
					int[] path = {i,j};
					ghostPath.add(path);
				} else if (mazeContext[i][j]=='G'){
					int[] path = {i,j};
					ghostPath.add(path);
					currentGPos = ghostPath.indexOf(path);
				}
			}
		Ghost ghost = new Ghost(ghostPath,currentGPos);
		
		
		
		
		
		
		ArrayList<Node> iniList = extractNodewGhost(mazeContext,startNode,ghost);
		Comparator<Node> nodeCom = new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				if(node1.getCost()+calcuManhattonDistance(node1.getX(),endNode.getX(),node1.getY(),endNode.getY())<node2.getCost()+calcuManhattonDistance(node2.getX(),endNode.getX(),node2.getY(),endNode.getY())) return -1;
				else if(node1.getCost()+calcuManhattonDistance(node1.getX(),endNode.getX(),node1.getY(),endNode.getY())>node2.getCost()+calcuManhattonDistance(node2.getX(),endNode.getX(),node2.getY(),endNode.getY())) return 1;
				//else if(node1.getCost()<node2.getCost()) return -1;
				//else if(node1.getCost()>node2.getCost() return 1;
				return 0;
			}
		};
		PriorityQueue<Node> mazeLocaQue = new PriorityQueue<Node>(iniList.size(),nodeCom);
		for(Node node:iniList)
			mazeLocaQue.offer(node);
		Node result = mazeLocaQue.poll();
		while(calcuManhattonDistance(result.getX(),endNode.getX(),result.getY(),endNode.getY())>1){
			//mazeContext[result.getX()][result.getY()] = '.';
			//System.out.println((result.getX()+1)+" "+(result.getY()+1));
			/*
			for(int k =0;k<mazeContext.length;k++) {
				for(int j = 0;j<mazeContext[k].length;j++){
					System.out.print(mazeContext[k][j]);
				}
				System.out.println();
			}
			*/
			//Thread.sleep(200);
			ArrayList<Node> cnodes = extractNodewGhost(mazeContext,result,ghost);
			for(Node node:cnodes)
				mazeLocaQue.offer(node);
			result = mazeLocaQue.poll();
		}
		/*
		mazeContext[result.getX()][result.getY()] = '.';
		System.out.println((result.getX()+1)+" "+(result.getY()+1));
		
		for(int k =0;k<mazeContext.length;k++) {
			for(int j = 0;j<mazeContext[k].length;j++){
				System.out.print(mazeContext[k][j]);
			}
			System.out.println();
		}
		*/
		
		/*
		while(result.lastNode != null){
			System.out.println((result.lastNode.getX()+1)+","+(result.lastNode.getY()+1));
			result = result.lastNode;
		}
		*/
		
		clearScreen();
		ArrayList<Node> path = new ArrayList<>();
		path.add(result);
		while(result.lastNode != null){
			path.add(result.lastNode);
			result = result.lastNode;
		}
		for(int i=0;i<mazecopy.length;i++)
			for(int j=0;j<mazecopy[i].length;j++)
				if (mazecopy[i][j]=='g'||mazecopy[i][j]=='G') mazecopy[i][j] = ' ';
		Collections.reverse(path);
		for(Node player: path){
			int[] gpos = ghost.posAtTurn(player.getCost());
			mazecopy[gpos[0]][gpos[1]] = 'G';
			mazecopy[player.getX()][player.getY()] = 'P';
			for(int i=0;i<mazecopy.length;i++){
				for(int j=0;j<mazecopy[i].length;j++)
					System.out.print(mazecopy[i][j]);
				System.out.println();
			}
			mazecopy[gpos[0]][gpos[1]] = ' ';
			mazecopy[player.getX()][player.getY()] = ' ';
			Thread.sleep(1000);
			clearScreen();
		}
		
	}
	
	public static ArrayList<Node> extractNodewGhost(char[][]mazeContext, Node node, Ghost ghost){
		ArrayList<Node> resultList = new ArrayList<>();
		int[] ghostPosition = ghost.posAtTurn(node.getCost());
		if(node.getX()==ghostPosition[0]&&node.getY()==ghostPosition[1]) return resultList;
		if (mazeContext[node.getX()+1][node.getY()]!='%'&&!(node.getX()+1==ghostPosition[0]&&node.getY()==ghostPosition[1])) resultList.add(new Node(node.getX()+1,node.getY(),node.getCost()+1,node));
		if (mazeContext[node.getX()-1][node.getY()]!='%'&&!(node.getX()-1==ghostPosition[0]&&node.getY()==ghostPosition[1])) resultList.add(new Node(node.getX()-1,node.getY(),node.getCost()+1,node));
		if (mazeContext[node.getX()][node.getY()+1]!='%'&&!(node.getX()==ghostPosition[0]&&node.getY()+1==ghostPosition[1])) resultList.add(new Node(node.getX(),node.getY()+1,node.getCost()+1,node));
		if (mazeContext[node.getX()][node.getY()-1]!='%'&&!(node.getX()==ghostPosition[0]&&node.getY()-1==ghostPosition[1])) resultList.add(new Node(node.getX(),node.getY()-1,node.getCost()+1,node));
		return resultList;
	}
	
	public static void clearScreen(){
		for(int i=0;i<100;i++) System.out.println();
	}
	
}
