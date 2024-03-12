package com.foodordersystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private Long itemId;
    private String itemName;
    private double price;


    @ManyToOne(targetEntity = DiningTable.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tableId", insertable = true, updatable = true)
    private DiningTable diningTable;

    public FoodItem(Long itemId, String itemName, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

}
