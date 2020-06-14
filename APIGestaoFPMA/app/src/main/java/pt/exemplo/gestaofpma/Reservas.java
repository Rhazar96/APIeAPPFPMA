package pt.exemplo.gestaofpma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class Reservas extends AppCompatActivity {

    SearchView searchView;
    ArrayAdapter adapter;
    ListView _lv;
    ApiConnection2 _api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        searchView = findViewById(R.id.searchView);
        _lv = findViewById(R.id.listReservas);
        _api = new ApiConnection2();
        _api._activity = Reservas.this;
        _api._listaReservas = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/reservas","0");

    }

    public void CriarReserva(View v) {


        Intent reserva = new Intent(getApplicationContext(), ReservaNovo.class);

        startActivity(reserva);

    }

    public void Home(View v) {


        Intent home = new Intent( getApplicationContext(), MainActivity.class);

        startActivity(home);


    }

    //novo novo novinho novinho
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
            _api._listaReservas = sortListById.main(_api._listaReservas, "Reservas");
        }
        catch (JSONException e){}


        //ciclo que percorre todos os alunos retornados pela API
        for (int i=0; i<_api._listaReservas.size(); i++) {
            //variável que guarda os dados do aluno (no formato key-value-pair)
            HashMap<String, String> reservas = _api._listaReservas.get(i);


            //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
            String nome_e_id =                        " Nº RESERVA:     "
                    +reservas.get("id_reserva")+ "\n"+" Nº CLIENTE:      "
                    +reservas.get("id_cliente")+ " - "
                    +reservas.get("nome")+ "\n"+" QUARTO:             "
                    +reservas.get("id_quarto")+ " - "
                    +reservas.get("tipo_quarto")+ "\n"+" PLANO:               "
                    +reservas.get("tipo_plano")+ "\n"+" PREÇO:               "
                    +reservas.get("preco_plano")+ "€\n"+" CHECK IN:          "
                    +reservas.get("data_checkin")+ "\n"+" CHECK OUT:      "
                    +reservas.get("data_checkout")+ "\n"+" OBS.:                   "
                    +reservas.get("observacoes");
            String id =reservas.get("id_reserva");

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
                    Toast.makeText(Reservas.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                       @Override
                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                           Intent ReservaEditar = new Intent( getApplicationContext(), ReservaEditar.class);

                                           ReservaEditar.putExtra("id_reserva", buscarId.get(position));

                                           startActivity(ReservaEditar);



                                       }
                                   }
        );


    }


}