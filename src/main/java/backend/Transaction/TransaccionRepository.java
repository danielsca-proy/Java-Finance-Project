package backend.Transaction;

import backend.Category.Categoria;
import backend.Exceptions.TransaccionExceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransaccionRepository {
    List<Transaccion> list = new ArrayList<>();

    //Agrega a la lista
    public void save(Transaccion transaccion){
        list.add(transaccion);
    }

    //Elimina de la lista
    public void delete(Transaccion transaccion){
        list.remove(transaccion);
    }

    //Devuelve la lista
    public List<Transaccion> getList(int id){
        return list;
    }

    //Busca transacciones en un rango de fechas.
    public List<Transaccion> findByDateRange(LocalDate firstDate, LocalDate lastDate){
        List<Transaccion> listNew = new ArrayList<>();
        for(Transaccion transaccion : list){
            LocalDate fecha = transaccion.getDate();
            if(!fecha.isAfter(lastDate) && !fecha.isBefore(firstDate)){
                listNew.add(transaccion);
            }
        }
        return listNew;
    }
}
