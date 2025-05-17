package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import controller.RegistroParkingController;
import controller.VehiculoController;
import model.RegistroParking;
import model.Vehiculo;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;

public class ParkingRegistroCreate extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfMatricula;

	/**
	 * Create the panel.
	 */
	public ParkingRegistroCreate() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Matrícula del coche");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		tfMatricula = new JTextField();
		GridBagConstraints gbc_tfMatricula = new GridBagConstraints();
		gbc_tfMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_tfMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMatricula.gridx = 3;
		gbc_tfMatricula.gridy = 2;
		add(tfMatricula, gbc_tfMatricula);
		tfMatricula.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Hora de entrada");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		JDateChooser dcEntrada = new JDateChooser();
		dcEntrada.setDateFormatString("yyyy/MM/dd HH:mm:ss");
		GridBagConstraints gbc_dcEntrada = new GridBagConstraints();
		gbc_dcEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_dcEntrada.fill = GridBagConstraints.BOTH;
		gbc_dcEntrada.gridx = 3;
		gbc_dcEntrada.gridy = 4;
		add(dcEntrada, gbc_dcEntrada);

		JLabel lblHoraDeSalida = new JLabel("Hora de salida");
		GridBagConstraints gbc_lblHoraDeSalida = new GridBagConstraints();
		gbc_lblHoraDeSalida.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraDeSalida.gridx = 2;
		gbc_lblHoraDeSalida.gridy = 6;
		add(lblHoraDeSalida, gbc_lblHoraDeSalida);

		JDateChooser dcSalida = new JDateChooser();
		dcSalida.setDateFormatString("yyyy/MM/dd HH:mm:ss");
		GridBagConstraints gbc_dcSalida = new GridBagConstraints();
		gbc_dcSalida.insets = new Insets(0, 0, 5, 5);
		gbc_dcSalida.fill = GridBagConstraints.BOTH;
		gbc_dcSalida.gridx = 3;
		gbc_dcSalida.gridy = 6;
		add(dcSalida, gbc_dcSalida);

		JLabel lblNewLabel_2 = new JLabel("Pagado");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 8;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		JCheckBox cbPagado = new JCheckBox("");
		GridBagConstraints gbc_cbPagado = new GridBagConstraints();
		gbc_cbPagado.insets = new Insets(0, 0, 5, 5);
		gbc_cbPagado.gridx = 3;
		gbc_cbPagado.gridy = 8;
		add(cbPagado, gbc_cbPagado);

		JButton btnAddRegistro = new JButton("Añadir registro");
		btnAddRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = tfMatricula.getText();
				boolean pagado = cbPagado.isSelected();
				RegistroParking rp = new RegistroParking();
				Vehiculo v = VehiculoController.findVehiculoByMatricula(matricula);
				if (v != null) {
					addParkingRegistry(dcEntrada, dcSalida, pagado, rp, v);

				} else {
					v = new Vehiculo();
					v.setMatricula(matricula);
					VehiculoController.insertar(v);
					addParkingRegistry(dcEntrada, dcSalida, pagado, rp, v);

				}

				if (RegistroParkingController.insertar(rp)) {
					JOptionPane.showMessageDialog(null, "Inserción exitosa", "El registro del parking se ha insertado satisfactoriamente",
							JOptionPane.INFORMATION_MESSAGE, null);
					tfMatricula.setText("");
					cbPagado.setSelected(false);	
				} else {
					JOptionPane.showMessageDialog(null, "Error: el registro del parking no se ha insertado correctamente", "Información de la inserción",
							JOptionPane.ERROR_MESSAGE, null);
				}
		
			}

		});
		GridBagConstraints gbc_btnAddRegistro = new GridBagConstraints();
		gbc_btnAddRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddRegistro.gridx = 2;
		gbc_btnAddRegistro.gridy = 9;
		add(btnAddRegistro, gbc_btnAddRegistro);

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
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 9;
		add(btnCancelar, gbc_btnCancelar);

	}

	private void addParkingRegistry(JDateChooser dcEntrada, JDateChooser dcSalida, boolean pagado, RegistroParking rp,
			Vehiculo v) {
		rp.setVehiculo(v);
		Timestamp tmEntrada = new Timestamp(dcEntrada.getDate().getTime());
		Timestamp tmSalida = new Timestamp(dcSalida.getDate().getTime());
		rp.setHoraEntrada(tmEntrada);
		rp.setHoraSalida(tmSalida);
		if (pagado) {
			rp.setPagado(1);
		}
	}

}
