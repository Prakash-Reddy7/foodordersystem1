package test;

import com.foodordersystem.Entity.DiningTable;
import com.foodordersystem.Repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan("com.FoodOrderSytem.Repository")
class TableRepositoryTest {

    @Autowired
    private TableRepository tableRepository;

    @Test
    void testFindByTableNumber() {
        // Given
        DiningTable diningTable = new DiningTable();
        diningTable.setTableNumber(1L);
        diningTable.setBooked(true);
        diningTable.setCustomerName("prakash");
        diningTable.setPhoneNumber(12343454L);
        tableRepository.save(diningTable);

        DiningTable newDiningTable = tableRepository.findByTableNumber(diningTable.getTableNumber());

        assertEquals(1L, newDiningTable.getTableNumber());
    }
}
