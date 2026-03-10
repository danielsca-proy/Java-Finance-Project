package frontend;

import backend.Category.CategoriaController;
import backend.Transaction.TransaccionController;
import backend.User.UsuarioController;

import javax.swing.*;

public class MainFrame extends JFrame {

    private UsuarioController usuarioController;
    private CategoriaController categoriaController;
    private TransaccionController transaccionController;

    public MainFrame(UsuarioController usuarioController,
                     CategoriaController categoriaController,
                     TransaccionController transaccionController) {

        this.usuarioController = usuarioController;
        this.categoriaController = categoriaController;
        this.transaccionController = transaccionController;

        setTitle("Gestor de Finanzas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // centra la ventana

        // Arranca mostrando el panel de login
        mostrarLogin();

        setVisible(true);
    }

    // Muestra el panel de login
    public void mostrarLogin() {
        setContentPane(new LoginPanel(usuarioController, this));
        revalidate();
        repaint();
    }

    // Muestra el dashboard tras loguearse
    public void mostrarDashboard() {
        setContentPane(new DashboardPanel(categoriaController, transaccionController, this));
        revalidate();
        repaint();
    }
}
