package view.stock.nextFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ComboBoxActions implements ActionListener{

	JComboBox<String> comboBox;
	
	public ComboBoxActions(JComboBox<String> comboBox) {
		// TODO Auto-generated constructor stub
		this.comboBox = comboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		comboBox.getSelectedItem();
	}
}
