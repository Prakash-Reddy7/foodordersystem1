package com.foodordersystem.service;

import com.foodordersystem.Entity.DiningTable;
import com.foodordersystem.Entity.DiningTableWithFoodItemsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TableService {

    Page<DiningTableWithFoodItemsDTO> getAllTablesWithFoodItems(Pageable pageable);


    ResponseEntity<?> getTableById(Long tableId);

    ResponseEntity<?> bookTable(DiningTable diningTable);
    ResponseEntity<?> closeTable(Long tableId);
    Page<DiningTable> getAllTables(Pageable pageable);


}
