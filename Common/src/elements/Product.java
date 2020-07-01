package elements;

import java.io.Serializable;
import java.time.ZonedDateTime;


public class Product implements Serializable {
    private Integer idUser;
    private Integer id; //Поле не может быть null, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private String partNumber; //Поле может быть null
    private Long manufactureCost; //Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    public Product(int idUser, int id, String name, Coordinates coordinates, ZonedDateTime creationDate, double price, String partNumber, Long manufactureCost, UnitOfMeasure unitOfMeasure, Organization organization){
        this.idUser = idUser;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = organization;
    }

    public Product(String name, Coordinates coordinates, double price, String partNumber, Long manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer){
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturerId(int id) {
        manufacturer.setId(id);
    }

    public void setCreationDate() {
        if(this.creationDate != null) {
            return;
        }
        this.creationDate = ZonedDateTime.now();
    }

    public void setCreationDate(ZonedDateTime zonedDateTime) {
        if(this.creationDate != null) {
            return;
        }
        this.creationDate = zonedDateTime;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void updateProduct(Product product) {
        name = product.name;
        coordinates = product.coordinates;
        price = product.price;
        partNumber = product.partNumber;
        manufactureCost = product.manufactureCost;
        unitOfMeasure = product.unitOfMeasure;
        int id = manufacturer.getId();
        manufacturer = product.manufacturer;
        manufacturer.setId(id);
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Long getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return  "Product [idUser=" + idUser + ", \n" +
                "         id=" + id + ", \n" +
                "         name=" + name + ", \n" +
                "         coordinates=" + coordinates.toString() + ", \n" +
                "         creationDate=" + creationDate.toString() + ", \n" +
                "         price=" + price + ", \n" +
                "         partNumber=" + partNumber + ", \n" +
                "         manufactureCost=" + manufactureCost + ", \n" +
                "         unitOfMeasure=" + unitOfMeasure + ", \n" +
                "         manufacturer= " + manufacturer.toString() + "]\n";
    }
}