package utils;



import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class CellDoor extends Group {
	
	public CellDoor(Appearance app, int barNumber) {
		
		
		//BarsJuntion
		//** upper
		Primitive barsJuntion = new Box(0.008f, 0.01f, 0.65f, Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING,app);
		Transform3D tr = new Transform3D();
		tr.setTranslation(new Vector3d(0f,.48f,0.55f));
		TransformGroup tg = new TransformGroup(tr);		
		tg.addChild(barsJuntion);
		this.addChild(tg);
		//**lower
		barsJuntion = new Box(0.008f, 0.01f, 0.65f, Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3d(0f,.03f,0.55f));
		tg = new TransformGroup(tr);		
		tg.addChild(barsJuntion);
		this.addChild(tg);
		
		//Bars

		double tz = -0.009;
		for (int i = 0; i <barNumber;i++) {
			Primitive cellBar = new Cylinder(0.006f, 0.5f, 
					Cylinder.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING,app);	
			tr.setTranslation(new Vector3d(0,0.25,tz));
			tz+=0.03;
			tg = new TransformGroup(tr);
			tg.addChild(cellBar);
			this.addChild(tg);
			
		}
		
		
	}

}
