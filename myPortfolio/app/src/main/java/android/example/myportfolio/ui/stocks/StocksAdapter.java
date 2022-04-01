package android.example.myportfolio.ui.stocks;

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
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class StocksAdapter extends ItemAdapter {

    public StocksAdapter(List<ItemView> viewList) {
        super(viewList);
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ItemView.StockView) {
            View stockView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stock, parent, false);
            return new StockViewHolder(stockView);
        }
        throw new IllegalArgumentException("Cannot create ViewHolder with " +
                "invalid view type: " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // set stock information
        if (getViewList().get(position).getViewType() == ItemView.StockView) {
            ((StockViewHolder) holder).setStockInfo(getViewList().get(position).getStock());
        }
    }
}
