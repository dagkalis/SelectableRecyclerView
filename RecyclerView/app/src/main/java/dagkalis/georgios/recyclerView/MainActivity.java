package dagkalis.georgios.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    Adapter adapter;
    ImageButton binBtn;
    ImageButton cancelBinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<String> t = new ArrayList<>();

        for(int i = 0; i < 1000; i++){
            t.add(i + i + i + "");
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
        setRecyclerViewIconsVisibility(false);
    }

    public void setRecyclerViewIconsVisibility(boolean visible){
        binBtn.setVisibility(visible ? View.VISIBLE : View.GONE);
        cancelBinBtn.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}