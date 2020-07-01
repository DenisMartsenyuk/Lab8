package arguments.checkers;

import arguments.exceptions.CheckerException;

import java.util.StringTokenizer;

public class StringSize implements Checker {

    private Integer value;

    public StringSize(Integer value) {
        this.value = value;
    }

    @Override
    public void check(String argument) throws CheckerException {
        if(new StringTokenizer(argument).countTokens() != value) {
            throw new CheckerException("Количество слов в строке не равно заданному!");
        }
    }
}
