package boliu;

import java.io.File;

import boliu.TraClusterDoc.Parameter;

public class MainLoop {
		
	public static void main(String[] args) {
	 
		
		String[] pathnames; 
		//File f = new File("src/"); 
		File f = new File("all_input_files/");
		
				
		pathnames = f.list(); 
		for (String pathname:pathnames) {
			if (pathname.endsWith(".txt")) {
				TraClusterDoc tcd = new TraClusterDoc();
				tcd.onOpenDocument("all_input_files/" + pathname);
				tcd.partitionOnly("trajectoryfiles/partitions_short_" + pathname, false);
				tcd.partitionOnly("trajectoryfiles/partitions_points_" + pathname, true);
				
			}
		}
		
				
		
	}
	
}
