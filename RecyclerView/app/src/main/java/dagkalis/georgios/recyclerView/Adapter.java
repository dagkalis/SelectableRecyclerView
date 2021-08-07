package dagkalis.georgios.recyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import dagkalis.georgios.selectablerecyclerview.SelectableRecyclerViewAdapter;


public class Adapter extends SelectableRecyclerViewAdapter<Adapter.CViewHolder>{
    ArrayList<String> strings = new ArrayList<>();
    Context context;

    public Adapter(ArrayList<String> strings, Context context) {
        this.strings = strings;
        this.context = context;



        setItemsDrawable(ContextCompat.getDrawable(context, R.drawable.selected_item_background_drawable), ContextCompat.getDrawable(context, R.drawable.un_selected_item_background_drawable));
//        setUnSelectedItemDrawable();
//        setSelectedItemBackgroundColor(Color.RED);
//        setUnSelectedItemBackgroundColor(Color.RED);
    }

    @Override
    public void onBindSelectableViewHolder(@NonNull CViewHolder holder, int position) {
        if(position >= getItems().size()){
            holder.textView.setText("dummy");
            holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.un_selected_item_background_drawable));
            return;
        }

        holder.textView.setText(strings.get(position).toString());
        holder.itemView.setOnClickListener(v -> {
            reverseSelectionStatus(position);
            notifyDataSetChanged();
        });

        holder.itemView.setOnLongClickListener(v -> {
            removeItem(position);
            notifyDataSetChanged();
            return false;
        });
    }

    @NonNull
    @Override
    protected ArrayList getItems() {
        return strings;
    }

//    @Override
//    public void onNoItemSelected() {
//        super.onNoItemSelected();
//    }
//
//    @Override
//    public void onAtLeastOneItemSelected() {
//        super.onAtLeastOneItemSelected();
//        ((MainActivity)context).setRecyclerViewIconsVisibility(true);
//
//        toaster("At least one item selected in adapter");
//    }
//

    @NonNull
    @Override
    public CViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    public void toaster(String mes){
        Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
    }

}
