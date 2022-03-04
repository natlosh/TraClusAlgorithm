package boliu;

import java.io.File;

import boliu.TraClusterDoc.Parameter;

public class MainArray {
		
	public static void main(String[] args) {
	 
		
		TraClusterDoc tcd = new TraClusterDoc();
		tcd.onOpenDocument(args[0]);
		tcd.onClusterGenerate(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				
				
			}
		}
		
				
		
	
	



