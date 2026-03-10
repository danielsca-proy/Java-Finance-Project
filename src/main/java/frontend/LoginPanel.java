package frontend;

import backend.Exceptions.AppExceptions;
import backend.Session.UsuarioLogueado;
import backend.User.Usuario;
import backend.User.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private UsuarioController usuarioController;
    private MainFrame frame;

    public LoginPanel(UsuarioController usuarioController, MainFrame frame) {
        this.usuarioController = usuarioController;
        this.frame = frame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titulo, gbc);

        // Campo ID
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("ID de usuario:"), gbc);

        JTextField campoId = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        add(campoId, gbc);

        // Botón login
        JButton btnLogin = new JButton("Ingresar");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnLogin, gbc);

        // Botón registrarse
        JButton btnRegistrar = new JButton("Crear cuenta");
        gbc.gridy = 3;
        add(btnRegistrar, gbc);

        // ── Acción login ──
        btnLogin.addActionListener(e -> {
            try {
                Usuario usuario = usuarioController.search(campoId.getText().trim());
                UsuarioLogueado.login(usuario);
                frame.mostrarDashboard();
            } catch (AppExceptions ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ── Acción registrar ──
        btnRegistrar.addActionListener(e -> {
            // Abre un diálogo para crear cuenta
            JTextField campoNombre = new JTextField(15);
            JTextField campoUser = new JTextField(15);
            JPasswordField campoPassword = new JPasswordField(15);

            JPanel panelRegistro = new JPanel(new GridLayout(3, 2, 5, 5));
            panelRegistro.add(new JLabel("Nombre:"));
            panelRegistro.add(campoNombre);
            panelRegistro.add(new JLabel("Usuario:"));
            panelRegistro.add(campoUser);
            panelRegistro.add(new JLabel("Contraseña:"));
            panelRegistro.add(campoPassword);

            int resultado = JOptionPane.showConfirmDialog(
                    this, panelRegistro, "Crear cuenta",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (resultado == JOptionPane.OK_OPTION) {
                try {
                    String password = new String(campoPassword.getPassword());
                    usuarioController.save(campoNombre.getText(), password, campoUser.getText());
                    JOptionPane.showMessageDialog(this, "Cuenta creada. Ya podés iniciar sesión con tu ID.");
                } catch (AppExceptions ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
