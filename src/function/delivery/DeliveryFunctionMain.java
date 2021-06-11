package function.delivery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.DeliveryMainFrame;

public class DeliveryFunctionMain implements ActionListener {
	JFrame jf;
	public DeliveryFunctionMain(JFrame jf) {
		this.jf = jf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jf.setVisible(false);//MainFrame에서 받은 JFrame을 꺼버림
		
		new DeliveryMainFrame(jf);//여기에 MainFrame의 JFrame을 넘기는 이유는 결제 창에서 다시 메인 MainFrame으로
		//돌아가기 위해서는 jf.setVisible(true);을 해주기 위해서임
		
	}
}
