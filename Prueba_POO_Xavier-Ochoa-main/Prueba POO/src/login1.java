import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login1 {
    private JTextField usuario1;
    private JPasswordField passwordField1;
    private JButton validarDatosButton;
    public JPanel mainPanel;
    private JLabel mensaje_validacion;

    public login1() {
        validarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = usuario1.getText();
                String contraseña = new String(passwordField1.getPassword());

                if (DatabaseConnector.validarUsuarioPorCorreo(correo, contraseña)) {
                    mensaje_validacion.setText("Acceso correcto");
                    abrirMenu();
                } else {
                    mensaje_validacion.setText("Correo o contraseña incorrectos");
                }
            }
        });
    }

    private void abrirMenu() {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new Registro().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setVisible(true);


        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        loginFrame.dispose(); // Cierra la ventana de login
    }
}
