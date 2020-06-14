package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class QuartoEditar extends AppCompatActivity {

    private ApiConnection2 _api;
    private EditText edNumero;
    private Spinner edTipo, edStatus;

    String buscarID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_editar);

        edNumero = findViewById(R.id.edNumero);
        edTipo = findViewById(R.id.edTipo);
        edStatus = findViewById(R.id.edStatus);

        String id = getIntent().getStringExtra("id_quarto");
        buscarID = id;

        // Tipos de quarto
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.TiposQuarto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edTipo.setAdapter(adapter);

        // Disponibilidade do quarto
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edStatus.setAdapter(adapter2);


        _api = new ApiConnection2();
        _api._activity = QuartoEditar.this;
        _api._listaQuartos = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/quarto/" +id,"0");


    }

    public void updateUI() {

        //ciclo que percorre todos os alunos retornados pela API
        for (int i = 0; i < _api._listaQuartos.size(); i++) {
            //variável que guarda os dados do aluno (no formato key-value-pair)
            HashMap<String, String> quartos = _api._listaQuartos.get(i);
            edNumero.setText(quartos.get("id_quarto"));
        }
    }





    public void alterarQuarto(View v) {
        //validação das caixas de texto
        if (edNumero.getText().toString().length() == 0) {
            edNumero.setError("É necessário preencher o id_quarto do aluno.");
            edNumero.requestFocus();
        } else {
            String disponibilidade = "status" + edStatus.getSelectedItem();
            String disponibilidadeConverter = disponibilidade.replaceAll("[^\\d.]", "");

            //dados a enviar para a API (formato BODY)
            String dados = "id_quarto=" + edNumero.getText() + "&tipo=" + edTipo.getSelectedItem() +  "&status=" + disponibilidadeConverter;

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = QuartoEditar.this;
            _api._listaQuartos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/quarto/" + buscarID, "2", dados);
        }
        sucessMessage();
    }


    public void apagarQuarto(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Apagar Quarto")
                .setMessage("Tem a certeza que pretender apagar este quarto?" +
                        " Só será possível caso o quarto não esteja associado a nenhuma reserva.")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //executa o pedido à API
                        _api = new ApiConnection2();
                        _api._activity = QuartoEditar.this;
                        _api._listaQuartos= new ArrayList();
                        _api.execute("http://10.0.2.2:3000/api/v1/quarto/" +buscarID, "3");

                        apagarMessage();
                    }
                }).setNegativeButton("Não", null).show();



    }


    public void sucessMessage() {
        Toast.makeText(this, "Quarto alterado com sucesso.", Toast.LENGTH_LONG).show();

        Intent quartos = new Intent( getApplicationContext(), Quartos.class);

        startActivity(quartos);
    }

    public void apagarMessage() {


        Intent quartos = new Intent( getApplicationContext(), Quartos.class);

        startActivity(quartos);

    }
}