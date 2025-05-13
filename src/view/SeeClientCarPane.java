package view;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.VehiculoController;
import model.Cliente;
import model.Vehiculo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;

public class SeeClientCarPane extends JPanel {
	JComboBox<Cliente> cbClientes;
	List<Cliente> clientesList;
	JComboBox<Vehiculo> cbVehiculo;
	List<Vehiculo> vehiculosList;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SeeClientCarPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Clientes");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		cbClientes = new JComboBox<Cliente>();
		GridBagConstraints gbc_cbClientes = new GridBagConstraints();
		gbc_cbClientes.insets = new Insets(0, 0, 5, 5);
		gbc_cbClientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbClientes.gridx = 4;
		gbc_cbClientes.gridy = 2;
		add(cbClientes, gbc_cbClientes);

		JLabel lblNewLabel_1 = new JLabel("Coche de ese cliente");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 5;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		cbVehiculo = new JComboBox<Vehiculo>();
		GridBagConstraints gbc_cbCoches = new GridBagConstraints();
		gbc_cbCoches.insets = new Insets(0, 0, 5, 5);
		gbc_cbCoches.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCoches.gridx = 4;
		gbc_cbCoches.gridy = 5;
		add(cbVehiculo, gbc_cbCoches);

		JButton btnEliminar = new JButton("Desvíncular coche");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = (Cliente) cbClientes.getSelectedItem();
				Vehiculo v = (Vehiculo) cbVehiculo.getSelectedItem();
				List<Vehiculo> list = c.getVehiculos();
				list.remove(v);
				if (ClienteController.update(c)) {
					JOptionPane.showMessageDialog(null, "Actualización exitosa", "Información de la actualización",
							JOptionPane.INFORMATION_MESSAGE, null);
					cbVehiculo.removeAllItems();
					cbClientes.setSelectedIndex(-1);

				} else {
					JOptionPane.showMessageDialog(null, "Error desvinculando el coche. Inténtelo de nuevo.",
							"Información de la actualización", JOptionPane.ERROR_MESSAGE, null);

				}
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 9;
		add(btnEliminar, gbc_btnEliminar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 9;
		add(btnCancelar, gbc_btnCancelar);
		clientesList = ClienteController.findAll();

		for (Cliente c : clientesList) {
			cbClientes.addItem(c);
		}
		cbClientes.setSelectedIndex(-1);

		cbClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbVehiculo.removeAllItems();
				if (cbClientes.getSelectedIndex() != -1) {
					Cliente c = (Cliente) cbClientes.getSelectedItem();
					List<Vehiculo> vehiculos = c.getVehiculos();

					for (Vehiculo v : vehiculos) {
						cbVehiculo.addItem(v);
					}
				}
			}
		});

	}

}
