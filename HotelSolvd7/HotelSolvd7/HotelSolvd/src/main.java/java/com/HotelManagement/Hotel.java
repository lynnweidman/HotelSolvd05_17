package com.HotelManagement;

import exceptions.CheckHotelExistsException;
import exceptions.CheckHotelIdException;

import java.util.ArrayList;
import java.util.Objects;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private int numberOfEmployees;

    public Hotel(int hotelId, String hotelName, int numberOfEmployees) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.numberOfEmployees = numberOfEmployees;
    }

    public static ArrayList<Hotel> allHotels = new ArrayList<>();

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) throws CheckHotelIdException {
        if (hotelId <= 0) {
            throw new CheckHotelIdException("Id cannot be 0");
        } else {
            this.hotelId = hotelId;
        }
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) throws CheckHotelExistsException {
        for (Hotel h : allHotels)
            if (h.getHotelName().equals(hotelName)) {
                throw new CheckHotelExistsException("This hotel already exists");
            } else {
                this.hotelName = hotelName;
            }
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public static ArrayList<Hotel> getAllHotels() {
        return allHotels;
    }


    public static void setAllHotels(ArrayList<Hotel> allHotels) {
        Hotel.allHotels = allHotels;
    }

    @Override
    public String toString() {
        return "HotelManagement{" +
                "hotelName='" + hotelName + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return numberOfEmployees == hotel.numberOfEmployees && Objects.equals(hotelName, hotel.hotelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelName, numberOfEmployees);
    }
}

