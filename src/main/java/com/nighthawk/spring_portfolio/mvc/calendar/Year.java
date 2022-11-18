package com.nighthawk.spring_portfolio.mvc.calendar;

/** Simple POJO 
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private int month;
   private int day;
   private boolean isLeapYear;
   private int firstDayOfYear;

   // zero argument constructor
   public Year() {} 

   /* year getter/setters */
   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
      this.setFirstDayOfYear(year);
   }

   public void setDate(int year, int month, int day) {
      this.year = year;
      this.month = month;
      this.day = day;
   }

   /* isLeapYear getter/setters */
   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }
   private void setIsLeapYear(int year) {  // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"isLeapYear\": "  +this.isLeapYear+ " }" );
   }	

   /* standard toString placeholder until class is extended */
   public String toString() { 
      return isLeapYearToString(); 
   }

   // firstDayOfYear getter/setter
   public int getFirstDayOfYear(int year) {
      return APCalendar.firstDayOfYear(year);
   }
   private void setFirstDayOfYear(int year) {
      this.firstDayOfYear = APCalendar.firstDayOfYear(year);
   }

   public String firstDayOfYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"firstDayOfYear\": "  +this.firstDayOfYear+ " }" );
   }	

   public String dayOfYearToString (){
      int dayOfYear = APCalendar.dayOfYear(this.month, this.day, this.year);
      return ( "{ \"year\": "  + this.year +  ", " + "\"month\": " + this.month + ", " + "\"day\": "  + this.day  + ", " + "\"dayOfYear\": "  + dayOfYear + " }" );
   }

   public String dayOfWeekToString (){
      int dayOfWeek = APCalendar.dayOfWeek(this.month, this.day, this.year);
      return ( "{ \"year\": "  + this.year +  ", " + "\"month\": " + this.month + ", " + "\"day\": "  + this.day  + ", " + "\"dayOfWeek\": "  + dayOfWeek + " }" );
   }

   public String leapYearsToString (int year1, int year2){
      int leapYears = APCalendar.numberOfLeapYears(year1, year2);
      return ( "{ \"year1\": "  + year1 +  ", " + "\"year2\": " + year2 + ", " + "\"numberOfLeapYears\": "  + leapYears + " }" );
   }

   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2022);
      System.out.println(year);
   }
}