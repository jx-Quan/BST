import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }
    private Node root;
    private int size;

    public BST(){
        root=null;
        size=0;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    //向二分搜索树中添加新的元素e
    public void add(E e){
        if(root==null){
            root=new Node(e);
            size++;
        }
        else
            add(root,e);
    }
    //向以node为根的二分搜索树中插入元素e，递归算法
    /*private void add(Node node,E e){
        if(e.equals(node.e)) return;
        else if(e.compareTo(node.e)<0&&node.left==null){
            node.left=new Node(e);
            size++;
            return;
        }
        else if(e.compareTo(node.e)>0&&node.right==null){
            node.right=new Node(e);
            size++;
            return;
        }
        if(e.compareTo(node.e)<0)
            add(node.left,e);
        else if(e.compareTo(node.e)>0)
            add(node.right,e);
    }*/
    //向以node为根的二分搜索树中插入元素e，递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node,E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0)
            node.left=add(node.left,e);
        else if(e.compareTo(node.e)>0)
            node.right=add(node.right,e);
        return node;
    }
    //向以node为根的二分搜索树中插入元素e，非递归算法
    public void add2(E e){
        //二分搜索树为空时，直接让root指向新的节点
        if(root==null){
            root=new Node(e);
            size++;
            return;
        }
        //用p来跟踪待插入节点的上一个节点，p的作用相当于pre的作用
        Node p=root;
        while(p!=null) {
            if (e.compareTo(p.e)<0) {
                if(p.left==null) {
                    p.left = new Node(e);
                    size++;
                    return;
                }
                p=p.left;
            }
            else if(e.compareTo(p.e)>0){
                if(p.right==null) {
                    p.right = new Node(e);
                    size++;
                    return;
                }
                p=p.right;
            }
            else return;
        }
    }
    //看二分搜索树中是否包含e
    public boolean contains(E e){
        return contains(root,e);
    }
    private boolean contains(Node node,E e){
        if(node==null)
            return false;
        if(e.compareTo(node.e)==0)
            return true;
        else if(e.compareTo(node.e)<0)
            return contains(node.left,e);
        else //(e.compareTo(node.e)>0)
            return contains(node.right,e);
    }
    //二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }
    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node){
       if(node==null)
            return;
       System.out.println(node.e);
       preOrder(node.left);
       preOrder(node.right);
    }
    //前序遍历以node为根的二分搜索树，非递归算法
    public void preOrderNR(){
        Stack<Node>stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur=stack.pop();
            System.out.println(cur.e);

            if(cur.right!=null)
                stack.push(cur.right);
            if(cur.left!=null)
                stack.push(cur.left);
        }
    }
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node==null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node==null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public  void levelOrder(){
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if(cur.left!=null)
                queue.add(cur.left);
            if(cur.right!=null)
                queue.add(cur.right);
        }
    }
    //寻找二分搜索树最小元素
    public E minnum(){
        if(size==0)
            throw new IllegalArgumentException("BST is empty!");
        return minnum(root).e;
    }
    //返回以node为根的二分搜索树的最小值所在节点
    private Node minnum(Node node){
        if(node.left==null)
            return node;
        return minnum(node.left);
    }
    //寻找二分搜索树最大元素
    public E maxnum(){
        if(size==0)
            throw new IllegalArgumentException("BST is empty!");
        return maxnum(root).e;
    }
    //返回以node为根的二分搜索树的最大值所在节点
    private Node maxnum(Node node){
        if(node.right==null)
            return node;
        return maxnum(node.right);
    }
    //从二分搜索树中删除最小节点，返回最小值
    public E removeMin(){
        E ret=minnum();
        removeMin(root);
        return ret;
    }
    //删除掉以node为根的二分搜索树的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        if(node.left==null){
            Node rightNode=node.right;
            node.right=null;
            size--;
            return rightNode;
        }
        node.left=removeMin(node.left);
        return node;
    }
    //从二分搜索树中删除最大节点，返回最大值
    public E removeMax(){
        E ret=maxnum();
        removeMax(root);
        return ret;
    }
    //删除掉以node为根的二分搜索树的最大节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        if(node.right==null){
            Node leftNode=node.left;
            node.left=null;
            size--;
            return leftNode;
        }
        node.right=removeMax(node.right);
        return node;
    }
    //从二分搜索树中删除元素为e的节点
    public void remove(E e){
        root=remove(root,e);
    }
    //删除以node为根的二分搜索树中值为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node,E e){
        if(node==null)
            return null;
        if(e.compareTo(node.e)<0){
            node.left=remove(node.left,e);
            return node;
        }
        else if(e.compareTo(node.e)>0){
            node.right=remove(node.right,e);
            return node;
        }
        else{
            if(node.left==null){
                Node rightNode=node.right;
                node.right=null;
                size--;
                return rightNode;
            }
            if(node.right==null){
                Node leftNode=node.left;
                node.left=null;
                size--;
                return leftNode;
            }
            Node successor=minnum(node.right);
            successor.right=removeMin(node.right);
            successor.left=node.left;
            node.left=node.right=null;
            return  successor;
        }
    }
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }
    //生成一个node为根节点，深度为的普通话的描述二叉树的字符串
    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node==null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }
    private String generateDepthString(int depth){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<depth;i++)
            res.append("--");
        return res.toString();
    }
}
