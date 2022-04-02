package android.example.myportfolio;

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
                View balanceLayout =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.header_balance, parent, false);
                return new BalanceViewHolder(balanceLayout);
            case CustomView.BalanceGraphView:
                View balanceGraphLayout =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_balance, parent, false);
                return new android.example.myportfolio.CustomAdapter.BalanceGraphViewHolder(balanceGraphLayout);
            case CustomView.WatchlistHeaderView:
                View watchlistLayout =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.header_text, parent, false);
                return new android.example.myportfolio.CustomAdapter.WatchlistHeaderViewHolder(watchlistLayout);
            case CustomView.StockView:
                View stockView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stock, parent, false);
                return new StockViewHolder(stockView);
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
                ((android.example.myportfolio.CustomAdapter.BalanceGraphViewHolder) holder).setBalanceData(getViewList().get(position).getBalanceData());
                break;
            case CustomView.WatchlistHeaderView:  // set watchlist header
                ((android.example.myportfolio.CustomAdapter.WatchlistHeaderViewHolder) holder).setHeaderText("Watchlist");
                break;
            case CustomView.StockView:            // set stock information
                ((StockViewHolder) holder).setStockInfo(getViewList().get(position).getStock());
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
}
