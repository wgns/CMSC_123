/**
 * Created by Sotto and Serato on November 08, 2016.
 */
public class BST {
    MyTreeNode root;

    public BST() {
        root = null;
    }

    public BST(MyTreeNode root) {
        this.root = root;
    }

    public MyTreeNode add (String data) throws Exception {
        return add(root, data);
    }

    private MyTreeNode add (MyTreeNode node, String data) throws Exception {
        if (node == null || root == null) {
            if (root == null) {
                root = new MyTreeNode(data, null, null);
            }
            return new MyTreeNode(data, null, null);
        }
        else if (node.getData().equals(data)) {
            throw new Exception("Error: " + data + " already added.");
        }
        else {
            if (Integer.parseInt(data) < Integer.parseInt(node.getData())) {
                node.setLeft(add(node.getLeft(), data));
            }
            else {
                node.setRight(add(node.getRight(), data));
            }
        }
        return node;
    }

    public String toString(MyTreeNode root)
    {
        String result = "";
        if (root == null)
            return "";
        result += toString(root.getLeft());
        result += root.getData() + " ";
        result += toString(root.getRight());
        return result;
    }
}
