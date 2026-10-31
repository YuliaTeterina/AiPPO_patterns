package com.example;

// Структурный паттерн Adapter
// 1. Целевой интерфейс
public interface Notifier {
    void sendMessage(String text);
}

// 2. Сторонние классы с несовместимыми интерфейсами
class TelegramAPI {
    public void sendTelegramMsg(String chatId, String msg) {
        System.out.println("Telegram [chat:" + chatId + "]: " + msg);
    }
}

class SmsGatewayAPI {
    public void sendSms(String phoneNumber, String message) {
        System.out.println("SMS [на номер " + phoneNumber + "]: " + message);
    }
}

// 3. Адаптеры
class TelegramAdapter implements Notifier {
    private TelegramAPI api;
    private String chatId;

    public TelegramAdapter(TelegramAPI api, String chatId) {
        this.api = api;
        this.chatId = chatId;
    }

    @Override
    public void sendMessage(String text) {
        api.sendTelegramMsg(chatId, text);
    }
}

class SmsAdapter implements Notifier {
    private SmsGatewayAPI api;
    private String phone;

    public SmsAdapter(SmsGatewayAPI api, String phone) {
        this.api = api;
        this.phone = phone;
    }

    @Override
    public void sendMessage(String text) {
        api.sendSms(phone, text);
    }
}