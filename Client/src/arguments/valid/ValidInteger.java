package arguments.valid;

import arguments.exceptions.TypeException;
import arguments.checkers.Checker;

public class ValidInteger extends ValidArgument<Integer> {

    public ValidInteger(Checker ... checkers) {
        super(checkers);
    }

    @Override
    public Integer parse(String argument) throws TypeException {
        try {
            result = Integer.parseInt(argument);
            return result;
        } catch (Exception e) {
            throw new TypeException();
        }
    }
}
