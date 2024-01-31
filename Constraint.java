import java.util.HashMap;
import java.util.Map;

public class Constraint {
    private LinearSum left;
    private LinearSum right;
    private Comparison op;

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

    // Simplify the two sides of the equation
    public void simplify() {
        HashMap<String, Double> leftMap = left.buildMap();
        HashMap<String, Double> rightMap = right.buildMap();

        // Convert the two maps into one simplified map on the left
        for(Map.Entry<String, Double> set : rightMap.entrySet()) {
            if(leftMap.get(set.getKey()) == null) {
                leftMap.put(set.getKey(), -1 * set.getValue());
            }
            else {
                leftMap.put(set.getKey(), leftMap.get(set.getKey()) - set.getValue());
            }
        }

        // Assign the right linear sum to the simplified constant
        Double constant = leftMap.get("");
        if(constant == null)
            right = new LinearSum("", 0);
        else
            right = new LinearSum("", -1 * constant);

        // Remove the constant from the left linear sum and assign the left linear sum
        leftMap.remove("");
        left = new LinearSum(leftMap);
    }

    // For debugging
    private void printMap(HashMap<String, Double> map) {
        System.out.println("---Printing constraint map---");
        for(Map.Entry<String, Double> set : map.entrySet()) {
            System.out.println(set.getKey() + ", " + set.getValue());
        }
    }

    public void display() {
        left.display();
        
        System.out.print(" " + comparisonOpNames.get(op) + " ");

        right.display();

        System.out.println();
    }
}
