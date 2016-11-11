public class MyTreeNode {
  private Integer data;
  private MyTreeNode left;
  private MyTreeNode right;

  public MyTreeNode(Integer data) {
      this(data, null, null);
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
