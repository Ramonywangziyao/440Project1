import java.util.*;
import java.awt.Color;
import java.awt.Image;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MazeLoader {
	//initialize variables here
	
	
		boolean [][] trace;
	
		public static void main(String[]args) throws IOException, InterruptedException
		{
			FileChooseingMethodChoosing();
		}
		public static char[][] FileChooseingMethodChoosing() throws IOException, InterruptedException
		{
			String fileName;
			Scanner scanner = new Scanner(System.in);
			System.out.println("enter the file name");
			fileName = scanner.next();
			FileReader fr = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader (fr);

			char [][] maze = null;
			String line;
			int x = 0;
			int count = 0;
			int size = 0;
			while((line = buffer.readLine())!=null)
			{
		
				count++;
			}
			fr.close();
			
			while(true)
			{
			x=0;
			maze=null;
			System.out.println();
			System.out.println("What method do you want to use? 1.Depth   2.Breath   3.Greedy   0.Exit   9.New File Path");
			int methodChose = scanner.nextInt();
			FileReader fr2 = new FileReader(fileName);
			BufferedReader bufferNew = new BufferedReader (fr2);
			while((line = bufferNew.readLine())!=null)
			{
				char [] vals = line.toCharArray();
	            if (maze == null) {
	                size = vals.length;
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
			Node startNode = new Node(startPacman[0],startPacman[1]);
			Node endNode = new Node(goalofPacman[0],goalofPacman[1]);
			ArrayList<Node> solution = new ArrayList<Node>();
			boolean [][] trace = new boolean[count][size];

			if(methodChose == 0)
			{
				System.exit(0);
			}
			if(methodChose == 1)
			{
			int stepsTotal = 0;
			int totalNode = 0;
			int result = depthFirst(solution,maze,startNode.getX(),startNode.getY(),trace,size,count,stepsTotal,totalNode);
			if(result == 1)
			{
				System.out.println("Goal Found!");
				fr2.close();
			}
			else if(result == 0)
			{
				System.out.println("Goal Not Found!");
				fr2.close();
			}
			}else if(methodChose == 2)
			{
			int result = breadth(maze,startNode,size,count);
			if(result == 1)
			{
				System.out.println("Goal Found!");
				fr2.close();
			}
			else if(result == 0)
			{
				System.out.println("Goal Not Found!");
				fr2.close();
			}
			}else if(methodChose == 3)
			{
			int result = greedyBest(maze,startNode,endNode,size,count);
			if(result == 1)
			{
				System.out.println("Goal Found!");
				fr2.close();
			}
			else if(result == 0)
			{
				System.out.println("Goal Not Found!");
				fr2.close();
			}

			}
			else if(methodChose == 9)
			{
				fr2.close();
				FileChooseingMethodChoosing();
			}
		}
		
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

	public static int depthFirst(ArrayList<Node> solution, char[][] mazeContext,int x,int y,boolean [][] walkedTrace,int width, int height,int steps,int nodesE) throws InterruptedException
	{
		for(int k =0;k<height;k++)
		{
			for(int j = 0;j<width;j++)
			{
				System.out.print(mazeContext[k][j]);
			}
			System.out.println();
		}
		Thread.sleep(70);
	if(mazeContext[x][y] == '.')
	{
		int leastNodeExpanded= backTrace(solution.get(solution.size()-1),mazeContext);
		for(int k =0;k<height;k++)
		{
			for(int j = 0;j<width;j++)
			{
				System.out.print(mazeContext[k][j]);
			}
			System.out.println();
		}
		System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodesE);
		return 1;
	}
	else
	{
		if(mazeContext[x][y-1]=='.'||mazeContext[x][y-1]==' '&&walkedTrace[x][y-1]==false&&x>0&&y-1>0&&x<height-1&&y-1<width-1) //east
		{
				nodesE++;
				Node newNode = new Node(x,y);
				if(!solution.isEmpty())
				{
				newNode.lastNode = solution.get(solution.size()-1);
				}
				solution.add(newNode);
				walkedTrace[x][y] = true;
				mazeContext[x][y] = 'O';
				if(depthFirst(solution,mazeContext,x,y-1,walkedTrace,width,height,steps,nodesE)==1)
				{
					return 1;	
				}
				solution.remove(solution.size()-1); //unpaint
				mazeContext[x][y] = ' ';
		}
			if(mazeContext[x-1][y]=='.'||mazeContext[x-1][y]==' '&&walkedTrace[x-1][y]==false&&x-1>0&&y>0&&x-1<height-1&&y<width-1) //east
			{
					nodesE++;
					Node newNode = new Node(x,y);
					if(!solution.isEmpty())
					{
					newNode.lastNode = solution.get(solution.size()-1);
					}
					solution.add(newNode);
					walkedTrace[x][y] = true;
					mazeContext[x][y] = 'O';
					if(depthFirst(solution,mazeContext,x-1,y,walkedTrace,width,height,steps,nodesE)==1)
					{
						return 1;	
					}
					solution.remove(solution.size()-1); //unpaint
					mazeContext[x][y] = ' ';
					
			}
			if(mazeContext[x][y+1]=='.'||mazeContext[x][y+1]==' '&&walkedTrace[x][y+1]==false&&x>0&&y+1>0&&x<height-1&&y+1<width-1) //east
			{
					nodesE++;
					Node newNode = new Node(x,y);
					if(!solution.isEmpty())
					{
					newNode.lastNode = solution.get(solution.size()-1);
					}
					solution.add(newNode);
					walkedTrace[x][y] = true;
					mazeContext[x][y] = 'O';
					if(depthFirst(solution,mazeContext,x,y+1,walkedTrace,width,height,steps,nodesE)==1)
					{
						return 1;	
					}
					solution.remove(solution.size()-1); //unpaint
					mazeContext[x][y] = ' ';
					
			}
			if(mazeContext[x+1][y]=='.'||mazeContext[x+1][y]==' '&&walkedTrace[x+1][y]==false&&x+1>0&&y>0&&x+1<height-1&&y<width-1) //east
			{
					Node newNode = new Node(x,y);
					nodesE++;
					if(!solution.isEmpty())
					{
					newNode.lastNode = solution.get(solution.size()-1);
					}
					solution.add(newNode);
					walkedTrace[x][y] = true;
					mazeContext[x][y] = 'O';
					if(depthFirst(solution,mazeContext,x+1,y,walkedTrace,width,height,steps,nodesE)==1)
					{
						return 1;	
					}
					solution.remove(solution.size()-1); //unpaint
					mazeContext[x][y] = ' ';
					
			}

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
			nodeExpanded++;
			int currentX = currentNode.getX();
			int currentY = currentNode.getY();
		
		//	System.out.println("currentX: "+currentX+" currentY: "+currentY+" Height: "+Height);
			
			if(currentX>0 && currentY+1>0 && currentX<Width-1 && currentY+1<Height-1)
			{
			//	System.out.println("entered1");
				
				Node theNewNode = new Node(currentX,currentY+1);
				
				numberOfSteps++;
	
				
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX][currentY+1] == ' '||mazeContext[currentX][currentY+1] == '.')
				{
				//	numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX][currentY+1] == '.')
					{
					//	numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode,mazeContext);
						for(int k =0;k<Width;k++)
						{
							for(int j = 0;j<Height;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
					
					
				}
				
			}
			/*
			if(currentX-1>0 && currentY+1>0 && currentX-1<Width-1 && currentY+1<Height-1)
			{
			//	System.out.println("entered2");
				Node theNewNode = new Node(currentX-1,currentY+1);


				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX-1][currentY+1] == ' '||mazeContext[currentX-1][currentY+1] == '.')
				{
					nodeExpanded++;
				//	numberOfSteps++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX-1][currentY+1] == '.')
					{
						//numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode);
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
			}
			*/
			if(currentX-1>0 && currentY>0 && currentX-1<Width-1 && currentY<Height-1)
			{
			//	System.out.println("entered3");
				Node theNewNode = new Node(currentX-1,currentY);

				

				theNewNode.lastNode = currentNode;
				//System.out.println("the NEW node X: "+theNewNode.getX()+" newY: "+theNewNode.getY());
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX-1][currentY] == ' ' || mazeContext[currentX-1][currentY]== '.')
				{
				//	numberOfSteps++;
					nodeExpanded++;
				//	System.out.println("RightTrack!");
					if(mazeContext[currentX-1][currentY] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode,mazeContext);
						for(int k =0;k<Width;k++)
						{
							for(int j = 0;j<Height;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
			}
			/*
			if(currentX-1>0 && currentY-1>0 && currentX-1<Width-1 && currentY-1<Height-1)
			{
		//		System.out.println("entered4");
				Node theNewNode = new Node(currentX-1,currentY-1);
				//nodeExpanded++;
				//numberOfSteps++;
				
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX-1][currentY-1] == ' '||mazeContext[currentX-1][currentY-1]== '.')
				{
					nodeExpanded++;
				//	numberOfSteps++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX-1][currentY-1] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode);
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
			}
			*/
			if(currentX>0 && currentY-1>0 && currentX<Width-1 && currentY-1<Height-1)
			{
			//	System.out.println("entered5");
				Node theNewNode = new Node(currentX,currentY-1);


			//	System.out.println("the NEW node X: "+theNewNode.getX()+" newY: "+theNewNode.getY());
			
				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX][currentY-1] == ' '||mazeContext[currentX][currentY-1]== '.')
				{
				//	System.out.println("RightTrack!");
				//	numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX][currentY-1] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode,mazeContext);
						for(int k =0;k<Width;k++)
						{
							for(int j = 0;j<Height;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
			}
			/*
			if(currentX+1>0 && currentY-1>0 && currentX+1<Width-1 && currentY-1<Height-1)
			{
		//		System.out.println("entered6");
				Node theNewNode = new Node(currentX+1,currentY-1);


				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX+1][currentY-1] == ' ' || mazeContext[currentX+1][currentY-1]== '.')
				{
					//numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX+1][currentY-1] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode);
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
				
			}
			*/
			if(currentX+1>0 && currentY>0 && currentX+1<Width-1 && currentY<Height-1)
			{
			//	System.out.println("entered7");
				Node theNewNode = new Node(currentX+1,currentY);


				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX+1][currentY] == ' '||mazeContext[currentX+1][currentY]== '.')
				{
					//numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX+1][currentY] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode,mazeContext);
						for(int k =0;k<Width;k++)
						{
							for(int j = 0;j<Height;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
			}
			/*
			if(currentX+1>0 && currentY+1>0 && currentX+1<Width-1 && currentY+1<Height-1)
			{
			//	System.out.println("entered8");
				Node theNewNode = new Node(currentX+1,currentY+1);
				
				//numberOfSteps++;
				

				if(isChecked.indexOf(theNewNode.getX()+"/"+theNewNode.getY())<0 && mazeContext[currentX+1][currentY+1] == ' '||mazeContext[currentX+1][currentY+1]== '.')
				{
					
					//numberOfSteps++;
					nodeExpanded++;
					theNewNode.lastNode = currentNode;
					if(mazeContext[currentX+1][currentY+1] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded= backTrace(theNewNode);
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					mazeLocaQue.add(theNewNode);
					isChecked.add(theNewNode.getX()+"/"+theNewNode.getY());
					mazeContext[theNewNode.getX()][theNewNode.getY()] = 'O';
				}
			}
			*/
			for(int k =0;k<Width;k++)
			{
				for(int j = 0;j<Height;j++)
				{
					System.out.print(mazeContext[k][j]);
				}
				System.out.println();
			}
			Thread.sleep(70);

		}
		
		
		return 0;
	}
	
	public static int greedyBest(char[][]mazeContext, Node newNode,Node endNode, int Width, int Height) throws InterruptedException
	{
		int numberOfSteps = 0;
		int nodeExpanded = 0;
		//Open state
		PriorityQueue<Node> mazeLocaQue = new PriorityQueue<Node>();
		//checked state
		List<String> isChecked = new ArrayList<String>();
		newNode.setH(calcuManhattonDistance(endNode.getX(),newNode.getX(),endNode.getY(),newNode.getY()));
		mazeLocaQue.add(newNode);
		int [] xM = {0,0,-1,1};
		int [] yM = {-1,1,0,0};
		numberOfSteps++;
		while(!mazeLocaQue.isEmpty())
		{
	//		System.out.println("The value!:    "+mazeLocaQue.peek().getH());
		//	Thread.sleep(500);
			Node currentNode = mazeLocaQue.poll();
			isChecked.add(currentNode.getX()+"/"+currentNode.getY());
			numberOfSteps++;
			if(mazeContext[currentNode.getX()][currentNode.getY()] == '.')
			{
				numberOfSteps++;
				int leastNodeExpanded = backTrace(currentNode,mazeContext);
				for(int k =0;k<Width;k++)
				{
					for(int j = 0;j<Height;j++)
					{
						System.out.print(mazeContext[k][j]);
					}
					System.out.println();
				}
				System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
				return 1;
			}else
			{
				numberOfSteps++;
			//starting counter
			//applicable actions
				for(int i = 0;i<4;i++ )//not finished.  > starting counter
				{
					numberOfSteps++;
					int newX = currentNode.getX()+xM[i];
					int newY = currentNode.getY()+yM[i];
					if(mazeContext[newX][newY] == '.')
					{
						numberOfSteps++;
						int leastNodeExpanded = backTrace(currentNode,mazeContext);
						for(int k =0;k<Height;k++)
						{
							for(int j = 0;j<Width;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
						Thread.sleep(70);
						System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
						return 1;
					}
					if(mazeContext[newX][newY] == ' ' ||mazeContext[newX][newY] == '.' && newX>0 && newY>0 && newX<Width-1 && newY<Height-1)
					{				
				//current_action = applicable action
				//successor state= currentstate apply
						Node expandedNode = new Node(newX,newY);
						
						//numberOfSteps++;
						nodeExpanded++;
						if(mazeContext[expandedNode.getX()][expandedNode.getY()] == '.')
						{
							//numberOfSteps++;
							expandedNode.lastNode = currentNode;
							int leastNodeExpanded = backTrace(expandedNode,mazeContext);
							for(int k =0;k<Width;k++)
							{
								for(int j = 0;j<Height;j++)
								{
									System.out.print(mazeContext[k][j]);
								}
								System.out.println();
							}
							Thread.sleep(70);
							System.out.println("Total steps: "+leastNodeExpanded+"    Total Node Expanded: "+nodeExpanded);
							return 1;
						}
				//end if
						expandedNode.setH(calcuManhattonDistance(endNode.getX(),expandedNode.getX(),endNode.getY(),expandedNode.getY()));

						numberOfSteps++;
						if(isChecked.indexOf(expandedNode.getX()+"/"+expandedNode.getY())<0)
						{
							//numberOfSteps++;
							expandedNode.lastNode = currentNode;
							mazeLocaQue.add(expandedNode);//insert current state
					//		if(mazeLocaQue.peek()!=null)
					//		{
					//		System.out.println("expande H:    "+mazeLocaQue.peek().getH());
					//		}
							isChecked.add(expandedNode.getX()+"/"+expandedNode.getY());//insert successor state
							mazeContext[expandedNode.getX()][expandedNode.getY()] = 'O';
						}
						for(int k =0;k<Height;k++)
						{	
							for(int j = 0;j<Width;j++)
							{
								System.out.print(mazeContext[k][j]);
							}
							System.out.println();
						}
					Thread.sleep(70);
					}
				}
			}
		}
		return 0;
	}
	//backtrace get the least node
	public static int backTrace(Node walkedNode,char [][] mazeContext)
	{
		int leastSteps = 1;
		while(walkedNode.lastNode!=null)
		{
			leastSteps++;
			mazeContext[walkedNode.getX()][walkedNode.getY()] = '.';
			walkedNode = walkedNode.lastNode;
			//System.out.println("X: "+walkedNode.getX()+"   Y: "+walkedNode.getY());
		}
		
		return leastSteps;
	}
	
	//Calculating heuristic
	public static int calcuManhattonDistance(int x0,int x1, int y0, int y1)
	{
		int distance = Math.abs(x1-x0) + Math.abs(y1-y0);
		return distance;
	}
	
	public void aStar(int x, int y)
	{
		
		
	}

	
	
}
