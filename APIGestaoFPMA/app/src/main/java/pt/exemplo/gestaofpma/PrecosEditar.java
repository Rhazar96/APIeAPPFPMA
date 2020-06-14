package pt.exemplo.gestaofpma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PrecosEditar extends AppCompatActivity {

    private ApiConnection2 _api;
    private EditText edpreco1, edpreco2, edpreco3, edpreco4, edpreco5, edpreco6;

    String buscarID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos_editar);

        edpreco1 = findViewById(R.id.edpreco1);
        edpreco2 = findViewById(R.id.edpreco2);
        edpreco3 = findViewById(R.id.edpreco3);
        edpreco4 = findViewById(R.id.edpreco4);
        edpreco5 = findViewById(R.id.edpreco5);
        edpreco6 = findViewById(R.id.edpreco6);

        String id = getIntent().getStringExtra("id_plano");
        buscarID = id;





        _api = new ApiConnection2();
        _api._activity = PrecosEditar.this;
        _api._listaPlanos = new ArrayList();
        _api.execute("http://10.0.2.2:3000/api/v1/planos","0");



    }

    public void updateUI() {

        final ArrayList<String> dadosLista = new ArrayList<>();

        if(_api._listaPlanos.size()==0){

        }else {

            for (int i = 0; i < _api._listaPlanos.size(); i++) {
                HashMap<String, String> planos = _api._listaPlanos.get(i);

                String id = planos.get("preco_plano");

                dadosLista.add(id);
            }

            edpreco1.setText(dadosLista.get(0));
            edpreco2.setText(dadosLista.get(1));
            edpreco3.setText(dadosLista.get(2));
            edpreco4.setText(dadosLista.get(3));
            edpreco5.setText(dadosLista.get(4));
            edpreco6.setText(dadosLista.get(5));
        }
    }

    public void alterarPrecos1(View v) {
        //validação das caixas de texto
        if (edpreco1.getText().toString().length() == 0) {
            edpreco1.setError("É necessário preencher o preco_plano do plano.");
            edpreco1.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "preco_plano=" + edpreco1.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = PrecosEditar.this;
            _api._listaPlanos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/plano/" + 1, "2", dados);

        }
        sucessMessage();
        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);


    }
    public void alterarPrecos2(View v) {
        //validação das caixas de texto
        if (edpreco2.getText().toString().length() == 0) {
            edpreco2.setError("É necessário preencher o preco_plano do plano.");
            edpreco2.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "preco_plano=" + edpreco2.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = PrecosEditar.this;
            _api._listaPlanos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/plano/" + 2, "2", dados);


        }

        sucessMessage();
        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);
    }
    public void alterarPrecos3(View v) {
        //validação das caixas de texto
        if (edpreco3.getText().toString().length() == 0) {
            edpreco3.setError("É necessário preencher o preco_plano do plano.");
            edpreco3.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "preco_plano=" + edpreco3.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = PrecosEditar.this;
            _api._listaPlanos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/plano/" + 3, "2", dados);
        }

        sucessMessage();
        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);
    }
    public void alterarPrecos4(View v) {
        //validação das caixas de texto
        if (edpreco4.getText().toString().length() == 0) {
            edpreco4.setError("É necessário preencher o preco_plano do plano.");
            edpreco4.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "preco_plano=" + edpreco4.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = PrecosEditar.this;
            _api._listaPlanos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/plano/" + 4, "2", dados);
        }

        sucessMessage();
        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);
    }
    public void alterarPrecos5(View v) {
        //validação das caixas de texto
        if (edpreco5.getText().toString().length() == 0) {
            edpreco5.setError("É necessário preencher o preco_plano do plano.");
            edpreco5.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "preco_plano=" + edpreco5.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = PrecosEditar.this;
            _api._listaPlanos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/plano/" + 5, "2", dados);
        }

        sucessMessage();
        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);
    }
    public void alterarPrecos6(View v) {
        //validação das caixas de texto
        if (edpreco6.getText().toString().length() == 0) {
            edpreco6.setError("É necessário preencher o preco_plano do plano.");
            edpreco6.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "preco_plano=" + edpreco6.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = PrecosEditar.this;
            _api._listaPlanos = new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/plano/" + 6, "2", dados);
        }

        sucessMessage();
        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);
    }

    public void sucessMessage() {
        Toast.makeText(this, "Preço alterado com sucesso.", Toast.LENGTH_LONG).show();

        Intent precos = new Intent(getApplicationContext(), Precos.class);

        startActivity(precos);
    }
}