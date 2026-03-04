package backend.User;

import backend.Exceptions.AppExceptions;
import backend.Transaction.Transaccion;

import java.util.List;

public class UsuarioService{

    UsuarioRepository repository = new UsuarioRepository();

    public boolean add(Usuario usuario){
        //Verificar si no es nulo
        if(usuario == null) {
            throw new AppExceptions("Nuevo usuario Invalido. ");
        }

        //Agregar
        repository.save(usuario);
        return true;
    }

    public boolean eliminate(Usuario usuario){
        //Verificar si no es nulo
        if(usuario == null) throw new AppExceptions("El usuario a eliminar es nulo. ");

        //Verificar si esta en la lista
        for(Usuario usuario1 : repository.getList()){
            if(!usuario1.equals(usuario)){
                throw new AppExceptions("El usuario no existe");
            }
        }

        //Eliminar
        repository.delete(usuario);
        return true;
    }

    public List<Usuario> viewList(){
        //Verificar si no es nulo
        if(repository.getList() == null){
            throw new AppExceptions("No permitido, lista vacia.");
        }

        //Devolver lista
        return repository.getList();
    }
}
