package amsi.dei.estg.ipleiria.app_adatel.vistas;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.app_adatel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicoQuartosFragment extends Fragment {


    public ServicoQuartosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listas, container, false);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetalhesServicoQuartosActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
