import java.util.HashMap;
import java.util.Map;

public class LinearSum {
    public LinearSumNode node;

    public LinearSum() {
        node = new LinearSumNode();
    }

    public LinearSum(String var, double con) {
        addNode(var, con);
    }

    public LinearSum(HashMap<String, Double> map) {
        convertMapToLS(map);
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

    public void addNode(String var, double con) {
        if(node == null)
            node = new LinearSumNode();
        else {
            LinearSumNode newNode = new LinearSumNode();
            newNode.next = node;
            node = newNode;
        }
        node.variable = var;
        node.constant = con;
    }

    // Only used to create a new LinearSum
    private void convertMapToLS(HashMap<String, Double> map) {
        for(Map.Entry<String, Double> set : map.entrySet()) {
            addNode(set.getKey(), set.getValue());
        }
    }

    // Simplifies the linear sum, ensuring all like variables are combined
    public void simplify() {
        HashMap<String, Double> map = buildMap();
        node = null;
        convertMapToLS(map);
    }

    public HashMap<String, Double> buildMap() {
        HashMap<String, Double> productMap = new HashMap<String, Double>();

        LinearSumNode temp = node;

        while(temp != null) {
            if(productMap.get(temp.variable) == null)
                productMap.put(temp.variable, temp.constant);
            else
                productMap.put(temp.variable, productMap.get(temp.variable) + temp.constant);

            temp = temp.next;
        }

        return productMap;
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
        this.constant = con;
    }

    public double getConstant() {
        return constant;
    }
}