package boliu;

import boliu.TraClusterDoc.Parameter;

public class MainSep {
		
	public static void main(String[] args) {
		
				TraClusterDoc tcd = new TraClusterDoc();
				tcd.onOpenDocument(args[0]);
				System.out.println("Setup done!");
				
				int[] epses = {15, 20, 25, 30, 35, 40};
				int[] minlnses = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200};
				
			    for (int i = 0; i < epses.length; ++i) {
			      for(int j = 0; j < minlnses.length; ++j) {
			        String params = "" + epses[i] + "_" + minlnses[j];
			    	System.out.println("eps: " + epses[i] + ", minlns: " + minlnses[j]);
			        tcd.onClusterGenerate(args[1] + params, epses[i], minlnses[j]);
			        
			      }
			    }
				
				
			
	
			
		
		System.out.println("Have a good day!");
		
		}
}
	

/**
 * To use the GUI, Remove the below comment and comment out the above section of code
 * An adjustable GUI is to be added.
 */
/*
		TraClusterDoc tcd = new TraClusterDoc();
		
		//tcd.onOpenDocument("src/deer_1995.tra");		
		//tcd.onClusterGenerate("testDeerResult.txt", 29, 8);
		
		//tcd.onOpenDocument("src/hurricane1950_2006.tra");
		//tcd.onClusterGenerate("testHurricaneResult.txt", 32, 6); 
		
		tcd.onOpenDocument("src/elk_1993.tra");
		tcd.onClusterGenerate("testElkResult.txt", 25, 5);// 25, 5~7
		
		
		
		Parameter p = tcd.onEstimateParameter();
		if (p != null) {
			System.out.println("Based on the algorithm, the suggested parameters are:\n" + "eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
		}
*/
	
	

