import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Promedios {
    public JPanel panel1;
    private JTable tabla_de_notas;
    private JButton boton_de_volver;

    public Promedios() {
        configurarTabla();
        cargarDatosEnTabla();

        boton_de_volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurarTabla();
                cargarDatosEnTabla();
                JFrame frame = new JFrame("Menú Principal");
                frame.setContentPane(new Registro().panel1);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1024, 768);
                frame.setVisible(true);


                JFrame promediosFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                promediosFrame.dispose();
            }
        });
    }

    private void configurarTabla() {
        String[] columnas = {"ID", "Cédula", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Nota 5", "Promedio", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tabla_de_notas.setModel(modelo);
        // Deshabilitar la edición de celdas
        tabla_de_notas.setDefaultEditor(Object.class, null);
    }

    private void cargarDatosEnTabla() {
        String query = "SELECT * FROM estudiantes";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            DefaultTableModel modelo = (DefaultTableModel) tabla_de_notas.getModel();
            modelo.setRowCount(0);

            // Recorrer los resultados de la consulta y añadirlos a la tabla
            while (rs.next()) {
                double nota1 = rs.getDouble("estudiante1");
                double nota2 = rs.getDouble("estudiante2");
                double nota3 = rs.getDouble("estudiante3");
                double nota4 = rs.getDouble("estudiante4");
                double nota5 = rs.getDouble("estudiante5");

                // Calcular el promedio
                double promedio = (nota1 + nota2 + nota3 + nota4 + nota5) / 5;
                double sumaa = (nota1 + nota2 + nota3 + nota4 + nota5) ;

                // Determinar el estado del estudiante
                String estado = sumaa >= 60 ? "Aprueba" : "Reprueba";

                Object[] fila = new Object[]{
                        rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        nota1,
                        nota2,
                        nota3,
                        nota4,
                        nota5,
                        promedio,
                        estado
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel1, "Error al cargar los datos: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel1, "Error inesperado: " + e.getMessage());
        }
    }


}
