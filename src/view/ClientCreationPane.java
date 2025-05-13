package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.LocalidadController;
import controller.TarifaController;
import model.Cliente;
import model.Localidad;
import model.Tarifa;

import java.awt.Insets;
import java.awt.Window;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientCreationPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNombre;
	private JTextField tfEmail;
	private JTextField tfTelefono;
	private JTextField tfDni;
	JComboBox<Tarifa> cbTarifa;
	JComboBox<Localidad> cbLocalidad;

	/**
	 * Create the panel.
	 */
	public ClientCreationPane(List<Localidad> localidadList, List<Tarifa> tarifaList) {
		setSize(490, 331);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Nombre del cliente:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		tfNombre = new JTextField();
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNombre.gridx = 4;
		gbc_tfNombre.gridy = 2;
		add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Correo eletrónico del cliente");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		tfEmail = new JTextField();
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.insets = new Insets(0, 0, 5, 5);
		gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail.gridx = 4;
		gbc_tfEmail.gridy = 3;
		add(tfEmail, gbc_tfEmail);
		tfEmail.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Teléfono del cliente");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 4;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		tfTelefono = new JTextField();
		GridBagConstraints gbc_tfTelefono = new GridBagConstraints();
		gbc_tfTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_tfTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelefono.gridx = 4;
		gbc_tfTelefono.gridy = 4;
		add(tfTelefono, gbc_tfTelefono);
		tfTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("DNI del cliente");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 5;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		tfDni = new JTextField();
		GridBagConstraints gbc_tfDni = new GridBagConstraints();
		gbc_tfDni.insets = new Insets(0, 0, 5, 5);
		gbc_tfDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDni.gridx = 4;
		gbc_tfDni.gridy = 5;
		add(tfDni, gbc_tfDni);
		tfDni.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Localidad del cliente");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 6;
		add(lblNewLabel_4, gbc_lblNewLabel_4);

		cbLocalidad = new JComboBox();
		GridBagConstraints gbc_cbLocalidad = new GridBagConstraints();
		gbc_cbLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_cbLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbLocalidad.gridx = 4;
		gbc_cbLocalidad.gridy = 6;
		add(cbLocalidad, gbc_cbLocalidad);

		JLabel lblNewLabel_5 = new JLabel("Tarifa del cliente");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 7;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		cbTarifa = new JComboBox();
		GridBagConstraints gbc_cbTarifa = new GridBagConstraints();
		gbc_cbTarifa.insets = new Insets(0, 0, 5, 5);
		gbc_cbTarifa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTarifa.gridx = 4;
		gbc_cbTarifa.gridy = 7;
		add(cbTarifa, gbc_cbTarifa);

		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroTelefono = 0;
				try {
					numeroTelefono = Integer.parseInt(tfTelefono.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "El teléfono puesto no es un número, revíselo", "Error",
							JOptionPane.ERROR_MESSAGE, null);
					return;
				}

				Cliente c = new Cliente();
				c.setNombre(tfNombre.getText());
				c.setDni(tfDni.getText());
				c.setEmail(tfEmail.getText());
				c.setTelefono(numeroTelefono + "");
				c.setLocalidad((Localidad) cbLocalidad.getSelectedItem());
				c.setTarifa((Tarifa) cbTarifa.getSelectedItem());
				if (ClienteController.insertar(c)) {
					JOptionPane.showMessageDialog(null, "Inserción exitosa", "Información de la inserción",
							JOptionPane.INFORMATION_MESSAGE, null);

					tfNombre.setText("");
					tfDni.setText("");
					tfEmail.setText("");
					tfTelefono.setText("");
					cbLocalidad.setSelectedIndex(0);
					cbTarifa.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Comprueba si el DNI introducido lo tiene otro cliente", "Información de la inserción",
							JOptionPane.ERROR_MESSAGE, null);
				}

			}
		});
		GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
		gbc_btnAnadir.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnadir.gridx = 2;
		gbc_btnAnadir.gridy = 10;
		add(btnAnadir, gbc_btnAnadir);

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
		gbc_btnCancelar.gridy = 10;
		add(btnCancelar, gbc_btnCancelar);

		for (Tarifa t : tarifaList) {
			cbTarifa.addItem(t);
		}
		for (Localidad l : localidadList) {
			cbLocalidad.addItem(l);
		}

	}

}
