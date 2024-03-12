package com.foodordersystem.Controller;

import com.foodordersystem.Entity.DiningTable;
import com.foodordersystem.Entity.DiningTableWithFoodItemsDTO;
import com.foodordersystem.Exception.TableAlreadyBookedException;
import com.foodordersystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5000")



@RestController
@RequestMapping("/tables")
@Controller
public class TableController {
    @Autowired
    private TableService tableService;

    @PostMapping("/bookTable")
    public ResponseEntity<?> bookTable(@RequestBody DiningTable diningTable) {
        System.out.println("Received request: " + diningTable);
        try {
            return tableService.bookTable(diningTable);
        } catch (TableAlreadyBookedException e) {
            return new ResponseEntity<>("Table is already booked", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/closeTable")
    public ResponseEntity<?> closeTable(@RequestParam(name = "tableId", required = true) Long tableId) {
        return tableService.closeTable(tableId);
    }




    @GetMapping("/getAllTablesWithFoodItems")
    public ResponseEntity<Page<DiningTableWithFoodItemsDTO>> getAllTablesWithFoodItems(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "4") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<DiningTableWithFoodItemsDTO> tablesWithFoodItems = tableService.getAllTablesWithFoodItems(pageable);
            if (pageable.getPageNumber() > pageable.getPageSize()) {
                return new ResponseEntity<>(tablesWithFoodItems, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(tablesWithFoodItems, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTableById")
    public ResponseEntity<?> getTableById(@RequestParam(name = "tableId", required = true) Long tableId) {
        return tableService.getTableById(tableId);
    }





    @GetMapping("/getAllTables")
    public ResponseEntity<Page<DiningTable>> getAllTables(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "4") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<DiningTable> allTables = tableService.getAllTables(pageable);
            if (allTables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(allTables, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
