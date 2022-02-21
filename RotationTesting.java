package boliu;

import java.lang.Math;

public class RotationTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		double rot_angle = Math.toRadians(90);
		double my_sin = Math.sin(rot_angle) ;
		double my_cos = Math.cos(rot_angle);
		double result = rotate_z_axis_xval(1, 0, 0, my_cos, my_sin);
		System.out.print(result); 
		double resulty = rotate_z_axis_yval(1, 0, 0, my_cos, my_sin);
		System.out.print(resulty); 
		double resultz = rotate_z_axis_zval(1, 0, 0, my_cos, my_sin);
		System.out.print(resultz);
	}



// 2D Case from code:
	// the 2D case requires the inputs as cosTheta, sinTheta already 

private double GET_X_ROTATION(double _x, double _y, double _cos, double _sin) {
	return ((_x)*(_cos) + (_y)*(_sin));
}
private double GET_Y_ROTATION(double _x, double _y, double _cos, double _sin) {
	return  (-(_x)*(_sin) + (_y)*(_cos));
}
private double GET_X_REV_ROTATION(double _x, double _y, double _cos, double _sin) {
	return ((_x)*(_cos) - (_y)*(_sin));
}
private double GET_Y_REV_ROTATION(double _x, double _y, double _cos, double _sin) {
	return ((_x)*(_sin) + (_y)*(_cos));
}


// 3D Case: elemental rotation, need to specify an axis

//requires angles input as costheta, sintheta
	private double rotate_x_axis_xval(double x, double y, double z, double cos, double sin) {
		return(x); 
	}
	
	private double rotate_x_axis_yval(double x, double y, double z, double cos, double sin) {
		return(y*cos - z*sin); 
	}
	private double rotate_x_axis_zval(double x, double y, double z, double cos, double sin) {
		return(y*sin + z*cos); 
	}
	
	
	private double rotate_y_axis_xval(double x, double y, double z, double cos, double sin) {
		return(x*cos + z*sin); 
	}
	private double rotate_y_axis_yval(double x, double y, double z, double cos, double sin) {
		return(y); 
	}
	private double rotate_y_axis_zval(double x, double y, double z, double cos, double sin) {
		return((-x)*sin + z*cos); 
	}
		
		
	private static double rotate_z_axis_xval(double x, double y, double z, double cos, double sin) {
		return(x*cos - y*sin); 
	}
	private static double rotate_z_axis_yval(double x, double y, double z, double cos, double sin) {
		return(x*sin + y*cos); 
	}		
	private static double rotate_z_axis_zval(double x, double y, double z, double cos, double sin) {
		return(z); 
	}
	
	


// 3D Case using three angles: alpha, beta, gamma rotations around z, y, x axes 
//requires input as the angles in radians, which are then converted to the sin/cos versions. 
	//Mainly because it was slightly easier to write out this way. 
	
private double intrinsic_rotation_xval(double x, double y, double z, double alpha, double beta, double gamma) {
	// R = R_z(alpha)R_y(beta)R_x(gamma) 
	// intrinsic rotation with Tait-Bryan angles of alpha, beta, gamma about axes z, y, x
	double cos_alpha = Math.cos(alpha);
	double cos_beta = Math.cos(beta);
	double cos_gamma = Math.cos(gamma);
	double sin_alpha = Math.sin(alpha);
	double sin_beta = Math.sin(beta);
	double sin_gamma = Math.sin(gamma);
	
	return(x*cos_alpha*cos_beta + y*(cos_alpha*sin_beta*sin_gamma - sin_alpha*cos_gamma) + z*(cos_alpha*sin_beta*cos_gamma + sin_alpha*sin_gamma));
}
	
private double intrinsic_rotation_yval(double x, double y, double z, double alpha, double beta, double gamma) {
	// R = R_z(alpha)R_y(beta)R_x(gamma) 
	// intrinsic rotation with Tait-Bryan angles of alpha, beta, gamma about axes z, y, x

	double cos_alpha = Math.cos(alpha);
	double cos_beta = Math.cos(beta);
	double cos_gamma = Math.cos(gamma);
	double sin_alpha = Math.sin(alpha);
	double sin_beta = Math.sin(beta);
	double sin_gamma = Math.sin(gamma); 	
	
	return(x*sin_alpha*cos_beta + y*(sin_alpha*sin_beta*sin_gamma + cos_alpha*cos_gamma) + z*(sin_alpha*sin_beta*cos_gamma - cos_alpha*sin_gamma));
}



private double intrinsic_rotation_zval(double x, double y, double z, double alpha, double beta, double gamma) {
	// R = R_z(alpha)R_y(beta)R_x(gamma) 
	// intrinsic rotation with Tait-Bryan angles of alpha, beta, gamma about axes z, y, x

	double cos_alpha = Math.cos(alpha);
	double cos_beta = Math.cos(beta);
	double cos_gamma = Math.cos(gamma);
	double sin_alpha = Math.sin(alpha);
	double sin_beta = Math.sin(beta);
	double sin_gamma = Math.sin(gamma); 	
	
	return (x*(-sin_beta) + y*(cos_beta*sin_gamma) + z*(cos_beta*cos_gamma));
}


private double extrinsic_rotation_xval(double x, double y, double z, double alpha, double beta, double gamma) {
	// R = R_z(gamma)R_y(beta)R_x(alpha)
	// improper Euler angles alpha, beta, gamma about axes x, y, z

	double cos_alpha = Math.cos(alpha);
	double cos_beta = Math.cos(beta);
	double cos_gamma = Math.cos(gamma);
	double sin_alpha = Math.sin(alpha);
	double sin_beta = Math.sin(beta);
	double sin_gamma = Math.sin(gamma); 	
	
	return (x*(cos_beta*cos_gamma) + y*(sin_alpha*sin_beta*cos_gamma - cos_alpha*sin_gamma) + z*(cos_alpha*sin_beta*cos_gamma + sin_alpha*sin_gamma));
	
}

private double extrinsic_rotation_yval(double x, double y, double z, double alpha, double beta, double gamma) {
	// R = R_z(gamma)R_y(beta)R_x(alpha)
	// improper Euler angles alpha, beta, gamma about axes x, y, z

	double cos_alpha = Math.cos(alpha);
	double cos_beta = Math.cos(beta);
	double cos_gamma = Math.cos(gamma);
	double sin_alpha = Math.sin(alpha);
	double sin_beta = Math.sin(beta);
	double sin_gamma = Math.sin(gamma); 	
	
	return (x*cos_beta*sin_gamma + y*(sin_alpha*sin_beta*sin_gamma + cos_alpha*cos_gamma) + z*(cos_alpha*sin_beta*sin_gamma - sin_alpha*cos_gamma));
	
}

private double extrinsic_rotation_zval(double x, double y, double z, double alpha, double beta, double gamma) {
	// R = R_z(gamma)R_y(beta)R_x(alpha)
	// improper Euler angles alpha, beta, gamma about axes x, y, z
	double cos_alpha = Math.cos(alpha);
	double cos_beta = Math.cos(beta);
	double cos_gamma = Math.cos(gamma);
	double sin_alpha = Math.sin(alpha);
	double sin_beta = Math.sin(beta);
	double sin_gamma = Math.sin(gamma); 	
	
	return (x*(-sin_beta) + y*sin_alpha*cos_beta + z*(cos_alpha*cos_beta));
}

}