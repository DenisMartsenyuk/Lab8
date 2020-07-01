package commands;

import communication.Argument;
import application.Context;
import communication.Response;

public abstract class Command {

    protected Context context;
    protected Argument[] arguments;
    protected String login;
    protected String password;

    public void setContext(Context context) {
        this.context = context;
    }

    public Command setParameters(Argument[] arguments) {
        this.arguments = arguments;
        return this;
    }

    public Command setLogin(String login) {
        this.login = login;
        return this;
    }

    public Command setPassword(String password) {
        this.password = password;
        return this;
    }

    public abstract String getName();

    public abstract String getManual();

    public abstract Response execute();
}
