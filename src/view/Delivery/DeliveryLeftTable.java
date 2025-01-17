package view.Delivery;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import function.member.Customer_Update;
import view.Members.MemberSearchFrame;

public class DeliveryLeftTable extends JPanel {

	public static int row;
	public static int delivery_id;
	public static String MEMBERS_NAME;
	public static int members_id;
	public static int payment;

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private String columns[] = { 
			"DELIVERY_ID",    
			"MEMBERS_NAME", 
			"PAYMENT",
			"SALES_TIME",
			"Delivery_Check"
		};
	
private DefaultTableModel model = new DefaultTableModel(columns, 0);

	JPanel center;
	JFrame jf;
	
	public DeliveryLeftTable(JFrame jf, JPanel center, JPanel DeliveryRightPanel) {
		this.jf = jf;
		this.center = center;
//		setLayout(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//테이블 생성
		table = new JTable(model);	
		//테이블에 마우스리스너 감지하는 클래스를 추가
		table.addMouseListener(new JTableMouseListener());
		table.addMouseListener(new JTableMouseListener1());
		table.getColumn("DELIVERY_ID").setPreferredWidth(WIDTH);
		table.getColumn("MEMBERS_NAME").setPreferredWidth(WIDTH); 
		table.getColumn("PAYMENT").setPreferredWidth(WIDTH);
		table.getColumn("SALES_TIME").setPreferredWidth(WIDTH);
		table.getColumn("Delivery_Check").setPreferredWidth(WIDTH);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		table.setSize(500, 400);
		
		table.getColumnModel().getColumn(0).setHeaderValue("주문 번호");
		table.getColumnModel().getColumn(1).setHeaderValue("회원 이름");
		table.getColumnModel().getColumn(2).setHeaderValue("총 금액");
		table.getColumnModel().getColumn(3).setHeaderValue("날짜");
		table.getColumnModel().getColumn(4).setHeaderValue("배달 여부");
		
		//빈 테이블 객체 생성
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
			
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		
//		dtcr.setBackground(new Color(43, 51, 62));
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(510, 410));
//		scrollPane.setLocation(0,0);
		add(scrollPane);
	
//		initialize();
		select("select DISTINCT DELIVERY_ID, MEMBERS_NAME, PAYMENT, MEMBERS_PHONENUMBER, TO_DATE(SALES_TIME, 'YY/MM/DD') as Sales_time,  DELIVERY_CHECK from delivery "
				+ "INNER JOIN member_informations USING (members_id) "
				+ "LEFT OUTER JOIN SALES USING (DELIVERY_ID) order by DELIVERY_ID", null);

	}
	private class JTableMouseListener implements MouseListener{	
		
		//마우스클릭했을때의 액션
		public void mouseClicked(MouseEvent e) {	
			TableModel tm = table.getModel();
			row = table.getSelectedRow();
			delivery_id = (int)tm.getValueAt(row, 0);
			MEMBERS_NAME = (String) tm.getValueAt(row, 1);
			payment = (int)tm.getValueAt(row, 2);  
			
			DeliveryRightPanel drp = DeliveryRightPanel.getRightPanel();
			
			DeliveryConnectDB dcb = new DeliveryConnectDB("select DISTINCT * from delivery INNER JOIN member_informations USING(members_id) WHERE delivery_id = " + delivery_id , "check");
			
			
			drp.getInformationText().setText(DeliveryLeftTable.MEMBERS_NAME);
			drp.getAddressText().setText(dcb.getArr2().get(0));
			drp.getPhoneNumberText().setText(dcb.getArr2().get(1));
			drp.getPaymentText().setText(String.format("%d", DeliveryLeftTable.payment));	
		
		}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	}
	
	private class JTableMouseListener1 implements MouseListener{	
		
		//마우스클릭했을때의 액션
		public void mouseClicked(MouseEvent e) {	
			int selectRow = ((JTable) e.getSource()).getSelectedRow();
			int YesOrNo = 0;
			
			
			if(((DefaultTableModel) table.getModel()).getValueAt(selectRow, 4).equals("Y")) {
				
				int choice = JOptionPane.showConfirmDialog(null, "배달취소하시겠습니까?", "배달 취소", JOptionPane.YES_NO_OPTION);	
				YesOrNo = Integer.parseInt(String.format("%d", ((DefaultTableModel) table.getModel()).getValueAt(selectRow, 0)));
				
				if (choice == 0) {
				new DeliveryCheck(YesOrNo);
				((DefaultTableModel) table.getModel()).setValueAt('N', selectRow, 4);
				JOptionPane.showMessageDialog(null, "배달취소하셨습니다");
				
				} else if (choice == 1) {
					JOptionPane.showMessageDialog(null, "취소하셨습니다");
				}
			
				
			}	else if(((DefaultTableModel) table.getModel()).getValueAt(selectRow, 4).equals("N")) {
				int choice = JOptionPane.showConfirmDialog(null, "배달완료하시겠습니까?", "배달 완료", JOptionPane.YES_NO_OPTION);	
				YesOrNo = Integer.parseInt(String.format("%d", ((DefaultTableModel) table.getModel()).getValueAt(selectRow, 0)));
				
				if(choice == 0) {
				new DeliveryCheck2(YesOrNo);
				((DefaultTableModel) table.getModel()).setValueAt('Y', selectRow, 4);
				JOptionPane.showMessageDialog(null, "배달완료하셨습니다");
			
			} else if (choice == 1){
				JOptionPane.showMessageDialog(null, "취소하셨습니다");
			}
		}
		}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	}
	
	
	
	
	private void select(String sql, String check) {	
		
		DeliveryConnectDB cdb = new DeliveryConnectDB(sql, check);
		
		ArrayList<Object[]> arr = new ArrayList<>();
		
		arr = cdb.getSelect();
		for(int i = 0; i < arr.size(); i++) {
			model.addRow(arr.get(i));
		}
	}

}