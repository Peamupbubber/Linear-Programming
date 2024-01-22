public class LinearSum {
    public LinearSumNode node;

    public LinearSum() {
        node = new LinearSumNode();
    }

    public void display() {
        LinearSumNode temp = node;
        while(temp != null) {
            if(temp.constant != 1.0 || temp.variable.equals("")) {
                System.out.print(temp.constant);
                if(!temp.variable.equals(""))
                    System.out.print("*");
            }
            if(!temp.variable.equals(""))
                System.out.print(temp.variable);

            if(temp.next != null) {
                System.out.print(" + ");
            }
            
            temp = temp.next;
        }
    }

    // Creates a reverse linked list of linear sum nodes, this is not a problem since addition is communitive
    public void addNode() {
        if(node == null)
            node = new LinearSumNode();
        else {
            LinearSumNode newNode = new LinearSumNode();
            newNode.next = node;
            node = newNode;
        }
    }

    public void setFractionConstant(int numerator, int denominator) {
        node.setFractionConstant(numerator, denominator);
    }

    public void setDecimalConstant(int beforeDecimal, int afterDecimal) {
        node.setDecimalConstant(beforeDecimal, afterDecimal);
    }

    public void setConstant(double con) {
        node.setConstant(con);
    }

    public void negateConstant() {
        node.constant *= -1;
    }

    public void setVariable(String var) {
        node.setVariable(var);
    }
}

class LinearSumNode {
    protected double constant;
    protected String variable;

    protected LinearSumNode next;

    public LinearSumNode() {
        constant = 1.0;
        variable = "";
    }

    public void setFractionConstant(int numerator, int denominator) {
        constant = (double)numerator / denominator;
    }

    public void setDecimalConstant(int beforeDecimal, int afterDecimal) {
        int len = String.valueOf(afterDecimal).length();
        constant = beforeDecimal + (afterDecimal / Math.pow(10, len));
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void setNext(LinearSumNode next) {
        this.next = next;
    }

    public void setConstant(double con) {
        // if(this.constant == 1.0) //TEMP FIX, dont need anymore?
            this.constant = con;
    }

    public double getConstant() {
        return constant;
    }
}