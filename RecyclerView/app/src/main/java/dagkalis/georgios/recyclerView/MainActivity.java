package dagkalis.georgios.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<String> t = new ArrayList<>();
        t.add("thunder");
        t.add("thunderstruck");
        t.add("tttttttt");
        adapter = new Adapter(t, context);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
//        adapter.setSelected(1);
        adapter.notifyDataSetChanged();


    }
}