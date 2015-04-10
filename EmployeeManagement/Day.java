/**
 * Day of the week as an enumerated type
 * @author Abraham
 *
 */
	public enum Day {

	    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5),
	    SATURDAY(6), SUNDAY(7), INVALID(-1);
	    private int dayIndex;

	    private Day(int day) {
	        dayIndex = -1;
	        if (day >= 1 && day <= 7) {
	            dayIndex = day;
	        }
	    }

	    public static boolean isValid(int day) {
	        return day >= 1 && day <= 7 || day == -1;
	    }

	    public String toString() {
	        switch (dayIndex) {
	            case 1:
	                return "Monday";
	            case 2:
	                return "Tuesday";
	            case 3:
	                return "Wednesday";
	            case 4:
	                return "Thursday";
	            case 5:
	                return "Friday";
	            case 6:
	                return "Saturday";
	            case 7:
	                return "Sunday";
	            default:
	                return "Invalid";
	        }
	    }

	    public static Day getDay(int d) {
	        switch (d) {
	            case 1:
	                return Day.MONDAY;
	            case 2:
	                return Day.TUESDAY;
	            case 3:
	                return Day.WEDNESDAY;
	            case 4:
	                return Day.THURSDAY;
	            case 5:
	                return Day.FRIDAY;
	            case 6:
	                return Day.SATURDAY;
	            case 7:
	                return Day.SUNDAY;
	            default:
	                return Day.INVALID;
	        }
	    }

	    
	    /** Sample Program exceution
	    public static void main(String args[]) {

	        Day day01 = Day.TUESDAY;
	        Day day02 = Day.getDay(5);

	        System.out.println("Day #1: " + day01);
	        System.out.println("Day #2: " + day02);
	        
	        System.out.println("Days of the week:");
	        for (Day d : Day.values()) {
	            System.out.print(d + "  ");
	        }
	        System.out.println();
	    }*/
	}
