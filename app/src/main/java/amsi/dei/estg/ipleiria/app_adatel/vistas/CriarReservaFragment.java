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
/*
entrada = rootView.findViewById(R.id.etDataEntrada);

        Calendar calendar = Calendar.getInstance();
final int year = calendar.get(Calendar.YEAR);
final int month = calendar.get(Calendar.MONTH);
final int day = calendar.get(Calendar.DAY_OF_MONTH);

        entrada.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
        CriarReservaFragment.this, new DatePickerDialog.OnDateSetListener() {
@Override
public void onDateSet(DatePicker view, int year, int month, int day) {
        month = month + 1;
        String date = day + "/" + month + "/" + year;
        entrada.setText(date);
        }
        }, year, month, day);
        datePickerDialog.show();
        }
        });
 */