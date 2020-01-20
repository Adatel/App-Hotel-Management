package amsi.dei.estg.ipleiria.app_adatel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Pedido;

public class PedidoJsonParser {

    // Devolve um Array de Pedidos vindo da API
    public static ArrayList<Pedido> parserJsonPedidos(JSONArray response, Context context){

        System.out.println("--> Pedido: " + response);
        ArrayList<Pedido> tempListaPedidos = new ArrayList<Pedido>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject pedido = (JSONObject)response.get(i);

                int idPedido = pedido.getInt("id");
                int custo = pedido.getInt("custo");
                int id_reservaquarto = pedido.getInt("id_reservaquarto");
                String dta_hora = pedido.getString("data_hora");

                Pedido auxPedido = new Pedido(idPedido, custo, id_reservaquarto, dta_hora);

                tempListaPedidos.add(auxPedido);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaPedidos;
    }


    // Devolve um Pedido vindo da API
    public static Pedido parserJsonPedidos(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Pedido auxPedido = null;

        try {
            JSONObject pedido = new JSONObject(response);

            int idPedido = pedido.getInt("id");
            int custo = pedido.getInt("custo");
            int id_reservaquarto = pedido.getInt("id_reservaquarto");
            String dta_hora = pedido.getString("data_hora");

            auxPedido = new Pedido(idPedido, custo, id_reservaquarto, dta_hora);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxPedido;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
