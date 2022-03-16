package android.example.myportfolio;

import androidx.annotation.NonNull;

public class Stock {
    private String name;
    private double price;
    private double change_24h;

    public Stock() {
        name = "ABC";
        price = 0;
        change_24h = 0;
    }

    public Stock(String name, double price, double change_24h) {
        this.name = name;
        this.price = price;
        this.change_24h = change_24h;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getChange_24h() {
        return change_24h;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setChange_24h(double change_24h) {
        this.change_24h = change_24h;
    }

    @NonNull
    public String toString() {
        return name + " is trading at $" + price + " with a 24 hour change of" +
                " %" + change_24h;
    }
}
