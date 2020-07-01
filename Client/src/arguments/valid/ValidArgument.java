package arguments.valid;

import arguments.exceptions.CheckerException;
import arguments.exceptions.TypeException;
import arguments.checkers.Checker;

public abstract class ValidArgument<T>{

    T result;
    private Checker[] checkers;

    public ValidArgument(Checker ... checkers) {
        this.checkers = checkers;
    }

    private void validation(String argument) throws CheckerException{
        for (int i = 0; i < checkers.length; ++i) {
            try {
                checkers[i].check(argument);
            } catch (CheckerException e) {
                result = null;
                throw e;
            }
        }
    }

    public T getResult() {
        return result;
    };

    public T get(String argument) throws CheckerException, TypeException {
        T value = parse(argument);
        validation(argument);
        return value;
    }

    protected abstract T parse(String argument) throws TypeException;
}
