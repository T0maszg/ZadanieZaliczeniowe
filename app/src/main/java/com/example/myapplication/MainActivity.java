package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String fullhouse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = findViewById(R.id.spinner);
        String[] stanowisko={"Kierownik","Starszy programista","Młodszy programista","Tester"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,stanowisko);
        spinner.setAdapter(adapter);
        Button generate = findViewById(R.id.generuj);


        generate.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                int length;
                try {
                     length =Integer.parseInt(((EditText)findViewById(R.id.znaki)).getText().toString());
                } catch (NumberFormatException e) {

                    Toast.makeText(MainActivity.this, "zle", Toast.LENGTH_SHORT).show();
                    return;
                }



                CheckBox litery,cyfry,znaki;
                litery=findViewById(R.id.litery);
                cyfry=findViewById(R.id.cyfry);
                znaki=findViewById(R.id.znakis);
                boolean itery=litery.isChecked();
                boolean yfry=cyfry.isChecked();
                boolean naki = znaki.isChecked();
                String haslo;
                Random generator = new Random();
                String x = Integer.toString ((generator.nextInt(96) + 32));
                Random random=new Random();

                StringBuilder sb=new StringBuilder(length);
                final String ALLOWED_CHARACTERS ="qwertyuiopasdfghjklzxcvbnm";
                final String Numbers ="1234567890";
                final String Specjal ="!@#$%^&*()_+-=";
                for(int i=0;i<length;++i)
                    sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
                haslo=sb.toString();


                if(itery){

                    sb.replace(0,0, String.valueOf(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length()))));

                }
                if (yfry){
                    sb.replace(0,0, String.valueOf(Numbers.charAt(random.nextInt(Numbers.length()))));

                    sb.delete(sb.length()-1,sb.length()-1);



                }
                if (naki){
                    sb.replace(1,1, String.valueOf(Specjal.charAt(random.nextInt(Specjal.length()))));

                }
                fullhouse=sb.toString();

                new AlertDialog.Builder(MainActivity.this)

                        .setTitle("Wygenerowane hasło")
                        .setMessage(sb.toString())
                        .show();





            }


        });
        Button zatwierdz = findViewById(R.id.zatwierdz);
        zatwierdz.setOnClickListener(new View.OnClickListener()  {



            @Override
            public void onClick(View v) {
                String imie=(((EditText)findViewById(R.id.imie)).getText().toString());
                String nazwisko=(((EditText)findViewById(R.id.nazwisko)).getText().toString());
                String stan=stanowisko[Math.toIntExact(spinner.getSelectedItemId())];
                new AlertDialog.Builder(MainActivity.this)

                        .setTitle("Dane pracownika")
                        .setMessage("Imie: "+imie +"\n"+"Nazwisko: "+nazwisko+"\n"+"Stanowisko: "+stan+"\n"+"hasło: "+fullhouse)

                        .show();





            }



        });


    }
}