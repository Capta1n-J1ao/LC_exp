package amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc94 {
    private List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
//        stack.addFirst(root);
        //stack.push(root);
        //注意这里的条件不一样，因为root在不停变化
//        还有不能用root.left != null来做条件，一定要用root本身来做条件
//        下面这个testcase就是很好的例子
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.addFirst(root);
                root = root.left;
            }
            TreeNode temp = stack.pollFirst();
            res.add(temp.val);
            root = temp.right;
        }
        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;
        System.out.println(new lc94().inorderTraversal(n1));
    }
}
