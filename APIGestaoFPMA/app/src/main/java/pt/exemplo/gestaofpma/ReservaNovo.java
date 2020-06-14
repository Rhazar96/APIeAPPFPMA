package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservaNovo extends AppCompatActivity {

    private ApiConnection2 _api, _api2, _api3;
    private EditText edIn, edOut, edObs;

    AutoCompleteTextView edCliente, edQuarto, edPlano;

    String disponivel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_novo);

        edCliente = findViewById(R.id.edCliente);
        edQuarto = findViewById(R.id.edQuarto);
        edPlano = findViewById(R.id.edPlano);
        edIn = findViewById(R.id.edIn);
        edOut = findViewById(R.id.edOut);
        edObs = findViewById(R.id.edObs);


        //executa o pedido à API Clientes
        _api = new ApiConnection2();
        _api._activity = ReservaNovo.this;
        _api._listaClientes= new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/clientes" , "0");

        //executa o pedido à API Quartos
        _api2 = new ApiConnection2();
        _api2._activity = ReservaNovo.this;
        _api2._listaQuartos= new ArrayList();
        _api2.execute("http://10.0.2.2:3000/api/v1/quartos" , "0");

        //executa o pedido à API Planos
        _api3 = new ApiConnection2();
        _api3._activity = ReservaNovo.this;
        _api3._listaPlanos= new ArrayList();
        _api3.execute("http://10.0.2.2:3000/api/v1/planos" , "0");

    }


    public void updateUI() {

        //lista onde irá ficar armazenado a string para mostrar na lista (listView)
        final ArrayList<String> buscarIdCliente = new ArrayList<>();
        final ArrayList<String> buscarIdQuarto = new ArrayList<>();
        final ArrayList<String> buscarIdPlano = new ArrayList<>();

        // Pedido Clientes
        if(_api._listaClientes == null ){
        }else {

            //ciclo que percorre todos os alunos retornados pela API
            for (int i = 0; i < _api._listaClientes.size(); i++) {
                //variável que guarda os dados do aluno (no formato key-value-pair)
                HashMap<String, String> reservas = _api._listaClientes.get(i);


                //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
                String idcliente = reservas.get("id_cliente") + " - " + reservas.get("nome");

                buscarIdCliente.add(idcliente);
                Log.d("msg", String.valueOf(buscarIdCliente));


                AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.edCliente);
                actv.setThreshold(1);//will start working from first character
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buscarIdCliente);
                actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                //actv.setTextColor(Color.RED);
            }
        }

        // Pedido Clientes
        if(_api2._listaQuartos == null ){
        }else {

            //ciclo que percorre todos os alunos retornados pela API
            for (int i = 0; i < _api2._listaQuartos.size(); i++) {
                //variável que guarda os dados do aluno (no formato key-value-pair)
                HashMap<String, String> reservas = _api2._listaQuartos.get(i);

                String conversao = reservas.get("status");
                if (conversao.equals("0")) {
                    disponivel = "[DISPONIVEL]";


                } else{
                    disponivel = "[OCUPADO]";
                }

                //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
                String idquarto = reservas.get("id_quarto") + " - " + reservas.get("tipo") + "  " + disponivel;

                buscarIdQuarto.add(idquarto);
                Log.d("msg", String.valueOf(buscarIdQuarto));


                AutoCompleteTextView actv2 = (AutoCompleteTextView) findViewById(R.id.edQuarto);
                actv2.setThreshold(1);//will start working from first character
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buscarIdQuarto);
                actv2.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
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

    }


    public void inserirReserva(View v)
    {
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
        } else if (edObs.getText().toString().length() == 0) {
            edObs.setError("É necessário preencher o telemovel do aluno.");
            edObs.requestFocus();
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


            int estado = 1;
            String quartos = "id_quarto" + idQuartoConverter + "&status=" + estado ;

            //executa o pedido à API
            _api2 = new ApiConnection2();
            _api2._activity = ReservaNovo.this;
            _api2._listaQuartos= new ArrayList();
            _api2.execute("http://10.0.2.2:3000/api/v1/quarto/" +idQuartoConverter , "2", quartos);

            //dados a enviar para a API (formato BODY)
            String dados = "id_cliente=" + idClienteConverter + "&id_quarto=" + idQuartoConverter + "&id_plano=" + idPlanoConverter + "&data_checkin=" + edIn.getText()  + "&data_checkout=" + edOut.getText()  + "&observacoes=" + edObs.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = ReservaNovo.this;
            _api._listaReservas= new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/reserva" , "1", dados);

        }
    }

    public void sucessMessage() {
        Toast.makeText(this, "Reserva inserido com sucesso.", Toast.LENGTH_LONG).show();

        Intent reservas = new Intent( getApplicationContext(), Reservas.class);

        startActivity(reservas);

        edCliente.setText("");
        edQuarto.setText("");
        edPlano.setText("");
        edIn.setText("");
        edOut.setText("");
        edObs.setText("");
    }
}
