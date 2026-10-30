package com.example;

import java.util.HashMap;
import java.util.Map;

// Структурный паттерн Proxy (Заместитель)
// 1. Интерфейс (совпадает с именем файла)
public interface Recipe {
    String getDetails();
}

// 2. Реальный объект (тяжелый запрос к БД) - без public
class RealRecipe implements Recipe {
    private int recipeId;

    public RealRecipe(int recipeId) {
        this.recipeId = recipeId;
        System.out.println("Загрузка рецепта " + recipeId + " из базы данных...");
    }

    @Override
    public String getDetails() {
        return "Состав торта для рецепта " + recipeId;
    }
}

// 3. Прокси с кэшем (без public)
class RecipeProxy implements Recipe {
    private static Map<Integer, String> cache = new HashMap<>();
    private int recipeId;
    private RealRecipe realRecipe;

    public RecipeProxy(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String getDetails() {
        if (cache.containsKey(recipeId)) {
            System.out.println("Рецепт " + recipeId + " взят из кэша (быстро).");
            return cache.get(recipeId);
        }
        if (realRecipe == null) realRecipe = new RealRecipe(recipeId);
        String result = realRecipe.getDetails();
        cache.put(recipeId, result);
        return result;
    }
}