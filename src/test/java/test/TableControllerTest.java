package test;

import com.foodordersystem.Controller.TableController;
import com.foodordersystem.Entity.DiningTable;
import com.foodordersystem.Entity.DiningTableWithFoodItemsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootApplication(scanBasePackages = "com.FoodOrderSytem")
@ContextConfiguration


public class TableControllerTest {

    @Autowired
    private TableController tableController;

    @Test
    public void testBookTableSuccess() {
        // Arrange
        DiningTable mockDiningTable = new DiningTable();
        mockDiningTable.setId(1L);
        mockDiningTable.setCustomerName("prakash");
        mockDiningTable.setPhoneNumber(12343454L);
        mockDiningTable.setTableNumber(1L);
        mockDiningTable.setBooked(false);
        // Act
        ResponseEntity<?> responseEntity = tableController.bookTable(mockDiningTable);
        // Assert

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void testGetAllTablesWithFoodItemsByPage() {
        ResponseEntity<Page<DiningTableWithFoodItemsDTO>> responseEntity = tableController.getAllTablesWithFoodItems(0, 4);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());

    }

    @Test
    public void testGetAllTablesWithFoodItemsWithInvalidPageNumber() {
        ResponseEntity<Page<DiningTableWithFoodItemsDTO>> responseEntity = tableController.getAllTablesWithFoodItems(30, 4);
        assertNotNull(responseEntity);
        assertEquals(404, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());

    }

}

