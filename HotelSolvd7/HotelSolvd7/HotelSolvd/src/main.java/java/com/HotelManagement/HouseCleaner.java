package com.HotelManagement;

import enums.Division;

public class HouseCleaner<E> extends HotelEmployee {
    HouseCleaner(int hotelEmployeeId, int hotelId, String hotelEmployeeFirstName, String hotelEmployeeLastName, int salary, int yearHired, Division division){
        super(hotelEmployeeId, hotelId, hotelEmployeeFirstName, hotelEmployeeLastName, salary, yearHired, division);
    }
}

