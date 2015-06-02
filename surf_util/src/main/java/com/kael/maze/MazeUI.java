package com.kael.maze;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MazeUI extends JFrame {
	
	public MazeUI()  {
		super();
		
		this.setBounds(100, 100, 600, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new MazePanel());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
			
				new MazeUI();
			}
		});
	}
}
