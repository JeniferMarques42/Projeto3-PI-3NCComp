package br.com.aula.appapoefteste;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProvaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prova);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Proxima(View view){
        // Instanciando objetos
        TextView campoPergunta = findViewById(R.id.textPergunta);
        TextView campoChekA = findViewById(R.id.alternativacheckA);
        TextView campoRespA = findViewById(R.id.textRespA);
        TextView campoChekB = findViewById(R.id.alternativacheckB);
        TextView campoRespB = findViewById(R.id.textRespB);
        TextView campoChekC = findViewById(R.id.alternativacheckC);
        TextView campoRespC = findViewById(R.id.textRespC);
        TextView campoChekD = findViewById(R.id.alternativacheckD);
        TextView campoRespD = findViewById(R.id.textRespD);
        TextView campoChekE = findViewById(R.id.alternativacheckE);
        TextView campoRespE = findViewById(R.id.textRespE);

        // Extratindo inorações
        String pergunta = campoPergunta.getText().toString();
        String checkA = campoChekA.getText().toString();
        String respA = campoRespA.getText().toString();
        String checkB = campoChekB.getText().toString();
        String respB = campoRespB.getText().toString();
        String checkC = campoChekC.getText().toString();
        String respC = campoRespC.getText().toString();
        String checkD = campoChekD.getText().toString();
        String respD = campoRespD.getText().toString();
        String checkE = campoChekE.getText().toString();
        String respE = campoRespE.getText().toString();
        campoChekA.setSelected(false);

    }

    public boolean Check(){
        TextView campoChekA = findViewById(R.id.alternativacheckA);

        return false;
    }
}