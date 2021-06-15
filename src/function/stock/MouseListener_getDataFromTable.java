package function.stock;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import view.stock.nextFrame.findImage.NOPE;
import view.stock.nextFrame.productEvent.RowInfo;
import view.stock.nextFrame.productEvent.UpdateFrame;

public class MouseListener_getDataFromTable implements MouseListener{	

		JTable table;
		RowInfo selectUser;
		String doit;
		
		public MouseListener_getDataFromTable(JTable table, String doit) {
			
			this.doit = doit;
			this.table = table;
			// TODO Auto-generated constructor stub
		}
	
		public void mouseClicked(MouseEvent e) {	//마우스클릭했을때의 액션
			
			JTable jtable = (JTable)e.getSource();
			int row = jtable.getSelectedRow();	//행값 넣기
			int col = jtable.getSelectedColumn();	//컬럼값 넣기
			
			if(e.getClickCount() == 1) {
				int rows = table.getSelectedRow();
				
				TableModel data = table.getModel();
				
				int product_id = (int) data.getValueAt(rows, 0);
				String product_name = (String)data.getValueAt(rows, 1);
				String product_type = (String)data.getValueAt(rows, 2);
				int price = (int)data.getValueAt(rows, 3);
				Date expiration_date = (Date) data.getValueAt(rows, 4);
				String discount_type = (String)data.getValueAt(rows, 5);
				double discount_rate = (double)data.getValueAt(rows, 6);
				String product_origin = (String)data.getValueAt(rows, 7);
				int stocks = (int)data.getValueAt(rows, 8);
				int gram = (int)data.getValueAt(rows, 9);
				String sales_type = (String)data.getValueAt(rows, 10);
				int cost = (int)data.getValueAt(rows, 11);
				
				selectUser =
					    new RowInfo(product_id, 
					    		product_name, 
					    		product_type, 
					    		price, 
					    		expiration_date, 
					    		discount_type, 
					    		discount_rate,
					    		product_origin, 
					    		stocks, gram, 
					    		sales_type, 
					    		cost);
				
				if(doit.equals("regist")) {
					String confirmStr = "품목번호 : " + selectUser.product_id + "\n품목이름 : " + selectUser.product_name + 
							"\n\t행사를 등록하시겠습니까?";
					int confirm = JOptionPane.showConfirmDialog(null,confirmStr,"이벤트 등록", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						//UPDATE coffees SET cname = '인절미빙수' where cname = '팥빙수';
						String eventSql = "UPDATE PRODUCTS SET DISCOUNT_TYPE = '" +NOPE.eventName+ "', DISCOUNT_RATE = " +NOPE.rate
										+ " WHERE PRODUCT_ID = " + selectUser.product_id;
						System.out.println(eventSql);
						new InsertDB_product(eventSql);
					}
				}
//				UpdateFrame uf = new UpdateFrame(selectUser);
				
				
		
					
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
