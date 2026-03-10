import backend.Category.CategoriaController;
import backend.Category.CategoriaRepository;
import backend.Category.CategoriaService;
import backend.Transaction.TransaccionController;
import backend.Transaction.TransaccionRepository;
import backend.Transaction.TransaccionService;
import backend.User.UsuarioController;
import backend.User.UsuarioRepository;
import backend.User.UsuarioService;
import frontend.MainFrame;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        // 1. Armás las capas de cada módulo
        CategoriaRepository categoriaRepo = new CategoriaRepository();
        CategoriaService categoriaService = new CategoriaService(categoriaRepo);
        CategoriaController categoriaController = new CategoriaController(categoriaService);

        UsuarioRepository usuarioRepo = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepo);
        UsuarioController usuarioController = new UsuarioController(usuarioService);

        TransaccionRepository transaccionRepo = new TransaccionRepository();
        TransaccionService transaccionService = new TransaccionService(transaccionRepo);
        TransaccionController transaccionController = new TransaccionController(transaccionService);

        // 2. Arrancás la ventana principal pasándole los controllers
        SwingUtilities.invokeLater(()-> {
            new MainFrame(usuarioController, categoriaController, transaccionController);
        });
    }
}