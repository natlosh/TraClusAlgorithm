package boliu;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class TraClusterDoc {
	
	public int m_nDimensions;
	public int m_nTrajectories;
	public int m_nClusters;
	public double m_clusterRatio;
	public int m_maxNPoints;
	public ArrayList<Trajectory> m_trajectoryList;
	public ArrayList<Cluster> m_clusterList;
	public String inputFile;
	public TraClusterDoc() {
			
		m_nTrajectories = 0;
		m_nClusters = 0;
		m_clusterRatio = 0.0;	
		m_trajectoryList = new ArrayList<Trajectory>();
		m_clusterList = new ArrayList<Cluster>();
	}
	
	public class Parameter {
		double epsParam;
		int minLnsParam;
	}
	
	boolean onOpenDocument(String inputFileName) {
		
		int nDimensions = 2;		// default dimension = 2
		int nTrajectories = 0;
		int nTotalPoints = 0;		//no use
		int trajectoryId;
		int nPoints;
		double value;
		inputFile = inputFileName;
		
		
		DataInputStream in;
		BufferedReader inBuffer = null;
		try {
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFileName)));
			
			inBuffer = new BufferedReader(new InputStreamReader(in));			
			nDimensions = Integer.parseInt(inBuffer.readLine()); // the number of dimensions
			m_nDimensions = nDimensions;
			nTrajectories = Integer.parseInt(inBuffer.readLine()); // the number of trajectories
			m_nTrajectories = nTrajectories;
			
			m_maxNPoints = -1; // initialize for comparison
			
			// the trajectory Id, the number of points, the coordinate of a point ...
			for (int i = 0; i < nTrajectories; i++) {				
	
				String str = inBuffer.readLine();
				
				Scanner sc = new Scanner(str); 
				sc.useLocale(Locale.US);
				
				trajectoryId = sc.nextInt(); //trajectoryID
				nPoints = sc.nextInt(); // number of points in the trajectory
				
				if (nPoints > m_maxNPoints) {
					m_maxNPoints = nPoints;
				}
				nTotalPoints += nPoints;
				Trajectory pTrajectoryItem = new Trajectory(trajectoryId, nDimensions);		
				for (int j = 0; j < nPoints; j++) {
					CMDPoint point = new CMDPoint(nDimensions);   // initialize the CMDPoint class for each point
					
					for (int k = 0; k < nDimensions; k++) {						
						value = sc.nextDouble();
						point.setM_coordinate(k, value);						
					}
					pTrajectoryItem.addPointToArray(point);				
				}
				
				m_trajectoryList.add(pTrajectoryItem);
			}					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Unable to open input file");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				inBuffer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        		
		return true;
	}
	

	
	boolean onClusterGenerate(String clusterFileName, double epsParam, int minLnsParam) {
//////////////////////////////////////////////////still to be written
		long startTime = System.currentTimeMillis();
		ClusterGen generator = new ClusterGen(this);
		
		if(m_nTrajectories == 0) {
			System.out.println("Load a trajectory data set first");
		}
		
		// FIRST STEP: Trajectory Partitioning
		if (!generator.partitionTrajectory())
		{
			System.out.println("Unable to partition a trajectory\n");
			return false;
		} 
		long endTime = System.currentTimeMillis();
		System.out.println("Successfully Partitioned " +  m_trajectoryList.size() + " trajectories in " + (endTime - startTime)/1000 + " secs");
		
		
		//System.out.println(m_trajectoryList.get(0).getM_nPartitionPoints()); 
		//System.out.println(m_trajectoryList.get(0).getM_partitionPointArray().size());
		
		//System.out.println(m_trajectoryList);
		//System.out.println("Partition point array of first element" + m_trajectoryList.get(0).getM_partitionPointArray());
		
		
		// SECOND STEP: Density-based Clustering
		if (!generator.performDBSCAN(epsParam, minLnsParam))
		{
			System.out.println("Unable to perform the DBSCAN algorithm\n");
			return false;
		}
		long endTimeDB = System.currentTimeMillis();
		System.out.println("Successful DBSCAN in " + (endTimeDB - startTime)/1000 + " secs, found " + generator.m_currComponentId + " dense regions.");
		
		
		// THIRD STEP: Cluster Construction
		if (!generator.constructCluster())
		{
			System.out.println( "Unable to construct a cluster\n");
			return false;
		}
		long endTimeCC = System.currentTimeMillis();
		System.out.println("Finished description of cluster representative trajectories in " + (endTimeCC - startTime)/1000 + " secs");
		
		int pointfails = 0;
		int trajectoryfails = 0; 
		int acceptedclusters = 0;
		for (int i = 0; i < generator.m_lineSegmentClusters.length; i ++) {
			
			if (generator.m_lineSegmentClusters[i].failreason == "insufficient trajectories") {
				trajectoryfails++;				
			}
			else if (generator.m_lineSegmentClusters[i].failreason == "insufficient points") {
				pointfails++;
			}
			else {acceptedclusters++;
			}
			}
		
		System.out.println("Of the " + generator.m_lineSegmentPointArray.size() + " points, " + generator.m_noisecounter + " were noise.");
		System.out.println("Of the " + generator.m_lineSegmentClusters.length + " regions, " + trajectoryfails + " were rejected due to insufficient trajectories, and " + pointfails + " were rejected due to insufficient representative points."); 
		System.out.println("This leaves " + acceptedclusters + " clusters found.");
		// System.out.println("quick check: " + generator.m_currComponentId + " == " + generator.m_lineSegmentClusters.length + "and " + acceptedclusters + " == " + m_clusterList.size());		
		
		//Segment cluster details 
		
		for (int i = 0; i < generator.m_lineSegmentClusters.length; i ++) {
			System.out.println("segcluster: " + i + ", n line segments: " 
		+ generator.m_lineSegmentClusters[i].nLineSegments + ", nclusterpoints: " 
					+ generator.m_lineSegmentClusters[i].nClusterPoints + ", candidate " 
		+ generator.m_lineSegmentClusters[i].candidatePointList.size() + ", trajs " 
					+ generator.m_lineSegmentClusters[i].nTrajectories 
					+ ", enabled: " + generator.m_lineSegmentClusters[i].enabled
					+ ", failreason: " + generator.m_lineSegmentClusters[i].failreason);
		}
		
		
		
		
		// Write to standard out
				
		for (int i = 0; i <m_clusterList.size(); i++) {
			//m_clusterList.
			System.out.println(m_clusterList.get(i).getM_clusterId());
			//System.out.println("number trajectories: " + m_clusterList.get(i).getDensity()+"\n");
			for (int j = 0; j<m_clusterList.get(i).getM_PointArray().size(); j++) {
				double x = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(0);
				double y = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(1);
				//NJA
				if (m_nDimensions == 3){
					double z = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(2);
					System.out.print("   "+ x +" "+ y +" "+ z + "   ");
					}
			
			 else { 
				System.out.print("   "+ x +" "+ y + "   ");
			}
			}
			System.out.println();
		}
		
		// Write to output file
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(clusterFileName);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			
			bw.write(inputFile + "\n" + "epsParam:"+epsParam +"   minLnsParam:"+minLnsParam+"\n");
			bw.write("Of the " + generator.m_lineSegmentPointArray.size() + " points, " + generator.m_noisecounter + " were noise.");
			bw.write("Of the " + generator.m_lineSegmentClusters.length + " regions, " + trajectoryfails + " were rejected due to insufficient trajectories, and " + pointfails + " were rejected due to insufficient representative points."); 
			bw.write(" This leaves " + acceptedclusters + " clusters found.\n");
			for (int i = 0; i < m_clusterList.size(); i++) {
				// m_clusterList.
				bw.write("\nclusterID: "+ m_clusterList.get(i).getM_clusterId() + "  Points Number:  " + m_clusterList.get(i).getM_PointArray().size() + "  Segment Points: " + m_clusterList.get(i).getM_nLineSegments() + " from " + m_clusterList.get(i).getDensity() +  " trajectories\n");
				
				for (int j = 0; j < m_clusterList.get(i).getM_PointArray().size(); j++) {
					double x = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(0);
					double y = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(1);
					// NJA
					if (m_nDimensions == 3) {
						double z = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(2);
						bw.write(x+" "+y+" " + z + "   ");
					} else {
					bw.write(x+" "+y + "   ");
				}
				}
			}						
			
		} catch (FileNotFoundException e) {
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
		
		
		return true;		
	}
	
	Parameter onEstimateParameter() {
		Parameter p = new Parameter();
		ClusterGen generator = new ClusterGen(this);
		if (!generator.partitionTrajectory()) {
			System.out.println("Unable to partition a trajectory\n");
			return null;
		}
		if (!generator.estimateParameterValue(p)) {
			System.out.println("Unable to calculate the entropy\n");
			return null;
		}
		return p;
	}

 


