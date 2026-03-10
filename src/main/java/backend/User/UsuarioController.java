package backend.User;

import backend.Exceptions.AppExceptions;
import backend.Transaction.TransaccionService;

import java.util.List;

public class UsuarioController {
    UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //Guardar
    public void save(String name, String password, String user){
        service.save(name, password, user);
    }

    //Eliminar
    public void delete(String id){
        try {
            int idUser = Integer.parseInt(id.trim());

            service.delete(idUser);
        }catch (NumberFormatException e){
            throw new AppExceptions("ID invalido, debe ser un numero entero.");
        }
    }

    //Buscar por id
    public Usuario search(String id){
        try {
            int idUser = Integer.parseInt(id.trim());
            return service.searchById(idUser);
        }catch (NumberFormatException e){
            throw new AppExceptions("ID invalido, debe ser un numero entero.");
        }
    }

}
