import java.util.Objects;

public class BST {

    MyTreeNode root;

    public BST() {
        this.root = null;
    }

    public void add(Integer data) throws Exception {
        this.root = add(root, data);
    }

    private MyTreeNode add(MyTreeNode node, Integer data) throws Exception {
        if (node == null) {
            return new MyTreeNode(data);
        } else if (node.getData().equals(data)) {
            throw new Exception("Item is already in the tree");
        }

        if (data < node.getData()) {
            node.setLeft(add(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(add(node.getRight(), data));
        }
        return node;
    }

    public void remove(Integer data) throws Exception{
        root = remove(root, data);
    }

    public MyTreeNode remove(MyTreeNode node, Integer data) throws Exception{
        if (node == null) {
            return null;
        }
        else if (Objects.equals(data, node.getData())) { // we found the node
            if (node.getLeft() == null && node.getRight() == null) {    // no child
                return null;
            }
            else if (node.getLeft() != null && node.getRight() != null) {   // two children
                Integer temp = successor(node).getData();
                remove(temp);
                node.setData(temp);
                return node;
            }
            else {
                if (node.getLeft() != null) { // if its child is at left
                    node = node.getLeft();
                }
                else { // if its child is at right
                    node = node.getRight();
                }
                return node;
            }
        }
        else { // we did not find the node just yet
            if (node.getLeft() == null && node.getRight() == null) {
                throw new Exception("Number to be removed does not exist!");
            }
            else if (data < node.getData()) {
                node.setLeft(remove(node.getLeft(), data));
            }
            else {
                node.setRight(remove(node.getRight(), data));
            }
            return node;
        }
    }

    private MyTreeNode successor(MyTreeNode node) {
        if (node == null) {
            return null;
        }
        return min(node.getRight());
    }

    private MyTreeNode min(MyTreeNode node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;
        } else {
            return min(node.getLeft());
        }
    }


    public boolean contains(Integer data) {
        return contains(root, data);
    }

    private boolean contains(MyTreeNode root, Integer data) {
        if (root == null) {
            return false;
        } else if (root.getData() > data) {
            return contains(root.getLeft(), data);
        } else if(root.getData() < data)  {
            return contains(root.getRight(), data);
        } else {
            return true;
        }
    }

    public String toString() {
        return toString(root);
    }

    String toString(MyTreeNode root) {
        if (root == null) {
            return "";
        } else {
            return toString(root.getLeft()) + root.getData() + " " + toString(root.getRight());
        }
    }
}