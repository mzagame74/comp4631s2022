package android.example.myportfolio.ui.home;

import android.example.myportfolio.ItemView;
import android.example.myportfolio.Stock;
import android.example.myportfolio.databinding.FragmentHomeBinding;
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

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // set up recycler view
        RecyclerView homeRecycler = binding.recyclerHome;
        List<ItemView> viewList = new ArrayList<>();
        List<Stock> watchlist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        HomeAdapter homeAdapter = new HomeAdapter(viewList);
        homeRecycler.setLayoutManager(layoutManager);
        homeRecycler.setAdapter(homeAdapter);

        // add stocks
        watchlist.add(new Stock("ABC", 1.23, -4.20, new DataPoint[]{
                new DataPoint(0, 5),
                new DataPoint(1, 6),
                new DataPoint(2, 5),
                new DataPoint(3, 4)
        }));
        watchlist.add(new Stock("XYZ", 5.89, 6.9, new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 7),
                new DataPoint(3, 15)
        }));

        // add views and set adapter
        viewList.add(new ItemView(ItemView.BalanceView, 5.89));
        viewList.add(new ItemView(ItemView.BalanceGraphView, new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 7),
                new DataPoint(3, 11)
        }));
        viewList.add(new ItemView(ItemView.WatchlistView, watchlist));

        for (int i = 0; i < watchlist.size(); ++i) {
            viewList.add(new ItemView(ItemView.StockView, watchlist.get(i)));
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}