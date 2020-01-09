package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class DetalhesReservaClienteActivity extends AppCompatActivity {

    public static final String CHAVE_ID = "idReserva";

    private int idReserva;
    private EditText  dataEntrada, dataSaida, numeroPessoas, quartosSolteiro, quartosDuplo, quartosCasal, quartosFamilia;
    private Reserva reserva;
    private FloatingActionButton fab;

    //Calendario
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private int day,mes,year;

    private Reserva reservaSelecionada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_reserva_cliente);

        dataEntrada = findViewById(R.id.etDataEntrada);
        dataSaida = findViewById(R.id.etDataSaida);
        numeroPessoas = findViewById(R.id.etNumPessoas);
        quartosSolteiro = findViewById(R.id.etSolteiro);
        quartosCasal = findViewById(R.id.etCasal);
        quartosDuplo = findViewById(R.id.etDuplo);
        quartosFamilia = findViewById(R.id.etFamilia);
        fab = findViewById(R.id.fab);

        // Recebe o id da reserva como parâmentro e vai buscar a reserva ao SingletonGestaoHotel pelo id
        idReserva = getIntent().getIntExtra(CHAVE_ID,-1);


        System.out.println("--> " + dataSaida);

        if(idReserva == -1){
            setTitle("Adicionar Reserva");
            System.out.println("adicionar");
            fab.setImageResource(R.drawable.ic_adicionar);
        } else {
            System.out.println("Reserva: " + idReserva);
            mostrarReserva(idReserva);
            //System.out.println("Reserva: " + idReserva);
            fab.setImageResource(R.drawable.ic_alterar);
        }

        /// <----------------------------Calendario--------------------------------->
        ////God all mighty https://www.youtube.com/watch?v=-mJmScTAWyQ
        ///Tem que ter a EditText android:focusable="false" para resultar

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        dataEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(DetalhesReservaClienteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // alterar a ordem da data dd/mm/yy
                        dataEntrada.setText(day + "/" + (month+1)  + "/" + year);
                    }
                }, day,mes,year);
                //Impede que o utilizador escolha uma data anterior à atual
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        dataSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(DetalhesReservaClienteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // alterar a ordem da data dd/mm/yy
                        dataSaida.setText(day + "/" + (month+1)  + "/" + year);
                    }
                }, day,mes,year);
                //Impede que o utilizador escolha uma data anterior à atual
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        // <----------------------------------Fab------------------------------------->

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idReserva == -1){
                    SingletonGestaoHotel.getInstance(getApplicationContext()).adicionarReservaBD(adicionarReserva());
                    finish();
                } else {
                    SingletonGestaoHotel.getInstance(getApplicationContext()).guardarReservaBD(editarReserva());
                    finish();
                }
            }
        });

    }

    private Reserva adicionarReserva(){

        int quartoS = Integer.parseInt(quartosSolteiro.getText().toString());
        int quartoD = Integer.parseInt(quartosDuplo.getText().toString());
        int quartoF = Integer.parseInt(quartosFamilia.getText().toString());
        int quartoC = Integer.parseInt(quartosCasal.getText().toString());

        int numeroQuartos = quartoS + quartoD + quartoF + quartoC;

        Reserva auxiliar = new Reserva(0, Integer.parseInt(numeroPessoas.getText().toString()), numeroQuartos, quartoS, quartoD, quartoF, quartoC,  dataEntrada.getText().toString(), dataSaida.getText().toString(), 1);
        return auxiliar;
    }

    private Reserva editarReserva(){

        reservaSelecionada.setNumPessoas(Integer.parseInt(numeroPessoas.getText().toString()));
        reservaSelecionada.setQuartoSol(Integer.parseInt(quartosSolteiro.getText().toString()));
        reservaSelecionada.setQuartoD(Integer.parseInt(quartosDuplo.getText().toString()));
        reservaSelecionada.setQuartoF(Integer.parseInt(quartosFamilia.getText().toString()));
        reservaSelecionada.setQuartoF(Integer.parseInt(quartosCasal.getText().toString()));
        reservaSelecionada.setDtEntrada(dataEntrada.getText().toString());
        reservaSelecionada.setDtSaida(dataSaida.getText().toString());

        return reservaSelecionada;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(idReserva != -1) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_detalhes_reserva, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.itemRemover){
            //Toast.makeText(this, "Remover", Toast.LENGTH_SHORT).show();
            dialogRemover();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void  mostrarReserva(int idReserva){

        reservaSelecionada = SingletonGestaoHotel.getInstance(getApplicationContext()).getReservaBD(idReserva);
        System.out.println("--> ReservaSelecionada: " + reservaSelecionada);
        //setTitle("Reserva");
        dataEntrada.setText(reservaSelecionada.getDtEntrada());
        dataSaida.setText(reservaSelecionada.getDtSaida());
        numeroPessoas.setText(reservaSelecionada.getNumPessoas()+"");
        quartosSolteiro.setText(reservaSelecionada.getQuartoSol()+"");
        quartosDuplo.setText(reservaSelecionada.getQuartoD()+"");
        quartosCasal.setText(reservaSelecionada.getQuartoC()+"");
        quartosFamilia.setText(reservaSelecionada.getQuartoF()+"");
    }


    private void dialogRemover() {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        // Contruindo Alert Dialog
        // Título
        builder.setTitle("Cancelar Reserva")
                // Messagem
                .setMessage("Pretende mesmo cancelar a reserva?")
                // 2 Botões
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGestaoHotel.getInstance(getApplicationContext()).removerReservaBD(reservaSelecionada.getId());
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada
                    }
                })
                // Icon
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

}
