package amsi.dei.estg.ipleiria.app_adatel.ultis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import amsi.dei.estg.ipleiria.app_adatel.models.User;

public class UserJsonParser {
    //da return de um array de users eu Acho
    public static ArrayList<User> parserJsonUsers(JSONArray response, Context context){
        System.out.println("--> Parser Lista Users " + response);
        ArrayList<User> tempListaUsers = new ArrayList<User>();

        try{
            for(int i = 0; i < response.length(); i++){
                JSONObject user = (JSONObject) response.get(i);

                int idUser = user.getInt("id");
                String username = user.getString("username");
                String email = user.getString("email");
                String password =  user.getString("password_hash");

                System.out.println("--> Dados UserParser " + idUser + " " + username + " " + email + " " + password);

                User auxUser = new User(idUser,username,email,password);
                tempListaUsers.add(auxUser);
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaUsers;
    }

    //para verificar se a internet etá ligada
    public static boolean isConnectionEthernet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
