package backend.Transaction;

import backend.Exceptions.AppExceptions;

import java.util.List;

public class TransaccionService{

    TransaccionRepository repository = new TransaccionRepository();

    public boolean add(Transaccion transaccion){
        //Verificar si no es nulo
        if(transaccion == null) {
            throw new AppExceptions("Nueva transaccion Invalida. ");
        }

        //Agregar
        repository.save(transaccion);
        return true;
    }

    public boolean eliminate(Transaccion transaccion){
        //Verificar si no es nulo
        if(transaccion == null) throw new AppExceptions("La transaccion a eliminar es nula. ");

        //Verificar si esta en la lista
        for(Transaccion transaccion1 : repository.getList()){
            if(!transaccion1.equals(transaccion)){
                throw new AppExceptions("La transaccion no existe");
            }
        }

        //Eliminar
        repository.delete(transaccion);
        return true;
    }

    public List<Transaccion> viewList(){
        //Verificar si no es nulo
        if(repository.getList() == null){
            throw new AppExceptions("No permitido, lista vacia.");
        }

        //Devolver lista
        return repository.getList();
    }



}
