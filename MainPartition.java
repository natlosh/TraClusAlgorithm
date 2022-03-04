package boliu;

import java.io.File;

import boliu.TraClusterDoc.Parameter;

public class MainPartition {
		
	public static void main(String[] args) {
	 
		
		String[] pathnames; 
		File f = new File("all_input_files/"); 
		//File f = new File(args[0]);
		
				
		pathnames = f.list(); 
		for (String pathname:pathnames) {
			if (pathname.contains("in_")) {
				TraClusterDoc tcd = new TraClusterDoc();
				tcd.onOpenDocument("all_input_files/" + pathname);
				tcd.partitionOnly();
				//tcd.partitionOnly("trajectoryfiles/partitions_points_" + pathname, true);
				
			}
		}
		
				
		
	}
	
}
