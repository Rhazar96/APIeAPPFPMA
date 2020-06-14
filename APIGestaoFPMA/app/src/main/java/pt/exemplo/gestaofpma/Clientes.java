package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class Clientes extends AppCompatActivity {

    SearchView searchView;
    ArrayAdapter adapter;
    ListView _lv;
    TextView tvID, tvNome, tvNumero;
    ApiConnection2 _api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);


        searchView = findViewById(R.id.searchView);
        _lv = findViewById(R.id.listPlanos);
        _api = new ApiConnection2();
        _api._activity = Clientes.this;
        _api._listaClientes = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/clientes","0");

    }

    public void CriarCliente(View v) {


        Intent cliente = new Intent(getApplicationContext(), ClienteNovo.class);

        startActivity(cliente);

    }

    public void Home(View v) {


        Intent home = new Intent( getApplicationContext(), MainActivity.class);

        startActivity(home);


    }

    public void ClickClientes(View v) {


        Intent clientes = new Intent( getApplicationContext(), Clientes.class);

        startActivity(clientes);


    }

    public void ClickQuartos(View v) {


        Intent quartos = new Intent(getApplicationContext(), Quartos.class);

        startActivity(quartos);

    }
    public void ClickReservas(View v) {


        Intent reservas = new Intent(getApplicationContext(), Reservas.class);

        startActivity(reservas);

    }
    public void ClickPrecos(View v) {


        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);

    }

    public void updateUI() {
        //lista onde irá ficar armazenado a string para mostrar na lista (listView)
        final ArrayList<String> dadosLista = new ArrayList<>();
        final ArrayList<String> buscarId = new ArrayList<>();
        try {
            _api._listaClientes = sortListById.main(_api._listaClientes, "Clientes");
        }
        catch (JSONException e){}

        //ciclo que percorre todos os alunos retornados pela API
        for (int i=0; i<_api._listaClientes.size(); i++) {
            //variável que guarda os dados do aluno (no formato key-value-pair)
            HashMap<String, String> clientes = _api._listaClientes.get(i);


            //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
            String nome_e_id = " CLIENTE:            "
                    +clientes.get("id_cliente")+" - "
                    +clientes.get("nome")+ "\n"+" EMAIL:                 "
                    +clientes.get("email")+ "\n"+" TELEMOVEL:     "
                    +clientes.get("telemovel");
            String id =clientes.get("id_cliente");

            dadosLista.add(nome_e_id);
            buscarId.add(id);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.row, dadosLista);
        _lv.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search", query);
                if(dadosLista.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(Clientes.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
            //ACABA ACABA ACABA
        });

        // _lv.setAdapter(new ArrayAdapter<String>(this, R.layout.row, dadosLista));
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                           Intent ClienteEditar = new Intent( getApplicationContext(), ClienteEditar.class);

                                           ClienteEditar.putExtra("id_cliente", buscarId.get(position));

                                           startActivity(ClienteEditar);



                                       }
                                   }
        );


    }


}