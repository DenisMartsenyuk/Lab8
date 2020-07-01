package collection;

import application.Context;
import elements.Product;
import elements.UnitOfMeasure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ProductList {

    private Context context;

    private ArrayList<Product> products;
    private ReentrantLock locker;

    public ProductList(Context context) {
        this.context = context;
        products = new ArrayList<>();
        locker = new ReentrantLock();
    }

    public String getTable() {
        locker.lock();
        String result = "";
        for (Product product : products) {
            result = result + product.getIdUser() + " ";
            result = result + product.getId() + " ";
            result = result + product.getName() + " ";
            result = result + product.getCoordinates().getX() + " ";
            result = result + product.getCoordinates().getY() + " ";
            result = result + product.getCreationDate() + " ";
            result = result + product.getPrice() + " ";
            result = result + product.getPartNumber() + " ";
            result = result + product.getManufactureCost() + " ";
            result = result + product.getUnitOfMeasure() + " ";
            result = result + product.getManufacturer().getId() + " ";
            result = result + product.getManufacturer().getName() + " ";
            result = result + product.getManufacturer().getAnnualTurnover() + " ";
            result = result + product.getManufacturer().getOrganizationType() + " ";
        }
        locker.unlock();
        return result;
    }

    public ArrayList<Product> getProducts() {
        locker.lock();
        ArrayList<Product> result = products;
        locker.unlock();
        return result;
    }

    public String printInfo() {
        locker.lock();
        String result = "Тип коллекции: " + products.getClass() + ", Размер: " + products.size();
        locker.unlock();
        return result;
    }

    public void addFromDatabase(Product item) {
        locker.lock();
        products.add(item);
        locker.unlock();
    }

    public String add(Product item, int idUser) {
        locker.lock();
        item.setCreationDate();
        item.setIdUser(idUser);
        try {
            item = context.handlerDatabase.addProduct(item);
            products.add(item);
            locker.unlock();
            return "Элемент добавлен.";
        } catch (SQLException e) {
            locker.unlock();
            return "Элемент не удалось добавить в базу данных.";
        }
    }

    public String addIfMin(Product item, int idUser) {
        locker.lock();
        double price = Collections.min(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Double.compare(product.getPrice(), t1.getPrice());
            }
        }).getPrice();
        if (price > item.getPrice()) {
            add(item, idUser);
            locker.unlock();
            return "Элемент наименьший, поэтому он добавлен.";
        }
        locker.unlock();
        return "Элемент не является наименьшим, поэтому он добавлен не был.";
    }

    public String reverse() {
        locker.lock();
        Collections.reverse(products);
        locker.unlock();
        return "Коллекция выставлена в обратном порядке.";
    }

    public String clear(int idUser) {
        locker.lock();
        for (int i = 0; i < products.size(); ++i) {
            if(products.get(i).getIdUser() == idUser) {
                remove(i);
                i--;
            }
        }
        locker.unlock();
        return "Ваши элементы удалены.";
    }

    public String removeById(int id, int idUser) {
        locker.lock();
        for (Product product : products) {
            if(product.getId() == id) {
                if(product.getIdUser() != idUser) {
                    locker.unlock();
                    return "Вы не можете удалить этот элемент Product, так как он вам не принадлежит.";
                }
                else {
                    remove(product);
                    locker.unlock();
                    return "Элемент Product с таким id удален.";
                }
            }
        }
        locker.unlock();
        return "Элемент Product с таким id не найден.";
    }

    public void remove(int index) {
        locker.lock();
        try {
            context.handlerDatabase.removeProduct(products.get(index));
        } catch (SQLException e) {
            locker.unlock();
            return;
        }
        products.remove(index);
        locker.unlock();
    }

    public void remove(Product product) {
        locker.lock();
        try {
            context.handlerDatabase.removeProduct(product);
        } catch (SQLException e) {
            locker.unlock();
            return;
        }
        products.remove(product);
        locker.unlock();
    }

    public String removeFirst(int idUser) {
        locker.lock();
        try {
            if (products.get(0).getIdUser() == idUser) {
                remove(0);
                locker.unlock();
                return "Первый элемент коллекции удален.";
            }
            else {
                locker.unlock();
                return "Элемент удалить не удалось, т.к. он вам не принадлежит.";
            }
        }
        catch (IndexOutOfBoundsException e) {
            locker.unlock();
            return "Коллекция пуста, первого элемент удалить не удалось.";
        }
    }

    public String updateById(int id, Product item, int idUser) {
        locker.lock();
        for (Product product : products) {
            if(product.getId() == id) {
                if(product.getIdUser() != idUser) {
                    locker.unlock();
                    return "Product нельзя обновить, так как он вам не принадлежит.";
                }
                else {
                    product.updateProduct(item);
                    try {
                        context.handlerDatabase.updateProduct(product);
                        locker.unlock();
                        return "Элемент Product обновлен.";
                    } catch (SQLException e) {
                        locker.unlock();
                        return "Обновить элемент в базе данных не удалось.";
                    }
                }
            }
        }
        locker.unlock();
        return "Элемент Product с таким id не найден.";
    }

    public String removeAllByManufactureCost(Long manufactureCost, int idUser) {
        locker.lock();
        for (int i = 0; i < products.size(); ++i) {
            if(products.get(i).getManufactureCost().equals(manufactureCost)) {
                if (products.get(i).getIdUser() == idUser) {
                    remove(i);
                    i--;
                }
            }
        }
        locker.unlock();
        return "Команда выполнена!";
    }

    public String printLessThanUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        locker.lock();
        if (unitOfMeasure == UnitOfMeasure.SQUARE_METERS || unitOfMeasure == UnitOfMeasure.MILLIGRAMS || unitOfMeasure == UnitOfMeasure.MILLILITERS) {
            locker.unlock();
            return "Искомых элементов нет.";
        }
        ArrayList<Product> result = (ArrayList<Product>) products.stream().filter((product -> unitOfMeasure.ordinal() >
                product.getUnitOfMeasure().ordinal() && unitOfMeasure.ordinal() - product.getUnitOfMeasure().ordinal() == 1)).collect(Collectors.toList());
        locker.unlock();
        return getAlphabet(result);
    }

    public void sort() {
        locker.lock();
        products = (ArrayList<Product>) products.stream().sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice())).collect(Collectors.toList());
        locker.unlock();
    }

    public String getAlphabet() {
        return getAlphabet(new ArrayList<>(products));
    }

    public String getAlphabet(ArrayList<Product> srcProducts) {
        locker.lock();
        srcProducts = (ArrayList<Product>) srcProducts.stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        locker.unlock();
        return srcProducts.toString();
    }

    public void sort(Comparator<Product> comparator) {
        locker.lock();
        products = (ArrayList<Product>) products.stream().sorted(comparator).collect(Collectors.toList());
        locker.unlock();
    }

    public String printAscending() {
        locker.lock();
        ArrayList<Product> result = (ArrayList<Product>) new ArrayList<>(products).stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice())).collect(Collectors.toList());
        locker.unlock();
        return result.toString();
    }

    public Product[] getArray() {
        locker.lock();
        Product[] result = new Product[products.size()];
        for (int i = 0; i < products.size(); ++i) {
            result[i] = products.get(i);
        }
        locker.unlock();
        return result;
    }
}
