package amsi.dei.estg.ipleiria.app_adatel.vistas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amsi.dei.estg.ipleiria.app_adatel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriarReservaFragment extends Fragment {


    public CriarReservaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_criar_reserva, container, false);
    }

}
