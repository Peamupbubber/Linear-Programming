/* LE = CONSTANT * VARIABLE OP LE
 * ex: 2x - 3y - 4
 *     2, x, (-3, y, (4, null, (null)))
 *      
 */

public class LinearSum {
    public LinearSumNode node;

    public LinearSum() {
        node = null;
    }

    // public void display() {
        
    // }
    
    public void addNode() {
        LinearSumNode temp = node;
        while(temp != null && temp.next != null) {
            temp = temp.next;
        }
        temp.next = new LinearSumNode();
    }
}

class LinearSumNode {
    public double constant;
    public String variable;

    public LinearSumNode next;

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

    public double getConstant() {
        return constant;
    }

    public void display() {
        System.out.println(constant + "*" + variable + "+ ");

        if(next != null) {
            next.display();
        }
    }
}