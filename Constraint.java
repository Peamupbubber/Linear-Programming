public class Constraint {
    private LinearSum left;
    private LinearSum right;
    Comparison op;

    public Constraint() {
        left = null;
        right = null;
    }

    public void setLeftLinearSum(LinearSum ls) {
        left = ls;
    }

    public void setRightLinearSum(LinearSum ls) {
        right = ls;
    }

    //Simplify the two sides of the equation
    private void simplify() {
        System.out.println("Simplifying constraint");
    }

    public void display() {
        if(left != null)
            left.display();

        if(right != null)
            right.display();
    }
}
