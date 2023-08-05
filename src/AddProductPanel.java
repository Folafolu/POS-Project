import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductPanel extends JPanel implements ActionListener {

    JPanel add_product_panel = new JPanel();
    JLabel product_name = new JLabel();
    JLabel product_price = new JLabel();
    JLabel product_quantity = new JLabel();
    JTextField product_name_text = new JTextField();
    JTextField product_price_text = new JTextField();
    JTextField product_quantity_text = new JTextField();
    JButton add_button = new JButton();
    JButton back_button = new JButton();

    AddProductPanel(){
        hide_Panel1();

        product_name.setText("Product Name");
        product_name.setBounds(430,80,140,20);
        product_name.setForeground(Color.black);

        product_price.setText("Price");
        product_price.setBounds(300,150,140,20);
        product_price.setForeground(Color.black);

        product_quantity.setText("Quantity");
        product_quantity.setBounds(600,150,140,20);
        product_quantity.setForeground(Color.black);

        add_product_panel.setBounds(200,0,600,400); //200,0,600,400
        add_product_panel.setBackground(Color.white);


        product_name_text.setBounds(390,100,170,28);
        product_price_text.setBounds(270,170,100, 28);
        product_quantity_text.setBounds(575,170, 100, 28);

        add_button.setBounds(430,300,90,25);
        add_button.addActionListener(this);
        add_button.setText("Add Product");
        add_button.setFocusable(false);
        add_button.setBackground(Color.black);
        add_button.setFont(new Font(add_button.getFont().getFontName(),Font.BOLD,10));
        add_button.setForeground(Color.white);
        add_button.setMargin(new Insets(0,0,0,0));

        back_button.setBounds(220,40,60,25);
        back_button.addActionListener(this);
        back_button.setText("<< Back");
        back_button.setFocusable(false);
        back_button.setBackground(Color.black);
        back_button.setFont(new Font(back_button.getFont().getFontName(),Font.BOLD,10));
        back_button.setForeground(Color.white);
        back_button.setMargin(new Insets(0,0,0,0));
        
        
        Frame1.frame.add(product_name);Frame1.frame.add(product_price);Frame1.frame.add(product_quantity);
        Frame1.frame.add(product_name_text);
        Frame1.frame.add(product_price_text);
        Frame1.frame.add(product_quantity_text);
        Frame1.frame.add(add_button);
        Frame1.frame.add(back_button);
        Frame1.frame.add(add_product_panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button){
            hide_AddProductPanel();
            show_Panel1();
        }
        else if (e.getSource() == add_button){
            hide_AddProductPanel();
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
    public void hide_AddProductPanel(){
        product_name.setVisible(false);
        product_price.setVisible(false);
        product_quantity.setVisible(false);
        product_name_text.setVisible(false);
        product_price_text.setVisible(false);
        product_quantity_text.setVisible(false);
        back_button.setVisible(false);
        add_button.setVisible(false);
        add_product_panel.setVisible(false);
    }


}
