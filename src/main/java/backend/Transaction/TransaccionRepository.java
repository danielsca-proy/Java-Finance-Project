package backend.Transaction;

import backend.Category.Categoria;
import backend.Exceptions.AppExceptions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaccionRepository {
    private List<Transaccion> listInMemory = new ArrayList<>();

    //Guardar en la lista
    public void save(Transaccion transaccion){
        listInMemory.add(transaccion);
    }

    //Eliminar de la lista
    public void delete(int id){
        for (Transaccion transaccion:listInMemory){
            if (transaccion.getId() == id){
                listInMemory.remove(transaccion);
                return;
            }
        }
        throw new AppExceptions("Transaccion no existente.");
    }

    //Buscar por rango de fechas
    public List<Transaccion> findByRange(LocalDate first, LocalDate last, int idUser){
        List<Transaccion> auxList = new ArrayList<>();
        for(Transaccion transaccion:listInMemory) {
            if ((!transaccion.getDate().isAfter(last) && !transaccion.getDate().isBefore(first)) && (transaccion.getUser().getId() == idUser)){
                auxList.add(transaccion);
            }
        }
        return auxList;
    }

    //Buscar todas las transacciones por usuario
    public List<Transaccion> findAllListByUser(int id){
        List<Transaccion> auxList = new ArrayList<>();
        for (Transaccion transaccion : listInMemory){
            if (transaccion.getUser().getId() == id){
                auxList.add(transaccion);
            }
        }
        return auxList;
    }

}
