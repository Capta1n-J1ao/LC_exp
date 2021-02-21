package amazon;

/*
这道题目二刷的时候思路和以前不一样了，想了个其他办法更好理解

这人的思路和我最像：
https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236java-da-bai-bai-fen-zhi-100di-gui-xiang-jie-by-/
* */

public class lc236 {
    private TreeNode res;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = null;
        if(contains(root, p, q)) return res;
        return res;
    }

    private boolean contains(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return false;
        System.out.println("TreeNode = " + root.val);
        boolean left = contains(root.left, p, q);
        boolean right = contains(root.right, p, q);
//        五种情况
        if((root == p && right) || (root == q && right) || (root == p && left) || (root == q && left) || (left && right)){
            res = root;
            return true;
        }
//        不满足以上五种，就看是不是单边有满足的,少一个都是错的，后两个是前两个判断项的前提，
//        否则递归不下去，可以理解为前两项其实是base case
//        如果硬要区分下面这句也可以，但是要写在上面五个条件的下面才行，否则就会误判断了
//        if(root == p || root == q) return true;
        return  root == p || root == q || left || right;
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
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(7);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(0);
        TreeNode n7 = new TreeNode(8);
        TreeNode n8 = new TreeNode(6);
        TreeNode n9 = new TreeNode(2);
        n1.left = n2;
        n1.right = n3;
        n3.left = n6;
        n3.right = n7;
        n2.left = n8;
        n2.right = n9;
        n9.left = n4;
        n9.right = n5;
        TreeNode res = new lc236().lowestCommonAncestor(n1, n1.left, n1.left.right.right);
        System.out.println(res.val);
    }
}
