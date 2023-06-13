public class Tableau {
    private float[][] tableau;

    private int constraints;
    private int variables;

    private int rows;
    private int columns;

    public Tableau(int m, int n) {
        constraints = m;
        variables = n;

        rows = constraints + 1;
        columns = variables + 1;

        tableau = new float[rows][columns];
    }

    public float getOptimalValue() {
        return -1 * tableau[0][0];
    }

    public void pivot(int i, int j) {
        if(i < 1 || i >= rows || j < 1 || j >= columns) {
            System.out.println("Index out of bound in pivot: (" + i + ", " + j + ")");
            return;
        }

        float[] choices = new float[rows];
        
        for(int m = 0; m < rows; m++) {
            if(m == i)
                choices[m] = -1 * tableau[i][j];
            else
                choices[m] = -1 * (tableau[m][j] / tableau[i][j]);
        }

        for(int m = 0; m < rows; m++) {
            for(int n = 0; n < columns; n++) {
                tableau[m][n] += choices[m];
            }
        }
    }
}
