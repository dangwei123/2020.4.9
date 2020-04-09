给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

数学表达式如下:

如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int a=Integer.MAX_VALUE;
        int b=Integer.MAX_VALUE;
        for(int num:nums){
            if(num<=a){
                a=num;
            }else if(num<=b){
                b=num;
            }else{
                return true;
            }
        }
        return false;
    }
}

给定一个二叉树，返回它的中序 遍历。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode node=stack.pop();
            res.add(node.val);
            if(node.right!=null){
                cur=node.right;
            }
        }
        return res;
    }
}

给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>>res=new ArrayList<>();
        if(root==null) return res;
        Deque<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        boolean left=true;
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> row=new ArrayList<>();
            if(left){
                while(size--!=0){
                    TreeNode cur=queue.pollFirst();
                    row.add(cur.val);
                    if(cur.left!=null){
                        queue.offerLast(cur.left);
                    }
                    if(cur.right!=null){
                        queue.offerLast(cur.right);
                    }
                }
                left=false;
            }else{
                while(size--!=0){
                    TreeNode cur=queue.pollLast();
                    row.add(cur.val);
                    if(cur.right!=null){
                        queue.offerFirst(cur.right);
                    }
                    if(cur.left!=null){
                        queue.offerFirst(cur.left);
                    }
                }
                left=true;
            }
            res.add(row);
        }
        return res;
    }
}

根据一棵树的前序遍历与中序遍历构造二叉树。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(preorder,inorder,0,inorder.length-1);
        
    }
    private TreeNode build(int[] preorder,int[] inorder,int left,int right){
        if(index==inorder.length||left>right){
            return null;
        }
        TreeNode root=new TreeNode(preorder[index]);
        int in_in=map.get(preorder[index++]);
        root.left=build(preorder,inorder,left,in_in-1);
        root.right=build(preorder,inorder,in_in+1,right);
        return root;
        
    }
    
}

给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点.
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root==null){
            return null;
        }
        if(root.left!=null){
            if(root.right!=null){
                root.left.next=root.right;
            }else{
                root.left.next=getNext(root.next);
            }
        }
        if(root.right!=null){
            root.right.next=getNext(root.next);
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
    private Node getNext(Node root){
        if(root==null) return null;
        if(root.left!=null) return root.left;
        if(root.right!=null) return root.right;
        if(root.next!=null) return getNext(root.next);
        return null;
    }
}

给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int count;
    private int res;
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        inorder(root);
        return res;
    }
    private void inorder(TreeNode root){
        if(root==null){
            return ;
        }
        inorder(root.left);
        count--;
        if(0==count){
            res=root.val;
            return ;
        }
        inorder(root.right);
    }
}

