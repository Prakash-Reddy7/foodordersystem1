package com.foodordersystem.ServiceImpl;

import com.foodordersystem.Entity.DiningTable;
import com.foodordersystem.Entity.DiningTableWithFoodItemsDTO;
import com.foodordersystem.Exception.TableAlreadyBookedException;
import com.foodordersystem.Repository.FoodItemRepository;
import com.foodordersystem.Repository.TableRepository;
import com.foodordersystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public Page<DiningTableWithFoodItemsDTO> getAllTablesWithFoodItems(Pageable pageable) {
        Page<DiningTable> allTables = tableRepository.findAll(pageable);

        return allTables.map(diningTable ->
                new DiningTableWithFoodItemsDTO(diningTable,
                        foodItemRepository.findByDiningTableTableNumber(diningTable.getTableNumber())));
    }

    @Override
    public ResponseEntity<?> getTableById(Long tableId) {
        Optional<DiningTable> diningTable = tableRepository.findById(tableId);
        if (diningTable.isPresent()) {
            return new ResponseEntity<>(diningTable.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Table not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> bookTable(DiningTable diningTable) {
        try {
            Long tableNumber = diningTable.getTableNumber();
            String customerName = diningTable.getCustomerName();



            if (tableRepository.existsByTableNumberAndCustomerName(tableNumber, customerName)) {
                return new ResponseEntity<>("Table is already booked by the same customer.", HttpStatus.CONFLICT);
            }

            // Check if the table is already booked
            if (isTableAlreadyBooked(tableNumber)) {
                throw new TableAlreadyBookedException();
            }

            diningTable.setBooked(true);
            tableRepository.save(diningTable);

            // Fetch the updated table data after booking
            Optional<DiningTable> updatedTable = tableRepository.findById(diningTable.getId());

            if (updatedTable.isPresent()) {
                return new ResponseEntity<>(updatedTable.get(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Error while fetching updated table data", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (TableAlreadyBookedException e) {
            throw e;
        }
    }





    @Override
    public ResponseEntity<?> closeTable(Long tableId) {
        Optional<DiningTable> optionalTable = tableRepository.findById(tableId);

        if (optionalTable.isPresent()) {
            DiningTable diningTable = optionalTable.get();

            // Check if the table is currently booked
            if (diningTable.isBooked()) {
                diningTable.setBooked(false);
                diningTable.setBookingTime(null); // Reset booking time
                tableRepository.save(diningTable);
                return new ResponseEntity<>("Table closed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Table is not booked", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Table not found", HttpStatus.NOT_FOUND);
        }
    }

    private boolean isTableAlreadyBooked(Long tableNumber) {
        return tableRepository.existsByTableNumberAndBookedIsTrue(tableNumber);
    }
    @Override
    public Page<DiningTable> getAllTables(Pageable pageable) {
        return tableRepository.findAll(pageable);
    }
}

