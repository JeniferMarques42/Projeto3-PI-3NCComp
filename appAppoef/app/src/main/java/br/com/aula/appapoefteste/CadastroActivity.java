package br.com.aula.appapoefteste;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class CadastroActivity extends AppCompatActivity implements LoginActivity.ILoginActivity {

    private TextInputEditText campoUsuario, campoSenha, campoConfirmarSenha;
    private TextView mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void CriarConta(View view) {
        instDados();
        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();
        String confirmarSenha = campoConfirmarSenha.getText().toString();
        // VALIDAÇÃO ENTRADA ZERADA
        if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senha)){
            mensagem.setText("Os campos usuário ou senha não pode estar vazios.");
            return;
        }else if (senha.equals(confirmarSenha)) {
          //  CriarLogin();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            LimparCampos();
        } else {
            mensagem.setText("As senhas se diferem, tente novamente.");
        }

    }
    public void Associar(View view){
        instDados();
        // extrai dos Objetos, recuperando a String que pompões:
        String usuario = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();
        String confirmarSenha = campoConfirmarSenha.getText().toString();
        // VALIDAÇÃO ENTRADA ZERADA
        if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senha)){
            mensagem.setText("Os campos usuário ou senha não pode estar vazios.");
            return;
        }else if (senha.equals(confirmarSenha)) {
            //CriarLogin();
            Intent intent = new Intent(this, InformacoesActivity.class);
            startActivity(intent);
            LimparCampos();
       } else {
            mensagem.setText("As senhas se diferem, tente novamente.");
        }
    }
    public void instDados(){
        // Instânciamento dos elementos Views do meu Arquivo XML;
        campoUsuario = findViewById(R.id.textInputEditTextUsuario);
        campoSenha = findViewById(R.id.textInputEditTextSenha);
        campoConfirmarSenha = findViewById(R.id.textInputEditTextConfirmarSenha);
        mensagem = findViewById(R.id.textMensagemErro);
    }
    public void LimparCampos() {
        campoUsuario.setText("");
        campoSenha.setText("");
        mensagem.setText("");
        campoConfirmarSenha.setText("");
    }
    @Override
    public void CriarLogin() {}

}