package com.example.pim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Calendar;


public class ChoosePage extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    int numeroQuarto1 = 0;
    int numeroQuarto2 = 0;
    int numeroQuarto3 = 0;
    int quartoSolteiro1 = 230;
    int quartoSolteiro2 = 290;
    int quartoSolteiro3 = 430;

    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);
        dateText = findViewById(R.id.dateText);

        findViewById(R.id.showDialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickDialog();
            }
        });
    }
    private void showDatePickDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = "day/month/year: " + dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);
    }




    public void incremento1(View view){
        numeroQuarto1++;
        display1(numeroQuarto1);
    }
    public void incremento2(View view){
        numeroQuarto2++;
        display2(numeroQuarto2);
    }
    public void incremento3(View view){
        numeroQuarto3++;
        display3(numeroQuarto3);
    }

    public void decremento1(View view){
        numeroQuarto1--;
        if (numeroQuarto1 < 0)
            numeroQuarto1 = 0;
        display1(numeroQuarto1);
    }

    public void decremento2(View view){
        numeroQuarto2--;
        if (numeroQuarto2 < 0)
            numeroQuarto2 = 0;
        display2(numeroQuarto2);
    }

    public void decremento3(View view){
        numeroQuarto3--;
        if (numeroQuarto3 < 0)
            numeroQuarto3 = 0;
        display3(numeroQuarto3);
    }

    private void display1(int numero1){
        TextView quantidadeTextView1 = (TextView) findViewById(R.id.quantidade_text_view1);
        quantidadeTextView1.setText("" + numero1);
    }

    private void display2(int numero2){
        TextView quantidadeTextView2 = (TextView) findViewById(R.id.quantidade_text_view2);
        quantidadeTextView2.setText("" + numero2);
    }

    private void display3(int numero3){
        TextView quantidadeTextView3 = (TextView) findViewById(R.id.quantidade_text_view3);
        quantidadeTextView3.setText("" + numero3);
    }


}