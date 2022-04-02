package android.example.myportfolio;

public class Asset {
    private final String symbol;
    private double price;
    private double holdings;

    public Asset(String symbol) {
        this.symbol = symbol;
        price = 0;
        holdings = 0;
    }

    public Asset(String symbol, double price, double holdings) {
        this.symbol = symbol;
        this.price = price;
        this.holdings = holdings;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHoldings() {
        return holdings;
    }

    public void setHoldings(double holdings) {
        this.holdings = holdings;
    }

    public double getValue() {
        return price * holdings;
    }
}
