package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservaEditar extends AppCompatActivity {

    private ApiConnection2 _api, _api2, _api3;
    private EditText  edIn, edOut, edObs;
    AutoCompleteTextView  edCliente, edQuarto, edPlano;

    String buscarID;
    String idQuartoConverter;
    String idQuartoReserva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_editar);

        edCliente = findViewById(R.id.edCliente);
        edQuarto = findViewById(R.id.edQuarto);
        edPlano = findViewById(R.id.edPlano);
        edIn = findViewById(R.id.edIn);
        edOut = findViewById(R.id.edOut);
        edObs = findViewById(R.id.edObs);

        String id = getIntent().getStringExtra("id_reserva");
        buscarID = id;

        Log.d("msg", buscarID);




        //executa o pedido à API Clientes
        _api = new ApiConnection2();
        _api._activity = ReservaEditar.this;
        _api._listaReservas= new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/reserva/" +buscarID , "0");

        //executa o pedido à API Clientes
        _api2 = new ApiConnection2();
        _api2._activity = ReservaEditar.this;
        _api2._listaQuartos= new ArrayList();
        _api2.execute("http://10.0.2.2:3000/api/v1/quartos" , "0");

        //executa o pedido à API Clientes
        _api3 = new ApiConnection2();
        _api3._activity = ReservaEditar.this;
        _api3._listaPlanos= new ArrayList();
        _api3.execute("http://10.0.2.2:3000/api/v1/planos" , "0");



    }

    public void updateUI() {

        final ArrayList<String> buscarId = new ArrayList<>();
        final ArrayList<String> buscarIdPlano = new ArrayList<>();

        // Pedido Clientes
        if(_api2._listaQuartos == null ){
        }else {

            //ciclo que percorre todos os alunos retornados pela API
            for (int i = 0; i < _api2._listaQuartos.size(); i++) {
                //variável que guarda os dados do aluno (no formato key-value-pair)
                HashMap<String, String> reservas = _api2._listaQuartos.get(i);


                //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
                String idcliente = reservas.get("id_quarto") + " - " + reservas.get("tipo");

                buscarId.add(idcliente);
                Log.d("msg", String.valueOf(buscarId));


                AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.edQuarto);
                actv.setThreshold(1);//will start working from first character
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buscarId);
                actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                //actv.setTextColor(Color.RED);
            }
        }

        // Pedido Clientes
        if(_api3._listaPlanos == null ){
        }else {

            //ciclo que percorre todos os alunos retornados pela API
            for (int i = 0; i < _api3._listaPlanos.size(); i++) {
                //variável que guarda os dados do aluno (no formato key-value-pair)
                HashMap<String, String> reservas = _api3._listaPlanos.get(i);


                //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
                String idplano = reservas.get("id_plano") + " - " + reservas.get("tipo_plano");

                buscarIdPlano.add(idplano);
                Log.d("msg", String.valueOf(buscarIdPlano));


                AutoCompleteTextView actv2 = (AutoCompleteTextView) findViewById(R.id.edPlano);
                actv2.setThreshold(1);//will start working from first character
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buscarIdPlano);
                actv2.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                //actv.setTextColor(Color.RED);
            }
        }



        //ciclo que percorre todos os alunos retornados pela API
        for (int i = 0; i < _api._listaReservas.size(); i++) {
            //variável que guarda os dados do aluno (no formato key-value-pair)
            HashMap<String, String> reservas = _api._listaReservas.get(i);
            edCliente.setText(reservas.get("id_cliente") +" - " +reservas.get("nome"));
            edQuarto.setText(reservas.get("id_quarto") +" - " +reservas.get("tipo_quarto"));
            edPlano.setText(reservas.get("id_plano") +" - " +reservas.get("tipo_plano"));
            edIn.setText(reservas.get("data_checkin"));
            edOut.setText(reservas.get("data_checkout"));
            edObs.setText(reservas.get("observacoes"));

            idQuartoReserva = reservas.get("id_quarto");

        }
    }

    public void alterarReserva(View v) {
        //validação das caixas de texto
        if (edCliente.getText().toString().length() == 0) {
            edCliente.setError("É necessário preencher o nome do aluno.");
            edCliente.requestFocus();
        } else if (edQuarto.getText().toString().length() == 0) {
            edQuarto.setError("É necessário preencher o email do aluno.");
            edQuarto.requestFocus();
        } else if (edPlano.getText().toString().length() == 0) {
            edPlano.setError("É necessário preencher o telemovel do aluno.");
            edPlano.requestFocus();
        } else if (edIn.getText().toString().length() == 0) {
            edIn.setError("É necessário preencher o telemovel do aluno.");
            edIn.requestFocus();
        } else if (edOut.getText().toString().length() == 0) {
            edOut.setError("É necessário preencher o telemovel do aluno.");
            edOut.requestFocus();
        } else {
            //Conversão Cliente
            String idCliente = "id_cliente" + edCliente.getText();
            String idClienteConverter = idCliente.replaceAll("[^\\d.]", "");
            //Conversão Quarto
            String idQuarto = "id_quarto" + edQuarto.getText();
            String idQuartoConverter = idQuarto.replaceAll("[^\\d.]", "");
            //Conversão Plano
            String idPlano = "id_plano" + edPlano.getText();
            String idPlanoConverter = idPlano.replaceAll("[^\\d.]", "");


            //dados a enviar para a API (formato BODY)
            String dados = "id_cliente=" + idClienteConverter + "&id_quarto=" + idQuartoConverter + "&id_plano=" + idPlanoConverter + "&data_checkin=" + edIn.getText()  + "&data_checkout=" + edOut.getText()  + "&observacoes=" + edObs.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = ReservaEditar.this;
            _api._listaReservas = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/reserva/" + buscarID, "2", dados);
        }

        sucessMessage();
    }



    public void apagarReserva(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Remover reserva")
                .setMessage("Tem a certeza que pretende remover a reserva?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int estado = 0;
                        String quartos = "id_quarto=" + idQuartoReserva + "&status=" + estado ;

                        //executa o pedido à API
                        _api2 = new ApiConnection2();
                        _api2._activity = ReservaEditar.this;
                        _api2._listaQuartos= new ArrayList();
                        _api2.execute("http://10.0.2.2:3000/api/v1/quarto/" +idQuartoReserva , "2", quartos);

                        //executa o pedido à API
                        _api = new ApiConnection2();
                        _api._activity = ReservaEditar.this;
                        _api._listaReservas= new ArrayList();
                        _api.execute("http://10.0.2.2:3000/api/v1/reserva/" +buscarID, "3");

                        apagarMessage();


                    }
                }).setNegativeButton("Não", null).show();

    }

    public void sucessMessage() {
        Toast.makeText(this, "Reserva alterada com sucesso.", Toast.LENGTH_LONG).show();

        Intent reservas = new Intent( getApplicationContext(), Reservas.class);

        startActivity(reservas);

        edCliente.setText("");
        edQuarto.setText("");
        edPlano.setText("");
    }

    public void apagarMessage() {
        Toast.makeText(this, "Reserva apagada com sucesso.", Toast.LENGTH_LONG).show();

        Intent reservas = new Intent( getApplicationContext(), Reservas.class);

        startActivity(reservas);

        edCliente.setText("");
        edQuarto.setText("");
        edPlano.setText("");
    }
}
