package com.foodordersystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tableNumber", "customerName", "bookingTime"})
})

public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tableId")
    private Long id;

    @Column(name = "customerName", nullable = false)
    private String customerName;

    @Column(name = "phoneNumber", nullable = false)
    private Long phoneNumber;

    @Column(name = "tableNumber", nullable = false)
    private Long tableNumber;

    @Column(name = "booked", nullable = false)
    private boolean booked = false;

    @Column(name = "bookingTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime bookingTime = LocalDateTime.now();

    public DiningTable(Long id, String customerName, Long phoneNumber, Long tableNumber) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
    }


 public DiningTable() {
    }
}
