package com.example;

// Структурный паттерн Facade

// 1. Внутренние сервисы
// Этот класс просто имитирует проверку ингредиентов
class IngredientService {
    public boolean checkAvailability() {
        return true;
    }
}

// Этот класс имитирует расчет цены
class CatalogService {
    public double calculatePrice() {
        return 3500.0;
    }
}

// Этот класс имитирует создание заказа в базе данных
class OrderService {
    public void create(double price) {
        System.out.println("Заказ создан. Сумма: " + price);
    }
}

// 2. Сам фасад
public class OrderFacade {
    public void makeOrder() {
        System.out.println("--- Начинаем оформление заказа ---");

        // Проверяем наличие ингредиентов
        if (!new IngredientService().checkAvailability()) {
            System.out.println("Ошибка: нет ингредиентов");
            return;
        }

        // Считаем цену
        double price = new CatalogService().calculatePrice();

        // Создаем заказ
        new OrderService().create(price);
        System.out.println("--- Заказ успешно оформлен ---");
    }
}