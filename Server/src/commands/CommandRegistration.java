package commands;

import communication.Response;

public class CommandRegistration extends Command {
    @Override
    public String getName() {
        return "registration";
    }

    @Override
    public String getManual() {
        return "";
    }

    @Override
    public Response execute() {
        try {
            context.handlerDatabase.registrationUser((String) arguments[0].getValue(), (String) arguments[1].getValue());
           return new Response(getName(), "Пользователь " + arguments[0].getValue() + " зарегистрирован.");
        } catch (Exception e) {
            return new Response(getName(), "Регистрация не удалась. Поробуйте еще раз.");
        }
    }
}
