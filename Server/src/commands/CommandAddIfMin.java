package commands;

import communication.Argument;
import communication.Response;
import elements.Product;

public class CommandAddIfMin extends Command {

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getManual() {
        return "Добавить новый элемент в коллекцию если его значение станет наименьшим. Параметры: {element}.";
    }

    @Override
    public Response execute() {
        try {
            return new Response(getName(), context.productList.addIfMin((Product) arguments[0].getValue(), context.handlerDatabase.isExistingUser(login, password)), new Argument(context.productList.getArray()));
        }
        catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }
}
