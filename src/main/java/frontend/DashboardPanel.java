package frontend;

import backend.Category.Categoria;
import backend.Category.CategoriaController;
import backend.Exceptions.AppExceptions;
import backend.Session.UsuarioLogueado;
import backend.Transaction.Transaccion;
import backend.Transaction.TransaccionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {

    private CategoriaController categoriaController;
    private TransaccionController transaccionController;
    private MainFrame frame;

    public DashboardPanel(CategoriaController categoriaController,
                          TransaccionController transaccionController,
                          MainFrame frame) {
        this.categoriaController = categoriaController;
        this.transaccionController = transaccionController;
        this.frame = frame;

        setLayout(new BorderLayout());

        // ── Header ──
        JPanel header = new JPanel(new BorderLayout());
        JLabel lblBienvenida = new JLabel(
                "Bienvenido, " + UsuarioLogueado.getUsuario().getName(),
                SwingConstants.LEFT
        );
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

        JButton btnLogout = new JButton("Cerrar sesión");
        btnLogout.addActionListener(e -> {
            UsuarioLogueado.logout();
            frame.mostrarLogin();
        });

        header.add(lblBienvenida, BorderLayout.WEST);
        header.add(btnLogout, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // ── Tabs ──
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Transacciones", crearPanelTransacciones());
        tabs.addTab("Categorías", crearPanelCategorias());
        add(tabs, BorderLayout.CENTER);
    }

    // ══════════════════════════════════════════
    //  TAB TRANSACCIONES
    // ══════════════════════════════════════════
    private JPanel crearPanelTransacciones() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        // Tabla
        String[] columnas = {"ID", "Categoría", "Tipo", "Fecha", "Resumen"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modelo);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Formulario agregar
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JTextField campoCategoria = new JTextField(10);
        JTextField campoFecha = new JTextField("dd/MM/yyyy", 10);
        JTextField campoResumen = new JTextField(15);
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("↻ Refrescar");

        form.add(new JLabel("Categoría:"));
        form.add(campoCategoria);
        form.add(new JLabel("Fecha:"));
        form.add(campoFecha);
        form.add(new JLabel("Resumen:"));
        form.add(campoResumen);
        form.add(btnAgregar);
        form.add(btnEliminar);
        form.add(btnRefrescar);
        panel.add(form, BorderLayout.SOUTH);

        // Cargar tabla
        Runnable cargarTabla = () -> {
            modelo.setRowCount(0);
            try {
                String idUsuario = String.valueOf(UsuarioLogueado.getUsuario().getId());
                List<Transaccion> lista = transaccionController.findAll(idUsuario);
                for (Transaccion t : lista) {
                    modelo.addRow(new Object[]{
                            t.getId(),
                            t.getCategory().getName(),
                            t.getCategory().getType(),
                            t.getDate(),
                            t.getResume()
                    });
                }
            } catch (AppExceptions ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        cargarTabla.run();

        // ── Agregar transacción ──
        btnAgregar.addActionListener(e -> {
            try {
                Categoria cat = categoriaController.findByName(campoCategoria.getText());
                transaccionController.saveTransaction(
                        cat,
                        UsuarioLogueado.getUsuario(),
                        campoResumen.getText(),
                        campoFecha.getText()
                );
                cargarTabla.run();
                campoCategoria.setText("");
                campoFecha.setText("dd/MM/yyyy");
                campoResumen.setText("");
            } catch (AppExceptions ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ── Eliminar transacción ──
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccioná una transacción de la tabla.");
                return;
            }
            String id = modelo.getValueAt(fila, 0).toString();
            try {
                transaccionController.deleteTransaction(id);
                cargarTabla.run();
            } catch (AppExceptions ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRefrescar.addActionListener(e -> cargarTabla.run());

        return panel;
    }

    // ══════════════════════════════════════════
    //  TAB CATEGORÍAS
    // ══════════════════════════════════════════
    private JPanel crearPanelCategorias() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        // Tabla
        String[] columnas = {"ID", "Nombre", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modelo);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Formulario
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JTextField campoNombre = new JTextField(10);
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"INCOME", "EXPENSE"});
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("↻ Refrescar");

        form.add(new JLabel("Nombre:"));
        form.add(campoNombre);
        form.add(new JLabel("Tipo:"));
        form.add(comboTipo);
        form.add(btnAgregar);
        form.add(btnEliminar);
        form.add(btnRefrescar);
        panel.add(form, BorderLayout.SOUTH);

        // Cargar tabla
        Runnable cargarTabla = () -> {
            modelo.setRowCount(0);
            for (Categoria c : categoriaController.findAll()) {
                modelo.addRow(new Object[]{c.getId(), c.getName(), c.getType()});
            }
        };

        cargarTabla.run();

        // ── Agregar categoría ──
        btnAgregar.addActionListener(e -> {
            try {
                categoriaController.save(campoNombre.getText(), (String) comboTipo.getSelectedItem());
                cargarTabla.run();
                campoNombre.setText("");
            } catch (AppExceptions ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ── Eliminar categoría ──
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccioná una categoría de la tabla.");
                return;
            }
            String nombre = modelo.getValueAt(fila, 1).toString();
            try {
                categoriaController.delete(nombre);
                cargarTabla.run();
            } catch (AppExceptions ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRefrescar.addActionListener(e -> cargarTabla.run());

        return panel;
    }
}
