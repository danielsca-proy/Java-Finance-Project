package backend.User;

import backend.Exceptions.AppExceptions;
import backend.Transaction.TransaccionService;

import java.util.List;

public class UsuarioController {
    UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
}
