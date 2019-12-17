package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.User;

public interface UserListener {
    void onRefreshListaUser(ArrayList<User> listaLivros);
    void onUpdateListaUserBD(User user , int operacao);
}
