package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ClienteEditar extends AppCompatActivity {

    private ApiConnection2 _api;
    private EditText edNome, edEmail, edTelemovel;

    String buscarID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_editar);

        edNome = findViewById(R.id.edNumero);
        edEmail = findViewById(R.id.edTipo);
        edTelemovel = findViewById(R.id.edTelemovel);

        String id = getIntent().getStringExtra("id_cliente");
        buscarID = id;





        _api = new ApiConnection2();
        _api._activity = ClienteEditar.this;
        _api._listaClientes = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/cliente/" +id,"0");



    }

    public void updateUI() {

        //ciclo que percorre todos os alunos retornados pela API
        for (int i = 0; i < _api._listaClientes.size(); i++) {
            //variável que guarda os dados do aluno (no formato key-value-pair)
            HashMap<String, String> clientes = _api._listaClientes.get(i);
            edNome.setText(clientes.get("nome"));
            edEmail.setText(clientes.get("email"));
            edTelemovel.setText(clientes.get("telemovel"));
        }
    }

    public void alterarCliente(View v) {
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
            _api._activity = ClienteEditar.this;
            _api._listaClientes = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/cliente/" + buscarID, "2", dados);
        }

        sucessMessage();
    }



    public void apagarCliente(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Apagar Cliente")
                .setMessage("Tem a certeza que pretender apagar esta ficha de cliente?" +
                        " Só será possível caso o cliente não tenha uma nenhuma reserva registada.")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //executa o pedido à API
                        _api = new ApiConnection2();
                        _api._activity = ClienteEditar.this;
                        _api._listaClientes= new ArrayList();
                        _api.execute("http://10.0.2.2:3000/api/v1/cliente/" +buscarID, "3");

                        apagarMessage();
                    }
                }).setNegativeButton("Não", null).show();



    }

    public void sucessMessage() {
        Toast.makeText(this, "Cliente alterado com sucesso.", Toast.LENGTH_LONG).show();

        Intent clientes = new Intent(getApplicationContext(), Clientes.class);

        startActivity(clientes);

        edNome.setText("");
        edEmail.setText("");
        edTelemovel.setText("");
    }

    public void apagarMessage() {


        Intent clientes = new Intent( getApplicationContext(), Clientes.class);

        startActivity(clientes);

    }
}
