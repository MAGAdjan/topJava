package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> userMealsMap = repository.getOrDefault(userId, new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMealsMap.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return userMealsMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMealsMap = repository.get(userId);
        return userMealsMap != null && userMealsMap.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMealsMap = repository.get(userId);
        return userMealsMap != null ? userMealsMap.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getAllWithPredicate(userId, meal -> true);
    }

    @Override
    public Collection<Meal> getAllFiltered(LocalDateTime startTime, LocalDateTime endTime, int userId) {
        return getAllWithPredicate(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), startTime, endTime));
    }

    private Collection<Meal> getAllWithPredicate(int userId, Predicate<Meal> predicate) {
        Map<Integer, Meal> userMealsMap = repository.get(userId);
        return CollectionUtils.isEmpty(userMealsMap) ? Collections.EMPTY_LIST :
                userMealsMap.values().stream()
                        .filter(predicate)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}

