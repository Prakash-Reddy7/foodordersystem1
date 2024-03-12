package com.foodordersystem.Repository;
import com.foodordersystem.Entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    public List<FoodItem> findByDiningTableTableNumber(Long tableNumber);
}