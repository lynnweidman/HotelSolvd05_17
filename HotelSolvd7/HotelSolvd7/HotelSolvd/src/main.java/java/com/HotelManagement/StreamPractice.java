package com.HotelManagement;

import enums.Division;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String[] args) {

        List<Bill> b = new ArrayList<>();
        b.add(new Bill(1, 1, 50.99));
        b.add(new Bill(2, 2, 89.50));
        b.add(new Bill(3, 3, 33.99));
        b.add(new Bill(4, 4, 150.33));
        b.add(new Bill(5, 5, 45.56));

        //1. Not-terminal mapToDouble with Terminal average() and getAsDouble() stream that prints the average of bill totals.
        double averageBills = b
                .stream()
                .mapToDouble(Bill::getTotalBill)
                .average()
                .getAsDouble();
        System.out.println("The average bill is: " + averageBills);

        //2. Terminal max() and get() Stream that finds the bill with the highest amount.
        Bill maxBill = b
                .stream()
                .max((b1, b2) -> Double.compare(b1.getTotalBill(), b2.getTotalBill()))
                .get();
        System.out.println("The highest total bill we have received is: " + maxBill);

        //3. Not-terminal mapToDouble() with terminal sum Stream that sums all the total bills.
        double totalSales = b
                .stream()
                .mapToDouble(Bill::getTotalBill)
                .sum();
        System.out.println("Total sales: $" + totalSales);

        //4. Non-Terminal mapToDouble() and sorted() stream that prints the values of each bill's totalBill in ascending order.
        DoubleStream sortedBillTotals = b
                .stream()
                .mapToDouble(Bill::getTotalBill)
                .sorted();
        System.out.println("Bills we have received in ascending order:" + sortedBillTotals);

        //5. Non-terminal peek() and filter() with terminal forEach().
        Stream.of(b).peek(e -> System.out.println("Original value: " + e))
                .filter(e -> e.size() <= 5 )
                .peek(e -> System.out.println("Filtered value 1: " + e))
                .filter(e -> e.size() >= 6 )
                .peek(e -> System.out.println("Filtered value 2: " + e))
                .forEach(System.out::println);


        List<HotelEmployee> hotelEmployees = new ArrayList<>();
        HotelEmployee<HouseCleaner> emp1 = new HotelEmployee<>(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
        HotelEmployee<Manager> emp2 = new HotelEmployee<>(101, 2, "John", "Smith", 60000, 2022, Division.MANAGEMENT);
        HotelEmployee<Receptionist> emp3 = new HotelEmployee<>(103, 1, "Sally", "Johnson", 30000, 2020, Division.MANAGEMENT);
        HotelEmployee<Receptionist> emp4 = new HotelEmployee<>(103, 2, "Luke", "Wilson", 30000, 2020, Division.RECEPTIONIST);

        hotelEmployees.add(emp1);
        hotelEmployees.add(emp2);
        hotelEmployees.add(emp3);
        hotelEmployees.add(emp4);


        //6. Non-terminal filter() and map() with Terminal collect() stream aggregating the list of hotelEmployees and printing list of first names of employees in the MANAGEMENT division.
        List<String> empGroupedByDivision = hotelEmployees
                .stream()
                .filter(d -> d.getDivision() == Division.MANAGEMENT)
                .map(name -> name.getHotelEmployeeFirstName())
                .collect(Collectors.toList());
        System.out.println("All employees in the MANAGEMENT division: " + empGroupedByDivision);

        //7. Terminal forEach() stream that finds hotel employees first and last names.
        hotelEmployees.stream()
                .forEach(emp -> System.out.println(emp.hotelEmployeeFirstName + emp.hotelEmployeeLastName));

    }
}




