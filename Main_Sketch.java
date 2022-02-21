package boliu;

import boliu.TraClusterDoc.Parameter;

public class Main_Sketch {
		
	public static void main(String[] args) {
	/*	
		if (args.length == 4) {
			TraClusterDoc tcd = new TraClusterDoc();
			tcd.onOpenDocument(args[0]);
			tcd.onClusterGenerate(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3])); // 25, 5~7
		} else if (args.length == 2) {
			TraClusterDoc tcd = new TraClusterDoc();
			tcd.onOpenDocument(args[0]);

			Parameter p = tcd.onEstimateParameter();
			if (p != null) {				
				System.out.println("Based on the algorithm, the suggested parameters are:\n"+"eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
			} 
			tcd.onClusterGenerate(args[1], p.epsParam, p.minLnsParam);									
		} else {
			System.out.println("Please give me 2 or 4 input parameters! \n "
					+ "If you have no idea how to decide eps and minLns, just feed in 2 parameters (inputFilePath, outputFilePath):\n"
					+ "--e.g. java boliu.Main deer_1995.tra testOut.txt \n"
					+ "If you know the two parameters, just feed in all the 4 parameters (inputFilePath, outputFilePath, eps, minLns)"
					+ "--e.g. java boliu.Main deer_1995.tra testOut.txt 29 8 \n");
		}
*/
/**
 * To use the GUI, Remove the below comment and comment out the above section of code
 * An adjustable GUI is to be added.
 */
 
		TraClusterDoc tcd = new TraClusterDoc();
		
		//tcd.onOpenDocument("src/deer_1995.tra");		
		//tcd.onClusterGenerate("testDeerResult.txt", 29, 8);
		
		//tcd.onOpenDocument("src/hurricane1950_2006.tra");
		//tcd.onClusterGenerate("testHurricaneResult.txt", 32, 8);
		
		tcd.onOpenDocument("src/elk_1993.tra");
		//tcd.partitionOnly("elk_partitions.txt", false);
		tcd.onClusterGenerate("elkruns.txt", 25, 5);// 25, 5~7
	
		//Fake 3D test datasets
		
		//tcd.onOpenDocument("src/in_fake_elk.txt");
		//tcd.onClusterGenerate("out_3d_elk.txt", 25, 5);// 25, 5~7
		
		//tcd.onOpenDocument("src/deer3d_in.txt");		
		//tcd.onClusterGenerate("deer3d_out2.txt", 29, 8);
		
		//our data
		//tcd.onOpenDocument("src/sleep_28_full.txt");		 //takes about 5 seconds
		//tcd.onClusterGenerate("out_sleep_28_full.txt", 36, 5);	//takes about a minute 
		
		
		//tcd.onOpenDocument("src/in_INS_TRACLUS_xy.txt");		 //takes about 10 seconds
		//tcd.onClusterGenerate("cheese.txt", 20, 475);	//failing I think
		//System.out.print("done");
		//System.out.print(tcd.m_trajectoryList.get(0).getM_pointArray().get(0).getM_coordinate(2));
		//System.out.print(tcd.m_clusterList.get(0).getM_PointArray().get(0).getM_nDimensions());
		
		/*
		Parameter p = tcd.onEstimateParameter();
		if (p != null) {				
			System.out.println("Based on the algorithm, the suggested parameters are:\n"+"eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
		} 
		
		*/
		//MainFrame mf = new MainFrame(tcd.m_trajectoryList, tcd.m_clusterList);
		
		
		
	}
	
}
