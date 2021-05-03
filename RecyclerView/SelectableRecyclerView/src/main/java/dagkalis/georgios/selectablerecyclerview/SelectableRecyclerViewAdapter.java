package dagkalis.georgios.selectablerecyclerview;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class SelectableRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final HashSet<Object> selectedItems = new HashSet<Object>();
    Drawable selectedItemDrawable;
    Drawable unSelectedItemDrawable;
    boolean useDrawables = false;

    public void selectItem(Object item) {
        boolean result = selectedItems.add(item);
        if (selectedItems.size() == 1 && result) { // if the size just went from 0 to 1 then fire the event in case the developer wants to know
            onAtLeastOneItemSelected();
        }
    } //check

    public void unSelectItem(Object item) {
        boolean result = selectedItems.remove(item);
        if (selectedItems.isEmpty() && result) { // if there are no selected items left fire the event in case the developer wants to know
            onNoItemSelected();
        }
    } // check

    public void selectItem(int position) {
        selectItem(getItems().get(position));
    } // check

    public void unSelectItem(int position) {
        unSelectItem(getItems().get(position));
    } // check

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
        if(onNoItemSelectedFlag)
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
     * Warning. When removing a SELECTED item outside of this libraries methods
     * then the developer should unSelect the item. Otherwise onNoItemSelected will not work
     */

    public void onNoItemSelected() {} //check

    /**
     * Called when selectedItems.size goes from 0 to 1
     */
    public void onAtLeastOneItemSelected() {} //check


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

}
