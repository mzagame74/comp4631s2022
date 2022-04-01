package android.example.myportfolio;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    @Override
    public int getItemCount() {
        return viewList.size();
    }
}
