package package1;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

public class DialogRentDVD extends JDialog implements ActionListener{

        private static final long serialVersionUID = 1L;
        /** Renter's name label**/
        private JLabel nameLb;
        /** The tile label**/
        private JLabel titleLb;
        /** Date rented on label**/
        private JLabel rentDateLb;
        /** Due date label**/
        private JLabel dueDateLb;
        /** Renter's name text**/
        private JTextField nameTxt;
        /** The tile text**/
        private JTextField titleTxt;
        /** Date rented on text**/
        private JTextField rentDateTxt;
        /** Due date label**/
        private JTextField dueDateTxt;
        /** Cancel button**/
        private JButton cancelButton;
        /** Ok button**/
        private JButton okButton;
        /** Panel display rental info**/
        private JPanel panelOne;
        /** Panel display buttons**/
        private JPanel panelTwo;
        /** DVD object**/
        private DVD unit;
        /** Knows if all info was collected correctly**/
        private boolean closedOk;
        /** The current date**/
        private Calendar current;
        /** Date format**/
        private SimpleDateFormat simple;

        /*****************************************************
         * Creates the DVD Dialog Box
         * @param JFrame and DVD object
         * @return the DVD dialog box
         ****************************************************/
        public DialogRentDVD(JFrame parent, DVD d)
        {
                super(parent, true);
                closedOk = false;
                unit = d;
                simple = new SimpleDateFormat("MM/dd/yyyy");
                current = Calendar.getInstance();
                cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(this);
                okButton  = new JButton("Ok");
                okButton.addActionListener(this);
                nameLb = new JLabel("Your Name");
                titleLb = new JLabel("Title");
                rentDateLb = new JLabel("Rented on Date");
                dueDateLb = new JLabel("Due Back");
                nameTxt = new JTextField();
                titleTxt = new JTextField();
                rentDateTxt = new JTextField(simple.format(current.getTime()));
                current.add(Calendar.DATE, 1);
                dueDateTxt = new JTextField(simple.format(current.getTime()));
                GridLayout newLayout = new GridLayout(6,2);

                panelOne = new JPanel();
                panelOne.setLayout(newLayout);
                panelOne.add(nameLb);
                panelOne.add(nameTxt);
                panelOne.add(titleLb);
                panelOne.add(titleTxt);
                panelOne.add(rentDateLb);
                panelOne.add(rentDateTxt);
                panelOne.add(dueDateLb);
                panelOne.add(dueDateTxt);

                panelTwo =  new JPanel();
                panelTwo.add(cancelButton);
                panelTwo.add(okButton);

                getContentPane().add(panelOne, BorderLayout.NORTH);
                getContentPane().add(panelTwo, BorderLayout.CENTER);

                setSize(300, 300);
                setVisible(true);


        }

        /******************************************************
         Returns the DVD unit
         @param None
         @return The DVD
         ******************************************************/
        public DVD getUnit(){
                return unit;
        }

        /******************************************************
         Returns boolean value true if all info was collected
         @param None
         @return true value if all info was collected
         ******************************************************/
        public boolean getClosedOk(){
                return closedOk;
        }

        /******************************************************
         Action listener
         @param Action event
         ******************************************************/
        @Override
        public void actionPerformed(ActionEvent event) {

                if(event.getSource() == cancelButton){
                //Does nothing and shuts off dialog box if the cancel
                //button is selected
                  setVisible(false);
                  dispose();
                }

                if(event.getSource() == okButton){
                        closedOk = true;

                        Calendar x = new GregorianCalendar();

                        //Checks to make sure if the date is valid
                        try {
                                Date date = simple.parse(rentDateTxt.getText());
                                x.setTime(date);
                        } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null,"Please enter a cor" +
                                                  "rect rent date.");
                                closedOk = false;
                                setVisible(false);
                                dispose();
                        }

                        //sets rented date
                        unit.setRented((GregorianCalendar)x);


                        Calendar a = new GregorianCalendar();
                        //Checks if date is valid
                        try {
                                Date dateTwo = simple.parse(dueDateTxt.getText());
                                a.setTime(dateTwo);
                        } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null,"Please enter a cor" +
                                                  "rect due date.");
                                closedOk = false;
                                setVisible(false);
                                dispose();
                        }

                        //sets due date
                        unit.setDueDate((GregorianCalendar)a);


                        unit.setTitle(titleTxt.getText());
                        unit.setnameOfRenter(nameTxt.getText());

                        //Checks if the title is valid
                        if(unit.getTitle().isEmpty()){
                         JOptionPane.showMessageDialog(null,"Please enter a cor" +
                                                  "rect Title.");
                                closedOk = false;
                                setVisible(false);
                                dispose();

                        }

                        //Checks if the name is valid
                        if(unit.getNameOfRenter().isEmpty()){
                         JOptionPane.showMessageDialog(null,"Please enter a cor" +
                                                  "rect Name.");
                                closedOk = false;
                                setVisible(false);
                                dispose();

                        }

                        setVisible(false);
                        dispose();


                }
        }

}