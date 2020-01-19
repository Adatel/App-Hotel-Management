package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Profile;

public interface ProfilesListener {

    void onRefreshListaProfiles(ArrayList<Profile> listaProfiles);
    void onUpdateListaProfilesBD(Profile profile, int operacao);
}
