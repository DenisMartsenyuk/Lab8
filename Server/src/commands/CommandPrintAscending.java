package commands;

import communication.Response;

public class CommandPrintAscending extends Command {

    @Override
    public String getName() {
        return "print_ascending";
    }

    @Override
    public String getManual() {
        return "Вывести элементы коллекции в порядке возрастания";
    }

    @Override
    public Response execute() {
        try {
            if(context.handlerDatabase.isExistingUser(login, password) == -1) {
                throw new Exception();
            }
            else {
                return new Response(getName(), context.productList.printAscending());
            }
        } catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }
}
