package dagkalis.georgios.recyclerView;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dagkalis.georgios.selectablerecyclerview.SelectableRecyclerViewAdapter;

public class NewAdapter extends SelectableRecyclerViewAdapter<NewAdapter.NewHolder>{


    @Override
    public void onBindSelectableViewHolder(@NonNull NewHolder holder, int position) {

    }

    @NonNull
    @Override
    protected ArrayList getItems() {
        return null;
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class NewHolder extends RecyclerView.ViewHolder {
        public NewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
