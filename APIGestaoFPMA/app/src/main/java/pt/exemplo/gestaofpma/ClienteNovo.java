package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ClienteNovo extends AppCompatActivity {
    private ApiConnection2 _api;
    private EditText edNome, edEmail, edTelemovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_novo);

        edNome = findViewById(R.id.edNumero);
        edEmail = findViewById(R.id.edTipo);
        edTelemovel = findViewById(R.id.edTelemovel);
    }


    public void inserirCliente(View v)
    {
        //validação das caixas de texto
        if (edNome.getText().toString().length() == 0) {
            edNome.setError("É necessário preencher o nome do aluno.");
            edNome.requestFocus();
        } else if (edEmail.getText().toString().length() == 0) {
            edEmail.setError("É necessário preencher o email do aluno.");
            edEmail.requestFocus();
        } else if (edTelemovel.getText().toString().length() == 0) {
            edTelemovel.setError("É necessário preencher o telemovel do aluno.");
            edTelemovel.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "nome=" + edNome.getText() + "&email=" + edEmail.getText() + "&telemovel=" + edTelemovel.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = ClienteNovo.this;
            _api._listaClientes= new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/cliente" , "1", dados);
        }


    }

    public void sucessMessage() {
        Toast.makeText(this, "Cliente inserido com sucesso.", Toast.LENGTH_LONG).show();

        Intent clientes = new Intent(getApplicationContext(), Clientes.class);

        startActivity(clientes);

        edNome.setText("");
        edEmail.setText("");
        edTelemovel.setText("");
    }
}
