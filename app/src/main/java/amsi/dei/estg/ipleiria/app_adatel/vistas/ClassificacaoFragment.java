package amsi.dei.estg.ipleiria.app_adatel.vistas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.Classificacao;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificacaoFragment extends Fragment {

    RatingBar ratingBar1 = null;
    private RatingBar ratingQuarto;
    private RatingBar ratingComida;
    private RatingBar ratingStaff;
    private RatingBar ratingServicos;
    private RatingBar ratingGeral;
    private Button btnClassificacao;

    public ClassificacaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  View rootView = inflater.inflate(R.layout.fragment_classificacao, container, false);
        // Inflate the layout for this fragment
       // ratingBar1 = rootView.findViewById(R.id.ratingBar); // initiate a rating bar textView2

        View rootView = inflater.inflate(R.layout.fragment_classificacao, container, false);

        btnClassificacao = rootView.findViewById(R.id.btClassificacao);
        ratingQuarto = rootView.findViewById(R.id.ratingQuarto);
        ratingComida = rootView.findViewById(R.id.ratingComida);
        ratingStaff = rootView.findViewById(R.id.ratingHospitalidade);
        ratingServicos = rootView.findViewById(R.id.ratingServicos);
        ratingGeral = rootView.findViewById(R.id.ratingGeral);

        btnClassificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonGestaoHotel.getInstance(getContext()).adicionarClassificacaoAPI(adicionarClassificacao(), getContext());
            }
        });

        return rootView;
    }

    public Classificacao adicionarClassificacao(){

        String ratingQ = "Rating is :" + ratingQuarto.getRating();
        String ratingC = "Rating is :" + ratingComida.getRating();
        String ratingH = "Rating is :" + ratingStaff.getRating();
        String ratingS = "Rating is :" + ratingServicos.getRating();
        String ratingG = "Rating is :" + ratingGeral.getRating();

        double quarto = Double.parseDouble(ratingQ);
        double comida = Double.parseDouble(ratingC);
        double staff = Double.parseDouble(ratingH);
        double servicos = Double.parseDouble(ratingS);
        double geral = Double.parseDouble(ratingG);


        Classificacao auxiliar = new Classificacao(0, quarto, comida, staff, servicos, geral, 25);
        return auxiliar;

    }

}
