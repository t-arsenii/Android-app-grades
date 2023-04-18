package com.example.project1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String STATE_liczbaOcen = "";
    static final String STATE_isImie = "isImie";
    static final String STATE_isNazwisko = "isNazwisko";
    static final String STATE_isOcena = "isOcena";
    static final String STATE_isPrzyciskSriednia = "isPrzyciskSriednia";
    static final String STATE_Sriednia = "sriednia";
    private boolean isImie = false;
    private boolean isNazwisko = false;
    private boolean isOcena = false;
    private boolean isPrzyciskSriednia= false;
    EditText ImiePoleTekstowe;
    EditText NazwiskoPoleTekstowe;
    EditText LiczbaOcenPoleTekstowe;
    Button przycisk;
    Button przyciskSriednia;
    private Double sriednia = 0.0;

    private ActivityResultLauncher<Intent> mActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         przycisk = (Button)findViewById(R.id.button);
         przycisk.setVisibility(View.INVISIBLE);
         przyciskSriednia = (Button)findViewById(R.id.buttonSriednia);
         przyciskSriednia.setVisibility(View.INVISIBLE);

         ImiePoleTekstowe=findViewById(R.id.imieEditText);
         NazwiskoPoleTekstowe=findViewById(R.id.NazwiskoEditText);
         LiczbaOcenPoleTekstowe=findViewById(R.id.LiczbaOcenEditText);
         mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(
                            ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            System.out.println(isImie);
                            System.out.println(isOcena);
                            System.out.println(isNazwisko);
                            Intent resultIntent = result.getData();
                            sriednia = Double.parseDouble(resultIntent.getStringExtra(GradesActivity.SRIEDNIA_KEY));
                            Toast.makeText(MainActivity.this, sriednia.toString(),Toast.LENGTH_LONG).show();
                            isPrzyciskSriednia = true;
                            CheckSriedniaResButton();
                            }
                        }});


         przycisk.setOnClickListener(v -> startSecondActivity());

         ImiePoleTekstowe.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if(ImiePoleTekstowe.getText().toString().isEmpty()){
                     isImie = false;
                     return;
                 }
                 isImie = true;
             }

             @Override
             public void afterTextChanged(Editable s) {
                 CheckEditText();
             }
         });
         ImiePoleTekstowe.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!isImie && !hasFocus){
                            ImiePoleTekstowe.setError(getString(R.string.ErrorImie));
                        };
                    }
                }
        );

        NazwiskoPoleTekstowe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(NazwiskoPoleTekstowe.getText().toString().isEmpty()){
                    isNazwisko = false;
                    return;
                }
                isNazwisko = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckEditText();
            }
        });


        NazwiskoPoleTekstowe.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!isNazwisko && !hasFocus){
                            NazwiskoPoleTekstowe.setError(getString(R.string.ErrorImie));
                        };
                    }
                }
        );

        LiczbaOcenPoleTekstowe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(LiczbaOcenPoleTekstowe.getText().toString().isEmpty()){
                    isOcena = false;
                    return;
                }
                int LiczbaOcen = Integer.parseInt(LiczbaOcenPoleTekstowe.getText().toString());
                if(!(LiczbaOcen >= 5 && LiczbaOcen  <= 15)){
                    LiczbaOcenPoleTekstowe.setError(getString(R.string.ErrorLiczbaOcenZakres));
                    isOcena = false;
                    return;
                }
                isOcena = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckEditText();
            }
        });

        LiczbaOcenPoleTekstowe.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!isOcena && !hasFocus){
                            LiczbaOcenPoleTekstowe.setError(getString(R.string.ErrorImie));
                        };
                        }
                    }
        );
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_isImie, (boolean)isImie);
        outState.putBoolean(STATE_isNazwisko, (boolean) isNazwisko);
        outState.putBoolean(STATE_isOcena, (boolean)isOcena);
        outState.putBoolean(STATE_isPrzyciskSriednia, (boolean)isPrzyciskSriednia);
        outState.putDouble(STATE_Sriednia, sriednia);
        System.out.println(isNazwisko);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            isImie = savedInstanceState.getBoolean(STATE_isImie);
            isNazwisko = savedInstanceState.getBoolean(STATE_isNazwisko);
            isOcena = savedInstanceState.getBoolean(STATE_isOcena);
            isPrzyciskSriednia = savedInstanceState.getBoolean(STATE_isPrzyciskSriednia);
            sriednia = savedInstanceState.getDouble(STATE_Sriednia);
        }
        CheckEditText();
        CheckSriedniaResButton();
    }
    private void startSecondActivity() {
        Intent intent = new Intent(this, GradesActivity.class);
        intent.putExtra(STATE_liczbaOcen, LiczbaOcenPoleTekstowe.getText().toString());
        mActivityResultLauncher.launch(intent);
    }
    protected void CheckEditText(){
        if(isImie && isNazwisko && isOcena){
            przycisk.setVisibility(View.VISIBLE);
            return;
        }
        przycisk.setVisibility(View.INVISIBLE);
    }
    protected void CheckSriedniaResButton(){
        String przyciskSriedniaText;
        String przyciskSriedniaTextShow = null;
        if(!isPrzyciskSriednia){
            przyciskSriednia.setVisibility(View.INVISIBLE);
            return;
        }
        przyciskSriednia.setVisibility(View.VISIBLE);
        if(sriednia >= 3) {
            przyciskSriedniaText = "Super :)";
            przyciskSriedniaTextShow = "Gratulacje! Otrzymujesz zaliczenie!";
        }else {
            przyciskSriedniaText = "Tym razem mi nie poszło";
            przyciskSriedniaTextShow = "Wysyłam podanie o zaliczenie warunkowe";
        }
        przyciskSriednia.setText(przyciskSriedniaText);
        String finalPrzyciskSriedniaTextShow = przyciskSriedniaTextShow;
        przyciskSriednia.setOnClickListener(v -> Toast.makeText(MainActivity.this, finalPrzyciskSriedniaTextShow,Toast.LENGTH_LONG).show());
    }
}