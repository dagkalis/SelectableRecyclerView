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
            holder.textView.setText("thunder");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.un_selected_item_background_drawable));
            return;
        }

        holder.textView.setText(strings.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected(position))
                    unSelectItem(position);
                else
                    selectItem(position);
//                toaster(getSelectedItemCount() + "");
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onNoItemSelected() {

//        ((MainActivity)context).setRecyclerViewIconsVisibility(false);
    }

    @Override
    public void onOneItemSelected() {
//        ((MainActivity)context).setRecyclerViewIconsVisibility(true);
    }

    @Override
    public void onAllItemsSelected() {
        toaster("All items selected");
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 6;
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

    public void toaster(String mes){
        Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
    }

}
