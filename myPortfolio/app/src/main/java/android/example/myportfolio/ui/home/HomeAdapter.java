package android.example.myportfolio.ui.home;

import android.example.myportfolio.ItemAdapter;
import android.example.myportfolio.ItemView;
import android.example.myportfolio.R;
import android.example.myportfolio.Stock;
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

import java.util.List;

public class HomeAdapter extends ItemAdapter {

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

    public static class WatchlistViewHolder extends RecyclerView.ViewHolder {
        private final TextView headerText;

        public WatchlistViewHolder(@NonNull View itemView) {
            super(itemView);
            // TODO: create header TextView programmatically and delete
            //  unnecessary header text layout
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

    public HomeAdapter(List<ItemView> viewList) {
        super(viewList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemView.BalanceView:
                View balanceLayout =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.header_balance, parent, false);
                return new BalanceViewHolder(balanceLayout);
            case ItemView.BalanceGraphView:
                View balanceGraphLayout =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_balance, parent, false);
                return new BalanceGraphViewHolder(balanceGraphLayout);
            case ItemView.WatchlistView:
                View watchlistLayout =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.header_text, parent, false);
                return new WatchlistViewHolder(watchlistLayout);
            case ItemView.StockView:
                View stockView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stock, parent, false);
                return new StockViewHolder(stockView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getViewList().get(position).getViewType()) {
            case ItemView.BalanceView:
                // update balance string
                ((BalanceViewHolder) holder).setBalance(getViewList().get(position).getBalance());
                break;
            case ItemView.BalanceGraphView:
                // update balance graph data
                ((BalanceGraphViewHolder) holder).setBalanceData(getViewList().get(position).getBalanceData());
                break;
            case ItemView.WatchlistView:
                // update watchlist view
                ((WatchlistViewHolder) holder).setHeaderText("Watchlist");
                break;
            case ItemView.StockView:
                // update stock information
                ((StockViewHolder) holder).setStockInfo(getViewList().get(position).getStock());
            default:
        }
    }
}
