package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class Invoice implements Serializable {
    private int invoiceId;
    private int orderID;
    private User user;
    private double sum;
    private Payment payment;

    public Invoice(int invoiceId, int orderID,User user, double sum, Payment payment) {
        this.invoiceId = invoiceId;
        this.orderID = orderID;
        this.user = user;
        this.sum = sum;
        this.payment = payment;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
