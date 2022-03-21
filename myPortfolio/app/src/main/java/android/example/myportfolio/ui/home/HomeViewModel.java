package android.example.myportfolio.ui.home;

import android.example.myportfolio.Stock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<LineGraphSeries<DataPoint>> balanceData;
    private final MutableLiveData<List<Stock>> watchlist;

    public HomeViewModel() {
        // initialize balance data as an empty line graph series
        balanceData = new MutableLiveData<>(new LineGraphSeries<>());

        // initialize watchlist as an empty array list
        watchlist = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<LineGraphSeries<DataPoint>> getBalanceData() {
        return balanceData;
    }

    public LiveData<List<Stock>> getWatchlist() {
        return watchlist;
    }
}