public class LinearProgramParser {

    public class LValues {
        public String sValue;
        public char cValue;
        public int iValue;

        public double dValue;

        public Constraint currentConstraint;
        public LinearSum currentLinearSum;
    }

    public static LinearProgramParser lpParser;

    public LValues lValues;

    private Token currentToken;
    private boolean userProgramHasErrors;

    public LinearProgramParser() {
        System.out.println("Init Linear Program Parser");

        if(lpParser == null)
            lpParser = this;

        lValues = new LValues();
        userProgramHasErrors = false;
    }

    // Matches the current token with the given token, and gets the next token from the scanner
    // Reports an error if the tokens do not match
    private void match(Token tok) {
        if(currentToken == tok) {
            currentToken = LinearProgramScanner.lpScanner.scanner();
        }
        else {
            userProgramHasErrors = true;
        }
    }

    // Returns true on success
    public boolean parse() {
        currentToken = LinearProgramScanner.lpScanner.scanner();

        LP();

        if(currentToken != Token.SCANEOF)
            userProgramHasErrors = true;

        if(userProgramHasErrors) {
            System.out.println("Error: invalid sytax in user program");
            //Could implement line tracking to see where the syntax error occurred
        }
        else {
            System.out.println("This is a valid user LP");
        }

        return !userProgramHasErrors;
    }

    private void LP() {
        var_list();
        obj_fn();
        constr_list();
    }

    // Store variables in the LP
    private void var_list() {
        var();
        LP.lp.addVariable(lValues.sValue);
        while(currentToken == Token.VAR) {
            var();
            LP.lp.addVariable(lValues.sValue);
        }
    }

    private void var() {
        match(Token.VAR);
        match(Token.COLON);
        match(Token.ID);
        //Non neg would be here
        match(Token.SEMI);
    }

    private void obj_fn() {
        match(Token.OPT);
        match(Token.COLON);
        linear_sum();
        LP.lp.setObjectiveFunction(lValues.currentLinearSum);
        match(Token.SEMI);
    }

    private void linear_sum() {
        lValues.currentLinearSum = new LinearSum();
        product();
        while(currentToken == Token.ADD) {
            match(Token.ADD);
            char op = lValues.cValue;
            lValues.currentLinearSum.addNode();
            product();
            if(op == '-') {
                lValues.currentLinearSum.negateConstant();
            }
        }
    }

    private void product() {
        // x
        if(currentToken == Token.ID) {
            match(Token.ID);
            lValues.currentLinearSum.setVariable(lValues.sValue);
        }

        // cx || c*x
        else {
            pcoef();
            if(currentToken == Token.MUL)
                match(Token.MUL);

            if(currentToken == Token.ID) {
                match(Token.ID);
                lValues.currentLinearSum.setVariable(lValues.sValue);
            }
        }
    }

    private void pcoef() {
        switch (currentToken) {
            // -c
            case ADD:
                match(Token.ADD);
                scoef();
                if(lValues.cValue == '-') {
                    lValues.currentLinearSum.negateConstant();
                }
                break;

            // (-c) || (c)
            case LPAR:
                match(Token.LPAR);
                if(currentToken == Token.ADD) {
                    match(Token.ADD);
                }
                
                scoef();
                if(lValues.cValue == '-') {
                    lValues.currentLinearSum.negateConstant();
                }
                match(Token.RPAR);
                break;
        
            // c
            default:
                scoef();
                break;
        }
    }

    // Matches single coefficient or a fraction
    private void scoef() {
        coef();
        double numerator = lValues.dValue;

        if(currentToken == Token.SLASH) {
            match(Token.SLASH);
            coef();
            lValues.currentLinearSum.setConstant(numerator / lValues.dValue);
        }
        else
            lValues.currentLinearSum.setConstant(numerator);
    }

    // c || .c || c.c
    private void coef() {
        if(currentToken == Token.DOT) {
            match(Token.DOT);
            match(Token.CONST);
            lValues.dValue = convertToDecimal(0, lValues.iValue);
        }
        else {
            match(Token.CONST);
            int beforeDecimal = lValues.iValue;
            if(currentToken == Token.DOT) {
                match(Token.DOT);
                match(Token.CONST);

                lValues.dValue = convertToDecimal(beforeDecimal, lValues.iValue);
            }
            else {
                lValues.dValue = (double)beforeDecimal;
            }
        }

    }

    private void constr_list() {
        constr();
        while(currentToken == Token.ST) {
            constr();
        }
    }

    private void constr() {
        lValues.currentConstraint = new Constraint();
        match(Token.ST);
        match(Token.COLON);
        linear_sum();
        lValues.currentConstraint.setLeftLinearSum(lValues.currentLinearSum);

        lValues.currentConstraint.setComparisonOp(lValues.cValue);
        match(Token.COMP);

        linear_sum();
        lValues.currentConstraint.setRightLinearSum(lValues.currentLinearSum);

        match(Token.SEMI);
        LP.lp.addConstraint(lValues.currentConstraint);
    }

    public double convertToDecimal(int beforeDecimal, int afterDecimal) {
        int len = String.valueOf(afterDecimal).length();
        return beforeDecimal + (afterDecimal / Math.pow(10, len));
    }
}
