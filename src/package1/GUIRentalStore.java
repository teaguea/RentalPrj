package package1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;


public class GUIRentalStore extends JFrame implements ActionListener{

        private static final long serialVersionUID = 1L;

        JMenuBar menus;
        JMenu fileMenu;
        JMenuItem exitItem;
        JMenuItem openSerialItem;
        JMenuItem saveSerialItem;
        JMenuItem openTextItem;
        JMenuItem saveTextItem;
        JMenu actionMenu;
        JMenuItem rentDVDItem;
        JMenuItem rentGameItem;
        JMenuItem returnItem;
        JMenuItem dueDateItem;
        JMenuItem searchTitleItem;
        JList myList;
        JFileChooser chooser;
        JScrollPane myScroll;
        ListEngine myModel;


        public static void main(String[] args){
                new GUIRentalStore();
        }

        public GUIRentalStore(){
                menus = new JMenuBar();
                fileMenu = new JMenu ("File");
                exitItem = new JMenuItem("Exit!");
                openSerialItem = new JMenuItem("Open Serial...");
                saveSerialItem = new JMenuItem("Save Serial...");
                openTextItem = new JMenuItem("Open Text...");
                saveTextItem = new JMenuItem("Save Text...");

                fileMenu.add(openSerialItem);
                fileMenu.add(saveSerialItem);
                fileMenu.add(openTextItem);
                fileMenu.add(saveTextItem);
                fileMenu.add(exitItem);
                menus.add(fileMenu);

                openSerialItem.addActionListener(this);
                saveSerialItem.addActionListener(this);
                openTextItem.addActionListener(this);
                saveTextItem.addActionListener(this);
                exitItem.addActionListener(this);


                actionMenu = new JMenu ("Action");
                rentDVDItem = new JMenuItem("Rent DVD");
                rentGameItem = new JMenuItem("Rent Game");
                returnItem = new JMenuItem("Return");
                searchTitleItem = new JMenuItem("Search Title");
                dueDateItem = new JMenuItem("Search by Due Date");

                actionMenu.add(rentDVDItem);
                actionMenu.add(rentGameItem);
                actionMenu.add(returnItem);
                actionMenu.add(searchTitleItem);
                actionMenu.add(dueDateItem);
                menus.add(actionMenu);

                rentDVDItem.addActionListener(this);
                rentGameItem.addActionListener(this);
                returnItem.addActionListener(this);
                searchTitleItem.addActionListener(this);
                dueDateItem.addActionListener(this);
                

                myModel = new ListEngine();
                myList = new JList(myModel);
                myScroll = new JScrollPane(myList);
                chooser = new JFileChooser();

                this.setJMenuBar(menus);
                this.add(myScroll);


                this.setSize(700, 700);
                this.setVisible(true);

        }

        public void actionPerformed(ActionEvent event) {
                if(event.getSource() == exitItem)
                        System.exit(1);

                if(event.getSource() == openSerialItem){
                        chooser.showOpenDialog(null);
                        try {
                                FileInputStream fileIn =
                                new FileInputStream(chooser.getSelectedFile());
                                myModel.openSerialItem(fileIn);
                        } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (NullPointerException e) {
                                // TODO Auto-generated catch block

                        }

                }


                if(event.getSource() == saveSerialItem){
                        chooser.showSaveDialog(null);
                        try {
                                FileOutputStream fileOut =
                                new FileOutputStream(chooser.getSelectedFile());
                                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                myModel.saveSerialItem(out, fileOut);
                        } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (NullPointerException e) {
                                // TODO Auto-generated catch block

                        }
                }


                if(event.getSource() == openTextItem){
                        chooser.showOpenDialog(null);
                        File file = chooser.getSelectedFile();
                        try {
                                BufferedReader getInfo = new BufferedReader(new FileReader(file));
                                myModel.openText(getInfo);

                        } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (NullPointerException e) {
                                // TODO Auto-generated catch block

                        } catch (ArrayIndexOutOfBoundsException e){
                        	JOptionPane.showMessageDialog(null, "Check file.");
                        }
                }


                if(event.getSource() == saveTextItem){
                        chooser.showSaveDialog(null);
                File file = chooser.getSelectedFile();
                try {
                        PrintWriter filewriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                        myModel.saveText(filewriter);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (NullPointerException e) {
                        // TODO Auto-generated catch block

                }

        }


                if(event.getSource() == rentDVDItem) {
                        DVD d = new DVD();
                        DialogRentDVD dialog = new DialogRentDVD(this, d);
                        if(dialog.getClosedOk())
                        {
                                myModel.addElement(dialog.getUnit());
                        }

                }

                if(event.getSource() == rentGameItem) {
                        Game game = new Game();
                        DialogRentGame dialog = new DialogRentGame(this, game);
                                if(dialog.getClosedOk())
                                        myModel.addElement(dialog.getUnit());

                }


                if(event.getSource() == returnItem){
                        int selectedIndex = myList.getSelectedIndex();
                        try{
                        String source = JOptionPane.showInputDialog(null, "Please enter a date.");
                        
                        if(!source.isEmpty()){
                        if(selectedIndex != -1)
                                myModel.deleteElement(selectedIndex, source);
                        }
                        else
                        {
                                JOptionPane.showMessageDialog(null, "Sorry we need a date to " +
                        "complete search.");
                        }
                        }
                        catch(NullPointerException e){
                        	
                        }
                }

                if(event.getSource() == searchTitleItem){
                        CharSequence source = " ";
                        //JFrame input = new JFrame();
                        source = JOptionPane.showInputDialog("Search for for?");
                        myModel.search(source);
                }

                if(event.getSource() == dueDateItem){
                        String input =
                        JOptionPane.showInputDialog("Search for what date?");
                        SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
                        Calendar x = new GregorianCalendar();
                        try {
                                Date date = simple.parse(input);
                                x.setTime(date);
                        } catch (ParseException  e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                        catch(NullPointerException e){
                        	
                        }
                        
                        myModel.searchDate(x);


                }

        }
        
                


}