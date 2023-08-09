import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditProductPanel extends JPanel implements ActionListener {

    static JPanel edit_product_panel = new JPanel();
    static JLabel product_name = new JLabel();
    static JLabel product_price = new JLabel();
    static JLabel product_quantity = new JLabel();
    static JLabel edit_product_label = new JLabel();
    static JTextField product_name_text = new JTextField();
    static JTextField product_price_text = new JTextField();
    static JTextField product_quantity_text = new JTextField();
    static JButton save_button = new JButton();
    static JButton back_button = new JButton();

    EditProductPanel(){

        edit_product_label.setText("Edit Product");
        edit_product_label.setBounds(410,30,200,40);
        edit_product_label.setForeground(Color.black);
        edit_product_label.setFont(new Font(edit_product_label.getFont().getFontName(),Font.BOLD,20));

        product_name.setText("Product Name");
        product_name.setBounds(430,100,140,20);
        product_name.setForeground(Color.black);

        product_price.setText("Price");
        product_price.setBounds(300,170,140,20);
        product_price.setForeground(Color.black);

        product_quantity.setText("Quantity");
        product_quantity.setBounds(600,170,140,20);
        product_quantity.setForeground(Color.black);

        edit_product_panel.setBounds(200,0,600,400); //200,0,600,400
        edit_product_panel.setBackground(Color.white);

        product_name_text.setBounds(390,120,170,28);
        addTextPrompt(product_name_text,"product name");
        product_price_text.setBounds(270,190,100, 28);
        addTextPrompt(product_price_text,"price");
        product_quantity_text.setBounds(575,190, 100, 28);
        addTextPrompt(product_quantity_text,"quantity");


        save_button.setBounds(430,300,90,25);
        save_button.addActionListener(this);
        save_button.setText("Save Product");
        save_button.setFocusable(false);
        save_button.setBackground(Color.black);
        save_button.setFont(new Font(save_button.getFont().getFontName(),Font.BOLD,10));
        save_button.setForeground(Color.white);
        save_button.setMargin(new Insets(0,0,0,0));

        back_button.setBounds(220,40,60,25);
        back_button.addActionListener(this);
        back_button.setText("<< Back");
        back_button.setFocusable(false);
        back_button.setBackground(Color.black);
        back_button.setFont(new Font(back_button.getFont().getFontName(),Font.BOLD,10));
        back_button.setForeground(Color.white);
        back_button.setMargin(new Insets(0,0,0,0));

        Frame1.frame.add(edit_product_label);
        Frame1.frame.add(product_name);Frame1.frame.add(product_price);Frame1.frame.add(product_quantity);
        Frame1.frame.add(product_name_text);
        Frame1.frame.add(product_price_text);
        Frame1.frame.add(product_quantity_text);
        Frame1.frame.add(save_button);
        Frame1.frame.add(back_button);
        Frame1.frame.add(edit_product_panel);
        Frame1.frame.repaint();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button){
            AddandRemovePanels.remove_EditProductPanel();
            AddandRemovePanels.add_Panel1();
        }else if (e.getSource() == save_button){
            try {
                String edit_sql_query = "UPDATE products SET Name =" + "'" + product_name_text.getText().substring(0,product_name_text.getText().length()) + "'" + ", Quantity = " + Integer.parseInt(product_quantity_text.getText()) + ", Price = "+ Integer.valueOf(product_price_text.getText()) +" WHERE Product_no = " + Panel1.edit_prod_no_int;
                Panel1.statement = Panel1.connection.createStatement();
                Panel1.statement.execute(edit_sql_query);
            }catch (SQLException ex){
                throw new RuntimeException(ex);
            }
            // clear the old table, connect and display the new database
            try {
                while (Panel1.model.getRowCount() > 1){
                    Panel1.model.removeRow(1);
                }
                Panel1.create_connection();
                Panel1.display_products();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            AddandRemovePanels.remove_EditProductPanel();
            AddandRemovePanels.add_Panel1();
        }
    }

    // The below code makes textfield gray
    private static void addTextPrompt(JTextComponent textComponent, String promptText) {
        textComponent.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textComponent.getText().equals(promptText)) {
                    textComponent.setText("");
                    textComponent.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textComponent.getText().isEmpty()) {
                    textComponent.setText(promptText);
                    textComponent.setForeground(Color.GRAY);
                }}
        });
        // Set initial text and color
        textComponent.setText(promptText);
        textComponent.setForeground(Color.GRAY);


}}
