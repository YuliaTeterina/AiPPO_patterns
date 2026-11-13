package com.example;

// Поведенческий паттерн Strategy (Стратегия)
// 1. Интерфейс стратегии (совпадает с именем файла)
public interface PriceStrategy {
    double calculate(double basePrice);
}

// 2. Конкретные стратегии (без public)
class StandardPrice implements PriceStrategy {
    @Override
    public double calculate(double basePrice) {
        return basePrice;
    }
}

class UrgentPrice implements PriceStrategy {
    @Override
    public double calculate(double basePrice) {
        return basePrice * 1.5; // Наценка 50% за срочность
    }
}

class DiscountPrice implements PriceStrategy {
    @Override
    public double calculate(double basePrice) {
        return basePrice * 0.9; // Скидка 10%
    }
}

// 3. Контекст, который использует стратегию (без public)
class PriceCalculator {
    private PriceStrategy strategy;

    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculatePrice(double basePrice) {
        return strategy.calculate(basePrice);
    }
}