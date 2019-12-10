package amsi.dei.estg.ipleiria.app_adatel.vistas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import amsi.dei.estg.ipleiria.app_adatel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificacaoFragment extends Fragment {

    RatingBar ratingBar1 = null;

    public ClassificacaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  View rootView = inflater.inflate(R.layout.fragment_classificacao, container, false);
        // Inflate the layout for this fragment
       // ratingBar1 = rootView.findViewById(R.id.ratingBar); // initiate a rating bar

        return inflater.inflate(R.layout.fragment_classificacao, container, false);
    }

}
