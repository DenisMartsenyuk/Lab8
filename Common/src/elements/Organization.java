package elements;

import java.io.Serializable;

public class Organization implements Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null

    public Organization(int id, String name, long annualTurnover, OrganizationType type) {
        this.id = id;
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.type = type;
    }

    public Organization(String name, long annualTurnover, OrganizationType type) {
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getOrganizationType() {
        return type;
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", name=" + name + ", annualTurnover=" + annualTurnover + ", type=" +type + " ]";
    }

}