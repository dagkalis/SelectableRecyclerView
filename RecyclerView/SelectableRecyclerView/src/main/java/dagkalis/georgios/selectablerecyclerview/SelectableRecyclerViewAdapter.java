package dagkalis.georgios.selectablerecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class SelectableRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private HashSet selectedItems = new HashSet();
    Drawable selectedItemDrawable;
    Drawable unSelectedItemDrawable;


    public void selectItem(Object item) {
        selectedItems.add(item);
        if (selectedItems.size() == 1) { // if the size just went from 0 to 1 then fire the event in case the developer wants to know
            onOneItemSelected();
        }
    }

    public void unSelectItem(Object item) {
        selectedItems.remove(item);
        if (selectedItems.isEmpty()) { // if there are no selected items left fire the event in case the developer wants to know
            onNoItemSelected();
        }
    }

    public void selectItem(int position) {
        selectItem(getItems().get(position));
    }

    public void unSelectItem(int position) {
        unSelectItem(getItems().get(position));
    }

    public ArrayList getSelectedItems() {
        return new ArrayList(selectedItems);
    }

    public void unSelectAllItems() {
        selectedItems.clear();
    }

    public void selectAllItems() {
        selectedItems.addAll(getItems());
    }

    public int getSelectedItemsCount() {
        return selectedItems.size();
    }

    public void onNoItemSelected() {
    }

    public void onOneItemSelected() {
    }

    public boolean isSelected(Object item) {
        return selectedItems.contains(item);
    }

    public boolean isSelected(int position) {
        return isSelected(getItems().get(position));
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    public abstract void onBindSelectableViewHolder(@NonNull VH holder, int position);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        boolean isSelected = isSelected(position);
        if (isSelected) {
            holder.itemView.setBackground(selectedItemDrawable);
        }
        if (!isSelected) {
            holder.itemView.setBackground(unSelectedItemDrawable);
        }

        onBindSelectableViewHolder((VH) holder, position);
    }

    @NonNull
    protected abstract ArrayList getItems();


    public void setSelectedItemDrawable(Drawable selectedItemDrawable) {
        this.selectedItemDrawable = selectedItemDrawable;
    }

    public void setUnSelectedItemDrawable(Drawable unSelectedItemDrawable) {
        this.unSelectedItemDrawable = unSelectedItemDrawable;
    }

    public void setSelectedItemBackgroundColor(int colorInt){
        selectedItemDrawable = new ColorDrawable(colorInt);
    }

    public void setUnSelectedItemBackgroundColor(int colorInt){
        unSelectedItemDrawable = new ColorDrawable(colorInt);
    }



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
