package view.Payment.lowPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LowPanel extends JPanel{
	public LowPanel() {
		setBackground(new Color(43, 51, 62));
		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		setLayout(new GridLayout(2, 3, 5, 5));
		JButton reset = new LowJButtonReset("초기화");
		JButton receipt = new LowJButton("영수증출력");
		JButton cashPayment = new LowJButtoncashPayment("현금결제");
		JButton cancelPayment = new LowJButtonCancel("배달");
		JButton refund = new LowJButton("환불");
		JButton cardPayment = new LowJButtoncardPayment("카드결제");
		
		add(reset);
		add(receipt);
		add(cashPayment);
		add(cancelPayment);
		add(refund);
		add(cardPayment);
	}
}