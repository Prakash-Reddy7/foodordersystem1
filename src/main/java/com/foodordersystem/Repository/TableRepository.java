package com.foodordersystem.Repository;
import com.foodordersystem.Entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<DiningTable, Long>{


    DiningTable findByTableNumber(Long tableNumber);
    boolean existsByTableNumberAndBookedIsTrue(Long tableNumber);
    boolean existsByTableNumberAndCustomerName(Long tableNumber, String customerName );

}





