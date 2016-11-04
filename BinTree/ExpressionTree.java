import java.util.Stack;

/**
 * Created by Jay Vince Serato and Wina Gen Sotto on November 04, 2016
 */
 
public class ExpressionTree {

    public static int eval(MyTreeNode root, Stack<Integer> var) {
        if (root.getLeft() == null) { // if root is operand
            var.push(Integer.valueOf(root.getData()));
            return Integer.parseInt(root.getData());
        }

        else { // if root is operator
            eval(root.getRight(), var);
            eval(root.getLeft(), var);
            int ans = 0;
            switch (root.getData()) {
                case "+":
                    ans = var.pop() + var.pop();
                    break;
                case "*":
                    ans = var.pop() * var.pop();
                    break;
                case "-":
                    ans = var.pop() - var.pop();
                    break;
                case "/":
                    ans = var.pop() / var.pop();
                    break;
                case "^":
                    ans = 1;
                    int base = var.pop();
                    int pow = var.pop();
                    for (int i = 0; i < pow; i++) {
                        ans *= base;
                    }
                    break;
                default:
                    // do nothing
            }
            var.push(ans);
            return var.peek();
        }
    }
}
