import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		MySinglyLinkedList list = new MySinglyLinkedList();
		for(int i=0; i<20; i++){
			int value = (int)(Math.random()*100);
			list.insert(0, value);
		}
		
		for(int i =0; i<20; i++){
			int pos = (int)(Math.random()*100);
			list.insert(pos, -1);
		}
		
		System.out.println("Original list: ");
		list.printList();
		
		list.reverse();
		
		System.out.println("Reversed list: ");
		list.printList();
	}
}
