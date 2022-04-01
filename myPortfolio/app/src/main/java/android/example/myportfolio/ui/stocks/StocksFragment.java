package android.example.myportfolio.ui.stocks;

import android.example.myportfolio.ItemView;
import android.example.myportfolio.Stock;
import android.example.myportfolio.databinding.FragmentStocksBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class StocksFragment extends Fragment {

    private FragmentStocksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*StocksViewModel stocksViewModel =
                new ViewModelProvider(this).get(StocksViewModel.class);*/

        binding = FragmentStocksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // set up recycler view
        RecyclerView stocksRecycler = binding.recyclerStocks;
        List<ItemView> viewList = new ArrayList<>();
        List<Stock> stocks = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        StocksAdapter stocksAdapter = new StocksAdapter(viewList);
        stocksRecycler.setLayoutManager(layoutManager);
        stocksRecycler.setAdapter(stocksAdapter);

        // add stocks
        stocks.add(new Stock("ABC", 4.04, -3.50, new DataPoint[]{
                new DataPoint(0, 5),
                new DataPoint(1, 6),
                new DataPoint(2, 5),
                new DataPoint(3, 4)
        }));
        stocks.add(new Stock("XYZ", 5.89, 6.9, new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 7),
                new DataPoint(3, 15)
        }));
        stocks.add(new Stock("123", 1.23, -4.20, new DataPoint[]{
                new DataPoint(0, 5),
                new DataPoint(1, 6),
                new DataPoint(2, 5),
                new DataPoint(3, 4)
        }));
        stocks.add(new Stock("DOREME", 0, 0, new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 7),
                new DataPoint(3, 15)
        }));

        // add views to view list
        try {
            for (Stock stock : stocks) {
                viewList.add(new ItemView(ItemView.StockView, stock));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}