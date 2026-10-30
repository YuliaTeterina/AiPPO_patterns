package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("   ТЕСТИРОВАНИЕ ПАТТЕРНОВ ПРОЕКТИРОВАНИЯ");
            System.out.println("=========================================");
            System.out.println("1. Запустить Singleton (Одиночка)");
            System.out.println("2. Запустить Factory Method (Фабричный метод)");
            System.out.println("3. Запустить Adapter (Адаптер)");
            System.out.println("4. Запустить Facade (Фасад)");
            System.out.println("5. Запустить Proxy (Заместитель)");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт (0-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Тест Singleton: проверяем, что создается только один объект
                    System.out.println("\n[ТЕСТ SINGLETON]");
                    DatabaseConnection db1 = DatabaseConnection.getInstance();
                    DatabaseConnection db2 = DatabaseConnection.getInstance();
                    System.out.println("db1 и db2 это один объект? " + (db1 == db2));
                    System.out.println("Статус: " + db1.getConnectionStatus());
                    break;

                case "2":
                    // Тест Factory: создаем нужный обработчик платежа
                    System.out.println("\n[ТЕСТ FACTORY METHOD]");
                    System.out.print("Введите способ оплаты (CARD / CASH): ");
                    String method = scanner.nextLine();
                    try {
                        PaymentProcessor p = PaymentFactory.getProcessor(method);
                        p.processPayment(2500.0);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "3":
                    // Тест Adapter: единый интерфейс для разных мессенджеров
                    System.out.println("\n[ТЕСТ ADAPTER]");
                    Notifier tg = new TelegramAdapter(new TelegramAPI(), "confectioner_123");
                    tg.sendMessage("Внимание! Новый заказ на торт 'Наполеон'!");
                    Notifier sms = new SmsAdapter(new SmsGatewayAPI(), "+79991234567");
                    sms.sendMessage("Ваш заказ принят в работу.");
                    break;

                case "4":
                    // Тест Facade: один вызов запускает цепочку сервисов
                    System.out.println("\n[ТЕСТ FACADE]");
                    new OrderFacade().makeOrder();
                    break;

                case "5":
                    // Тест Proxy: проверяем кэширование рецептов
                    System.out.println("\n[ТЕСТ PROXY]");
                    Recipe r1 = new RecipeProxy(1);
                    System.out.println("Результат 1: " + r1.getDetails());
                    System.out.println("--- Повторный запрос того же рецепта ---");
                    Recipe r2 = new RecipeProxy(1);
                    System.out.println("Результат 2: " + r2.getDetails());
                    break;

                case "0":
                    // Выход из программы
                    running = false;
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите от 0 до 5.");
            }
        }
        scanner.close();
    }
}