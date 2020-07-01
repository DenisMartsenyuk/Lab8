package arguments.checkers;

import arguments.exceptions.CheckerException;

public class DoubleLargerValue implements Checker {

    private Double value;

    public DoubleLargerValue(Double value) {
        this.value = value;
    }

    @Override
    public void check(String argument) throws CheckerException {
        if(Double.parseDouble(argument) < value) {
            throw new CheckerException("Число меньше минамального порога!");
        }
    }
}
