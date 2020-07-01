package arguments.checkers;

import arguments.exceptions.CheckerException;

public class DoubleLessValue implements Checker{
    private Double value;

    public DoubleLessValue(Double value) {
        this.value = value;
    }

    @Override
    public void check(String argument) throws CheckerException {
        if(Double.parseDouble(argument) > value) {
            throw new CheckerException("Число больше максимального порога!");
        }
    }
}
