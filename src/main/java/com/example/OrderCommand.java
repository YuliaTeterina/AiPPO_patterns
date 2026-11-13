package com.example;

// Поведенческий паттерн Command (Команда)
// 1. Интерфейс команды (совпадает с именем файла)
public interface OrderCommand {
    void execute();
    void undo();
}

// 2. Получатель (Receiver) - тот, кто реально выполняет работу (без public)
class KitchenReceiver {
    public void cookCake() {
        System.out.println("Повар начал готовить торт...");
    }
    public void cancelCooking() {
        System.out.println("Готовка отменена, продукты возвращены на склад.");
    }
}

// 3. Конкретная команда (без public)
class MakeCakeCommand implements OrderCommand {
    private KitchenReceiver kitchen;

    public MakeCakeCommand(KitchenReceiver kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public void execute() {
        kitchen.cookCake();
    }

    @Override
    public void undo() {
        kitchen.cancelCooking();
    }
}

// 4. Инициатор (Invoker) - вызывает команду (без public)
class Waiter {
    private OrderCommand command;

    public void setCommand(OrderCommand command) {
        this.command = command;
    }

    public void takeOrder() {
        System.out.println("Официант принял заказ и передал на кухню.");
        command.execute();
    }

    public void cancelOrder() {
        System.out.println("Официант отменяет заказ.");
        command.undo();
    }
}