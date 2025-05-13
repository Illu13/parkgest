package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LocalidadController;
import controller.TarifaController;
import model.Localidad;
import model.Tarifa;
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

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
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
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 361);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Ver registros del parking");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("Facturas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Creación de facturas");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		List<Tarifa> tarifaList = TarifaController.findAll();
		List<Localidad> localidadList = LocalidadController.findAll();
		
		miCreacionClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				// El usuario no puede redimensionar el di�logo
				dialogo.setResizable(true);
				// t�tulo del d�alogo
				dialogo.setTitle("Gestión marcas");
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
				dialogo.setTitle("Gestión marcas");
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
				dialogo.setTitle("Gestión marcas");
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
				dialogo.setTitle("Gestión marcas");
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
		gbl_contentPane.columnWidths = new int[]{0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	}

}
