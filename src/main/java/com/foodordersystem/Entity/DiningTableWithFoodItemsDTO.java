package com.foodordersystem.Entity;

import lombok.Data;

import java.util.List;


@Data
public class DiningTableWithFoodItemsDTO {
    private DiningTable diningTable;
    private List<FoodItem> foodItems;

    public DiningTableWithFoodItemsDTO(DiningTable diningTable, List<FoodItem> foodItems) {
        this.diningTable = diningTable;
        this.foodItems = foodItems;
    }
}

