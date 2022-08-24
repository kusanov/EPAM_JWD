package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class Payment implements Serializable {
    private int id;
    private String payment;

    public Payment() {
        this.id = 1;
        this.payment = "unpaid";
    }

    public Payment(String payment) {
        this.payment = payment;
    }

    public Payment(int id, String payment) {
        this.id = id;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
