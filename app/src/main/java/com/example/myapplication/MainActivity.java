package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

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
        EditText imie = findViewById(R.id.name);
        EditText email = findViewById(R.id.mail);
        EditText password=findViewById(R.id.password);
        EditText passwordagain = findViewById(R.id.passwordagain);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (imie.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("") || passwordagain.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Wypełniej każde pole", Toast.LENGTH_SHORT).show();
                }
                String email = ((EditText)findViewById(R.id.mail)).getText().toString();
                String expression = "^[a-zA-Z0-9+_.-]+@[a-z]+\\.+[a-z]+";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                String password=((EditText)findViewById(R.id.password)).getText().toString();
                String passwordagain=((EditText)findViewById(R.id.passwordagain)).getText().toString();

                Matcher matcher = pattern.matcher(email);
                if(matcher.matches()){
                    Toast.makeText(MainActivity.this, "Dobrze wpisany email", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Zle wpisany email", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(passwordagain)&& password.length()>=6){
                    Toast.makeText(MainActivity.this, "Dobre hasło", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "Złe hasło", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}