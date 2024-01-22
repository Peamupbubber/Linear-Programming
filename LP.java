import java.util.List;
import java.util.ArrayList;

public class LP {
    public static LP lp;

    private List<String> variables;
    private List<Constraint> constraints;
    private LinearSum objectiveFunction;

    public LP(String file) throws Exception {
        System.out.println("\nInit Linear Program");

        if(lp == null)
            lp = this;

        variables = new ArrayList<String>();
        constraints = new ArrayList<Constraint>();
        objectiveFunction = null;

        FileReader.fileReader = new FileReader(file);
    }

    public void setObjectiveFunction(LinearSum objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }

    public void addVariable(String variable) {
        variables.add(variable);
    }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    public void display() {
        displayVariables();
        displayObjectiveFunction();
        displayConstraints();
    }

    public void displayObjectiveFunction() {
        System.out.println();
        objectiveFunction.display();
    }

    public void displayVariables() {
        System.out.println();
        for(int i = 0; i < variables.size(); i++) {
            System.out.print(variables.get(i));
            if(i != variables.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
    }

    public void displayConstraints() {
        System.out.println();
        for(Constraint c : constraints) {
            c.display();
        }
    }
}
