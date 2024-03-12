
        package com.foodordersystem.Controller;
import com.foodordersystem.Entity.FoodItem;
import com.foodordersystem.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/foodItems")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;
    @GetMapping()
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @PostMapping("/createFoodItem")
    public ResponseEntity<?> createFoodItem(
            @RequestParam(name = "tableNumber", required = true) Long tableNumber,
            @RequestParam(name = "itemName", required = true) String itemName,
            @RequestBody FoodItem foodItem
    ) {
        try {
            itemName = URLEncoder.encode(itemName, "UTF-8")
                    .replace("%5C", "%5C");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Set the itemName in the FoodItem object
        foodItem.setItemName(itemName);
        return foodItemService.createFoodItem(tableNumber, foodItem);
    }


    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable long foodItemId) {
        return foodItemService.deleteFoodItem(foodItemId);
    }
    @GetMapping("/by-table/{tableNumber}")
    public ResponseEntity<List<FoodItem>> getFoodItemsByTable(@PathVariable Long tableNumber) {
        return foodItemService.getFoodItemsByTable(tableNumber);
    }
}
 