package backend.User;

import backend.Exceptions.AppExceptions;
import backend.Transaction.Transaccion;

import java.util.List;

public class UsuarioService{
    UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
}
