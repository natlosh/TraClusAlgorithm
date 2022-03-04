package boliu;

import boliu.TraClusterDoc.Parameter;

public class Main {
		
	public static void main(String[] args) {
		
		if (args.length == 5) {
			TraClusterDoc tcd = new TraClusterDoc();
			tcd.onOpenDocument(args[0]);
			System.out.println("Setup done!");
			
						
			tcd.onClusterGenerate(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])); // 25, 5~7
			if (args[4] == "1") {
				MainFrame mf = new MainFrame(tcd.m_trajectoryList, tcd.m_clusterList);
		}
		}
			else if (args.length == 4) {
				TraClusterDoc tcd = new TraClusterDoc();
				tcd.onOpenDocument(args[0]);
				System.out.println("Setup done!");
				tcd.onClusterGenerate(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])); // 25, 5~7
				
			}
			else if (args.length == 2) {
			TraClusterDoc tcd = new TraClusterDoc();
			tcd.onOpenDocument(args[0]);
			System.out.println("Setup done!");
			Parameter p = tcd.onEstimateParameter();
			if (p != null) {				
				System.out.println("Based on the algorithm, the suggested parameters are:\n"+"eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
			} 	System.out.println("Please run again with these parameters.");
												
		} else {
			System.out.println("Please give me 2, 4 or 5 input parameters! \n "
					+ "If you have no idea how to decide eps and minLns, just feed in 2 parameters (inputFilePath, outputFilePath):\n"
					+ "--e.g. java boliu.Main deer_1995.tra testOut.txt 1 \n"
					+ "If you know the two parameters, just feed in all the 4 parameters (inputFilePath, outputFilePath, eps, minLns)"
					+ "--e.g. java boliu.Main deer_1995.tra testOut.txt 29 8 \n"
					+ "or optionally add a fifth 1 or 0 to indicate running the draw sketch prgoram");
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
	
	

