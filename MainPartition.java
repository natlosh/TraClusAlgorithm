package boliu;

import java.io.File;

import boliu.TraClusterDoc.Parameter;

public class MainPartition {
		
	public static void main(String[] args) {
	 
		
		String[] pathnames; 
		//File f = new File("src/"); 
		File f = new File(args[0]);
		
				
		pathnames = f.list(); 
		for (String pathname:pathnames) {
			if (pathname.endsWith(".txt")) {
				TraClusterDoc tcd = new TraClusterDoc();
				tcd.onOpenDocument(args[0] +"/" + pathname);
				tcd.partitionOnly("out_" + pathname, false);
				//tcd.partitionOnly("trajectoryfiles/partitions_points_" + pathname, true);
				
			}
		}
		
				
		
	}
	
}
