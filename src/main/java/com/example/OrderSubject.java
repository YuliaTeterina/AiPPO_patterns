package com.example;

import java.util.ArrayList;
import java.util.List;

// Поведенческий паттерн Observer (Наблюдатель)
// 1. Интерфейс наблюдателя (подписчика)
interface Observer {
    void update(String orderStatus);
}

// 2. Конкретные наблюдатели (без public)
class CustomerObserver implements Observer {
    private String name;
    public CustomerObserver(String name) { this.name = name; }

    @Override
    public void update(String orderStatus) {
        System.out.println("Клиент " + name + " получил уведомление: Статус заказа - " + orderStatus);
    }
}

class ConfectionerObserver implements Observer {
    @Override
    public void update(String orderStatus) {
        System.out.println("Кондитер видит: Статус заказа изменился на " + orderStatus);
    }
}

// 3. Субъект (издатель) - совпадает с именем файла
public class OrderSubject {
    private List<Observer> observers = new ArrayList<>();
    private String status;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
}