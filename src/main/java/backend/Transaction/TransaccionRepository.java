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
    public void delete(Transaccion transaccion){
        listInMemory.remove(transaccion);
    }

    //Buscar por rango de fechas
    public List<Transaccion> findByRange(LocalDate first, LocalDate last){
        List<Transaccion> auxList = new ArrayList<>();
        for(Transaccion transaccion:listInMemory) {
            if (!transaccion.getDate().isAfter(last) && !transaccion.getDate().isBefore(first)) {
                auxList.add(transaccion);
            }
        }
        return auxList;
    }

    //Buscar todos
    public List<Transaccion> getList(){
        return List.copyOf(listInMemory);
    }

}
