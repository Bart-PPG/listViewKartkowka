package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> liczbyWylosowaneArrayList;
    ArrayAdapter<Integer> arrayAdapter;
    ListView listViewLiczby;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            listViewLiczby = findViewById(R.id.listView);
            liczbyWylosowaneArrayList = new ArrayList<>();

            Random random= new Random();
            for (int i = 0; i < 30; i++) {
                liczbyWylosowaneArrayList.add(random.nextInt(101));
            }
            arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,liczbyWylosowaneArrayList);
            listViewLiczby.setAdapter(arrayAdapter);

            listViewLiczby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    liczbyWylosowaneArrayList.remove(i);
                    arrayAdapter.notifyDataSetChanged();
                }
            });
            editText = findViewById(R.id.editTextNumber);
            button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int liczba = Integer.parseInt(editText.getText().toString());
                    liczbyWylosowaneArrayList.add(liczba);
                    arrayAdapter.notifyDataSetChanged();
                }
            });


            return insets;
        });
    }
}