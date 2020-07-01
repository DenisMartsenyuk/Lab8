package commands;

import communication.Response;

import java.util.Map;

public class CommandHelp extends Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getManual() {
        return "Вывести справку по всем командам сервера";
    }

    @Override
    public Response execute() {
        try {
            if(context.handlerDatabase.isExistingUser(login, password) == -1) {
                throw new Exception();
            }
            else {
                String result = "\n";
                for (Map.Entry<String, Command> command : context.handlerCommands.getCommands().entrySet()) {
                    if(!command.getValue().getManual().equals("")) {
                        result = result + command.getKey() + ": " + command.getValue().getManual() + "\n";
                    }
                }
                return new Response(getName(), result);
            }
        } catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }
}
