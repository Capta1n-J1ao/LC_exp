package amazon;

/*
和lc124/543一起服用,顺序的话687/543先做，特别是543的视频要看！然后124最后做即可
下面这个题解最好懂，而且和lc124的思路也吻合
https://leetcode-cn.com/problems/longest-univalue-path/solution/jian-dan-yi-dong-ban-by-a380922457-7/
* */

public class lc687 {
    private int res ;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
//        一开始想用的思路是helper(TreeNode root, int ref)，其中ref是上一层的数，
//        但是这样是不行的，会有问题，就差这一个代码就不一样了，还是要在当层做处理。
        helper(root);
        return res ;
    }

    private int helper(TreeNode root){
        if(root == null) return 0;
        int rootRes = 0;
        int leftRes = helper(root.left);
//        System.out.println("rootval是 " + root.val + " left " + leftRes);
        int rightRes = helper(root.right);
//        System.out.println("rootval是 " + root.val + " right " + rightRes);

        leftRes = root.left != null && root.val == root.left.val ? leftRes + 1 : 0;
        rightRes = root.right != null && root.val == root.right.val ? rightRes + 1 : 0;
        res = Math.max(res, leftRes + rightRes);
//        helper函数的作用就是为了算出当前节点的深度，那么返回的就是左右孩子的最大深度，这样就是当前节点的深度
        return Math.max(leftRes, rightRes);
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
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(4);
        TreeNode r3 = new TreeNode(5);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(4);
        TreeNode r6 = new TreeNode(5);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        System.out.println(new lc687().longestUnivaluePath(r1));
    }
}
