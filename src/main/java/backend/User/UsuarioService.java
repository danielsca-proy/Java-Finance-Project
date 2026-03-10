package backend.User;

import backend.Exceptions.AppExceptions;
import backend.Transaction.Transaccion;
import backend.Validator.PasswordValidator;
import backend.Validator.UserValidator;

import java.util.List;

public class UsuarioService{
    UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //Guardar
    public void save(String name, String password, String user){
        if (name == null || name.isBlank()) throw new AppExceptions("Nombre invalido");
        if (password == null || password.isBlank()) throw new AppExceptions("Contraseña invalida");
        if (user == null || user.isBlank()) throw new AppExceptions("Usuario invalido");
        if (!UserValidator.isValidUser(user)) throw new AppExceptions("Usuario invalido");
        if (!PasswordValidator.isValidPassword(password)) throw new AppExceptions("Contraseña invalida");

        repository.save(new Usuario(name, password, user));
    }

    //Eliminar
    public void delete(int id){
        if(id <= 0) throw new AppExceptions("id invalido.");
        repository.delete(id);
    }


    //Buscar por id
    public Usuario searchById(int id){
        if (id <= 0) throw new AppExceptions("id invalido.");
        return repository.searchByID(id);
    }

}
