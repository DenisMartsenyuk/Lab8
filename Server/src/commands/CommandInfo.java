package commands;

import communication.Response;

public class CommandInfo extends Command {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getManual() {
        return "Вывести информацию о коллекции.";
    }

    @Override
    public Response execute() {
        try {
            if(context.handlerDatabase.isExistingUser(login, password) == -1) {
                throw new Exception();
            }
            else {
                return new Response(getName(), context.productList.printInfo());
            }
        } catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }
}
