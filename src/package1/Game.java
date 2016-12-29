package package1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Game extends DVD{

         private static final long serialVersionUID = 1L;
         /**The player type **/
         private PlayerType player;

         /************************************************
          * Default constructor creates a blank game
          ************************************************/
         public Game(){
                 super();

         }

         /************************************************
          * Constructor with parameters
          * @param bought
          * @param back
          * @param title
          * @param nameOfRenter
          * @param player
          *************************************************/
         public Game(GregorianCalendar bought, GregorianCalendar back,
                         String title, String nameOfRenter, PlayerType player){
                 super(bought, back, title, nameOfRenter);
                 this.player = player;

         }

         /************************************************
          * Gets the player type
          * @return The Player type
          ************************************************/
         public PlayerType getPlayer(){
                 return player;
         }

         /************************************************
          * Sets the player type
          * @param The Player type
          ************************************************/
         public void setPlayer(PlayerType player){
                 this.player = player;
         }

         /**********************************************
          * Gets the standard price
          * @return the standard price
          **********************************************/
         public double getPirce(){
                        return 5;
                }

         /**********************************************
          *Gets the late price
          *@return the late price
          **********************************************/
         public double getLatePirce(){
                  return 10;
         }

         /**********************************************
          *Prints the game in a nice format
          *@return The game facts
          **********************************************/
         public String toString(){

                Calendar calRent = new GregorianCalendar();
                Calendar calBack = new GregorianCalendar();
                SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
                calRent = rented;
                calBack = back;



                        return  "Name: " + nameOfRenter + ",   Title: " + title +
                        ",   Rented on: " +
                        simple.format(calRent.getTime()) +
                        ",   Due back on: " + simple.format(calBack.getTime()) +
                        ",   Game Console: " + player.name();


                }


}