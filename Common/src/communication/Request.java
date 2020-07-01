package communication;

import java.io.Serializable;

public class Request implements Serializable {

    private String name;
    private Argument[] arguments;

    private String login;
    private String password;

    public Request(String name, Argument ... arguments) {
        this.name = name;
        this.arguments = arguments;
        return;
    }

    public String getName() {
        return name;
    }

    public Argument[] getArguments() {
        return arguments;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Request setLogin(String login) {
        this.login = login;
        return this;
    }

    public Request setPassword(String password) {
        this.password = password;
        return this;
    }
}
