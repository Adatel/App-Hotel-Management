package amsi.dei.estg.ipleiria.app_adatel.models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.listeners.UserListener;
import amsi.dei.estg.ipleiria.app_adatel.utils.UserJsonParser;

public class SingletonGestaoHotel {

    private  static RequestQueue volleyQueue = null;

    //private String token = "AMSI-TOKEN";
    private String mUrlAPIUSERS = "http://localhost:8081/api/users";
    private String mUrlAPIPROFILES = "http://localhost:8081/api/profiles";
    private String mUrlAPIRESERVAS = "http://localhost:8081/api/reservas";

    ///Adicionei
    private ArrayList<User> users;
    private ArrayList<Reserva> reservas;
    private ArrayList<Profile> profiles;

    private static SingletonGestaoHotel INSTANCE = null;
    private HotelBDHelper hotelBDHelper = null;

    private UserListener userListener;

    public static synchronized SingletonGestaoHotel getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestaoHotel(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private SingletonGestaoHotel(Context context) {
        ///Adicionei
        users = new ArrayList<>();
        profiles = new ArrayList<>();

        reservas = new ArrayList<>();
        //gerarFakeData();
        hotelBDHelper = new HotelBDHelper(context);
    }


    // <--------------  Métodos para garantir que os dados da BD estão atualizados com os dados vindos da API -------------->


    // <----------------------------------- RESERVAS ----------------------------------->

    public ArrayList<Reserva> getReservasBD(){
        return reservas;
    }

    public Reserva getReservaBD(long idReserva){
        for(Reserva r: reservas){
            if(r.getId() == idReserva){
                return r;
            }
        }
        return null;
    }

    public void adicionarReservaBD(Reserva reserva){
        reservas.add(reserva);
    }

    public void removerReservaBD(int idReserva){
        Reserva auxReserva = getReservaBD(idReserva);
        reservas.remove(auxReserva);
    }

    public void editarReservaBD(Reserva reserva){
        if(!reservas.contains(reserva)){
            return;
        }
        Reserva auxReserva = getReservaBD(reserva.getId());
        auxReserva.setDtEntrada(reserva.getDtEntrada());
        auxReserva.setDtSaida(reserva.getDtSaida());
        auxReserva.setNumPessoas(reserva.getNumPessoas());
        //auxReserva.setNumQuartos(reserva.getNumQuartos());
    }


    // <----------------------------------- USERS ----------------------------------->

    public User getUserBD(int id){
        for (User u: users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    ///Adicionei
    public ArrayList<User> getUsersBD(){
        return users = hotelBDHelper.getAllUsersBD();
    }

    public void adicionarUserBD(User user){
        hotelBDHelper.adicionarUserBD(user);
    }

    public void adicionarUsersBD(ArrayList<User> users){
        hotelBDHelper.removerAllUsers();
    }

    public void removerUserBD(int id){
        User auxUser = getUserBD(id);

        if(auxUser != null){
            if(hotelBDHelper.removerUserBD(auxUser.getId())){
                users.remove(auxUser);
                System.out.println("--> User removido");
            }
        }
    }

    public void guardarUserBD(User user){
        if(!users.contains(user)){
            return;
        }

        User auxUser = getUserBD(user.getId());
        auxUser.setUsername(user.getUsername());
        auxUser.setEmail(user.getEmail());
        auxUser.setPassword(user.getPassword());

        if(hotelBDHelper.guardarUserBD(auxUser)){
            System.out.println("--> USer Guardado na BD");
        }
    }


    // <----------------------------------- PROFILE ----------------------------------->

    public Profile getProfileBD(int id_user){
        for (Profile p: profiles){
            if(p.getId_user() == id_user){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Profile> getProfilesBD(){
        profiles = hotelBDHelper.getAllProfilesBD();
        return profiles;
    }

    public void adicionarProfileBD(Profile profile){
        hotelBDHelper.adicionarProfileBD(profile);
    }

    public void removerProfileBD(int id_user){
        Profile auxProfile = getProfileBD(id_user);

        if(auxProfile != null){
            if(hotelBDHelper.removerProfileBD(auxProfile.getId_user())){
                profiles.remove(auxProfile);
                System.out.println("--> Profile removido");
            }
        }
    }

    public void guardarProfileBD(Profile profile){
        if(!profiles.contains(profile)){
            return;
        }

        Profile auxProfile = getProfileBD(profile.getId_user());
        auxProfile.setNome(profile.getNome());
        auxProfile.setNif(profile.getNif());
        auxProfile.setTelefone(profile.getTelefone());
        auxProfile.setIs_admin(profile.getIs_admin());
        auxProfile.setIs_cliente(profile.getIs_cliente());
        auxProfile.setIs_funcionario(profile.getIs_funcionario());

        if(hotelBDHelper.guardarProfileBD(auxProfile)){
            System.out.println("--> Profile Guardado");
        }
    }



    // <----------------------------- Métodos para atualizarem a API ----------------------------->

    // <----------------------------------- USERS ----------------------------------->

    public  void getAllUsersAPI(final Context context, boolean isConected){
        Toast.makeText(context, "Is Connected", Toast.LENGTH_SHORT).show();

        if(!isConected){
            users = hotelBDHelper.getAllUsersBD();

            if(userListener != null){
               userListener.onRefreshListaUser(users);
            }
            else {
                JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIUSERS, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //recebe todos os users como um objeto
                        users = UserJsonParser.parserJsonUsers(response, context);
                        System.out.println("--> Response Users: " + users);

                        adicionarUsersBD(users);

                        if(userListener != null){
                            userListener.onRefreshListaUser(users);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("--> Erro: GetAllUsersApi: " + error.getMessage());
                    }
                });
                volleyQueue.add(req);
            }
        }
    }

    private void gerarFakeData(){
        /*
            reservas.add(new Reserva(1, 2, 2, 0, 1,0, 1, "9/12/2019", "15/12/2019"));
            reservas.add(new Reserva(2, 1, 1, 1, 0,0, 0, "23/12/2019", "26/12/2019"));
            reservas.add(new Reserva(3, 6, 3, 0, 0,2, 1, "8/01/2020", "14/01/2020"));
            reservas.add(new Reserva(4, 3, 2, 1, 1,0, 0, "20/01/2020", "24/01/2020"));
         */
    }
}
