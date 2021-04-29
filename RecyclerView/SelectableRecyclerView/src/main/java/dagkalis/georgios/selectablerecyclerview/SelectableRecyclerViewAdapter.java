package dagkalis.georgios.selectablerecyclerview;

import android.graphics.Color;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class SelectableRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter  {

    ArrayList<Boolean> itemsStatus = new ArrayList<>();

    public void addItem(Object item){
        getItems().add(item);
        itemsStatus.add(false);
    }
    public void removeItem(int index){
        getItems().remove(index);
        itemsStatus.remove(index);
    }
    public void setSelected(int index){
        itemsStatus.set(index, true);
    }
    public void setUnselected(int index){
        itemsStatus.set(index, false);
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    public abstract void onBindSelectableViewHolder(@NonNull VH holder, int position);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(itemsStatus.get(position)){
            holder.itemView.setBackgroundColor(Color.rgb(100, 0, 0));
        }else{
            holder.itemView.setBackgroundColor(Color.rgb(0, 100, 0));
        }
        onBindSelectableViewHolder((VH) holder, position);
    }

    @NonNull
    protected abstract ArrayList getItems();

}


//public abstract class SelectableRecyclerViewAdapter {
//

//
//
// {

//    }
//

//
//}
