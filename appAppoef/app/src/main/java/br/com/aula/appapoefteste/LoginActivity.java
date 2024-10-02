package br.com.aula.appapoefteste;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    TextView campoUsuario, campoSenha, mensagem;
    private RequestQueue requestQueue;
    String url = "https://z8vpqp-3000.csb.app/criarLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Entrar(View view){
        campoUsuario = findViewById(R.id.textInputEditTextUsuario);
        campoSenha = findViewById(R.id.textInputEditTextSenha);
        mensagem = findViewById(R.id.textMensagemErro);

        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();

        // VALIDAÇÃO ENTRADA ZERADA
        if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senha)){
            mensagem.setText("Os campos usuário ou senha não pode estar vazios.");
            return;
        }else {
            fazerLogin();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            campoUsuario.setText("");
            campoSenha.setText("");
            mensagem.setText("");
        }
    }
    

    public void fazerLogin(){
        //dados no servidor
        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();

        JSONObject obj = new JSONObject();
        try{
            obj.put("usuario", usuario);
            obj.put("senha", senha);

        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(this, "Erro ao criar O JSON " , Toast.LENGTH_SHORT).show();
            return;
        }
       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, obj, response -> {
           Toast.makeText(LoginActivity.this, "Login cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

       },
       error -> {
           if(error.networkResponse != null){
               Log.e("Volley", "Erro na requisição: " + new String(error.networkResponse.data));
           }
               }
       );
               // adicionar requisição a fila
               requestQueue.add(jsonObjectRequest);


    }

    public void NaoTenhConta(View view) {
        TextInputEditText campoUsuario = findViewById(R.id.textInputEditTextUsuario);
        TextInputEditText campoSenha = findViewById(R.id.textInputEditTextSenha);
        TextView mensagem = findViewById(R.id.textMensagemErro);

        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();

        Intent intencao = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intencao);

        // Você pode querer mover a limpeza dos campos e a mensagem de erro
        // para algum lugar que faça mais sentido.
        campoUsuario.setText("");
        campoSenha.setText("");
        mensagem.setText("");
    } };



