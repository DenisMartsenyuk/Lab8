package communication;

import java.io.Serializable;

public class Response implements Serializable {

    private String nameCommand;
    private String resultComand;
    private Argument argument;

    public Response(Argument argument) {
        this.nameCommand = null;
        this.resultComand = null;
        this.argument = argument;
    }

    public Response(String nameCommand, String resultComand) {
        this.nameCommand = nameCommand;
        this.resultComand = resultComand;
        this.argument = null;
    }

    public Response(String nameCommand, String resultComand, Argument argument) {
        this.nameCommand = nameCommand;
        this.resultComand = resultComand;
        this.argument = argument;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public String getResultCommand() {
        return resultComand;
    }

    public Argument getArgument() {
        return argument;
    }
}
