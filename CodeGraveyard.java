package boliu;

public class CodeGraveyard {

	//Exploring the lineseg arrays. When run inside ClusterGen these output the same, showing us that 
	//the m_lineSegmentPointArray works on the same index as the m_idArray
	
	
	System.out.println("seg array: " + m_lineSegmentPointArray.get(0));
	System.out.println("0: " + m_lineSegmentPointArray.get(0).getM_coordinate(0) + "1: " + m_lineSegmentPointArray.get(0).getM_coordinate(1));
	System.out.println("2: " + m_lineSegmentPointArray.get(0).getM_coordinate(2) + "3: " + m_lineSegmentPointArray.get(0).getM_coordinate(3));

	System.out.println("id array: " + m_idArray.get(0).trajectoryId + ", order: " + m_idArray.get(0).order);
	
	CMDPoint temppt = m_document.m_trajectoryList.get(0).getM_partitionPointArray().get(10);
	CMDPoint temppt2 = m_document.m_trajectoryList.get(0).getM_partitionPointArray().get(11);
	System.out.println("0: " + temppt.getM_coordinate(0) + "1: " + temppt.getM_coordinate(1));
	System.out.println("2: " + temppt2.getM_coordinate(0) +"3: " + temppt2.getM_coordinate(1));

	
}


// Loop through all dense components and list their details
for (int i = 0; i < generator.m_lineSegmentClusters.length; i ++) {
	System.out.println("segcluster: " + i + ", n line segments: " 
+ generator.m_lineSegmentClusters[i].nLineSegments + ", nclusterpoints: " 
			+ generator.m_lineSegmentClusters[i].nClusterPoints + ", candidate " 
+ generator.m_lineSegmentClusters[i].candidatePointList.size() + ", trajs " 
			+ generator.m_lineSegmentClusters[i].nTrajectories 
			+ ", enabled: " + generator.m_lineSegmentClusters[i].enabled
			+ ", failreason: " + generator.m_lineSegmentClusters[i].failreason);

	//e.g./
	
	/* segcluster: 58, n line segments: 5, nclusterpoints: 0, candidate 0, trajs 5, enabled: false, failreason: insufficient points
	segcluster: 59, n line segments: 5, nclusterpoints: 0, candidate 0, trajs 4, enabled: false, failreason: insufficient trajectories
	
	*/