package commands;

import communication.Response;
import elements.UnitOfMeasure;

public class CommandPrintLessUnitOfMeasure extends Command {

    @Override
    public String getName() {
        return "filter_less_than_unit_of_measure";
    }

    @Override
    public String getManual() {
        return "Вывести элементы, значения поля  \"unitOfMeasure\" которых меньше заданного. Параметры: unitOfMeasure";
    }

    @Override
    public Response execute() {
        try {
            if(context.handlerDatabase.isExistingUser(login, password) == -1) {
                throw new Exception();
            }
            else {
                return new Response(getName(), context.productList.printLessThanUnitOfMeasure((UnitOfMeasure)arguments[0].getValue()));
            }
        } catch (Exception e) {
            return new Response(getName(), "Вы не прошли авторизацию.");
        }
    }
}
