package dagkalis.georgios.recyclerView;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

        setSelectedItemDrawable(ContextCompat.getDrawable(context, R.drawable.selected_item_background_drawable));
        setUnSelectedItemDrawable(ContextCompat.getDrawable(context, R.drawable.un_selected_item_background_drawable));
//        setSelectedItemBackgroundColor(Color.RED);
//        setUnSelectedItemBackgroundColor(Color.RED);
    }



    @Override
    public void onBindSelectableViewHolder(@NonNull CViewHolder holder, int position) {
        holder.textView.setText(strings.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected(position))
                    unSelectItem(position);
                else
                    selectItem(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onNoItemSelected() {

        ((MainActivity)context).setRecyclerViewIconsVisibility(false);
    }

    @Override
    public void onOneItemSelected() {
        ((MainActivity)context).setRecyclerViewIconsVisibility(true);
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
