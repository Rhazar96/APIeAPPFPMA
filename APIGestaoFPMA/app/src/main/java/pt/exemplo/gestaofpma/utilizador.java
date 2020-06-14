package pt.exemplo.gestaofpma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class utilizador extends AppCompatActivity {
    String usernameQM, passwordQM;
    EditText utilizador, password;
    Button entrar;
    ApiConnection2 _api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilizador);



        utilizador = (EditText) findViewById(R.id.utilizador);
        password = (EditText) findViewById(R.id.password);
        entrar = (Button) findViewById(R.id.entrar);
    }
    public void user (View v){
        utilizador = findViewById(R.id.utilizador);
        password = findViewById(R.id.password);

        if (utilizador.getText().toString().length() == 0) {
            utilizador.setError("É necessário preencher o Nome de Utilizador.");
            utilizador.requestFocus();
        } else if (password.getText().toString().length() == 0) {
            password.setError("É necessário preencher a passowrd.");
            password.requestFocus();
        } else {
            //dados a enviar para a API (formato BODY)
            String dados = "username=" + utilizador.getText() + "&password=" + password.getText();

            //executa o pedido à API
            _api = new ApiConnection2();
            _api._activity = utilizador.this;
            _api._listaUtilizador= new ArrayList();
            _api.execute("http://10.0.2.2:3000/api/v1/login" , "1", dados);
        }


    }

    public void updateUI(){

        for (int i=0; i<_api._listaUtilizador.size(); i++) {
            //variável que guarda os dados do utilizador (no formato key-value-pair)
            HashMap<String, String> _utilizador = _api._listaUtilizador.get(i);


            //string com os dados a mostrar na lista no formato - nome(id) e por baixo o email
            String username = _utilizador.get("username");
            String password = _utilizador.get("password");
            usernameQM = username;
            passwordQM = password;
        }

        if((utilizador.getText().toString().equals(usernameQM))&&(password.getText().toString().equals(passwordQM))){
            Intent intent = new Intent (utilizador.this, MainActivity.class);


            startActivity(intent);
        }else{
            Toast.makeText(this, "Dados incorretos...", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed() {

    }


}











