package com.example;

// Порождающий паттерн Factory Method (Фабричный метод)
// 1. Интерфейс продукта (совпадает с именем файла)
public interface PaymentProcessor {
    void processPayment(double amount);
}

// 2. Конкретные реализации (без слова public)
class CardPayment implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата " + amount + " руб. проведена через эквайринг (Карта).");
    }
}

class CashPayment implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата " + amount + " руб. будет принята наличными.");
    }
}

// 3. Сама фабрика
class PaymentFactory {
    public static PaymentProcessor getProcessor(String method) {
        if (method.equalsIgnoreCase("CARD")) return new CardPayment();
        if (method.equalsIgnoreCase("CASH")) return new CashPayment();
        throw new IllegalArgumentException("Неизвестный способ оплаты: " + method);
    }
}