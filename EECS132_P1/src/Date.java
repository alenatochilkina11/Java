/*Alena Tochilkina
 * Project 1
 * October 4, 2019
 * The Date class creates Date methods that are used in other classes of the program.
 */

public class Date{
  
  /*VARIABLES*/
  
  //this stores the day value for the Date
  private int day=0;
  
  //this stores the month value for the Date
  private int month=0;
  
  //this stores the year value for the Date
  private int year=0;

  
  /*CONSTRUCTORS*/
  
  //Sets inputs for Date in the format (day, month, year)
  public Date(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }
  
  /*METHODS*/
  
  // a. Returns the day of the date
  public int getDay(){
    //the day values can only be between 1 and 31
    if (day>0 && day<32){ 
      return day;
    }
    else {
      //if the value of day is not between 1 and 31, returns 0
      return 0; 
    }
  }
  
  // b. Returns the month of the date
  public int getMonth(){
    // the month values can only be between 1 and 12
    if (month>0 && month<13){ 
      return month;
    }
    else {
      // if the value is not between 1 and 12, returns 0
      return 0; 
    }
  }
  
  /* EXTRA Sets the year of the date
   * used in renewPolicy method of InsurancePolicy Class
   */
  public void setYear( int year){
    this.year = year;
  }
  
 /*c. Returns the year of the date*/
  public int getYear(){
    return year;
  }
 
 /*d. Compares a date with the input date.
  * if the dates are the same, returns 0;
  * if the other date comes after the input date, returns 1;
  * if the other date comes earlier than the input date, returs -1;
  */
  public int compareTo(Date date){
    if (this.getYear() == date.getYear()) {
      if(this.getMonth() == date.getMonth()){
        if(this.getDay() == date.getDay()){
          //here the dates are completely the same
          return 0;
        } 
        //if year and month are the same, compares days
        else if(this.getDay() > date.getDay()){ 
          return 1; 
        }
      }
        //if the years are the same, compares by months
        else if (this.getMonth() > date.getMonth()){ 
         return 1; 
        }
     }
      //if years are different, compares which year is larger
      else if (this.getYear() > date.getYear()){ 
        return 1;
     }
    //if all the conditions above fail, returns -1
    return -1; 
  }
 
 /*OVERRIDEN METHODS*/
  
  // a. Checks if the dates are the same
  @Override
  public boolean equals(Object date){
    Date date1 = (Date) date;
    if (this.getYear() == date1.getYear()){
      if(this.getMonth() == date1.getMonth()){
        if(this.getDay() == date1.getDay()){
          return true;
        }
      }
    }
     return false;
  }
  
  // Returns the date in a String format like "12 May 2000"
  public String toString(){
    //creates new string s to store the date
    String s = new String();
    if (this.getMonth() == 1)
     s= this.getDay() + " Jan " + this.getYear(); //for January
    else if (getMonth() == 2)
     s= this.getDay() + " Feb " + this.getYear(); //for February 
    else if (this.getMonth() == 3)
     s= this.getDay() + " Mar " + this.getYear(); //for March
    else if (this.getMonth() == 4)
     s= this.getDay() + " Apr " + this.getYear(); //for April
    else if (this.getMonth() == 5)
     s= this.getDay() + " May " + this.getYear(); //for May
    else if (this.getMonth() == 6)
     s= this.getDay() + " Jun " + this.getYear(); //for June
    else if (this.getMonth() == 7)
     s= this.getDay() + " Jul " + this.getYear(); //for July
    else if (this.getMonth() == 8)
     s= this.getDay() + " Aug " + this.getYear(); // for August
    else if (this.getMonth() == 9)
     s=  this.getDay() + " Sep " + this.getYear(); // for September
    else if (this.getMonth() == 10)
     s= this.getDay() + " Oct " + this.getYear(); //for October
    else if (this.getMonth() == 11)
     s= this.getDay() + " Nov " + this.getYear(); //for November
    else if (this.getMonth() == 12)
      s= this.getDay() + " Dec " + this.getYear(); //for December
    else 
      s= "DNE";
    return s;
  }
}


