package boliu;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import boliu.TraClusterDoc.Parameter;

public class MainGrid {
		
	public static void main(String[] args) {
		
				long startTimeOverall = System.currentTimeMillis();
				int[] epses = {15, 25};
				int[] minlnses = {20, 50, 100};
				
				FileOutputStream fos = null;
				BufferedWriter bw = null;
				OutputStreamWriter osw = null;
				
			    for (int i = 0; i < epses.length; ++i) {
			      for(int j = 0; j < minlnses.length; ++j) {
			        String params = "" + epses[i] + "_" + minlnses[j];
			        TraClusterDoc tcd = new TraClusterDoc();
					long startTime = System.currentTimeMillis();
			        tcd.onOpenDocument(args[0]);
			        System.out.println("eps: " + epses[i] + ", minlns: " + minlnses[j]);
			        tcd.onClusterGenerate(args[1] + params, epses[i], minlnses[j]);
			        long endTime2 = System.currentTimeMillis();
					System.out.println("These Params took " + (endTime2 - startTime)/60000 + " mins and time since start is " + (endTime2 - startTimeOverall)/60000 + " mins\n");
					
					
			
			        
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
	
	

