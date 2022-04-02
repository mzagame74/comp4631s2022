package android.example.myportfolio;

import com.jjoe64.graphview.series.DataPoint;

public class CustomView {
    // valid view types
    public static final int BalanceView = 0;
    public static final int BalanceGraphView = 1;
    public static final int WatchlistHeaderView = 2;
    public static final int StockView = 3;

    private int viewType;
    private double balance;
    private DataPoint[] balanceData;
    private Stock stock;

    // default constructor
    public CustomView(int viewType) throws IllegalArgumentException {
        this.viewType = viewType;
        if (viewType < 0 || viewType > 3) {
            throw new IllegalArgumentException("Cannot create CustomView with invalid view " +
                    "type: " + viewType);
        }
    }

    // constructor for balance view
    public CustomView(int viewType, double balance) throws IllegalArgumentException {
        if (viewType < 0 || viewType > 3) {
            throw new IllegalArgumentException("Cannot create CustomView with invalid view " +
                    "type: " + viewType);
        }
        this.viewType = viewType;
        this.balance = balance;
    }

    // constructor for balance graph view
    public CustomView(int viewType, DataPoint[] balanceData) throws IllegalArgumentException {
        if (viewType < 0 || viewType > 3) {
            throw new IllegalArgumentException("Cannot create CustomView with invalid view " +
                    "type: " + viewType);
        }
        this.viewType = viewType;
        this.balanceData = balanceData;
    }

    // constructor for stock view
    public CustomView(int viewType, Stock stock) throws IllegalArgumentException {
        this.viewType = viewType;
        this.stock = stock;
        if (viewType < 0 || viewType > 3) {
            throw new IllegalArgumentException("Cannot create CustomView with invalid view " +
                    "type: " + viewType);
        }
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public DataPoint[] getBalanceData() {
        return balanceData;
    }

    public void setBalanceData(DataPoint[] balanceData) {
        this.balanceData = balanceData;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
