package arguments.checkers;

import arguments.exceptions.CheckerException;

public class IntegerLargerValue implements Checker{

    private Integer value;

    public IntegerLargerValue(Integer value) {
        this.value = value;
    }

    @Override
    public void check(String argument) throws CheckerException {
        if(Integer.parseInt(argument) < value) {
            throw new CheckerException("Число меньше минимального порога!");
        }
    }
}
