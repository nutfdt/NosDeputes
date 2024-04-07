package com.example.nosdeputes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchObserver, AdapterView.OnItemClickListener {
    private SearchView searchView;
    private ListView listView;
    private DeputyAdapter deputyAdapter;
    private ArrayList<Deputy> deputies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchViewMain);
        searchView.setOnQueryTextListener(this);
        listView = findViewById(R.id.listViewMain);
        deputies = new ArrayList<>();
        deputyAdapter = new DeputyAdapter(deputies, this);
        listView.setAdapter(deputyAdapter);
        listView.setOnItemClickListener(this);

        //On récupère le searchView afin de modifier ses valeurs (ici la couleur de texte)
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.BLACK);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        deputies = new ArrayList<>();
        ApiServices.searchRequest(this, query, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    @Override
    public void onReceiveDeputyInfo(Deputy deputy) {
        if(!deputies.contains(deputy)){
            deputies.add(deputy);
            deputyAdapter.setDeputies(deputies);
            deputyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent(MainActivity.this, DeputyActivity.class);
        intent.putExtra("deputy", deputies.get(position));
        startActivity(intent);
    }
}