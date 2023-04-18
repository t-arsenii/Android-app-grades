package com.example.project1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InteraktywnyAdapterTablicy extends
        RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder>
{
    public ArrayList<ModelOceny> getmListaOcen() {
        return mListaOcen;
    }

    private ArrayList<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    public InteraktywnyAdapterTablicy(Activity kontekst, ArrayList<ModelOceny> listaOcen)
    {
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }
    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View wiersz = mPompka.inflate(R.layout.single_grade,viewGroup,false);
        return new OcenyViewHolder(wiersz);
    }
    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza)
    {
        int ocena = mListaOcen.get(ocenyViewHolder.getAdapterPosition()).getOcena();
        switch (ocena) {
            case 2:
                ocenyViewHolder.radioGroup.check(R.id.radioButton2);
                break;
            case 3:
                ocenyViewHolder.radioGroup.check(R.id.radioButton3);
                System.out.println("Ocena 3");
                break;
            case 4:
                ocenyViewHolder.radioGroup.check(R.id.radioButton4);
                System.out.println("Ocena 4");
                break;
            case 5:
                ocenyViewHolder.radioGroup.check(R.id.radioButton5);
                System.out.println("Ocena 5");
                break;
        }
        ocenyViewHolder.textViewPrzedmiot.setText(mListaOcen.get(numerWiersza).getNazwa());
        mListaOcen.get(numerWiersza).setOcena(ocenyViewHolder.getOcena());
    }
    @Override
    public int getItemCount()
    {
        return mListaOcen.size();
    }
    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener
    {
        TextView textViewPrzedmiot;
        RadioGroup radioGroup;

        int ocena;
        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPrzedmiot = itemView.findViewById(R.id.textViewPrzedmiot);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
            ocena = Integer.parseInt(checkedRadioButton.getText().toString());
            mListaOcen.get(this.getAdapterPosition()).setOcena(ocena);
        }
        public int getOcena() {
            return ocena;
        }

    }
}