package Design;
import java.util.Stack;
/**
 * 
 * @author koutei
 *
 */
public class MinStack {
	int min = Integer.MAX_VALUE;
	Stack<Integer> stack = new Stack<>();
	public MinStack() {
        
    }
    
    public void push(int x) {
        if(x<=min){
        	stack.push(min);
        	min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if(stack.pop()==min)   //at least pop() one, if == min, need pop() again. otherwise not do min = ...
        	min = stack.pop();  //if(stack.peek() == min) stack.pop(); min = stack.pop(); else stack.pop(); better understand
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
    
    public static void main(String[] args){
    	MinStack test = new MinStack();
    	test.push(-2);
    	test.push(0);
    	test.push(-3); 
    	System.out.println(test.getMin());
    	test.pop();
    	System.out.println(test.top());
    	System.out.println(test.getMin());
    }
}
