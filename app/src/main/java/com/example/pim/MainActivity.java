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
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    EditText inputLogin, inputSenha;
    Button botaoLogin;
    TextView criarConta;

    final String url_Login = "https://localhost:44385/api/Autenticacao/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLogin = (EditText) findViewById(R.id.inputLogin);
        inputSenha = (EditText) findViewById(R.id.inputSenha);
        botaoLogin = (Button) findViewById(R.id.botaoLogin);
        criarConta = (TextView) findViewById(R.id.criarConta);

        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Login = inputLogin.getText().toString();
                String Senha = inputSenha.getText().toString();
            }
        });

    }

    public class LoginUser extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Login = strings[0];
            String Senha = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.builder
                    .add("Nome", Login)
                    .add("Senha", Senha)
                    .build();

            Request request = new Request.Builder()
                    .url(url_Login)
                    .post(formBody)
                    .build();

            Response response = null;
            try {
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if (result.equalsIgnoreCase("login")){
                        Intent i = new Intent(MainActivity.this,
                                HomePage.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this ,
                                "User ou senha é incompativel", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}