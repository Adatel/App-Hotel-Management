package amsi.dei.estg.ipleiria.app_adatel.models;

import android.content.Context;

import java.util.ArrayList;

public class SingletonGestaoHotel {

    private ArrayList<Reserva> reservas;
    private static SingletonGestaoHotel INSTANCE = null;

    public static synchronized SingletonGestaoHotel getInstance() {
        if(INSTANCE == null){
            INSTANCE = new SingletonGestaoHotel();
        }
        return INSTANCE;
    }

    private SingletonGestaoHotel() {
        reservas = new ArrayList<>();
        gerarFakeData();
    }

    public ArrayList<Reserva> getReservas(){
        return reservas;
    }

    public Reserva getReserva(long idReserva){
        for(Reserva r: reservas){
            if(r.getId() == idReserva){
                return r;
            }
        }
        return null;
    }

    public void removerReserva(int idReserva){
        Reserva auxReserva = getReserva(idReserva);
        reservas.remove(auxReserva);
    }

    public void editarReserva(Reserva reserva){
        if(!reservas.contains(reserva)){
            return;
        }
        Reserva auxReserva = getReserva(reserva.getId());
        auxReserva.setDtEntrada(reserva.getDtEntrada());
        auxReserva.setDtSaida(reserva.getDtSaida());
        auxReserva.setNumPessoas(reserva.getNumPessoas());
    }

    private void gerarFakeData(){
        /*
            reservas.add(new Reserva(1, 2, 2, 0, 1,0, 1, "9/12/2019", "15/12/2019"));
            reservas.add(new Reserva(2, 1, 1, 1, 0,0, 0, "23/12/2019", "26/12/2019"));
            reservas.add(new Reserva(3, 6, 3, 0, 0,2, 1, "8/01/2020", "14/01/2020"));
            reservas.add(new Reserva(4, 3, 2, 1, 1,0, 0, "20/01/2020", "24/01/2020"));
         */
    }
}
