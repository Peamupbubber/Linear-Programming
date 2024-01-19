public class LinearProgramParser {

    public class LValues {
        public String sValue;
        public char cValue;
        public int iValue;
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
            // System.out.println("Error: Current token should be " +
            // LinearProgramScanner.lpScanner.getTokenName(tok) + " but was " +
            // LinearProgramScanner.lpScanner.getTokenName(currentToken));
        }
    }
    
    // This ideally will parse the .txt file, identifying if it is valid syntax
    // and if so sending all of the information to the LP object

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
        while(currentToken == Token.VAR)
            var();
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
        match(Token.SEMI);
    }

    // Going to have to store this in a linked list somehow
    private void linear_sum() {
        product();
        while(currentToken == Token.ADD) {
            match(Token.ADD);
            product();
        }
    }

    // Going to have to store this
    private void product() {
        // x
        if(currentToken == Token.ID) {
            match(Token.ID);
        }

        // cx || c*x
        else {
            pcoef();
            if(currentToken == Token.MUL)
                match(Token.MUL);

            if(currentToken == Token.ID)
                match(Token.ID);
        }
    }

    private void pcoef() {
        switch (currentToken) {
            // -c
            case ADD:
                match(Token.ADD);
                scoef();
                break;

            // (-c) || (c)
            case LPAR:
                match(Token.LPAR);
                if(currentToken == Token.ADD)
                    match(Token.ADD);
                
                scoef();
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

        if(currentToken == Token.SLASH) {
            match(Token.SLASH);
            coef();
        }
    }

    // c || .c || c.c
    private void coef() {
        if(currentToken == Token.CONST) {
            match(Token.CONST);
        }

        if(currentToken == Token.DOT) {
            match(Token.DOT);
            match(Token.CONST);
        }
    }

    //Link together in a constraint class somehow?
    private void constr_list() {
        constr();
        while(currentToken == Token.ST) {
            constr();
        }
    }

    private void constr() {
        match(Token.ST);
        match(Token.COLON);
        linear_sum();
        match(Token.COMP);
        linear_sum();
        match(Token.SEMI);
    }
}
