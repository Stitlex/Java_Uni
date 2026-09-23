package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> catalog = new ArrayList<>();
        catalog.add(product1);
        catalog.add(product2);
        catalog.add(product3);

        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Пошук товарів (за назвою або категорією)");
            System.out.println("3 - Додати товар до кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Зробити замовлення");
            System.out.println("7 - Історія замовлень");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (Product product : catalog) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("Введіть ключове слово для пошуку:");
                    String nameProduct = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    for (Product product : catalog) {
                        if (product.getName().toLowerCase().contains(nameProduct) || product.getCategory().getName().toLowerCase().contains(nameProduct)) {
                            System.out.println(product);
                            found = true;
                        }
                    }

                    if (!found) System.out.println("За запитом нічого не знайдено!");
                    break;
                case 3:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int addId = scanner.nextInt();
                    found = false;

                    for (Product product : catalog) {
                        if (product.getId() == addId) {
                            cart.addProduct(product);
                            System.out.println("Товар додано до кошика!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) System.out.println("Товар з таким ID не знайдено!");
                    break;
                case 4:
                    System.out.println(cart);
                    break;
                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId = scanner.nextInt();
                    boolean removed = false;
                    for (Product product : cart.getProducts()) {
                        if (product.getId() == removeId) {
                            cart.removeProduct(product);
                            System.out.println("Товар успішно видалено з кошика!");
                            removed = true;
                            break;
                        }
                    }
                    if (!removed) System.out.println("Товар з таким ID у кошику не знайдено!");
                    break;
                case 6:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено!");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 7:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня!");
                    } else {
                        System.out.println("===Історія замовлень===");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("\nЗамовлення #" + (i + 1));
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}