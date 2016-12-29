package package1;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.* ;

public class ListEngine extends AbstractListModel {

        /**ArracyList of DVD objects **/
        private ArrayList<DVD> listDVD;

        /******************************************************
         Default constructor creates initializes ArrayList
         ******************************************************/
        public ListEngine(){

                listDVD = new ArrayList();
        }

        /***************************************
         Gets the object from the ArrayList
         @param the index of the item
         @return DVD object
         ***************************************/
        @Override
        public Object getElementAt(int i) {

                return listDVD.get(i);
        }

        /**************************************
         Gets the object from the ArrayList
         @param the index of the item
         @return DVD object
         **************************************/
        @Override
        public int getSize() {

                return listDVD.size();
        }

        /************************************
         Adds DVD object to the array.
         @param the index of the item
         ************************************/
        public void addElement(DVD d){
                listDVD.add(d);
                fireIntervalAdded(this, 0, listDVD.size());

        }

        /******************************************************
         Removes the object from the array and update the
         JList. And calculates the rate for the rental.
         @param the index of the item and the date of the return.
         ******************************************************/
        public void deleteElement(int d, String input){

                Calendar x = new GregorianCalendar();
                SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
                double priceOri = listDVD.get(d).getPirce();;
                double total = 0.0;
                double original = 0.0;
                double priceLate = listDVD.get(d).getLatePirce();
                NumberFormat formatter = NumberFormat.getCurrencyInstance();



                try {
                        Date date = simple.parse(input);
                        x.setTime(date);
                } catch (ParseException e) {
                 JOptionPane.showMessageDialog(null, "Sorry we'll we need at "+
                                                "date please try again.");
                }

                //Check to make sure the entered date is after the rent date
        if(listDVD.get(d).getRent().before(x)){

                //Set the DVD values to variables
                String name = listDVD.get(d).getNameOfRenter();
                String title = listDVD.get(d).getTitle();
                GregorianCalendar due = listDVD.get(d).getDue();
                GregorianCalendar rent = listDVD.get(d).getRent();

                //Checks if the return date is late
                if(x.before(due) || x.equals(due)){
                total = (diffDays(x.getTime(),
                listDVD.get(d).getRent().getTime())) * priceOri;

                }

                else
                {
                 //Caluclates the price before late charge
                 original = (diffDays(due.getTime(),rent.getTime()))
                            * priceOri;
                 //Calculates and adds late charge
                 total = original + diffDays(x.getTime(), due.getTime()) *
                                 priceLate;
                }

                JOptionPane.showMessageDialog(null,"Thank you " + name +
                                         " for renting " + title + " you owe " +
                                         formatter.format(total));
                listDVD.remove(d);
                fireContentsChanged(this, 0, listDVD.size());
                }

         else
         {

                   JOptionPane.showMessageDialog(null,"Please try a " +
                                                  "different date.");
         }


        }

        /******************************************************
         Removes all objects from the array and update the
         JList.
         ******************************************************/
        public void clearElements(){
                this.listDVD.clear();
                fireIntervalRemoved(this, 0, listDVD.size());

        }


        /******************************************************
         Save objects to a Serial file
         ******************************************************/
        public void saveSerialItem(ObjectOutputStream out,
                        FileOutputStream fileOut) throws IOException{

                for(int i = 0; i < listDVD.size(); i++){
                        out.writeObject(listDVD.get(i));
                }

                out.close();
                fileOut.close();

        }

        /******************************************************
         Loads the array from a serial file.
         @param fileIn
         ******************************************************/
        public void openSerialItem(FileInputStream fileIn) throws
        IOException, ClassNotFoundException{

                InputStream buffer = new BufferedInputStream(fileIn);
                try
                {
                ObjectInputStream in = new ObjectInputStream(buffer);
                
                //Clears Jlist to show new database
                this.clearElements();

                while(in != null){
                        try{
                                DVD dvd = null;

                                dvd = (DVD) in.readObject();
                                this.addElement(dvd);

                        }
                        catch(EOFException x){
                                break;
                        }
                }
               
                in.close();
                fileIn.close();
               }
               catch(StreamCorruptedException e) {
            	   
            	   JOptionPane.showMessageDialog(null,"Please try a "+
                           "different file.");
               }
                
        }

