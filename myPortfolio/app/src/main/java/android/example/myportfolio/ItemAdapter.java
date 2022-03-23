package android.example.myportfolio;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ItemView> viewList;

    public ItemAdapter(List<ItemView> viewList) {
        this.viewList = viewList;
    }

    public List<ItemView> getViewList() {
        return viewList;
    }

    @Override
    public int getItemViewType(int position) {
        return viewList.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    // TODO: figure out how to dynamically handle live data for RecyclerView
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return viewList.size();
    }
}
