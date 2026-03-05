package backend.Transaction;

import backend.Category.Categoria;
import backend.Exceptions.AppExceptions;
import backend.User.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaccionService{
    TransaccionRepository repository;

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    //Guarda
    public void save(Categoria categoria, Usuario usuario, String resume, LocalDate fecha){
        //Verificamos que no sean nulos
        if(fecha == null) throw new AppExceptions("Fecha invalida.");
        if(categoria == null) throw new AppExceptions("Categoria invalida.");
        if(usuario == null) throw new AppExceptions("Usuario invalido.");
        if(resume == null || resume.isBlank()) throw new AppExceptions("Resumen invalido. ");
        //Agregamos la transaccion
        repository.save(new Transaccion(categoria, usuario, resume, fecha));
    }

    //Elimina
    public void delete(int id){
        if(id <= 0) throw new AppExceptions("Id invalido.");
        //Eliminamos la transaccion
        repository.delete(id);
    }

    //Devuelve una lista con solo las transacciones que cumplan el rango de fechas
    public List<Transaccion> findByRange(LocalDate first, LocalDate last, int idUser){
        if (first == null || last == null) throw new AppExceptions("Fecha invalida.");
        return repository.findByRange(first, last, idUser);
    }

    //Buscar todos
    public List<Transaccion> findAll(int idUser){
        if(idUser <= 0) throw new AppExceptions("Id de usuario invalido.");
        return repository.findAllListByUser(idUser);
    }

}
