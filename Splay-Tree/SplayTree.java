public class SplayTree {

    private MyTreeNode root;

    public SplayTree() {
        this.root = null;
    }

    // convenience function for testing
    public Integer root() {
        return root.getData();
    }

    // - copied from BinarySearchTree for convenience
    // - !not correct splay add
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

    public boolean splay(Integer data) {
        while (!(root.getData().equals(data))){
            this.root = splay(data, null, root);
        }
        return false;
    }

    private MyTreeNode splay(Integer data, MyTreeNode parent, MyTreeNode node) {
        // ZIG
        if (node.getLeft() != null && node.getLeft().getData().equals(data)) {
            if (parent == null) {
                return rotate(node, 2);
            } else {
                parent.setLeft(rotate(node, 2));
                System.out.println("PROCESSING: ");
                print();
                return parent.getLeft();
            }
        } else if (node.getRight() != null && node.getRight().getData().equals(data)) {
            if (parent == null) {
                return rotate(node, 1);
            } else {
                parent.setRight(rotate(node, 1));
                System.out.println("PROCESSING: ");
                print();
                return parent;
            }
        } else {
             if (data < node.getData()) {
                 return splay(data, node, node.getLeft());
             } else {
                 return splay(data, node, node.getRight());
             }
        }
    }

    private MyTreeNode rotate(MyTreeNode node, int num) {
        MyTreeNode b;

        switch (num) {
            case 1:  // left rotate
                MyTreeNode x = node.getRight();
                b = x.getLeft();
                x.setLeft(node);
                node.setRight(b);

                return x;
            case 2:  // right rotate
                MyTreeNode y = node.getLeft();
                b = y.getRight();
                y.setRight(node);
                node.setLeft(b);

                return y;
            default:
                return null;
        }
    }

    public String toString() {
        return toString(root);
    }

    private String toString(MyTreeNode root) {
        if (root == null) {
            return "";
        } else {
            return toString(root.getLeft()) + root.getData() + " " + toString(root.getRight());
        }
    }

    void print() {
        if (root != null) {
            root.print();
        }
    }


    private class MyTreeNode {
        private Integer data;
        private MyTreeNode left;
        private MyTreeNode right;

        public MyTreeNode(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void print() {
            print("", true);
        }

        private void print(String prefix, boolean isTail) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + data);
            if (right != null) {
                right.print(prefix + (isTail ? "    " : "│   "), false);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + (isTail ? "└── " : "├── "));
            }

            if (left != null) {
                left.print(prefix + (isTail ? "    " : "│   "), false);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + (isTail ? "└── " : "├── "));
            }
        }

        public MyTreeNode(Integer data, MyTreeNode left, MyTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public MyTreeNode getLeft() {
            return left;
        }

        public void setLeft(MyTreeNode left) {
            this.left = left;
        }

        public MyTreeNode getRight() {
            return right;
        }

        public void setRight(MyTreeNode right) {
            this.right = right;
        }

    }
}