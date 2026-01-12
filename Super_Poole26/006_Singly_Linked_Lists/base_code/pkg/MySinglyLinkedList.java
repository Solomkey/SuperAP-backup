package pkg;
import java.util.Scanner;
import java.util.Random;


public class MySinglyLinkedList {
	Node head;

	/* 
		Postcondition: The head will be null 
	*/
	public MySinglyLinkedList() {
		head = null;
	}

	/* 
		Receives an integer position, searches through the SinglyLinkedList for the position and returns the data at that positon
	   	If the position doesn't exist, it returns -1
	*/ 
	public int get(int pos){
		if (pos<0) 
			return -1;
		
		Node curr = head;
		int index=0;
		
		while(curr!=null){
			if(index==pos){
				return curr.data;
			}
			curr = curr.next;
			index++;
		}
		
		return -1;
	}

	/*
		Insert a new Node at the given position with the data given
	*/
	public void insert(int pos, int data){
		Node newNode = new Node(data);
		
		if(pos<=0||head==null){
			newNode.next = head;
			head = newNode;
			return;
		}
		
		Node curr = head;
		int index = 0;
		
		while(curr.next != null && index < pos-1){
			curr = curr.next;
			index++;
		}
		
		newNode.next = curr.next;
		curr.next = newNode;

	}

	/*
		Remove the node at the given position
		If no position exists, don't change the list
	*/
	public void remove(int pos){
		if (head == null || pos<0)
			return;
	
	
		Node curr = head;
		int index = 0;
		
		while(curr.next!=null && index<pos-1){
			curr=curr.next;
			index++;
		}
		
		if(curr.next!=null){
			curr.next=curr.next.next;
		}
	}

	/*
		Print all data values in the LinkedList 
	*/
	public void printList(){
		Node curr = head;
		while (curr!=null){
			System.out.print(curr.data+" ");
			curr = curr.next;
		}
		System.out.println();
	}
	
	public void reverse(){
		Node prev = null;
		Node curr = head;
		
		while(curr != null){
			Node nextTemp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextTemp;
		}
		head = prev;
	}
}
