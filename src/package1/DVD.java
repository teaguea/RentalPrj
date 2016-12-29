package package1;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DVD implements Serializable{

    private static final long serialVersionUID = 1L;
        /**The date the DVD was rented **/
        protected GregorianCalendar rented;
        /**The date the DVD is back */
        protected GregorianCalendar back;
        /**The title of the DVD */
        protected String title;
        /**The name of the person who is renting the DVD */
        protected String nameOfRenter;
        /**Calendar varliable that helps print the date */
        private Calendar cal = new GregorianCalendar();
        /**Formats print the date */
        private SimpleDateFormat simple =
        new SimpleDateFormat("MM/dd/yyyy");
        /**Date variable that helps print the date */
        private Date date;

        /**************************************************
         * Constructor creates the DVD object
         * @param rented
         * @param back
         * @param title
         * @param nameOfRenter
         **************************************************/
        public DVD(GregorianCalendar rented, GregorianCalendar back,
         String title, String nameOfRenter){
                this.back = back;
                this.rented = rented;
                this.title = title;
                this.nameOfRenter = nameOfRenter;
        }

        /********************************************
         * Constructor creates a new blank DVD object
         *****************************************/
        public DVD(){
                rented = new GregorianCalendar();
                back =  new GregorianCalendar();
                title = " ";
                nameOfRenter = " ";

        }

        /****************************************
         * Gets the rent date in a string
         * @return Rent date as a string
         ****************************************/
        protected  String getRentString(){
                cal = rented;
                return simple.format(cal.getTime());

        }

        /***************************************
         * Gets the due date in a string
         * @return Due date as a string
         ***************************************/
        protected  String getBackString(){
                cal = back;
                return simple.format(cal.getTime());

        }

        /***********************************************
         * Gets the Rent date as a GregorianCalendar
         * @return Rent date as a GregorianCalendar
         ***********************************************/
        protected GregorianCalendar getRent(){
                return rented;
        }

        /***********************************************
         * Gets the Due date as a GregorianCalendar
         * @return Due date as a GregorianCalendar
         ***********************************************/
        protected GregorianCalendar getDue(){

                return back;
        }

        /***********************************************
         * Gets the Title.
         * @return The Title.
         ***********************************************/
        protected String getTitle(){

                return title;

        }

        /***********************************************
         * Gets the name of renter.
         * @return The name of renter.
         ***********************************************/
        protected String getNameOfRenter(){

                return nameOfRenter;

        }

        /***********************************************
         * Sets the name of renter.
         * @param The Title.
         ***********************************************/
        protected void setTitle(String title){

                 this.title = title;
        }

        /***********************************************
         * Sets the name of renter.
         * @param The name of renter.
         ***********************************************/
        protected void setnameOfRenter(String nameOfRenter){

                 this.nameOfRenter = nameOfRenter;
        }

        /***********************************************
         * Sets the rented date.
         * @param The rented date.
         ***********************************************/
        protected void setRented(GregorianCalendar x){

                 this.rented = x;
        }

        /***********************************************
         * Sets the rent date by String
         * @param The rent date by String
         ***********************************************/
        protected void setRented(String x) throws ParseException{
                 date = simple.parse(x);
                 rented.setTime(date);
        }

        /***********************************************
         * Sets the due date by String
         * @param due date by String
         ***********************************************/
        protected void setDueDate(String x) throws ParseException{
                 date = simple.parse(x);
                 rented.setTime(date);
        }

        /***********************************************
         * Sets the due date by String
         * @param due date by String
         ***********************************************/
        protected void setDueDate(GregorianCalendar back){

                 this.back = back;
        }

        /***********************************************
         * Gets the standard price
         * @param none
         ***********************************************/
        public double getPirce(){
                return 1.2;
        }

        /***********************************************
         * Gets the late price
         * @param none
         ***********************************************/
        public double getLatePirce(){
                return 2;
        }

        /***********************************************
         * Prints the DVD in a nice format
         * @param none
         ***********************************************/
        public String toString(){
                Calendar calBack = new GregorianCalendar();
                SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
                calBack = back;
                return  "Name: " + nameOfRenter + ",   Title: " + title +
                                ",   Rented on: " + this.getRentString() +
                                ",   Due back on: " + this.getBackString();

        }

}