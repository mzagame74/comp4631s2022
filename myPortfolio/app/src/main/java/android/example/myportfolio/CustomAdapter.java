package android.example.myportfolio;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Collections;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CustomView> viewList;

    public CustomAdapter(List<CustomView> viewList) {
        this.viewList = viewList;
    }

    public List<CustomView> getViewList() {
        return viewList;
    }

    @Override
    public int getItemViewType(int position) {
        return viewList.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case CustomView.BalanceView:
                View balanceView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.header_balance, parent, false);
                return new BalanceViewHolder(balanceView);
            case CustomView.BalanceGraphView:
                View balanceGraphView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_balance, parent, false);
                return new BalanceGraphViewHolder(balanceGraphView);
            case CustomView.WatchlistHeaderView:
                View watchlistHeaderView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.header_text, parent, false);
                return new WatchlistHeaderViewHolder(watchlistHeaderView);
            case CustomView.StockView:
                View stockView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stock, parent, false);
                return new StockViewHolder(stockView);
            case CustomView.PieChartView:
                View pieChartView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.pie_chart_portfolio, parent, false);
                return new PieChartViewHolder(pieChartView);
            default:
                throw new IllegalArgumentException("Cannot create ViewHolder" +
                        " with invalid view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getViewList().get(position).getViewType()) {
            case CustomView.BalanceView:          // set balance string
                ((BalanceViewHolder) holder).setBalance(getViewList().get(position).getBalance());
                break;
            case CustomView.BalanceGraphView:     // set balance graph data
                ((BalanceGraphViewHolder) holder).setBalanceData(getViewList().get(position).getBalanceData());
                break;
            case CustomView.WatchlistHeaderView:  // set watchlist header
                ((WatchlistHeaderViewHolder) holder).setHeaderText("Watchlist");
                break;
            case CustomView.StockView:            // set stock information
                ((StockViewHolder) holder).setStockInfo(getViewList().get(position).getStock());
                break;
            case CustomView.PieChartView:
                ((PieChartViewHolder) holder).setChartData(getViewList().get(position).getAssets());
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return viewList.size();
    }

    public static class BalanceViewHolder extends RecyclerView.ViewHolder {
        private final TextView balanceString;

        public BalanceViewHolder(@NonNull View itemView) {
            super(itemView);
            balanceString = itemView.findViewById(R.id.string_balance);
        }

        public void setBalance(double balance) {
            balanceString.setText("$");
            balanceString.append(String.valueOf(balance));
        }
    }

    public static class BalanceGraphViewHolder extends RecyclerView.ViewHolder {
        private final GraphView balanceGraph;

        public BalanceGraphViewHolder(@NonNull View itemView) {
            super(itemView);
            balanceGraph = itemView.findViewById(R.id.graph_balance);
            balanceGraph.setTitle("Balance");
            balanceGraph.setTitleTextSize(0);
            balanceGraph.getViewport().setDrawBorder(true);
            balanceGraph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
            balanceGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
            balanceGraph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        }

        public void setBalanceData(DataPoint[] balanceData) {
            balanceGraph.addSeries(new LineGraphSeries<>(balanceData));
        }
    }

    public static class WatchlistHeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView headerText;

        public WatchlistHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.header_text);
        }

        public void setHeaderText(String text) {
            headerText.setText(text);
        }
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        private final TextView stockSymbol;
        private final TextView stockPrice;
        private final TextView stockChange24h;
        private final GraphView stockPriceGraph;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            stockSymbol = itemView.findViewById(R.id.stock_symbol);
            stockPrice = itemView.findViewById(R.id.stock_price);
            stockChange24h = itemView.findViewById(R.id.stock_change_24h);
            stockPriceGraph = itemView.findViewById(R.id.stock_price_graph);
            stockPriceGraph.setTitleTextSize(0);
            stockPriceGraph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
            stockPriceGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
            stockPriceGraph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        }

        public void setStockInfo(Stock stock) {
            stockSymbol.setText(stock.getSymbol());
            stockPrice.setText("$");
            stockPrice.append(String.valueOf(stock.getPrice()));
            stockChange24h.setText(String.valueOf(stock.getChange24h()));
            stockChange24h.append("%");
            stockPriceGraph.setTitle(stock.getSymbol());
            stockPriceGraph.addSeries(new LineGraphSeries<>(stock.getGraphData()));
        }
    }

    public static class PieChartViewHolder extends RecyclerView.ViewHolder {
        private final PieChart pieChart;

        public PieChartViewHolder(@NonNull View itemView) {
            super(itemView);
            pieChart = itemView.findViewById(R.id.pie_chart_portfolio);
        }

        public void setChartData(List<Asset> assets) {
            int numAssets = assets.size();

            // sort assets by value
            Collections.sort(assets, (a1, a2) -> Double.compare(a2.getValue(), a1.getValue()));

            // add a pie slice for every asset up to 4 assets plus "other(s)"
            if (numAssets > 0) {
                pieChart.addPieSlice(new PieModel(assets.get(numAssets - 1).getSymbol(),
                        (int) getPercentValue(assets.get(numAssets - 1),
                                assets),
                        Color.parseColor("#FFFFA726")));
            }
            if (numAssets > 1) {
                pieChart.addPieSlice(new PieModel(assets.get(numAssets - 2).getSymbol(),
                        (int) getPercentValue(assets.get(numAssets - 2),
                                assets),
                        Color.parseColor("#FF66BB6A")));
            }
            if (numAssets > 2) {
                pieChart.addPieSlice(new PieModel(assets.get(numAssets - 3).getSymbol(),
                        (int) getPercentValue(assets.get(numAssets - 3),
                                assets),
                        Color.parseColor("#FFEF5350")));
            }
            if (numAssets > 3) {
                pieChart.addPieSlice(new PieModel(assets.get(numAssets - 4).getSymbol(),
                        (int) getPercentValue(assets.get(numAssets - 4),
                                assets),
                        Color.parseColor("#FF29B6F6")));
            }
            if (numAssets > 4) {
                double mainValue = 0;       // value of largest four assets
                for (int i = numAssets - 4; i < numAssets; ++i) {
                    mainValue += assets.get(i).getValue();
                }
                pieChart.addPieSlice(new PieModel("Other(s)",
                        100 - (int) (mainValue / getTotalValue(assets) * 100),
                        Color.parseColor("#FF78D6D0")));
            }
        }

        // get total value of the entire portfolio
        public double getTotalValue(List<Asset> assets) {
            double totalValue = 0;
            for (Asset asset : assets) {
                totalValue += asset.getValue();
            }
            return totalValue;
        }

        // get an asset's percent of the portfolio based on asset values
        public double getPercentValue(Asset asset, List<Asset> assets) {
            return asset.getValue() / getTotalValue(assets) * 100;
        }
    }
}
