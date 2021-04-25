package dagkalis.georgios.recyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import dagkalis.georgios.selectablerecyclerview.SelectableRecyclerViewAdapter;


public class Adapter extends SelectableRecyclerViewAdapter {
    ArrayList<Integer> integers = new ArrayList<>();
    Context context;

    public Adapter(ArrayList<Integer> integers, Context context) {
        this.integers = integers;
        this.context = context;
    }

    @Override
    public void onBindSelectableViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @NonNull
    @Override
    protected ArrayList getItems() {
        return integers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(context)).inflate(R.layout.reycler_integers, parent, false);
        return new CViewHolder(view);
    }

//    @NonNull
//    @Override
//    public CViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = (LayoutInflater.from(context)).inflate(R.layout.reycler_integers, parent, false);
//        return new CViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return integers.size();
//    }
//
//
    public class CViewHolder extends ViewHolder {
        public CViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
