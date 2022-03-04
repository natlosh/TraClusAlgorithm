package boliu;



public class looptest {
	  public static void main(String[] args) {
	    int[] yournums = {1, 2, 3, 4};
	    int[] myNumbers = {5, 6, 7} ;
	    for (int i = 0; i < yournums.length; ++i) {
	      for(int j = 0; j < myNumbers.length; ++j) {
	        System.out.println(yournums[i]);
	        System.out.println(myNumbers[j]);
	      }
	    }
	  }
	}
