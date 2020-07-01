package arguments.checkers;

import arguments.exceptions.CheckerException;

public interface Checker {
    public void check(String argument) throws CheckerException;
}
