import java.util.LinkedList;
import java.util.Queue;

/*二叉树的基本知识
 */
class Node {
    public char val;
    public Node left;
    public Node right;

    public Node(char val) {
        this.val = val;
    }
}
public class TestTree {
    public static Node build() {
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = g;
        g.right = h;
        c.right = f;

        return a;
    }

    //先序遍历
    public static void preOrder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历
    public static void inOrder(Node root) {
        if(root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    //后序遍历
    public static void postOrder(Node root) {
        if(root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    //层序遍历
    public static  void levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.val + " ");
            if(cur.left != null) {
                queue.offer(cur.left);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    public static int size(Node root) {
        if(root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    public static int leafsize(Node root) {
        if(root== null) {
            return 0;
        }
        if(root.right == null && root.left == null) {
            return 1;
        }
        return leafsize(root.right) + leafsize(root.left);
    }

    public static int ksize(Node root, int k) {
        if(k < 1 || root == null) {
            return 0;
        }
        if(k == 1) {
            return 1;
        }
        return ksize(root.left,k - 1) + ksize(root.right, k - 1);
    }

    Node find(Node root,char toFind) {
        if(root == null) {
            return null;
        }
        if(root.val == toFind) {
            return root;
        }
        Node result = find(root.left, toFind);
        if(result != null) {
            return result;
        }
        return find(root.right, toFind);
    }

    //比较两个树是否相等
    public boolean isSameTree(Node p, Node q) {
        if(p == null && q == null) {
            return true;
        }
        if((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if(p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //判断t是否为s的一个子树
    public boolean isSubTree(Node t, Node s) {
        if(s == null && t == null) {
            return true;
        }
        if(s == null || t == null) {
            return false;
        }
        boolean ret = false;
        if(s.val == t.val) {
            ret = isSubTree(t, s);
        }
        if(!ret) {
            ret = isSubTree(s.left,t);
        }
        if(!ret) {
            ret = isSubTree(s.right,t);
        }
        return ret;
    }

    //求树的最大深度
    public int maxDepth(Node root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        return 1 + (maxDepth(root.right) > maxDepth(root.left) ? maxDepth(root.right) : maxDepth(root.left));
    }

    //树是否是平衡的
    public boolean isBalanced(Node root) {
        if(root == null) {
            return true;
        }
        if(root.left == null && root.right == null) {
            return true;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if((leftDepth - rightDepth) < -1 || (leftDepth - rightDepth) > 1) {
            return false;
        }
        return isBalanced(root.right) && isBalanced(root.left);
    }

    //树是不是镜像对称
    public boolean isSymmetric(Node root) {
        if(root == null) {
            return true;
        }
        return isMirror(root.right, root.left);
    }

    private boolean isMirror(Node t1, Node t2) {
        if(t1 == null && t2 == null) {
            return true;
        }
        if(t1 == null || t2 == null) {
            return false;
        }
        if(t1.val != t2.val) {
            return false;
        }
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        Node root = build();
        System.out.println("节点个数为:" + size(root));
        System.out.println("先序遍历:");
        preOrder(root);
        System.out.println();

        System.out.println("中序遍历:");
        inOrder(root);
        System.out.println();

        System.out.println("后续遍历:");
        postOrder(root);
        System.out.println();

        System.out.println("层序遍历:");
        levelOrder(root);
    }
}
