package dagkalis.georgios.selectablerecyclerview;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class SelectableRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final HashSet<Object> selectedItems = new HashSet<Object>();
    private Drawable selectedItemDrawable;
    private Drawable unSelectedItemDrawable;
    private boolean useDrawables = false;
    private OnNoItemSelectedListener onNoItemSelectedListener;
    private OnAtLeastOneItemSelectedListener onAtLeastOneItemSelectedListener;

    public void selectItem(Object item) {
        boolean result = selectedItems.add(item);
        if (selectedItems.size() == 1 && result) { // if the size just went from 0 to 1 then fire the event
            onAtLeastOneItemSelected();
        }
    } //check

    public void unSelectItem(Object item) {
        boolean result = selectedItems.remove(item);
        if (selectedItems.isEmpty() && result) { // if there are no selected items left fire the event
            onNoItemSelected();
        }
    } // check

    /**
     * if the item is selected then unselect it
     * if the item is unselected then select it
     * @param item
     */
    public void reverseSelectionStatus(Object item){
        if(isSelected(item)){
            unSelectItem(item);
        }else{
            selectItem(item);
        }
    }

    public void selectItem(int position) {
        selectItem(getItems().get(position));
    } // check

    public void unSelectItem(int position) {
        unSelectItem(getItems().get(position));
    } // check

    /**
     * if the item in that position is selected then unselect it
     * if the item in that position is unselected then select it
     * @param position
     */
    public void reverseSelectionStatus(int position){
        reverseSelectionStatus(getItems().get(position));
    }

    public ArrayList getSelectedItems() {
        ArrayList<Object> selectedItemsArrayList = new ArrayList<Object>(selectedItems.size());
        for(Object object : getItems()){
            if(isSelected(object)){
                selectedItemsArrayList.add(object);
            }
        }
        return selectedItemsArrayList;
    } //check

    public ArrayList getUnselectedItems(){
        ArrayList<Object> items = new ArrayList<Object>(getItems());
        items.removeAll(selectedItems);
        return items;
    } //check

    public void unSelectAllItems() {
        boolean onNoItemSelectedFlag = selectedItems.size() > 0;
        selectedItems.clear();
        if(onNoItemSelectedFlag) // if selected items where more than 0 (and now they are 0 of course)
            onNoItemSelected();
    } //check

    public void selectAllItems() {
        int oldSelectedItemsCount = getSelectedItemCount();
        selectedItems.addAll(getItems());
        if(oldSelectedItemsCount == 0 && getSelectedItemCount() > 0){
            onAtLeastOneItemSelected();
        }
    } //check

    public int getSelectedItemCount() {
        return selectedItems.size();
    } //check


    /**
     * checks if all items of arraylist are selected
     * @return bool with the result
     */
    public boolean checkIfAllItemsSelected() {
        if(selectedItems.size() == getItems().size()){
            return true;
        }

        return selectedItems.containsAll(getItems());

    }

    public boolean isSelected(Object item) {
        return selectedItems.contains(item);
    } //check

    public boolean isSelected(int position) {
        return isSelected(getItems().get(position));
    } //check

    @Override
    public int getItemCount() {
        return getItems().size();
    } //check


    /**
     * To remove an item from the ArrayList and also unselect it.
     * If onNoItemSelected is used then the selectedItems should be updated when removing an item from the arrayList.
     * This function provides exactly that
     * @param position in the arrayList
     */
    public void removeItem(int position){
        Object item = getItems().remove(position);
        unSelectItem(item);
    }

    /**
     * To remove an item from the ArrayList and also unselect it.
     * If onNoItemSelected is used then the selectedItems should be updated when removing an item from the arrayList.
     * This function provides exactly that
     * @param item to be removed from the arrayList
     */
    public void removeItem(Object item){
        getItems().remove(item);
        unSelectItem(item);
    }

    public void removeSelectedItems(){
        getItems().removeAll(selectedItems);
        unSelectAllItems();

    }

    public void removeUnselectedItems(){
        getItems().clear();
        getItems().addAll(selectedItems);
    } //check

    public abstract void onBindSelectableViewHolder(@NonNull VH holder, int position); //check

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if(useDrawables && position < getItems().size()) { // this is only used for custom drawables, check if user has picked drawables and whether the arraylist has a value at this position (only way it is not is if developer has overrided getItemCount
//            try {
            boolean isSelected = isSelected(position);
            if (isSelected) {
                holder.itemView.setBackground(selectedItemDrawable);
            }
            if (!isSelected) {
                holder.itemView.setBackground(unSelectedItemDrawable);
            }
//            }catch (IndexOutOfBoundsException indexOutOfBoundsException){
//                System.out.println("position " + position);
//                System.out.println("itemCount " + getItemCount());
//            }

        }

        onBindSelectableViewHolder((VH) holder, position);
    } //check

    @NonNull
    protected abstract ArrayList getItems(); //check

    public void setItemsDrawable(Drawable selectedItemDrawable, Drawable unSelectedItemDrawable) {
        this.selectedItemDrawable = selectedItemDrawable;
        this.unSelectedItemDrawable = unSelectedItemDrawable;
        useDrawables = true;
    } //check

    public void setSelectedItemBackgroundColor(int selectedColorInt, int unSelectedColorInt){
        selectedItemDrawable = new ColorDrawable(selectedColorInt);
        unSelectedItemDrawable = new ColorDrawable(unSelectedColorInt);
        useDrawables = true;
    }

    public interface OnNoItemSelectedListener {

        void onNoItemSelected();
    }

    /**
     * Warning. When removing a SELECTED item outside of this libraries methods
     * then the developer should unSelect the item. Otherwise onNoItemSelected will not work
     */

    public void onNoItemSelected() {
        if(onNoItemSelectedListener != null)
            onNoItemSelectedListener.onNoItemSelected();
    }

    public void setOnNoItemSelectedListener(OnNoItemSelectedListener l){
        this.onNoItemSelectedListener = l;
    }

    public interface OnAtLeastOneItemSelectedListener{
        void OnAtLeastOneItemSelected();
    }

    public void setOnAtLeastOneItemSelectedListener(OnAtLeastOneItemSelectedListener l){
        this.onAtLeastOneItemSelectedListener = l;
    }


    /**
     * Called when selectedItems.size goes from 0 to 1
     */
    public void onAtLeastOneItemSelected() {
        if(onAtLeastOneItemSelectedListener != null){
            onAtLeastOneItemSelectedListener.OnAtLeastOneItemSelected();
        }
    } //check






}



