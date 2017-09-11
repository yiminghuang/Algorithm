package Design;

/**
 * 
 * @author koutei
 *
 */
import java.util.HashMap;

class LFUCache {
    class Node{
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    int capacity;
    Node head;
    Node tail;
    HashMap<Integer,Node> map = new HashMap<>();
    HashMap<Integer,Integer> valuemap = new HashMap<>();
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
    }
    
    public void deleteNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void Movetail(Node node){   //insert the new node to the tail
    	node.pre = tail.pre;
    	tail.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }
    
    public void increaseCount(int key){   //update valuemap and the position
        if(!valuemap.containsKey(key))
        	valuemap.put(key, 1);
        else
        	valuemap.put(key, valuemap.get(key)+1);
        
        Node node = map.get(key);
        Node cur = node.pre;   //not know the pre of the node, because no pre and next!!!
        deleteNode(node);
        
        
        while(cur!=head && valuemap.get(cur.key)<=valuemap.get(key))
            cur = cur.pre;
        
            cur.next.pre = node;		//insert to the right position;
            node.next = cur.next;
            node.pre = cur;
            cur.next = node;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            increaseCount(key);    //fre add 1;
            return map.get(key).value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
    	if(capacity==0) return;
        if(map.containsKey(key)){
            map.get(key).value = value;   //update value and position;
            increaseCount(key);
        }else{
            Node node = new Node(key,value);
            if(map.size()==capacity){		//if full, remove all recorde of last one;
            	map.remove(tail.pre.key);
                valuemap.remove(tail.pre.key);
                deleteNode(tail.pre);
            }    
            map.put(key,node);			//put new to tail,and update it;
            Movetail(node);
            increaseCount(key);
        }
    }
    
    public static void main(String[] args) {
		LFUCache test = new LFUCache(2);
		test.put(1, 1);
		test.put(2, 2);
		System.out.println(test.get(1));
		test.put(3, 3);
		System.out.println(test.get(2));
		System.out.println(test.get(3));
		test.put(4, 4);
		System.out.println(test.get(1));
		//test.put(1, 1000);
		//test.put(4, 4);
		System.out.println(test.get(3));
		System.out.println(test.get(4));
	}
}