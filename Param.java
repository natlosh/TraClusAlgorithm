package boliu;
import boliu.TraClusterDoc.Parameter;
import java.io.File;

public class Param {
		
	public static void main(String[] args) {
		
		String[] pathnames; 
		//File f = new File("src/"); 
		File f = new File("inputs");
		
				
		pathnames = f.list(); 
		for (String pathname:pathnames) {
			if (pathname.endsWith(".txt")) {
				System.out.println(pathname);
				 TraClusterDoc tcd = new TraClusterDoc();
				tcd.onOpenDocument("inputs/" + pathname);
				
				Parameter p = tcd.onEstimateParameter();
				if (p != null) {				
					System.out.println("Based on the algorithm, the suggested parameters for " + pathname +" are:\n"+"eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
				}  
			} 
			
			
			
			}
		}
		
		
}
	
	

