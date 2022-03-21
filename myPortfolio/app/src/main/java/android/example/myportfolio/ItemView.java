package android.example.myportfolio;

import com.jjoe64.graphview.series.DataPoint;

import java.util.List;

public class ItemView {
    // valid view types
    public static final int BalanceView = 0;
    public static final int BalanceGraphView = 1;
    public static final int WatchlistView = 2;
    public static final int StockView = 3;

    private int viewType;
    private double balance;
    private DataPoint[] balanceData;
    private List<Stock> watchlist;
    private Stock stock;

    // TODO: handle exception when view type is not within valid range
    // constructor for balance view
    public ItemView(int viewType, double balance) {
        this.viewType = viewType;
        this.balance = balance;
    }

    // constructor for balance graph view
    public ItemView(int viewType, DataPoint[] balanceData) {
        this.viewType = viewType;
        this.balanceData = balanceData;
    }

    // constructor for watchlist view
    public ItemView(int viewType, List<Stock> watchlist) {
        this.viewType = viewType;
        this.watchlist = watchlist;
    }

    // constructor for stock view
    public ItemView(int viewType, Stock stock) {
        this.viewType = viewType;
        this.stock = stock;
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

    public List<Stock> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Stock> watchlist) {
        this.watchlist = watchlist;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
