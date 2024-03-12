package com.foodordersystem.ServiceImpl;
import com.foodordersystem.Entity.DiningTable;
import com.foodordersystem.Entity.FoodItem;
import com.foodordersystem.Repository.FoodItemRepository;
import com.foodordersystem.Repository.TableRepository;
import com.foodordersystem.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private TableRepository tableRepository;
    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createFoodItem(Long tableNumber, FoodItem foodItem) {
        DiningTable table = tableRepository.findByTableNumber(tableNumber);

        if (table != null) {
            foodItem.setDiningTable(table);
            FoodItem savedFoodItem = foodItemRepository.save(foodItem);
            return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Table not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteFoodItem(long foodItemId) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(foodItemId);

        if (foodItem.isPresent()) {
            foodItemRepository.deleteById(foodItemId);
            return new ResponseEntity<>("Food Item deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Food Item not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<FoodItem>> getFoodItemsByTable(Long tableNumber) {
        List<FoodItem> foodItems = foodItemRepository.findByDiningTableTableNumber(tableNumber);

        if (foodItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foodItems, HttpStatus.OK);
        }
    }
}

