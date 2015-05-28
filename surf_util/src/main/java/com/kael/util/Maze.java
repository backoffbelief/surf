package com.kael.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * 迷宫
 * @author hanyuanliang
 * 2015-5-28 下午7:53:35
 */
public class Maze {
	
	static int[][] mazes = {
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

	static {
		List<Node> ps = new ArrayList<Node>();
		for (int i = 0; i < mazes.length; i++) {
			for (int j = 0; j < mazes[i].length; j++) {
				ps.add(new Node(i, j));
			}
		}
		for(Node p : ps){
			p.around(ps, mazes.length);
		}
	}
	
	static class Node{
		private int x,y,v;
		private Set<Node> arounds;

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
			return x== 0|| y == 0 || x == mazes.length || y == mazes[0].length;
		}

		@Override
		public String toString() {
			return String.format("[x=%s, y=%s]", x, y);
		}
	}
	
	public static void main(String[] args) {
		printf("\n----------原始迷宫(加外围围墙)(0 表示通路，1 表示障碍)---------\n");
		for(int[] tmp : mazes){
			printf(Arrays.toString(tmp));
		}
		
		Node start = new Node(8,9);
		Stack<Node> stack = new Stack<Maze.Node>();
		
		stack.push(start);
		
		if(find(stack)){
			System.out.println(Arrays.toString(stack.toArray(new Node[0])));
		}
		
	}
	
	

	private static boolean find(Stack<Node> stack) {
		Node p = stack.peek();
		if(p.isOut()){
			return true;
		}
		Set<Node> list = p.arounds;
		for(Node element : list){
			 if(element.isOk() && !stack.contains(element)){
				stack.push(element);
				return find(stack);
			 }
		}
		stack.pop();
		return false;
	}



	private static void printf(String string) {
		System.out.println(string);
	}
	
	
	

}
