package dagkalis.georgios.recyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import dagkalis.georgios.selectablerecyclerview.SelectableRecyclerViewAdapter;


public class Adapter extends SelectableRecyclerViewAdapter<Adapter.CViewHolder>{
    ArrayList<String> strings = new ArrayList<>();
    Context context;

    public Adapter(ArrayList<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }



    @Override
    public void onBindSelectableViewHolder(@NonNull CViewHolder holder, int position) {
        holder.textView.setText(strings.get(position));
    }

    @NonNull
    @Override
    protected ArrayList getItems() {
        return strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(context)).inflate(R.layout.reycler_integers, parent, false);
        return new CViewHolder(view);
    }

    public class CViewHolder extends ViewHolder {
        TextView textView;

        public CViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}
