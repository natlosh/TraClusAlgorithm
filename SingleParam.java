package boliu;
import boliu.TraClusterDoc.Parameter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SingleParam {
		
	public static void main(String[] args) {
		
		TraClusterDoc tcd = new TraClusterDoc();
		tcd.onOpenDocument(args[0]);
		
		Parameter p = tcd.onEstimateParameter();
		if (p != null) {				
			
			
			
			System.out.println("Based on the algorithm, the suggested parameters for are:\n"+"eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
		}  
		
		
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(args[1]);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			
			bw.write("for " + args[0] + ", epsParam:"+p.epsParam +"   minLnsParam:"+p.minLnsParam);
				
			
			}
		 catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
}
	
	

