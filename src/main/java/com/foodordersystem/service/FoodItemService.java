package com.foodordersystem.service;
import com.foodordersystem.Entity.FoodItem;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface FoodItemService {

    List<FoodItem> getAllFoodItems();

    ResponseEntity<?> createFoodItem(Long tableNumber, FoodItem foodItem);

    ResponseEntity<String> deleteFoodItem(long foodItemId);

    ResponseEntity<List<FoodItem>> getFoodItemsByTable(Long tableNumber);
}