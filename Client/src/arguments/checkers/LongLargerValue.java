package arguments.checkers;

import arguments.exceptions.CheckerException;

public class LongLargerValue implements Checker {

    private Long value;

    public LongLargerValue(Long value) {
        this.value = value;
    }

    @Override
    public void check(String argument) throws CheckerException {
        if(Long.parseLong(argument) <= value) {
            throw new CheckerException("Число меньше либо равно минамального порога!");
        }
    }
}
