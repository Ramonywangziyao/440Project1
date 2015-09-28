import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;


public class Maze {


	public Maze() throws IOException {
		// TODO Auto-generated constructor stub
		JFrame f = new JFrame();
		f.setTitle("440 Maze Pacman");
		f.add(new MazeMap());
		f.setSize(800,800);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
