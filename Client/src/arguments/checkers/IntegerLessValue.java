package arguments.checkers;

import arguments.exceptions.CheckerException;

public class IntegerLessValue implements Checker {

    private Integer value;

    public IntegerLessValue(Integer value) {
        this.value = value;
    }

    @Override
    public void check(String argument) throws CheckerException {
        if(Integer.parseInt(argument) > value) {
            throw new CheckerException("Число больше максимального порога!");
        }
    }
}
