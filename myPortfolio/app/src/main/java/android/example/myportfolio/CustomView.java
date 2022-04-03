package android.example.myportfolio;

import com.jjoe64.graphview.series.DataPoint;

import java.util.List;

public class CustomView {
    // valid view types
    public static final int BalanceView = 0;
    public static final int BalanceGraphView = 1;
    public static final int WatchlistHeaderView = 2;
    public static final int StockView = 3;
    public static final int PieChartView = 4;
    public static final int AssetsHeaderView = 5;
    public static final int AssetView = 6;

    private int viewType;
    private double balance;
    private DataPoint[] balanceData;
    private Stock stock;
    private List<Asset> assets;

    // default constructor
    public CustomView(int viewType) throws IllegalArgumentException {
        if (viewType < 0 || viewType > 7) {
            throw new IllegalArgumentException("Invalid view type " + viewType +
                    " for CustomView");
        }
        this.viewType = viewType;
    }

    // constructor for balance view
    public CustomView(int viewType, double balance) throws IllegalArgumentException {
        if (viewType != BalanceView) {
            throw new IllegalArgumentException("Invalid view type " + viewType +
                    " for BalanceView");
        }
        this.viewType = viewType;
        this.balance = balance;
    }

    // constructor for balance graph view
    public CustomView(int viewType, DataPoint[] balanceData) throws IllegalArgumentException {
        if (viewType != BalanceGraphView) {
            throw new IllegalArgumentException("Invalid view type " + viewType +
                    " for BalanceGraphView");
        }
        this.viewType = viewType;
        this.balanceData = balanceData;
    }

    // constructor for stock view
    public CustomView(int viewType, Stock stock) throws IllegalArgumentException {
        if (viewType != StockView) {
            throw new IllegalArgumentException("Invalid view type " + viewType +
                    " for StockView");
        }
        this.viewType = viewType;
        this.stock = stock;
    }

    // constructor for pie chart view and pie chart key view
    public CustomView(int viewType, List<Asset> assets) throws IllegalArgumentException {
        if (viewType != PieChartView) {
            throw new IllegalArgumentException("Invalid view type " + viewType +
                    " for PieChartView");
        }
        this.viewType = viewType;
        this.assets = assets;
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

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
