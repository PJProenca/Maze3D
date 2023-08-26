package utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.geometry.*;
public class KeyShape extends Group {
	
	
	public KeyShape(Appearance app) {
		
		//Key Handle
		Torus keyHandle = new Torus(0.01,0.05);
		keyHandle.setAppearance(app);
		Transform3D tr = new Transform3D();
		tr.rotZ(Math.toRadians(90));
		//tr.setTranslation(new Vector3d(0,0.5,0.5));
		tr.setScale(new Vector3d(1,1,0.5));
		TransformGroup tg =  new TransformGroup(tr);
		tg.addChild(keyHandle);
		this.addChild(tg);
		
		//Key Body
		Primitive keyBody = new Cylinder(0.015f,0.13f,Cylinder.GENERATE_NORMALS | Cylinder.ENABLE_GEOMETRY_PICKING,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3d(0,-0.11,0));
		tg = new TransformGroup(tr);
		tg.addChild(keyBody);
		this.addChild(tg);
		
		//Key Thoot
		//** Lower Tooth
		Primitive keyTooth = new Box(0.0075f, 0.0075f, 0.012f, 
				Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3d(0,-0.16,0.028));
		tg =  new TransformGroup(tr);
		tg.addChild(keyTooth);		
		this.addChild(tg);
		//** Upper Thoot
		keyTooth = new Box(0.0075f, 0.0075f, 0.012f, 
				Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3d(0,-0.13,0.028));
		tg =  new TransformGroup(tr);
		tg.addChild(keyTooth);		
		this.addChild(tg);	
		
	}

	
}
