package android.example.myportfolio.ui.stocks;

import android.example.myportfolio.databinding.FragmentStocksBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class StocksFragment extends Fragment {

    private FragmentStocksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StocksViewModel dashboardViewModel =
                new ViewModelProvider(this).get(StocksViewModel.class);

        binding = FragmentStocksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}