package android.example.myportfolio.ui.portfolio;

import android.example.myportfolio.Asset;
import android.example.myportfolio.CustomAdapter;
import android.example.myportfolio.CustomView;
import android.example.myportfolio.databinding.FragmentPortfolioBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*PortfolioViewModel portfolioViewModel =
                new ViewModelProvider(this).get(PortfolioViewModel.class);*/

        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // set up recycler view
        RecyclerView portfolioRecycler = binding.recyclerPortfolio;
        List<CustomView> viewList = new ArrayList<>();
        List<Asset> assets = new ArrayList<>();
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(requireContext());
        CustomAdapter customAdapter = new CustomAdapter(viewList);
        portfolioRecycler.setLayoutManager(layoutManager);
        portfolioRecycler.setAdapter(customAdapter);

        // add assets to portfolio
        assets.add(new Asset("BTC", 50000, 0.05));
        assets.add(new Asset("NVDA", 270, 3.9));
        assets.add(new Asset("TSLA", 1000, 1.7));
        assets.add(new Asset("ETH", 3500, 0.37));

        // add views to list
        try {
            viewList.add(new CustomView(CustomView.PieChartView, assets));
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