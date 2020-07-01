package arguments.valid;

import arguments.checkers.Checker;
import arguments.exceptions.TypeException;

public class ValidString extends ValidArgument<String> {

    public ValidString(Checker ... checkers) {
        super(checkers);
    }

    @Override
    public String parse(String argument) throws TypeException {
        result = argument;
        return result;
    }
}
