package commands;

import communication.Argument;
import communication.Response;
import elements.Product;

public class CommandAdd extends Command {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getManual() {
        return "Добавить новый элемент в коллекцию. Параметры: {element}.";
    }

    @Override
    public Response execute() {
        try {
            return new Response(getName(), context.productList.add((Product) arguments[0].getValue(), context.handlerDatabase.isExistingUser(login, password)), new Argument(context.productList.getArray()));
        }
        catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }
}
