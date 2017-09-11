package Design;
import java.util.HashMap;
/**
 * 
 * @author koutei
 *
 */

class Node{							//the new Class node to make processing easier
	int key;
	int value;
	Node pre;
	Node next;
public Node(int key, int value){
	this.key = key;
	this.value = value;
	}
}

public class LRU_cache {	
	int count, capacity;
	HashMap<Integer,Node> map = new HashMap<>();
	Node head,tail;
	public LRU_cache(int capacity){
		this.capacity = capacity;
		count=0;
		head = new Node(0,0);
		tail = new Node(0,0);
		head.next = tail;
		tail.pre =	head;
		head.pre = null;
		tail.next = null;
	}
	
	public void DeleteNode(Node node){
			node.pre.next = node.next;		//before node's next is node's next
			node.next.pre = node.pre;		//next node's pre is node's pre;
	}
	
	public void MoveHead(Node node){
			node.next = head.next;
			head.next.pre = node;   //node.next.pre = node;??head is better
			node.pre = head;
			head.next = node;		
	}
	
	public int get(int key){
		if(map.containsKey(key)){
			Node node = map.get(key);
			DeleteNode(node);			//firstly delete then move to head;
			MoveHead(node);
			return node.value;
		}
		return -1;	
	}
	
	public void put(int key, int value){
		if(map.containsKey(key)){
			Node node = map.get(key);
			DeleteNode(node);			//delete the old node;
			node.value = value;			//then update the value;
			map.put(key, node);
			MoveHead(node);
		}else{ 
			Node node = new Node(key,value);
			map.put(key, node);
				if(count<capacity){
					count++;
					MoveHead(node);
			}else{
				map.remove(tail.pre.key);
				DeleteNode(tail.pre);
				MoveHead(node);
			}
		}
	}
	public static void main(String[] args) {
		LRU_cache test = new LRU_cache(2);
		test.put(1, 1);
		test.put(2, 2);
		System.out.println(test.get(1));
		test.put(3, 3);
		System.out.println(test.get(2));
		test.put(4, 4);
		System.out.println(test.get(1));
		System.out.println(test.get(3));
		System.out.println(test.get(4));
	}
}
