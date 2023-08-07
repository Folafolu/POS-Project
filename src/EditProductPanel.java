import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductPanel extends JPanel implements ActionListener {

    JPanel edit_product_panel = new JPanel();
    JLabel product_name = new JLabel();
    JLabel product_price = new JLabel();
    JLabel product_quantity = new JLabel();
    JLabel edit_product_label = new JLabel();
    JTextField product_name_text = new JTextField();
    JTextField product_price_text = new JTextField();
    JTextField product_quantity_text = new JTextField();
    JButton save_button = new JButton();
    JButton back_button = new JButton();

    EditProductPanel(){
        hide_Panel1();

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

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button){
            hide_EditProductPanel();
            show_Panel1();
        }else if (e.getSource() == save_button){
            hide_EditProductPanel();
            show_Panel1();
        }
    }

    public void hide_Panel1(){
        Panel1.panel1.setVisible(false);
        Panel1.button.setVisible(false);
        Panel1.button1.setVisible(false);
        Panel1.button2.setVisible(false);
        Panel1.table.setVisible(false);
    }
    public void show_Panel1(){
        Panel1.button.setVisible(true);
        Panel1.button1.setVisible(true);
        Panel1.button2.setVisible(true);
        Panel1.table.setVisible(true);
        Panel1.panel1.setVisible(true);

    }
    public void hide_EditProductPanel(){
        product_name.setVisible(false);
        product_price.setVisible(false);
        product_quantity.setVisible(false);
        edit_product_label.setVisible(false);
        product_name_text.setVisible(false);
        product_price_text.setVisible(false);
        product_quantity_text.setVisible(false);
        back_button.setVisible(false);
        save_button.setVisible(false);
        edit_product_panel.setVisible(false);
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
