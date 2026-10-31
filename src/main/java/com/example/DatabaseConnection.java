package com.example;

// Порождающий паттерн Singleton
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private String connectionStatus;

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