/* LE = CONSTANT * VARIABLE OP LE
 * ex: 2x - 3y - 4
 *     2, x, (-3, y, (4, null, (null)))
 *      
 */ 

public class LinearSum {
    public double constant;
    private String variable;

    public LinearSum next;

    public LinearSum() {
        constant = 1.0;
        variable = "";
    }

    public void setFractionConstant(int numerator, int denominator) {
        constant = (double)numerator / denominator;
    }

    public void setDecimalConstant(int beforeDecimal, int afterDecimal) {
        // constant = numerator / denominator;
        int len = String.valueOf(afterDecimal).length();
        System.out.println(len);
        constant = beforeDecimal + (afterDecimal / Math.pow(10, len));
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void setNext(LinearSum next) {
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