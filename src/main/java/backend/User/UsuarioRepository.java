package backend.User;

import backend.Exceptions.AppExceptions;
import backend.Transaction.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> listInMemory = new ArrayList<>();
    private int counter = 1;

    //Guardar en la lista
    public void save(Usuario usuario){
        listInMemory.add(new Usuario(counter++, usuario.getName(), usuario.getPassword(), usuario.getUser()));
    }

    //Eliminar de la lista
    public void delete(int id){
        boolean removed = listInMemory.removeIf(c -> c.getId() == id);

        if(!removed){
            throw new AppExceptions("Categoria no encontrada.");
        }
    }

    //Buscar por id
    public Usuario searchByID(int id){
        for (Usuario usuario:listInMemory){
            if (usuario.getId() == id){
                return usuario;
            }
        }
        throw new AppExceptions("Usuario no existente.");
    }
}
