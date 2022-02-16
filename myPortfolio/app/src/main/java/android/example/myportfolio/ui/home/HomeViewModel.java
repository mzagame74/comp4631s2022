package android.example.myportfolio.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<LineGraphSeries<DataPoint>> balanceData;

    public HomeViewModel() {
        balanceData = new MutableLiveData<>();

        // set up balance graph with dummy data
        balanceData.setValue(new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(2, 3),
                new DataPoint(5, 7),
        }));
    }

    public LiveData<LineGraphSeries<DataPoint>> getBalanceData() {
        return balanceData;
    }
}