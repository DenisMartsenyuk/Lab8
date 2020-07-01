package arguments.valid;

import arguments.checkers.Checker;
import arguments.exceptions.TypeException;

public class ValidLong extends ValidArgument<Long> {

    public ValidLong(Checker ... checkers) {
        super(checkers);
    }

    @Override
    public Long parse(String argument) throws TypeException {
        try {
            result = Long.parseLong(argument);
            return result;
        } catch (Exception ex) {
            throw new TypeException();
        }
    }
}
