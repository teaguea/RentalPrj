package package1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

public class DialogRentGame extends JDialog implements ActionListener{

        private static final long serialVersionUID = 1L;
        /**Name label **/
        private JLabel nameLb;
        /**Title label **/
        private JLabel titleLb;
        /**Rent date label **/
        private JLabel rentDateLb;
        /**Due label **/
        private JLabel dueDateLb;
        /**Playertype label **/
        private JLabel playerLb;
        /**Name text **/
        private JTextField nameTxt;
        /**Title text **/
        private JTextField titleTxt;
        /**Rent date text **/
        private JTextField rentDateTxt;
        /**Due text **/
        private JTextField dueDateTxt;
        /**Cancel button **/
        private JButton cancelButton;
        /**Ok button **/
        private JButton okButton;
        /**Combobox holds the playertye **/
        private JComboBox playerCombo;
        /**Panel holds the info **/
        private JPanel panelOne;
        /**Panel holds the buttons **/
        private JPanel panelTwo;
        /**Array holds the playertype values **/
        private PlayerType types[];
        /**Game object **/
        private Game unit;
        /** Knows if all info was collected correctly**/
        private boolean closedOk;
        /** The current date**/
        private Calendar current;
        /** Formats the date**/
        private SimpleDateFormat simple;

        /*******************************************************
         * Creates the the Game Dailog Box
         * @param The frame and game object
         * @return The game dialog box
         ******************************************************/
        public DialogRentGame(JFrame parent, Game d)
        {
                super(parent, true);
                closedOk = false;
                current = Calendar.getInstance();
                simple = new SimpleDateFormat("MM/dd/yyyy");
                unit = d;
                types = new PlayerType[4];
                types[0] = PlayerType.XBox360;
                types[1] = PlayerType.PS3;
                types[2] = PlayerType.PS4;
                types[3] = PlayerType.XBoxOne;
                cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(this);
                okButton  = new JButton("Ok");
                okButton.addActionListener(this);
                nameLb = new JLabel("Your Name");
                titleLb = new JLabel("Title");
                rentDateLb = new JLabel("Rented on Date");
                dueDateLb = new JLabel("Due Back");
                playerLb = new JLabel("Type of Player");
                playerCombo = new JComboBox(types);
                nameTxt = new JTextField();
                titleTxt = new JTextField();
                rentDateTxt = new JTextField(simple.format(current.getTime()));
                current.add(Calendar.DATE, 1);
                dueDateTxt = new JTextField(simple.format(current.getTime()));
                GridLayout newLayout = new GridLayout(8,2);

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
                panelOne.add(playerLb);
                panelOne.add(playerCombo);


                panelTwo =  new JPanel();
                panelTwo.add(cancelButton);
                panelTwo.add(okButton);


                getContentPane().add(panelOne, BorderLayout.NORTH);
                getContentPane().add(panelTwo, BorderLayout.CENTER);

                setSize(300, 300);
                setVisible(true);


        }

        public DVD getUnit(){
                return unit;
        }

        public boolean getClosedOk(){
                return closedOk;
        }

        /************************************************
         * Action even handler
         * @param The event that happened
         * @return None
     ************************************************/
        @Override
        public void actionPerformed(ActionEvent event) {
                //
                if(event.getSource() == okButton){

                        closedOk = true;


                        Calendar x = new GregorianCalendar();

                        //sets the text to a date
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
                        //set the rented date
                        unit.setRented((GregorianCalendar)x);


                        Calendar a = new GregorianCalendar();

                        //sets the text to date
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
                        //sets the text to a date
                        unit.setDueDate((GregorianCalendar)a);


                        unit.setPlayer(types[playerCombo.getSelectedIndex()]);


                        unit.setTitle(titleTxt.getText());
                        unit.setnameOfRenter(nameTxt.getText());

                        //checks if the title is empty
                        if(unit.getTitle().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please enter a cor" +
                                                          "rect Title.");
                                        closedOk = false;
                                        setVisible(false);
                                        dispose();

                                }

                        //checks if the name is empty
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

                if(event.getSource() == cancelButton){
                          setVisible(false);
                          dispose();
                        }

        }

}