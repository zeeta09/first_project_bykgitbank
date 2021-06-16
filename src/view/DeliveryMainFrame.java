package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import function.topBar.ClickMain;
import function.topBar.ClickMainDesign;
import function.topBar.TopBar;
import view.Delivery.DeliveryCenterPanel;

public class DeliveryMainFrame extends JFrame {
	public static JFrame jf;
	public DeliveryMainFrame(JFrame jf) {
		this.jf = jf;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(jf.getWidth(), jf.getHeight());
		setLocation(jf.getX(), jf.getY());
	
//		setLayout(null);
		
		JButton test2 = new ClickMainDesign();
		test2.addActionListener(new ClickMain(jf, this));

		add(test2);
		
		JPanel topBar = new TopBar();
		add(topBar, BorderLayout.NORTH);
		
		JPanel DeliveryCenterPanel = new DeliveryCenterPanel();
		add(DeliveryCenterPanel, BorderLayout.CENTER);

		setVisible(true);
		
	}

}
