/* LE = CONSTANT * VARIABLE OP LE
 * ex: 2x - 3y - 4
 *     2, x, (-3, y, (4, null, (null)))
 *      
 */ 

public class LinearSum {
    private float constant;
    private String variable;

    private LinearSum next;

    public LinearSum() {
        setConstant("1");
        setVaraible("x");
    }

    public LinearSum(String constant, String variable) {
        setConstant(constant);
        this.variable = variable;
    }

    //Takes in a string and sets the constant to a float, can be given as "(x/y)", "x.y", or "x"
    public void setConstant(String constant) {
        String[] frac = constant.split("/");
        if(frac.length == 2) {
            this.constant = Integer.parseInt(frac[0].substring(1)) / (float)Integer.parseInt(frac[1].substring(0, frac[1].length() - 1));
        }
        else {
            this.constant = Float.parseFloat(constant);
        }
    }

    public void setVaraible(String variable) {
        this.variable = variable;
    }

    public void setNext(LinearSum next) {
        this.next = next;
    }

    public float getConstant() {
        return constant;
    }
}