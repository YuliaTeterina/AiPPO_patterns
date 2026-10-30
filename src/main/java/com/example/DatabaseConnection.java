package com.example;

// Порождающий паттерн Singleton (Одиночка)
public class DatabaseConnection {
    // Единственный экземпляр класса
    private static DatabaseConnection instance;
    private String connectionStatus;

    // Приватный конструктор, чтобы нельзя было создать через new
    private DatabaseConnection() {
        this.connectionStatus = "Подключено к БД каталога";
    }

    // Глобальная точка доступа
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }
}