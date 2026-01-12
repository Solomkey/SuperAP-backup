import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		/*
			Create an ArrayList of 100 Nodes
			Store random integers in each of them
			Print out all of the values
		*/
		class Node{
			int data;
			Node node;
			
			Node(int data){
				this.data = data;
			}
			
			public int getData(){
				return data;
			}
		}
		
		ArrayList<Node> nodes = new ArrayList<>();
		
		for(int i=0;i<100;i++){
			
			nodes.add(new Node((int)(Math.random()*100)));
			System.out.println(nodes.get(i).getData());
		}

	}
}
