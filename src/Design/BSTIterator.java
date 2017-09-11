package Design;
import java.util.Stack;

	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val){
			this.val = val;
		}
	}
	
	public class BSTIterator {
		
	private Stack<TreeNode> stack = new Stack<>();
	public BSTIterator(TreeNode root) {
        TreeNode cur = root;
        while(cur!=null){				//store all the left ones
        	stack.push(cur);
        	cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode cur = node.right;
        if(cur!=null){			//if right not null, so the next smallest,the right is smaller than the parent;
        	while(cur!=null){		//do the same thing, store all the left ones;
        		stack.push(cur);
        		cur = cur.left;
        	}
        }
        return node.val;
    }
    
    public static void main(String[] args){
    	TreeNode root = new TreeNode(1);
    	root.left = null;
    	root.right = new TreeNode(2);
    	BSTIterator test = new BSTIterator(root);
    	System.out.println(test.next());
    	System.out.println(test.hasNext());
    	System.out.println(test.next());
    }
}
