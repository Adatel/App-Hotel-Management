package amsi.dei.estg.ipleiria.app_adatel;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class SingletonGestaoHotel implements Serializable {

    private static SingletonGestaoHotel INSTANCE = null;

    public static SingletonGestaoHotel getInstance() {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestaoHotel();
        }
        return INSTANCE;
    }

    private ArrayList<Reserva> reservas;

    private SingletonGestaoHotel() {
        reservas = new ArrayList<>();
        gerarFakeData();
    }

    public ArrayList<Reserva> getReservas(){
        return reservas;
    }

    public Reserva getReserva(long idReserva){
        for(Reserva l: reservas){
            if(l.getId() == idReserva){
                return l;
            }
        }
        return null;
    }

    private void gerarFakeData(){
        reservas.add(new Reserva(1, 2, 1, 0, 0,0, 1, "9/12/2019", "15/12/2019"));
        reservas.add(new Reserva(2, 1, 1, 1, 0,0, 0, "23/12/2019", "26/12/2019"));
        reservas.add(new Reserva(3, 6, 3, 0, 0,0, 3, "8/01/2020", "14/01/2020"));

    }
}
