package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controller.AdminController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class AdminView {

	private JFrame frame;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_name;
	private JTextField textField_username;
	private JTextField textField_password;
	private JButton btnUpdate;
	
	private JScrollPane scrollPane_2;
	private DefaultTableModel tableModel_2;
	private String col_table_2[]=  {"id", "name", "ingredients", "manufacturer", "quantity", "price"};
	private JButton btnDelete;
	
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private String col_table[]= {"id", "username", "password", "name"};
	private JButton btnShowUsers;
	
	private AdminController adminController;
	private JTextField textField_id;
	private JLabel lblId_1;
	private JLabel lblIngredients;
	private JLabel lblManufacturer;
	private JLabel lblQuantity;
	private JLabel lblPrice;
	private JTextField textField_id_2;
	private JTextField textField_ingredients;
	private JTextField textField_manufacturer;
	private JTextField textField_quantity;
	private JTextField textField_name_2;
	private JTextField textField_price;
	private JButton btnOutOfStock;
	private JButton btnCsv;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView window = new AdminView();
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
	public AdminView() {
		adminController = new AdminController(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 790, 481);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(10, 38, 388, 161);
		frame.getContentPane().add(table_1);
		//scrollPane = new JScrollPane(table_1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane = new JScrollPane(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getRowDataTable1();
			}
		});
		scrollPane.setBounds(10, 38, 388, 161);
		frame.getContentPane().add(scrollPane);
		tableModel = new DefaultTableModel(col_table, 0);
		
		table_2 = new JTable();
		table_2.setBounds(10, 230, 388, 172);
		frame.getContentPane().add(table_2);
		scrollPane_2 = new JScrollPane(table_2);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getRowDataTable2();
			}
		});
		scrollPane_2.setBounds(10, 258, 388, 172);
		frame.getContentPane().add(scrollPane_2);
		tableModel_2 = new DefaultTableModel(col_table_2, 0);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setBounds(10, 13, 46, 14);
		frame.getContentPane().add(lblUsers);
		
		JLabel lblMedications = new JLabel("Medications");
		lblMedications.setBounds(10, 233, 88, 14);
		frame.getContentPane().add(lblMedications);
		
		textField_name = new JTextField();
		textField_name.setBounds(551, 38, 141, 20);
		frame.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setBounds(551, 69, 141, 20);
		frame.getContentPane().add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setBounds(551, 100, 141, 20);
		frame.getContentPane().add(textField_password);
		textField_password.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(476, 45, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(476, 72, 65, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(476, 103, 65, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnAddClicked();
			}
		});
		btnAdd.setBounds(551, 131, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminController.btnUpdateChemistClicked();
			}
		});
		btnUpdate.setBounds(650, 131, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnDeleteChemistClicked();
			}
		});
		btnDelete.setBounds(551, 165, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		btnShowUsers = new JButton("Show");
		btnShowUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (adminController != null) {
				adminController.btnShowChemistsClicked();
				}
			}
		});
		btnShowUsers.setBounds(66, 9, 89, 23);
		frame.getContentPane().add(btnShowUsers);
		
		JButton btnShowMedications = new JButton("Show");
		btnShowMedications.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adminController.btnShowMedicationsClicked();
			}
		});
		btnShowMedications.setBounds(84, 224, 88, 23);
		frame.getContentPane().add(btnShowMedications);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(476, 13, 46, 14);
		frame.getContentPane().add(lblId);
		
		textField_id = new JTextField();
		textField_id.setBounds(551, 10, 141, 20);
		frame.getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		lblId_1 = new JLabel("Id");
		lblId_1.setBounds(476, 233, 46, 14);
		frame.getContentPane().add(lblId_1);
		
		lblIngredients = new JLabel("Ingredients");
		lblIngredients.setBounds(476, 285, 80, 14);
		frame.getContentPane().add(lblIngredients);
		
		lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setBounds(476, 310, 80, 14);
		frame.getContentPane().add(lblManufacturer);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(476, 341, 46, 14);
		frame.getContentPane().add(lblQuantity);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(476, 366, 46, 14);
		frame.getContentPane().add(lblPrice);
		
		textField_id_2 = new JTextField();
		textField_id_2.setBounds(554, 230, 138, 20);
		frame.getContentPane().add(textField_id_2);
		textField_id_2.setColumns(10);
		
		textField_ingredients = new JTextField();
		textField_ingredients.setBounds(554, 282, 138, 20);
		frame.getContentPane().add(textField_ingredients);
		textField_ingredients.setColumns(10);
		
		textField_manufacturer = new JTextField();
		textField_manufacturer.setBounds(554, 307, 138, 20);
		frame.getContentPane().add(textField_manufacturer);
		textField_manufacturer.setColumns(10);
		
		textField_quantity = new JTextField();
		textField_quantity.setBounds(554, 335, 138, 20);
		frame.getContentPane().add(textField_quantity);
		textField_quantity.setColumns(10);
		
		textField_name_2 = new JTextField();
		textField_name_2.setBounds(554, 256, 138, 20);
		frame.getContentPane().add(textField_name_2);
		textField_name_2.setColumns(10);
		
		textField_price = new JTextField();
		textField_price.setBounds(554, 360, 138, 20);
		frame.getContentPane().add(textField_price);
		textField_price.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(476, 259, 46, 14);
		frame.getContentPane().add(lblName_1);
		
		JButton btnAdd_2 = new JButton("Add");
		btnAdd_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adminController.btnAddMedicationClicked();
			}
		});
		btnAdd_2.setBounds(551, 391, 89, 23);
		frame.getContentPane().add(btnAdd_2);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnUpdateMedicationClicked();
			}
		});
		btnUpdate_2.setBounds(650, 391, 89, 23);
		frame.getContentPane().add(btnUpdate_2);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adminController.btnDeleteMedicationClicked();
			}
		});
		btnDelete_2.setBounds(452, 391, 89, 23);
		frame.getContentPane().add(btnDelete_2);
		
		btnOutOfStock = new JButton("Out of stock");
		btnOutOfStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnOutOfStockClicked();
			}
		});
		btnOutOfStock.setBounds(194, 224, 115, 23);
		frame.getContentPane().add(btnOutOfStock);
		
		btnCsv = new JButton("CSV");
		btnCsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminController.btnCSVClicked();
			}
		});
		btnCsv.setBounds(309, 224, 89, 23);
		frame.getContentPane().add(btnCsv);
	}

	public void displayChemists(List<Object[]> userList) {
		tableModel.setRowCount(0);	 // delete the old rows	
		table_1.setModel(tableModel);
		
		for(Object[] user: userList) {
			 tableModel.addRow(user);	 
		}
	}
	
	
	public List<String> getChemistData() {
		List<String> data = new ArrayList<>();

		String id = textField_id.getText();
		String name = textField_name.getText();
		String username = textField_username.getText();
		String password = textField_password.getText();
		
		data.add(id);
		data.add(name);
		data.add(username);
		data.add(password); 
		
		return data;
	}
	
	
	private void getRowDataTable1() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table_1.getModel();

	    // get the selected row index
	    int selectedRowIndex = table_1.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    textField_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
	    textField_username.setText(model.getValueAt(selectedRowIndex, 1).toString());
	    textField_password.setText(model.getValueAt(selectedRowIndex, 2).toString());
	    textField_name.setText(model.getValueAt(selectedRowIndex, 3).toString());
	}
	
	public List<String> getMedicationData() {
		List<String> data = new ArrayList<String>();
		
		 String id = textField_id_2.getText();
		 String name = textField_name_2.getText();
		 String ingredients = textField_ingredients.getText();
		 String manufacturer = textField_manufacturer.getText();
		 String quantity = textField_quantity.getText();
		 String price = textField_price.getText();
		 
		 data.add(id);
		 data.add(name);
		 data.add(ingredients);
		 data.add(manufacturer);
		 data.add(quantity);
		 data.add(price);
		 
		return data;	
	}

	public void displayMedications(List<Object[]> dataList) {
		tableModel_2.setRowCount(0);	 // delete the old rows	
		table_2.setModel(tableModel_2);
		
		for(Object[] med: dataList) {
			 tableModel_2.addRow(med);	 
		}
	}
	
	private void getRowDataTable2() {
		// get the model from the jtable
	    DefaultTableModel model = (DefaultTableModel)table_2.getModel();

	    // get the selected row index
	    int selectedRowIndex = table_2.getSelectedRow();
	       
	    // set the selected row data into jtextfields
	    textField_id_2.setText(model.getValueAt(selectedRowIndex, 0).toString());
	    textField_name_2.setText(model.getValueAt(selectedRowIndex, 1).toString());
	    textField_ingredients.setText(model.getValueAt(selectedRowIndex, 2).toString());
	    textField_manufacturer.setText(model.getValueAt(selectedRowIndex, 3).toString());
	    textField_quantity.setText(model.getValueAt(selectedRowIndex, 4).toString());
	    textField_price.setText(model.getValueAt(selectedRowIndex, 5).toString());    
	}
	
	public void displayError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
