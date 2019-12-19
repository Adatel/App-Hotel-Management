package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View.OnClickListener;

import java.util.Calendar;

import amsi.dei.estg.ipleiria.app_adatel.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class CriarReservaFragment extends Fragment{

    private EditText etDtaEntrada, etDtaSaida;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private int day,mes,year;

    public CriarReservaFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_criar_reserva, container, false);
        etDtaEntrada = (EditText) rootView.findViewById(R.id.etDataEntrada);
        etDtaSaida = (EditText) rootView.findViewById(R.id.etDataSaida);

        ////God all mighty https://www.youtube.com/watch?v=-mJmScTAWyQ
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);


        etDtaEntrada.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                       // alterar a ordem da data dd/mm/yy
                        etDtaEntrada.setText(day + "/" + (month+1) + "/" + year);
                    }
                }, day,mes,year);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        etDtaSaida.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // alterar a ordem da data dd/mm/yy
                        etDtaSaida.setText(day + "/" + (month+1)  + "/" + year);
                    }
                }, day,mes,year);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        return rootView;
    }
}