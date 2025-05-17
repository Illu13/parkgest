package view;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.RegistroParkingController;
import model.Cliente;


import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreacionFacturasPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JComboBox<Cliente> cbCliente;
	List<Cliente> clienteList;

	/**
	 * Create the panel.
	 */
	public CreacionFacturasPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
		
		cbCliente = new JComboBox<Cliente>();
		GridBagConstraints gbc_cbCliente = new GridBagConstraints();
		gbc_cbCliente.insets = new Insets(0, 0, 5, 5);
		gbc_cbCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCliente.gridx = 6;
		gbc_cbCliente.gridy = 4;
		add(cbCliente, gbc_cbCliente);
		
		JButton btnCrearFactura = new JButton("Crear factura en PDF");
		btnCrearFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = (Cliente) cbCliente.getSelectedItem();
				RegistroParkingController.crearFactura(c);
			}
		});
		GridBagConstraints gbc_btnCrearFactura = new GridBagConstraints();
		gbc_btnCrearFactura.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrearFactura.gridx = 1;
		gbc_btnCrearFactura.gridy = 9;
		add(btnCrearFactura, gbc_btnCrearFactura);
		
		JButton btnCancelar = new JButton("Cancelar operación");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 9;
		add(btnCancelar, gbc_btnCancelar);
		
		clienteList = ClienteController.findAll();
		
		for(Cliente c : clienteList) {
			cbCliente.addItem(c);
		}

	}

}
