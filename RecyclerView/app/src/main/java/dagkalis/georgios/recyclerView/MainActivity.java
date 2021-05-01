package dagkalis.georgios.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    Adapter adapter;
    ImageButton binBtn;
    ImageButton binBtn3;
    ImageButton binBtn2;
    ImageButton cancelBinBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<String> t = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            t.add("real" + i);
        }
        adapter = new Adapter(t, context);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
//        adapter.setSelected(1);
        adapter.notifyDataSetChanged();

        binBtn = findViewById(R.id.imageButtonBin);
        binBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeSelectedItems();
                adapter.notifyDataSetChanged();
            }
        });
        cancelBinBtn = findViewById(R.id.imageButtonCancel);
        cancelBinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.selectAllItems();
                adapter.notifyDataSetChanged();
            }
        });
        binBtn3 = findViewById(R.id.blue) ;
        binBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.unSelectAllItems();
                adapter.notifyDataSetChanged();
                toaster("unselecting all items");
            }
        });
        binBtn2 = findViewById(R.id.imageButtonBin_the_third) ;
        binBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeUnselectedItems();
                adapter.notifyDataSetChanged();
                toaster("removing all unselected");
            }
        });

        ImageButton binBtnOrange = findViewById(R.id.orange);
        binBtnOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getItems().addAll(adapter.getSelectedItems());
                toaster("adding selected items");
                adapter.notifyDataSetChanged();
            }
        });
        ImageButton binBtnpurple = findViewById(R.id.purple);
        binBtnpurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getItems().addAll(adapter.getUnselectedItems());
                toaster("adding un-selected items");
                adapter.notifyDataSetChanged();

            }
        });

        Button checkIfAll = findViewById(R.id.checkIfAll);
        checkIfAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(adapter.checkIfAllItemsSelected()){
//                    toaster("all is checked");
//                }else{
//                    toaster("not all selected");
//                }
                ArrayList<String> strings = adapter.getSelectedItems();
                System.out.println(strings);
                toaster(strings.toString());
                ArrayList<String> unselectedStrings = adapter.getUnselectedItems();
                System.out.println(unselectedStrings);
                toaster(unselectedStrings.toString());
            }
        });





//        setRecyclerViewIconsVisibility(false);
    }

//    public void setRecyclerViewIconsVisibility(boolean visible){
//        binBtn.setVisibility(visible ? View.VISIBLE : View.GONE);
//        cancelBinBtn.setVisibility(visible ? View.VISIBLE : View.GONE);
//        binBtn2.setVisibility(visible ? View.VISIBLE : View.GONE);
//        binBtn3.setVisibility(visible ? View.VISIBLE : View.GONE);
//
//    }
    public void toaster(String mes){
        Toast.makeText(context, mes, Toast.LENGTH_LONG).show();
    }

}