package com.kael.maze;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MazePanel extends JPanel {
	int[][] mazes = {
			{1,0,1,1,1,1,1,1,1,1},
			{1,0,1,1,1,1,1,0,0,1},
			{1,0,0,1,1,1,0,0,1,1},
			{1,1,0,0,0,0,0,0,1,1},
			{1,1,0,1,1,1,1,0,1,1},
			{1,1,0,0,1,1,1,0,1,1},
			{1,1,1,0,1,1,1,0,0,1},
			{1,1,1,0,0,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1} 
	};
	
	Map<Node, JLabel> jls = new HashMap<Node, JLabel>();
	
	public MazePanel() {
		this.setLayout(new GridLayout(mazes.length, mazes[0].length));
		//Random r = new Random();
		for (int i = 0; i < mazes.length; i++) {
			for(int j = 0 ;j<mazes[i].length;j++){
				JLabel comp = new JLabel(new ImageIcon(ClassLoader
						.getSystemResource(mazes[i][j] == 0 ? "1.png" :"2.png")));
				this.add(comp);
				jls.put(new Node(i, j),comp);
			}
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				List<Node> ps = new ArrayList<Node>();
				for (int i = 0; i < mazes.length; i++) {
					for (int j = 0; j < mazes[i].length; j++) {
						ps.add(new Node(i, j));
					}
				}

				Node start = null;
				for(Node p : ps){
					p.around(ps, mazes.length);
					if(p.x == 0 && p.y == 1){
						start = p;
						start.start();
					}
				}
				
				Stack<Node> stack = new Stack<Node>();
				Set<Node> errors = new HashSet<Node>();
//				stack.push(start);
				jls.get(stack.push(start)).setIcon(new ImageIcon(ClassLoader.getSystemResource("2.png")));
				find(stack,errors);
//				if(find(stack)){
//					Node[] ns = stack.toArray(new Node[0]);
//					for(Node node : ns){
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						jls.get(node).setIcon(new ImageIcon(ClassLoader.getSystemResource("2.png")));
//					}
//				}
			}
			
			 boolean find(Stack<Node> stack,Set<Node> errors) {
				Node p = stack.peek();
				if(p.isOut()){
					return true;
				}
				Set<Node> list = p.arounds;
				for(Node element : list){
					 if(element.isOk() && !stack.contains(element) && !errors.contains(element)){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						jls.get(stack.push(element)).setIcon(new ImageIcon(ClassLoader.getSystemResource("2.png")));
						if(find(stack,errors)){
							return true;
						}
					 }
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				errors.add(stack.peek());
				jls.get(stack.pop()).setIcon(new ImageIcon(ClassLoader.getSystemResource("1.png")));
				return false;
			}
		}).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	class Node{
		private int x,y,v;
		private Set<Node> arounds;
		private boolean start;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getV() {
			return v;
		}

		public void setV(int v) {
			this.v = v;
		}

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.v = mazes[x][y];
		}
		
		public void around(List<Node> ps,int len){
			arounds = new CopyOnWriteArraySet<Node>();
			if(x > 0){
				arounds.add(findP(ps, x - 1, y));
			}
			if(x < len - 1){
				arounds.add(findP(ps, x + 1, y));
			}
			if(y > 0){
				arounds.add(findP(ps, x, y - 1));
			}
			if(y < len - 1){
				arounds.add(findP(ps, x, y + 1));
			}
		}
		
		public Node findP(List<Node> ps,int x,int y){
			for (Node p : ps) {
				if(p.x == x && p.y == y){
					return p;
				}
			}
			return null;
		}
		
		public void start() {
			this.start = true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + v;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (v != other.v)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		public boolean isOk(){
			return v == 0;
		}
		
		public boolean isOut(){
			return !start && x== 0|| y == 0 || x == mazes.length - 1 || y == mazes[0].length -1 ;
		}

		@Override
		public String toString() {
			return String.format("[x=%s, y=%s]", x, y);
		}
	}
}
