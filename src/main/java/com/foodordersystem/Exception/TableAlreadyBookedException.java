package com.foodordersystem.Exception;
public class TableAlreadyBookedException extends RuntimeException {
    public TableAlreadyBookedException() {
        super("Table is already booked");
    }
}
