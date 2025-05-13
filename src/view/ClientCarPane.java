package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Insets;
import java.awt.Window;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.VehiculoController;
import model.Cliente;
import model.Vehiculo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientCarPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfMatricula;
	JComboBox<Cliente> cbClientes;
	List<Cliente> clientesList;
	private JButton btnAdd;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public ClientCarPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		cbClientes = new JComboBox<Cliente>();
		GridBagConstraints gbc_cbClientes = new GridBagConstraints();
		gbc_cbClientes.insets = new Insets(0, 0, 5, 5);
		gbc_cbClientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbClientes.gridx = 5;
		gbc_cbClientes.gridy = 2;
		add(cbClientes, gbc_cbClientes);
		
		JLabel lblNewLabel_1 = new JLabel("Matrícula del coche a introducir");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		tfMatricula = new JTextField();
		GridBagConstraints gbc_tfMatricula = new GridBagConstraints();
		gbc_tfMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_tfMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMatricula.gridx = 5;
		gbc_tfMatricula.gridy = 4;
		add(tfMatricula, gbc_tfMatricula);
		tfMatricula.setColumns(10);
		
		btnAdd = new JButton("Añadir coche");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = tfMatricula.getText();
				Vehiculo v = VehiculoController.findVehiculoByMatricula(matricula);
				if (v != null) {
					addVehiculoToCliente(v);
				} else {
					Vehiculo veh = new Vehiculo();
					veh.setMatricula(matricula);
					VehiculoController.insertar(veh);
					addVehiculoToCliente(veh);
				}
			}

			
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 9;
		add(btnAdd, gbc_btnAdd);
		
		btnNewButton = new JButton("Cancelar operación");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 9;
		add(btnNewButton, gbc_btnNewButton);
		
		clientesList = ClienteController.findAll();
		
		for (Cliente c : clientesList) {
			cbClientes.addItem(c);
		}

	}
	
	private void addVehiculoToCliente(Vehiculo v) {
		Cliente c = (Cliente) cbClientes.getSelectedItem();
		List<Vehiculo> list = c.getVehiculos();
		list.add(v);
		if (ClienteController.update(c)) {
			JOptionPane.showMessageDialog(null, "Actualización exitosa", "Información de la actualización",
					JOptionPane.INFORMATION_MESSAGE, null);

			limpiarCampos();

		} else {
			JOptionPane.showMessageDialog(null, "Error introduciendo el coche. Inténtelo de nuevo.",
					"Información de la actualización", JOptionPane.ERROR_MESSAGE, null);
		}
	}

	private void limpiarCampos() {
		cbClientes.setSelectedIndex(-1);
		tfMatricula.setText("");
		
	}

}
