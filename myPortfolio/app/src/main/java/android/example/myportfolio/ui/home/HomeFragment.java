package android.example.myportfolio.ui.home;

import android.example.myportfolio.databinding.FragmentHomeBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.jjoe64.graphview.GraphView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // set up balance graph view
        final GraphView balanceGraph = binding.graphBalance;
        balanceGraph.setTitle("Balance");
        balanceGraph.setTitleTextSize(0);
        balanceGraph.getViewport().setScrollable(true);
        homeViewModel.getBalanceData().observe(getViewLifecycleOwner(),
                balanceGraph::addSeries);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}