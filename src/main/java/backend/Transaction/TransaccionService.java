package backend.Transaction;

import backend.Category.Categoria;
import backend.Exceptions.AppExceptions;
import backend.User.Usuario;

import java.time.LocalDate;
import java.util.List;

public class TransaccionService{
    TransaccionRepository repository;

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    public void save(Transaccion transaccion){
        repository.save(transaccion);
    }

    public void delete(Transaccion transaccion){
        repository.delete(transaccion);
    }

    public void findByRange(LocalDate first, LocalDate last){
        if (first == null || last == null) throw new AppExceptions("Fecha invalida.");


    }

    //Categoria category, Usuario user, String resume, LocalDate date

}