boolean partitionOnly(String clusterFileName, boolean flag) {
	long startTime = System.currentTimeMillis();
	ClusterGen generator = new ClusterGen(this);
	
	if(m_nTrajectories == 0) {
		System.out.println("Load a trajectory data set first");
	}
	
	// FIRST STEP: Trajectory Partitioning
	if (!generator.partitionTrajectory())
	{
		System.out.println("Unable to partition a trajectory\n");
		return false;
	} 
	
	System.out.println("Successfully Partitioned " +  m_trajectoryList.size() + " trajectories!");
	/*
	FileOutputStream fos = null;
	BufferedWriter bw = null;
	OutputStreamWriter osw = null;
	
	try {
		fos = new FileOutputStream(clusterFileName);
		osw = new OutputStreamWriter(fos);
		bw = new BufferedWriter(osw);
		
		for (int i = 0; i < m_trajectoryList.size(); i++) {
			bw.write("trajID: " + m_trajectoryList.get(i).getM_trajectoryId()+" npoints: " + m_trajectoryList.get(i).getM_nPoints() + " npartitionpoints: " + m_trajectoryList.get(i).getM_nPartitionPoints()+"\r\n");
			if (flag) { for ( int k = 0; k < m_trajectoryList.get(i).getM_nPartitionPoints(); k++) {
				double x = m_trajectoryList.get(i).getM_partitionPointArray().get(k).getM_coordinate(0);
				double y = m_trajectoryList.get(i).getM_partitionPointArray().get(k).getM_coordinate(1);
				if (m_nDimensions == 3) {
					double z = m_trajectoryList.get(i).getM_partitionPointArray().get(k).getM_coordinate(2);
					bw.write(x+" "+y+" " + z + "   ");
				} else {
				bw.write(x+" "+y + "   "); 
				}
				bw.write("\r\n");
			}
			
		}
		}		
		
		
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
			
			
			
	
	
	
	
	long endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime)/1000 + " secs");
	
	System.out.println(m_trajectoryList.get(0).getM_nPartitionPoints()); 
	System.out.println(m_trajectoryList.get(0).getM_partitionPointArray().size());
	*/
	
	return true;
}


}