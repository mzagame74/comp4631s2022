package android.example.myportfolio;

import androidx.annotation.NonNull;

import com.jjoe64.graphview.series.DataPoint;

public class Stock {
    private String symbol;
    private double price;
    private double change24h;
    private DataPoint[] graphData;

    public Stock() {
        symbol = "undefined";
        price = 0;
        change24h = 0;
        graphData = null;
    }

    public Stock(String symbol, double price, double change24h,
                 DataPoint[] graphData) {
        this.symbol = symbol;
        this.price = price;
        this.change24h = change24h;
        this.graphData = graphData;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange24h() {
        return change24h;
    }

    public void setChange24h(double change24h) {
        this.change24h = change24h;
    }

    public DataPoint[] getGraphData() {
        return graphData;
    }

    public void setGraphData(DataPoint[] graphData) {
        this.graphData = graphData;
    }

    @NonNull
    public String toString() {
        if (change24h < 0) {
            return symbol + " is currently trading at $" + price + " down " + change24h + "% over 24 hours";
        }
        return symbol + " is currently trading at $" + price + " up " + change24h + "% over 24 hours";
    }
}