        /******************************************************
         Save objects to a text file
         @param filewriter, text file
         ******************************************************/
        public void saveText(PrintWriter filewriter){
        //Prints the object info: "Name, Date..."
                String line = " ";
                String lineTwo = " ";
                for(int i = 0; i < listDVD.size(); i++){
                        DVD dvdUnit = listDVD.get(i);
                        line = dvdUnit.getClass().getName() + "," +
                        dvdUnit.getNameOfRenter() + "," + dvdUnit.getTitle() + "," +
                        dvdUnit.getRentString() + "," + dvdUnit.getBackString();

                        if(dvdUnit instanceof Game){
                                lineTwo = line + "," + ((Game)dvdUnit).getPlayer();
                                filewriter.println(lineTwo);
                        }

                        else{

                                filewriter.println(line);
                        }

                }

                filewriter.close();


        }

        /******************************************************
         Loads objects to the array List from a text file
         @param filereader, text file
         ******************************************************/
        public void openText(BufferedReader filereader)
                        throws IOException, ParseException{

                this.clearElements();

                String info = filereader.readLine();

                while(info != null){
                        //Splits the elements in text to strings
                        String[] listInfo = info.split(",");

                        //Checks if the object is a DVD or Game
                        if(listInfo[0].equals("package1.DVD"))
                        {
                                DVD newDVD = new DVD();
                                newDVD.setnameOfRenter(listInfo[1]);
                                newDVD.setTitle(listInfo[2]);
                                newDVD.setRented(listInfo[3]);
                                newDVD.setDueDate(listInfo[4]);
                                this.addElement(newDVD);
                        }


                        else
                        {
                                Game newGame = new Game();
                                newGame.setnameOfRenter(listInfo[1]);
                                newGame.setTitle(listInfo[2]);
                                newGame.setRented(listInfo[3]);
                                newGame.setDueDate(listInfo[4]);
                                newGame.setPlayer(PlayerType.valueOf(listInfo[5]));
                                this.addElement(newGame);
                        }

                        info = filereader.readLine();
                }

        }
        /******************************************************
         Searchs through the Array list to find all objects
         contain the CharSequence
         @param the sequence your searching for
         ******************************************************/
        public void search(CharSequence source){
                try{

                        ArrayList<DVD> match = new ArrayList();

                        for(int i = 0; i < listDVD.size(); i++){
                                //Fills an array list with matches
                                if(listDVD.get(i).getTitle().contains(source))
                                        match.add(listDVD.get(i));
                        }

                        //Clears the elements
                        this.clearElements();

                        //Adds the elements found
                        for(int i = 0; i < match.size(); i++){

                                this.addElement(match.get(i));
                        }
                }

                catch(NullPointerException e){

                }


        }
        /******************************************************
        Finds rentals past due.
        @param Due date
        ******************************************************/ 
       public void searchDate(Calendar due){
        	String output = "";
        	for(int i = 0; i < listDVD.size(); i++){	
        		if(listDVD.get(i).getDue().getTime().after(due.getTime())){
        		int days = diffDays(listDVD.get(i).getDue().getTime(), due.getTime());
        		output += listDVD.get(i).toString() +" "  + days + " days \n";
        		}
        	}
        	JOptionPane.showMessageDialog(null, output);

        }
       
        /******************************************************
         Calculates the number day between two dates.
         I found this method on stackoverflow.com
         @param Date d1, Date d2
         @return the difference between d1 and d2
         ******************************************************/
        private int diffDays(Date d1, Date d2){

                return (int)((d1.getTime() - d2.getTime()) /
                                (1000 * 60 * 60 * 24));

        }


}