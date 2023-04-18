package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {
    public static final String OCENY_ARRAYKEY = "";
    public static final String SRIEDNIA_KEY = "";
    ArrayList<ModelOceny> OcenyModels = new ArrayList<>();
    InteraktywnyAdapterTablicy adapter;
    int lizba_ocen = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        lizba_ocen = Integer.parseInt(getIntent().getExtras().getString(MainActivity.STATE_liczbaOcen));
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewOceny);
        System.out.println(OcenyModels.size());
        setOcenyModels();
        adapter = new InteraktywnyAdapterTablicy(this, OcenyModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button returnButton = findViewById(R.id.buttonToMain);
        returnButton.setOnClickListener(v -> returnToMain());
    }
    private void setOcenyModels(){
        String[] nazwyPrzedmiotow = getResources().getStringArray(R.array.nazwyPrzedmiotow);
        for(int i = 0; i < lizba_ocen; i++){
            OcenyModels.add(new ModelOceny(nazwyPrzedmiotow[i],2));

        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(OCENY_ARRAYKEY, OcenyModels);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        OcenyModels = savedInstanceState.getParcelableArrayList(OCENY_ARRAYKEY);
        System.out.println(OcenyModels);
    }

    private void returnToMain() {
        Double sriednia = 0.0;
        for (int i = 0; i < OcenyModels.size(); i++) {
            sriednia += OcenyModels.get(i).getOcena();
        }
        sriednia /= OcenyModels.size();

        Intent intent = new Intent();
        intent.putExtra(SRIEDNIA_KEY, String.format("%.2f", sriednia));
        setResult(RESULT_OK, intent);
        finish();
    }
}