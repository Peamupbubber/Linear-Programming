import java.util.HashMap;

public class Constraint {
    private LinearSum left;
    private LinearSum right;
    Comparison op;

    private HashMap<Comparison, String> comparisonOpNames;

    public Constraint() {
        left = null;
        right = null;

        initCompOpNames();
    }

    private void initCompOpNames() {
        comparisonOpNames = new HashMap<Comparison, String>();

        comparisonOpNames.put(Comparison.EQ, "==");
        comparisonOpNames.put(Comparison.LESS, "<");
        comparisonOpNames.put(Comparison.GREATER, ">");
        comparisonOpNames.put(Comparison.LESSEQ, "<=");
        comparisonOpNames.put(Comparison.GREATEREQ, ">=");
    }

    public void setLeftLinearSum(LinearSum ls) {
        left = ls;
    }

    public void setRightLinearSum(LinearSum ls) {
        right = ls;
    }

    //Perhaps change this to a hashmap system?
    public void setComparisonOp(char c) {
        switch(c) {
            case '=':
                op = Comparison.EQ;
                break;
            case '<':
                op = Comparison.LESS;
                break;
            case '>':
                op = Comparison.GREATER;
                break;
            case 'L':
                op = Comparison.LESSEQ;
                break;
            case 'G':
                op = Comparison.GREATEREQ;
                break;
            default:
                System.out.println("Error: invlid comparison op " + c);
                break;
        }
    }

    //Simplify the two sides of the equation
    private void simplify() {
        System.out.println("Simplifying constraint");
    }

    public void display() {
        System.out.println();
        left.display();
        
        System.out.print(" " + comparisonOpNames.get(op) + " ");

        right.display();

        System.out.println();
    }
}
