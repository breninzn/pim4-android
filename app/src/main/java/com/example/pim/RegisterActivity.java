package com.example.pim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText inputLogin2, inputSenha2, inputEmail;
    Button botaoRegistro;

    final String url_Registro = "https://localhost:44385/api/Autenticacao/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputLogin2 = (EditText) findViewById(R.id.inputLogin2);
        inputSenha2 = (EditText) findViewById(R.id.inputSenha2);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        botaoRegistro = (Button) findViewById(R.id.botaoRegistro);

        botaoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nome = inputLogin2.getText().toString();
                String Senha = inputSenha2.getText().toString();
                String Email = inputEmail.getText().toString();
                new RegisterUser().execute(Nome ,Senha, Email);
            }
        });
    }

    public class RegisterUser extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Nome = strings[1];
            String Senha = strings[2];
            String finalURL = url_Registro + "Email=" + Email + "Nome=" + Nome + "Senha=" + Senha;

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalURL)
                    .get()
                    .build();
            Response response = null;

            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()){
                    String result = response.body().string();
                    showToast(result);
                    if (result.equalsIgnoreCase("Usuário registrado com sucesso")){
                        showToast("Usuário registrado com sucesso");
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }else if (result.equalsIgnoreCase("Usuário já existe")){
                        showToast("Usuário Já existe");
                    }else{
                        showToast("Opps! tenta denovo!");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }
    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, Text,Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            }
        });
    }
}