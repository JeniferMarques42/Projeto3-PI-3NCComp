package br.com.aula.appapoefteste;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextView campoUsuario, campoSenha, mensagem, validQtdCarac, validCaracEspecial, validLetraMaius,
            validLetraMinusc, validNum;
    private RequestQueue requestQueue;
    private String url = "https://xm4tg7-3000.csb.app/criarLogin";
    private String emojierro;
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
        validQtdCarac = findViewById(R.id.textValidQtdCarac);
        validCaracEspecial = findViewById(R.id.textValidCaracEspecial);
        validLetraMaius = findViewById(R.id.textValidLetraMaius);
        validLetraMinusc = findViewById(R.id.textValidLetraMinusc);
        validNum = findViewById(R.id.textValidNum);
        emojierro = getString(R.string.emojierror);

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
        // Validação de senha e usuario conforme padrao
        if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senha)){
            mensagem.setText("Os campos de usuário e senha não podem estar vazios.");
            return;
        }
        if(!isValidEmail(usuario)) {
            mensagem.setText("E-mail fornecido é inválido.");
            return;
        }
        if(senha.length() < 6  ){

            validQtdCarac.setText(emojierro + " É necessário que a senha contenha 6 dígitos.");
            LimparCampos();
            return;
        }
        if(!senha.matches(".*[!@#&*$/;~^+_-].*")){
            validCaracEspecial.setText(emojierro + " É necessário que a senha contenha um caractere especial.");
            LimparCampos();
            return;
        }
        if(!senha.matches(".*[A-Z].*")){
          validLetraMaius.setText(emojierro +" É necessário que a senha contenha uma letra maiúscula.");
            LimparCampos();
            return;
        }
        if(!senha.matches(".*[a-z].*")){
            validLetraMinusc.setText(emojierro + " É necessário que a senha contenha uma letra minúscula.");
            return;
        }
        if(!senha.matches(".*[0-9].*")){
           validNum.setText(emojierro +" É necessário que a senha contenha um número.");
            return;
        }
        CriarLogin();
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
        LimparCampos();
    }
    // criaçaõ de elementos no layout para acompnhar em tempo real a verificação do usuario e senha
    private boolean isValidEmail(String usuario) {
        return usuario != null && Patterns.EMAIL_ADDRESS.matcher(usuario).matches();
    };
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
        campoSenha.setText("");
        mensagem.setText("");
    }
    public interface ILoginActivity{
        void CriarLogin();
    }
}