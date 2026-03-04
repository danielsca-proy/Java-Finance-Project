package backend;

import backend.User.*;
import backend.Transaction.*;
import backend.Category.*;
import backend.View.VistaLogin;
import backend.View.VistaMain;

public class Main {
    public static void main(String[] args) {
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        CategoriaController categoriaController = new CategoriaController(categoriaService);

        TransaccionRepository transaccionRepository = new TransaccionRepository();
        TransaccionService transaccionService = new TransaccionService(transaccionRepository);
        TransaccionController transaccionController = new TransaccionController(transaccionService);

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        UsuarioController usuarioController = new UsuarioController(usuarioService);
    }
}
