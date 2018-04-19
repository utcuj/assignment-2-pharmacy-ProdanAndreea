package view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

import controller.ChemistController;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChemistView {

	private JFrame frame;
	
	private ChemistController chemistController;
	private JTable table;
	private JTextField textField;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private String col_table[]= {"id", "name", "ingredients", "manufacturer", "quantity", "price"};
	private JTextField textField_name;
	private JTextField textField_quantity;
	private JTextField textField_total;
	private JTextField textField_price;
	

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChemistView window = new ChemistView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChemistView() {
		chemistController = new ChemistController(this);
		initialize();
		chemistController.showMedications();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 641, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 38, 356, 207);
		frame.getContentPane().add(table);
		scrollPane = new JScrollPane(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chemistController.RowDataSelected();
			}
		});
		scrollPane.setBounds(10, 38, 356, 207);
		frame.getContentPane().add(scrollPane);
		tableModel = new DefaultTableModel(col_table, 0);
		
		JLabel lblMedications = new JLabel("Medications");
		lblMedications.setBounds(10, 11, 74, 14);
		frame.getContentPane().add(lblMedications);
		
		textField = new JTextField();
		textField.setBounds(66, 256, 288, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(20, 262, 46, 14);
		frame.getContentPane().add(lblName);
		
		JButton btnSearchByName = new JButton("Name");
		btnSearchByName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chemistController.btnSearchMedByNameClicked();
			}
		});
		btnSearchByName.setBounds(65, 287, 113, 23);
		frame.getContentPane().add(btnSearchByName);
		
		JButton btnSearchByIngredients = new JButton("Ingredients");
		btnSearchByIngredients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chemistController.btnSearchMedByIngredientsClicked();
			}
		});
		btnSearchByIngredients.setBounds(200, 287, 139, 23);
		frame.getContentPane().add(btnSearchByIngredients);
		
		JButton btnShow = new JButton("Show");
		btnShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chemistController.showMedications();
			}
		});
		btnShow.setBounds(94, 7, 89, 23);
		frame.getContentPane().add(btnShow);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(408, 39, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_name = new JTextField();
		textField_name.setBounds(464, 36, 86, 20);
		frame.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		textField_quantity = new JTextField();
		textField_quantity.setBounds(464, 61, 86, 20);
		textField_quantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
				     chemistController.btnPriceEnterPressed();
				   }
			}
		});
		frame.getContentPane().add(textField_quantity);
		textField_quantity.setColumns(10);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chemistController.btnSellClicked();
			}
		});
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSell.setBounds(464, 165, 89, 23);
		frame.getContentPane().add(btnSell);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setBounds(408, 64, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_total = new JTextField();
		textField_total.setBounds(464, 121, 86, 20);
		frame.getContentPane().add(textField_total);
		textField_total.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(408, 124, 46, 14);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(408, 93, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_price = new JTextField();
		textField_price.setBounds(464, 90, 86, 20);
		frame.getContentPane().add(textField_price);
		textField_price.setColumns(10);
	}
	

	public void displayMedications(List<Object[]> dataList) {
		tableModel.setRowCount(0);	 // delete the old rows	
		table.setModel(tableModel);
		
		for(Object[] med: dataList) {
			 tableModel.addRow(med);	 
		}
	}
	
	
	public String getSearchField() {
		String medName = textField.getText();
		return medName;
	}
	
	public void displayError(String msg) {
		JOptionPane.showMessageDialog(new Frame(), msg);
	}
	
	public String[] getSellData() {
		String medName = textField_name.getText();
		String quantity = textField_quantity.getText();
		String price = textField_price.getText();
		String[] data = {medName, quantity, price};
		return data;
	}
	
	public void setTotalField(String price) {
		textField_total.setText(price);
	}
	
	public void getRowData() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table.getModel();

	    // get the selected row index
	    int selectedRowIndex = table.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    textField_name.setText(model.getValueAt(selectedRowIndex, 1).toString());
	    textField_price.setText(model.getValueAt(selectedRowIndex, 5).toString());
	   // textField_quantity.setText(model.getValueAt(selectedRowIndex, 4).toString());
	}	
}
