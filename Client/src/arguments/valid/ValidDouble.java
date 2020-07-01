package arguments.valid;

import arguments.checkers.Checker;
import arguments.exceptions.TypeException;

public class ValidDouble extends ValidArgument<Double> {

    public ValidDouble(Checker ... checkers) {
        super(checkers);
    }

    @Override
    public Double parse(String argument) throws TypeException {
        try {
            result = Double.parseDouble(argument);
            return result;
        } catch (Exception e) {
            throw new TypeException();
        }
    }
}
