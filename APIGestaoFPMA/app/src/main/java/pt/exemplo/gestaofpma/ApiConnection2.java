package pt.exemplo.gestaofpma;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ApiConnection2 extends AsyncTask<String, Void, Void> {

    public Activity _activity;
    private ProgressDialog _pdialog;

    public ArrayList<HashMap<String, String>> _listaClientes;
    public ArrayList<HashMap<String, String>> _listaQuartos;
    public ArrayList<HashMap<String, String>> _listaPlanos;
    public ArrayList<HashMap<String, String>> _listaReservas;
    public ArrayList<HashMap<String, String>> _listaUtilizador;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        _pdialog = new ProgressDialog(_activity);
        Log.d("msg", "Aguardar os dados 2");
        _pdialog.setMessage("Aguardar os dados...");
        _pdialog.setCancelable(false);
        _pdialog.show();
    }

    @Override
    protected Void doInBackground(String... urls) {
        HttpURLConnection _conexao;
        InputStream _is;
        String _resJson;

        _pdialog.setMessage("Pedido a ser executado...");

        try {
            //URL _endpoint = new URL("http://10.0.2.2:3001/api/v1/cliente");
            URL _endpoint = new URL(urls[0]);

            _conexao = (HttpURLConnection) _endpoint.openConnection();

            if (urls[1] == "0") { //GET
                _conexao.setRequestMethod("GET");
            } else if (urls[1] == "1") { //POST
                String data = urls[2];
                Log.d("http", "Os dados enviados no body do pedido foram: " + data);

                byte[] dados = data.getBytes(StandardCharsets.UTF_8);

                _conexao.setDoOutput(true);
                _conexao.setRequestMethod("POST");
                _conexao.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                _conexao.setRequestProperty("charset", "utf-8");
                _conexao.setRequestProperty("Content-Length", Integer.toString(dados.length));
                try (DataOutputStream wr = new DataOutputStream(_conexao.getOutputStream())) {
                    wr.write(dados);
                }
            }else if (urls[1] == "2") {
                String data = urls[2];
                Log.d("http", "Os dados alterados no body do pedido foram: " + data);

                byte[] dados = data.getBytes(StandardCharsets.UTF_8);

                _conexao.setDoOutput(true);
                _conexao.setRequestMethod("PUT");
                _conexao.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                _conexao.setRequestProperty("charset", "utf-8");
                _conexao.setRequestProperty("Content-Length", Integer.toString(dados.length));
                try (DataOutputStream wr = new DataOutputStream(_conexao.getOutputStream())) {
                    wr.write(dados);
                }
            }else if (urls[1] == "3") {

                _conexao.setDoOutput(true);
                _conexao.setRequestMethod("DELETE");
                _conexao.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }

            _conexao.setReadTimeout(12000);
            _conexao.setConnectTimeout(12000);
            _conexao.connect();
            Log.d("http", "O código HTTP do pedido foi: " + _conexao.getResponseCode());

            int _httpStatus = _conexao.getResponseCode();
            if (_httpStatus != HttpURLConnection.HTTP_BAD_REQUEST && _httpStatus != HttpURLConnection.HTTP_INTERNAL_ERROR && _httpStatus != HttpURLConnection.HTTP_NOT_FOUND) {
                _is = _conexao.getInputStream();
            } else {
                _is = _conexao.getErrorStream();
            }

            _resJson = converteStreamParaString(_is);
            Log.d("http", "A resposta ao pedido HTTP foi: " + _resJson);



            //CLIENTES
            if (_resJson != null) {
                try {
                    JSONObject _resposta = new JSONObject(_resJson);


                    if (_resposta.get("dados") instanceof JSONArray) {
                        JSONArray _listClientesJson = _resposta.getJSONArray("dados");


                        for (int i = 0; i < _listClientesJson.length(); i++) {
                            JSONObject _cliente  = _listClientesJson.getJSONObject(i);
                            String id = _cliente.getString("id_cliente");
                            String nome = _cliente.getString("nome");
                            String email = _cliente.getString("email");
                            String telemovel = _cliente.getString("telemovel");
                            String data = _cliente.getString("data");

                            //JSONObject _quarto = _cliente.getString("quarto")

                            HashMap<String, String> cliente = new HashMap();
                            cliente.put("id_cliente", String.valueOf(id));
                            cliente.put("nome", String.valueOf(nome));
                            cliente.put("email", String.valueOf(email));
                            cliente.put("telemovel", String.valueOf(telemovel));
                            cliente.put("data", String.valueOf(data));
                            _listaClientes.add(cliente);
                        }
                    } else if (_resposta.get("dados") instanceof JSONObject) {
                        JSONObject _cliente = _resposta.getJSONObject("dados");
                        String id = _cliente.getString("id_cliente");
                        String nome = _cliente.getString("nome");
                        String email = _cliente.getString("email");
                        String telemovel = _cliente.getString("telemovel");
                        String data = _cliente.getString("data");

                        HashMap<String, String> cliente = new HashMap();
                        cliente.put("id_cliente", String.valueOf(id));
                        cliente.put("nome", String.valueOf(nome));
                        cliente.put("email", String.valueOf(email));
                        cliente.put("telemovel", String.valueOf(telemovel));
                        cliente.put("data", String.valueOf(data));
                        _listaClientes.add(cliente);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            //QUARTOS
            if (_resJson != null) {
                try {
                    JSONObject _resposta = new JSONObject(_resJson);


                    if (_resposta.get("dados") instanceof JSONArray) {
                        JSONArray _listQuartosJson = _resposta.getJSONArray("dados");


                        for (int i = 0; i < _listQuartosJson.length(); i++) {
                            JSONObject _quarto = _listQuartosJson.getJSONObject(i);
                            String id = _quarto.getString("id_quarto");
                            String tipo = _quarto.getString("tipo");
                            String data = _quarto.getString("data");
                            String status = _quarto.getString("status");

                            HashMap<String, String> quarto = new HashMap();
                            quarto.put("id_quarto", String.valueOf(id));
                            quarto.put("tipo", String.valueOf(tipo));
                            quarto.put("data", String.valueOf(data));
                            quarto.put("status", String.valueOf(status));
                            _listaQuartos.add(quarto);
                        }
                    } else if (_resposta.get("dados") instanceof JSONObject) {
                        JSONObject _quarto = _resposta.getJSONObject("dados");
                        String id = _quarto.getString("id_quarto");
                        String tipo = _quarto.getString("tipo");
                        String data = _quarto.getString("data");
                        String status = _quarto.getString("status");

                        HashMap<String, String> quarto = new HashMap();
                        quarto.put("id_quarto", String.valueOf(id));
                        quarto.put("tipo", String.valueOf(tipo));
                        quarto.put("data", String.valueOf(data));
                        quarto.put("status", String.valueOf(status));
                        _listaQuartos.add(quarto);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            //PLANOS
            if (_resJson != null) {
                try {
                    JSONObject _resposta = new JSONObject(_resJson);


                    if (_resposta.get("dados") instanceof JSONArray) {
                        JSONArray _listPlanosJson = _resposta.getJSONArray("dados");


                        for (int i = 0; i < _listPlanosJson.length(); i++) {
                            JSONObject _plano = _listPlanosJson.getJSONObject(i);
                            String id_plano = _plano.getString("id_plano");
                            String tipo_plano = _plano.getString("tipo_plano");
                            String data = _plano.getString("data");
                            String preco_plano = _plano.getString("preco_plano");

                            HashMap<String, String> plano = new HashMap();
                            plano.put("id_plano", String.valueOf(id_plano));
                            plano.put("tipo_plano", String.valueOf(tipo_plano));
                            plano.put("data", String.valueOf(data));
                            plano.put("preco_plano", String.valueOf(preco_plano));
                            _listaPlanos.add(plano);
                        }
                    } else if (_resposta.get("dados") instanceof JSONObject) {
                        JSONObject _plano = _resposta.getJSONObject("dados");
                        String id_plano = _plano.getString("id_plano");
                        String tipo_plano = _plano.getString("tipo_plano");
                        String data = _plano.getString("data");
                        String preco_plano = _plano.getString("preco_plano");

                        HashMap<String, String> plano = new HashMap();
                        plano.put("id_plano", String.valueOf(id_plano));
                        plano.put("tipo_plano", String.valueOf(tipo_plano));
                        plano.put("data", String.valueOf(data));
                        plano.put("preco_plano", String.valueOf(preco_plano));
                        _listaPlanos.add(plano);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            //RESERVAS
            if (_resJson != null) {
                try {
                    JSONObject _resposta = new JSONObject(_resJson);

                    if (_resposta.get("dados") instanceof JSONArray) {
                        JSONArray _listReservasJson = _resposta.getJSONArray("dados");
                        for (int i = 0; i < _listReservasJson.length(); i++) {
                            JSONObject _reserva = _listReservasJson.getJSONObject(i);
                                //CLIENTE
                            JSONObject _cliente = _reserva.getJSONObject("cliente");
                            String nome_cliente = _cliente.getString("nome");
                                //PLANO
                            JSONObject _plano = _reserva.getJSONObject("plano");
                            String tipo_plano = _plano.getString("tipo_plano");
                            String preco_plano = _plano.getString("preco_plano");
                                 //QUARTO
                            JSONObject _quarto = _reserva.getJSONObject("quarto");
                            String tipo_quarto = _quarto.getString("tipo");
                                //RESERVA
                            String id_reserva = _reserva.getString("id_reserva");
                            String id_quarto = _reserva.getString("id_quarto");
                            String id_cliente = _reserva.getString("id_cliente");
                            String id_plano = _reserva.getString("id_plano");
                            String data_checkin = _reserva.getString("data_checkin");
                            String data_checkout = _reserva.getString("data_checkout");
                            String observacoes = _reserva.getString("observacoes");
                            String data_alteracao = _reserva.getString("data_alteracao");

                            HashMap<String, String> reserva = new HashMap();
                            reserva.put("id_reserva", String.valueOf(id_reserva));
                            reserva.put("id_quarto", String.valueOf(id_quarto));
                            reserva.put("tipo_quarto", String.valueOf(tipo_quarto));
                            reserva.put("id_cliente", String.valueOf(id_cliente));
                            reserva.put("id_plano", String.valueOf(id_plano));
                            reserva.put("nome", String.valueOf(nome_cliente));
                            reserva.put("tipo_plano", String.valueOf(tipo_plano));
                            reserva.put("preco_plano", String.valueOf(preco_plano));
                            reserva.put("data_checkin", String.valueOf(data_checkin));
                            reserva.put("data_checkout", String.valueOf(data_checkout));
                            reserva.put("observacoes", String.valueOf(observacoes));
                            reserva.put("data_alteracao", String.valueOf(data_alteracao));
                            _listaReservas.add(reserva);
                        }

                    } else if (_resposta.get("dados") instanceof JSONObject) {
                        JSONObject _reserva = _resposta.getJSONObject("dados");

                        String id_reserva = _reserva.getString("id_reserva");
                        String id_quarto = _reserva.getString("id_quarto");
                        String id_cliente = _reserva.getString("id_cliente");
                        String id_plano = _reserva.getString("id_plano");
                        String data_checkin = _reserva.getString("data_checkin");
                        String data_checkout = _reserva.getString("data_checkout");
                        String observacoes = _reserva.getString("observacoes");
                        String data_alteracao = _reserva.getString("data_alteracao");

                        HashMap<String, String> reserva = new HashMap();
                        reserva.put("id_reserva", String.valueOf(id_reserva));
                        reserva.put("id_quarto", String.valueOf(id_quarto));
                        reserva.put("id_cliente" , String.valueOf(id_cliente));
                        reserva.put("id_plano", String.valueOf(id_plano));
                        reserva.put("data_checkin", String.valueOf(data_checkin));
                        reserva.put("data_checkout", String.valueOf(data_checkout));
                        reserva.put("observacoes", String.valueOf(observacoes));
                        reserva.put("data_alteracao", String.valueOf(data_alteracao));
                        _listaReservas.add(reserva);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //UTILIZADOR
            if (_resJson != null) {
                try {
                    JSONObject _resposta = new JSONObject(_resJson);


                    if (_resposta.get("dados") instanceof JSONArray) {
                        JSONArray _listUtilizadorJson = _resposta.getJSONArray("dados");


                        for (int i = 0; i < _listUtilizadorJson.length(); i++) {
                            JSONObject _utilizador = _listUtilizadorJson.getJSONObject(i);
                            String username = _utilizador.getString("username");
                            String password = _utilizador.getString("password");

                            HashMap<String, String> utilizador = new HashMap();
                            utilizador.put("username", String.valueOf(username));
                            utilizador.put("password", String.valueOf(password));
                            _listaUtilizador.add(utilizador);
                        }
                    } else if (_resposta.get("dados") instanceof JSONObject) {
                        JSONObject _utilizador = _resposta.getJSONObject("dados");
                        String username = _utilizador.getString("username");
                        String password = _utilizador.getString("password");

                        HashMap<String, String> utilizador = new HashMap();
                        utilizador.put("username", String.valueOf(username));
                        utilizador.put("password", String.valueOf(password));

                        _listaUtilizador.add(utilizador);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




            _is.close();
            _conexao.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //este método converte de stream para string
    private static String converteStreamParaString(InputStream is) {
        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while ((linha = br.readLine()) != null) {
                buffer.append(linha);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPostExecute(Void _resultado) {
        super.onPostExecute(_resultado);

        if (_pdialog.isShowing()) {
            _pdialog.dismiss();
        }

        //verifica qual das activities invocou a API
        if (_activity.getClass().getSimpleName().equals("Clientes")) {
            Clientes ma = (Clientes) _activity;
            ma.updateUI();
        } else if (_activity.getClass().getSimpleName().equals("ClienteNovo")) {
            ClienteNovo ia = (ClienteNovo) _activity;
            ia.sucessMessage();
        }else if (_activity.getClass().getSimpleName().equals("ClienteEditar")) {
            ClienteEditar ia = (ClienteEditar) _activity;
            ia.updateUI();
        }
        else if (_activity.getClass().getSimpleName().equals("Quartos")) {
            Quartos qt = (Quartos) _activity;
            qt.updateUI();
        } else if (_activity.getClass().getSimpleName().equals("QuartoNovo")) {
            QuartoNovo ia = (QuartoNovo) _activity;
            ia.sucessMessage();
        } else if (_activity.getClass().getSimpleName().equals("QuartoEditar")) {
            QuartoEditar ia = (QuartoEditar) _activity;
            ia.updateUI();
        }
        else if (_activity.getClass().getSimpleName().equals("Precos")) {
            Precos ma = (Precos) _activity;
            ma.updateUI();
        }
        else if (_activity.getClass().getSimpleName().equals("PrecosEditar")) {
            PrecosEditar pe = (PrecosEditar) _activity;
            pe.updateUI();
        }
        else if (_activity.getClass().getSimpleName().equals("Reservas")) {
            Reservas ma = (Reservas) _activity;
            ma.updateUI();
        }
        else if (_activity.getClass().getSimpleName().equals("ReservaEditar")) {
            ReservaEditar re = (ReservaEditar) _activity;
            re.updateUI();
        }else if (_activity.getClass().getSimpleName().equals("ReservaNovo")) {
        ReservaNovo rn = (ReservaNovo) _activity;
            rn.updateUI();
            rn.sucessMessage();
        }
        else if (_activity.getClass().getSimpleName().equals("MainActivity")) {
            MainActivity ma = (MainActivity) _activity;
            ma.updateUI();
        }
        else if (_activity.getClass().getSimpleName().equals("utilizador")) {
            utilizador uta = (utilizador) _activity;
            uta.updateUI();
        }
    }




}
