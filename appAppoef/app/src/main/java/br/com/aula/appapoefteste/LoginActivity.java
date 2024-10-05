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
import androidx.lifecycle.viewmodel.CreationExtras;

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
import org.w3c.dom.Text;


import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    private TextView campoUsuario, campoSenha, mensagem;
    private RequestQueue requestQueue;
    private String url = "https://z8vpqp-3000.csb.app/criarLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // requisição acesso ao servidor
        requestQueue = Volley.newRequestQueue(this);

        // Instanciar dados
        campoUsuario = findViewById(R.id.textInputEditTextUsuario);
        campoSenha = findViewById(R.id.textInputEditTextSenha);
        mensagem = findViewById(R.id.textMensagemErro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Login(View view){
        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();
        // Validar dados
        if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senha)){
            mensagem.setText("Os campos usuário ou senha não pode estar vazios.");
            return;
        }else {
            CriarLogin();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        LimparCampos();
    }
    public void NaoTenhoConta(View view) {
        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();
        // Mudar para Tela Cadastro
        Intent intencao = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intencao);
        LimparCampos();
    }
    public void CriarLogin(){
        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();

        // criando Json para enviar dados
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
    public void LimparCampos(){
        campoUsuario.setText("");
        campoSenha.setText("");
        mensagem.setText("");
    }
    public interface ILoginActivity{
        void CriarLogin();
    }
}