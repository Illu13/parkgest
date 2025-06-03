package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LocalidadController;
import controller.LoginController;
import controller.TarifaController;
import model.Localidad;
import model.Tarifa;
import model.Usuario;
import utils.Apariencia;

import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JPasswordField tfPass;
	
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 358);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		
		JMenuItem miCreacionClientes = new JMenuItem("Añadir clientes");
		mnNewMenu.add(miCreacionClientes);
		
		JMenuItem miGestionClientes = new JMenuItem("Gestionar clientes");
		mnNewMenu.add(miGestionClientes);
		
		JMenu mnNewMenu_3 = new JMenu("Coches");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmAddCar = new JMenuItem("Añadir coche a cliente");
		mnNewMenu_3.add(mntmAddCar);
		
		JMenuItem mntmVerCochesClientes = new JMenuItem("Ver coches de los clientes");
		mnNewMenu_3.add(mntmVerCochesClientes);
		
		JMenu mnNewMenu_1 = new JMenu("Registros del parking");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Añadir registro del parking");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("Facturas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Creación de facturas");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		List<Tarifa> tarifaList = TarifaController.findAll();
		List<Localidad> localidadList = LocalidadController.findAll();
		
		
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Creación de facturas");
				// Introducimos el panel creado sobre el di�logo
				dialogo.setContentPane(new CreacionFacturasPane());
				// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben y el lugar adecuado
				dialogo.pack();
				// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
				dialogo.setModal(true);
				// Centro el di�logo en pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
						(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
				// Muestro el di�logo en pantalla
				dialogo.setVisible(true);
		    }
		});
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Creación de registros de parking");
				// Introducimos el panel creado sobre el di�logo
				dialogo.setContentPane(new ParkingRegistroCreate());
				// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben y el lugar adecuado
				dialogo.pack();
				// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
				dialogo.setModal(true);
				// Centro el di�logo en pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
						(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
				// Muestro el di�logo en pantalla
				dialogo.setVisible(true);
		    }
		});
		
		miCreacionClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Creación de clientes");
				// Introducimos el panel creado sobre el di�logo
				dialogo.setContentPane(new ClientCreationPane(localidadList, tarifaList));
				// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben y el lugar adecuado
				dialogo.pack();
				// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
				dialogo.setModal(true);
				// Centro el di�logo en pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
						(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
				// Muestro el di�logo en pantalla
				dialogo.setVisible(true);
		    }
		});
		
		mntmVerCochesClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Edición de coches de los clientes");
				// Introducimos el panel creado sobre el di�logo
				dialogo.setContentPane(new SeeClientCarPane());
				// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben y el lugar adecuado
				dialogo.pack();
				// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
				dialogo.setModal(true);
				// Centro el di�logo en pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
						(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
				// Muestro el di�logo en pantalla
				dialogo.setVisible(true);
		    }
		});
		
		miGestionClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Edición de clientes");
				// Introducimos el panel creado sobre el di�logo
				dialogo.setContentPane(new ClientEditPane(localidadList, tarifaList));
				// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben y el lugar adecuado
				dialogo.pack();
				// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
				dialogo.setModal(true);
				// Centro el di�logo en pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
						(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
				// Muestro el di�logo en pantalla
				dialogo.setVisible(true);
		    }
		});
		
		mntmAddCar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Añadir coche a cliente");
				// Introducimos el panel creado sobre el di�logo
				dialogo.setContentPane(new ClientCarPane());
				// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
				// deben y el lugar adecuado
				dialogo.pack();
				// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
				dialogo.setModal(true);
				// Centro el di�logo en pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
						(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
				// Muestro el di�logo en pantalla
				dialogo.setVisible(true);
		    }
		});
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
				
				tfNombre = new JTextField();
				GridBagConstraints gbc_tfNombre = new GridBagConstraints();
				gbc_tfNombre.gridwidth = 4;
				gbc_tfNombre.insets = new Insets(0, 0, 5, 5);
				gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfNombre.gridx = 1;
				gbc_tfNombre.gridy = 0;
				contentPane.add(tfNombre, gbc_tfNombre);
				tfNombre.setColumns(10);
		
				JLabel lblNewLabel_1 = new JLabel("Contraseña");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnLogin = new JButton("Identificarse");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario();
				u.setNombre(tfNombre.getText());
				u.setPass(tfPass.getText());
				
				if (LoginController.findAll(u) != null) {
					menuBar.setVisible(true);
					tfNombre.setVisible(false);
					tfPass.setVisible(false);
					btnLogin.setVisible(false);
					lblNewLabel.setVisible(false);
					lblNewLabel_1.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Error: ese usuario no existe o la contraseña no es correcta,"
							+ " vuelva a intentarlo", "Información de la identificación",
							JOptionPane.ERROR_MESSAGE, null);
				}
				

			}
		});
		
		
		tfPass = new JPasswordField();
		GridBagConstraints gbc_tfPass = new GridBagConstraints();
		gbc_tfPass.gridwidth = 4;
		gbc_tfPass.insets = new Insets(0, 0, 5, 5);
		gbc_tfPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPass.gridx = 1;
		gbc_tfPass.gridy = 1;
		contentPane.add(tfPass, gbc_tfPass);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 2;
		contentPane.add(btnLogin, gbc_btnLogin);
	}

}
