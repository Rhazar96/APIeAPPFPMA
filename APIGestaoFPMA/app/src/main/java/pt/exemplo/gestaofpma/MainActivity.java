package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ListView _lv;
    ApiConnection2 _api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        _lv = findViewById(R.id.listPlanos);
        _api = new ApiConnection2();
        _api._activity = MainActivity.this;
        _api._listaReservas = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/reservas","0");

    }
    public void Logout(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Terminar sessão")
                .setMessage("Tem a certeza que pretende sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logout = new Intent( getApplicationContext(), utilizador.class);

                        startActivity(logout);
                    }
                }).setNegativeButton("Não", null).show();




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
        _lv.setAdapter(new ArrayAdapter<String>(this, R.layout.row, dadosLista));
    }
}
