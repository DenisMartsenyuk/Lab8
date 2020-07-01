package commands;

import communication.Argument;
import communication.Response;

public class CommandRemoveFirst extends Command {

    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getManual() {
        return "Удалить первый элемент коллекции.";
    }

    @Override
    public Response execute() {
        try {
            return new Response(getName(), context.productList.removeFirst(context.handlerDatabase.isExistingUser(login, password)), new Argument(context.productList.getArray()));
        }
        catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }

}
