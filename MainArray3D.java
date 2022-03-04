package boliu;

import java.io.File;

import boliu.TraClusterDoc.Parameter;

public class MainArray3D {
		
	public static void main(String[] args) {
	 
		
		TraClusterDoc tcd = new TraClusterDoc();
		tcd.onOpenDocument(args[0]);
		tcd.partition_and_group(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				 
				
			}
		}
		
				
		
	
	



