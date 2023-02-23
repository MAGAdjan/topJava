package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MealTestData {

    public static List<Meal> initMeals() {
        return Arrays.asList(
                new Meal(null, LocalDateTime.of(2023, 02, 21, 07, 00), "breakfast", 500),
                new Meal(null, LocalDateTime.of(2023, 02, 21, 14, 00), "lunch", 1000),
                new Meal(null, LocalDateTime.of(2023, 02, 21, 20, 00), "dinner", 600),
                new Meal(null, LocalDateTime.of(2023, 02, 22, 07, 00), "breakfast", 500),
                new Meal(null, LocalDateTime.of(2023, 02, 22, 14, 00), "lunch", 1000),
                new Meal(null, LocalDateTime.of(2023, 02, 22, 20, 00), "dinner", 500)
        );
    }


}
