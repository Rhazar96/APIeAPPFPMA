package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Precos extends AppCompatActivity {


    ListView _lv;
    ApiConnection2 _api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos);



        _lv = findViewById(R.id.listPlanos);
        _api = new ApiConnection2();
        _api._activity = Precos.this;
        _api._listaPlanos = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/planos","0");

    }

    public void EditarPreco(View v) {


        Intent plano = new Intent(getApplicationContext(), PrecosEditar.class);

        startActivity(plano);

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


        //ciclo que percorre todos os alunos retornados pela API
        for (int i=0; i<_api._listaPlanos.size(); i++) {
            //variável que guarda os dados do aluno (no formato key-value-pair)
            HashMap<String, String> planos = _api._listaPlanos.get(i);

            //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
            String nome_e_id = "Plano:   "
                    +planos.get("tipo_plano")+"\n"+"ID do plano:   " +
                    planos.get("id_plano")+"\n" +"Preço:   "+
                    planos.get("preco_plano")+"€";
            dadosLista.add(nome_e_id);
            String id =planos.get("id_plano");

            buscarId.add(id);
        }
        _lv.setAdapter(new ArrayAdapter<String>(this, R.layout.row, dadosLista));
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                           Intent PrecosEditar = new Intent( getApplicationContext(), PrecosEditar.class);

                                           PrecosEditar.putExtra("id_plano", buscarId.get(position));

                                           startActivity(PrecosEditar);

                                       }
                                   }
        );
    }
}