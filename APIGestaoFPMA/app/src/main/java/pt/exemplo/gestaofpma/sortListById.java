package pt.exemplo.gestaofpma;
import java.util.*;
import org.json.*;

public class sortListById {
    public static ArrayList<HashMap<String, String>> main(ArrayList<HashMap<String, String>>listaDesordenada, String pagina) throws JSONException {
        JSONArray jsonArray = new JSONArray(listaDesordenada);
        JSONArray sortedJsonArray = new JSONArray();
        ArrayList<HashMap<String, String>> listaOrdenada = new ArrayList<HashMap<String, String>>();
        List list = new ArrayList();
        for(int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getJSONObject(i));
        }
        if(pagina == "Clientes") {
            Collections.sort(list, new Comparator<JSONObject>() {
                private static final String KEY_ID = "id_cliente";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String str1 = new String();
                    String str2 = new String();
                    try {
                        str1 = (String) a.get(KEY_ID);
                        str2 = (String) b.get(KEY_ID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return str2.compareTo(str1);
                }
            });
        }else if (pagina == "Reservas") {
            Collections.sort(list, new Comparator<JSONObject>() {
                private static final String KEY_ID = "id_reserva";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String str1 = new String();
                    String str2 = new String();
                    try {
                        str1 = (String) a.get(KEY_ID);
                        str2 = (String) b.get(KEY_ID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return str2.compareTo(str1);
                }
            });
        }
        for(int i = 0; i < jsonArray.length(); i++) {
            sortedJsonArray.put(list.get(i));
        }
        for (int i = 0; i < sortedJsonArray.length(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            try {
                JSONObject c = sortedJsonArray.getJSONObject(i);
                //Fill map
                Iterator<String> iter = c.keys();
                while (iter.hasNext()) {
                    String currentKey = iter.next();
                    map.put(currentKey, c.getString(currentKey));
                }
                listaOrdenada.add(map);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        return listaOrdenada;
    }
}