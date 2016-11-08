public class MyTreeNode {
  private String data;
  private MyTreeNode left;
  private MyTreeNode right;

  public MyTreeNode(String data, MyTreeNode left, MyTreeNode right) {

    this.data = data;
    this.left = left;
    this.right = right;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
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
