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
            System.out.println("--- ПОРОЖДАЮЩИЕ ---");
            System.out.println("1. Singleton (Одиночка)");
            System.out.println("2. Factory Method (Фабричный метод)");
            System.out.println("--- СТРУКТУРНЫЕ ---");
            System.out.println("3. Adapter (Адаптер)");
            System.out.println("4. Facade (Фасад)");
            System.out.println("5. Proxy (Заместитель)");
            System.out.println("--- ПОВЕДЕНЧЕСКИЕ ---");
            System.out.println("6. Observer (Наблюдатель)");
            System.out.println("7. Strategy (Стратегия)");
            System.out.println("8. Command (Команда)");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт (0-8): ");

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

                case "6":
                    // Тест Observer: подписчики получают уведомления об изменении статуса
                    System.out.println("\n[ТЕСТ OBSERVER]");
                    OrderSubject order = new OrderSubject();
                    Observer customer = new CustomerObserver("Анна");
                    Observer confectioner = new ConfectionerObserver();

                    order.attach(customer);
                    order.attach(confectioner);

                    System.out.println("-- Меняем статус на 'В работе' --");
                    order.setStatus("В работе");
                    System.out.println("-- Меняем статус на 'Готов' --");
                    order.setStatus("Готов");
                    break;

                case "7":
                    // Тест Strategy: разные алгоритмы расчета цены
                    System.out.println("\n[ТЕСТ STRATEGY]");
                    PriceCalculator calc = new PriceCalculator();
                    double base = 3000.0;

                    System.out.println("Базовая цена торта: " + base);

                    calc.setStrategy(new StandardPrice());
                    System.out.println("Обычный заказ: " + calc.calculatePrice(base));

                    calc.setStrategy(new UrgentPrice());
                    System.out.println("Срочный заказ (наценка 50%): " + calc.calculatePrice(base));

                    calc.setStrategy(new DiscountPrice());
                    System.out.println("Заказ со скидкой 10%): " + calc.calculatePrice(base));
                    break;

                case "8":
                    // Тест Command: инкапсуляция действия в объект
                    System.out.println("\n[ТЕСТ COMMAND]");
                    KitchenReceiver kitchen = new KitchenReceiver();
                    OrderCommand makeCake = new MakeCakeCommand(kitchen);
                    Waiter waiter = new Waiter();

                    waiter.setCommand(makeCake);
                    waiter.takeOrder(); // Выполняем

                    System.out.println("--- Ой, клиент передумал! ---");
                    waiter.cancelOrder(); // Отменяем
                    break;

                case "0":
                    // Выход из программы
                    running = false;
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите от 0 до 8.");
            }
        }
        scanner.close();
    }
}