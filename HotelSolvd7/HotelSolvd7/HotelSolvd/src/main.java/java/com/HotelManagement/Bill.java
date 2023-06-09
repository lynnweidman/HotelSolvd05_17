package com.HotelManagement;

import interfaces.CalculateBill;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Stream;

public class Bill implements CalculateBill {
    private int billId;
    private int orderId;
    private double totalBill;
    public static ArrayList<String> list;

    public Bill(int billId, int orderId, double totalBill) {
        this.billId = billId;
        this.orderId = orderId;
        this.totalBill = totalBill;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public double calculateBill(double subtotal) {
        Formatter f = new Formatter();
        double formattedTotal;
        double totalWithTax = (subtotal * TAX_RATE) + subtotal;
        formattedTotal = Double.parseDouble(String.valueOf(f.format("%.2f", totalWithTax)));
        return formattedTotal;
    }

    public static double sumOfTotalBills(Bill totalBill) {
        return Stream.of(totalBill).peek(e -> System.out.println("Original value: " + e)).count();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", orderId=" + orderId +
                ", totalBill=" + totalBill +
                '}';
    }
}
