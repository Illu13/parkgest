package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.TarifaController;
import model.Cliente;
import model.Localidad;
import model.Tarifa;
import javax.swing.JButton;

public class ClientEditPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNombre;
	private JTextField tfCorreo;
	private JTextField tfTelefono;
	private JTextField tfDni;
	JComboBox<Tarifa> cbTarifa;
	JComboBox<Localidad> cbLocalidad;
	JComboBox<Cliente> cbCliente;
	List<Localidad> localidadList;
	List<Tarifa> tarifaList;
	List<Cliente> clienteList;

	/**
	 * Create the panel.
	 */
	public ClientEditPane(List<Localidad> localidadList, List<Tarifa> tarifaList) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Cliente");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		cbCliente = new JComboBox();
		GridBagConstraints gbc_cbClientes = new GridBagConstraints();
		gbc_cbClientes.gridwidth = 4;
		gbc_cbClientes.insets = new Insets(0, 0, 5, 5);
		gbc_cbClientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbClientes.gridx = 7;
		gbc_cbClientes.gridy = 2;
		add(cbCliente, gbc_cbClientes);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		tfNombre = new JTextField();
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.gridwidth = 4;
		gbc_tfNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNombre.gridx = 7;
		gbc_tfNombre.gridy = 4;
		add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Correo");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		tfCorreo = new JTextField();
		GridBagConstraints gbc_tfCorreo = new GridBagConstraints();
		gbc_tfCorreo.gridwidth = 4;
		gbc_tfCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_tfCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCorreo.gridx = 7;
		gbc_tfCorreo.gridy = 5;
		add(tfCorreo, gbc_tfCorreo);
		tfCorreo.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Teléfono");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 6;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		tfTelefono = new JTextField();
		GridBagConstraints gbc_tfTelefono = new GridBagConstraints();
		gbc_tfTelefono.gridwidth = 4;
		gbc_tfTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_tfTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelefono.gridx = 7;
		gbc_tfTelefono.gridy = 6;
		add(tfTelefono, gbc_tfTelefono);
		tfTelefono.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("DNI");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 7;
		add(lblNewLabel_4, gbc_lblNewLabel_4);

		tfDni = new JTextField();
		GridBagConstraints gbc_tfDni = new GridBagConstraints();
		gbc_tfDni.gridwidth = 4;
		gbc_tfDni.insets = new Insets(0, 0, 5, 5);
		gbc_tfDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDni.gridx = 7;
		gbc_tfDni.gridy = 7;
		add(tfDni, gbc_tfDni);
		tfDni.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Localidad");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 8;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		cbLocalidad = new JComboBox();
		GridBagConstraints gbc_cbLocalidad = new GridBagConstraints();
		gbc_cbLocalidad.gridwidth = 4;
		gbc_cbLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_cbLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbLocalidad.gridx = 7;
		gbc_cbLocalidad.gridy = 8;
		add(cbLocalidad, gbc_cbLocalidad);

		JLabel lblNewLabel_6 = new JLabel("Tarifa");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 9;
		add(lblNewLabel_6, gbc_lblNewLabel_6);

		cbTarifa = new JComboBox();
		GridBagConstraints gbc_cbTarifa = new GridBagConstraints();
		gbc_cbTarifa.gridwidth = 4;
		gbc_cbTarifa.insets = new Insets(0, 0, 5, 5);
		gbc_cbTarifa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTarifa.gridx = 7;
		gbc_cbTarifa.gridy = 9;
		add(cbTarifa, gbc_cbTarifa);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroTelefono = 0;

				try {
					numeroTelefono = Integer.parseInt(tfTelefono.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "El teléfono puesto no es un número, revíselo", "Error",
							JOptionPane.ERROR_MESSAGE, null);
					return;
				}

				Cliente c = (Cliente) cbCliente.getSelectedItem();
				c.setNombre(tfNombre.getText());
				c.setDni(tfDni.getText());
				c.setEmail(tfCorreo.getText());
				c.setTelefono(numeroTelefono + "");
				c.setLocalidad((Localidad) cbLocalidad.getSelectedItem());
				c.setTarifa((Tarifa) cbTarifa.getSelectedItem());
				if (ClienteController.update(c)) {
					JOptionPane.showMessageDialog(null, "Actualización exitosa", "Información de la actualización",
							JOptionPane.INFORMATION_MESSAGE, null);

					limpiarCampos();

					recargarClientes();
				} else {
					JOptionPane.showMessageDialog(null, "Error: Comprueba si el DNI introducido lo tiene otro cliente",
							"Información de la actualización", JOptionPane.ERROR_MESSAGE, null);
				}

			}


		});
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 3;
		gbc_btnEditar.gridy = 12;
		add(btnEditar, gbc_btnEditar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = (Cliente) cbCliente.getSelectedItem();
				if (ClienteController.eliminar(c)) {
					JOptionPane.showMessageDialog(null, "Eliminación exitosa", "Información de la eliminación",
							JOptionPane.INFORMATION_MESSAGE, null);
					limpiarCampos();

				} else {
					JOptionPane.showMessageDialog(null, "Ha habido un error en la eliminación",
							"Información de la eliminación", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		btnBorrar.setEnabled(false);
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrar.gridx = 7;
		gbc_btnBorrar.gridy = 12;
		add(btnBorrar, gbc_btnBorrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.SOUTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 10;
		gbc_btnCancelar.gridy = 12;
		add(btnCancelar, gbc_btnCancelar);

		recargarClientes();
		for (Tarifa t : tarifaList) {
			cbTarifa.addItem(t);
		}
		for (Localidad l : localidadList) {
			cbLocalidad.addItem(l);
		}

		for (Cliente c : clienteList) {
			cbCliente.addItem(c);
		}

		cbCliente.setSelectedIndex(-1);

		cbCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente c = (Cliente) cbCliente.getSelectedItem();

					tfNombre.setText(c.getNombre());
					tfDni.setText(c.getDni());
					tfTelefono.setText(c.getTelefono());
					tfCorreo.setText(c.getEmail());

					for (int i = 0; i < tarifaList.size(); i++) {
						if (tarifaList.get(i).getId() == c.getTarifa().getId()) {
							cbTarifa.setSelectedIndex(i);
						}
					}

					for (int i = 0; i < localidadList.size(); i++) {
						if (localidadList.get(i).getId() == c.getLocalidad().getId()) {
							cbLocalidad.setSelectedIndex(i);
						}
					}
					btnBorrar.setEnabled(true);
					btnEditar.setEnabled(true);
				} catch (NullPointerException exc) {
					btnBorrar.setEnabled(false);
					btnEditar.setEnabled(false);
				}
			}

		});

	}

	private void recargarClientes() {
		clienteList = ClienteController.findAll();
	}
	
	private void limpiarCampos() {
		tfNombre.setText("");
		tfDni.setText("");
		tfCorreo.setText("");
		tfTelefono.setText("");
		cbLocalidad.setSelectedIndex(0);
		cbTarifa.setSelectedIndex(0);
		cbCliente.setSelectedIndex(-1);
	}

}
