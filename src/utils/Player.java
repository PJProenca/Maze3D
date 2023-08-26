package utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.*;
public class Player extends BranchGroup {

	public Player(Appearance app) {
		//left arm
		Primitive arms = new Cylinder(0.015f,0.15f,app);
		Transform3D tr = new Transform3D();
		tr.rotX(Math.toRadians(30));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(arms);
		this.addChild(tg);
		
		//right arm
		arms = new Cylinder(0.015f,0.15f,app);
		tr = new Transform3D();		
		tr.rotX(Math.toRadians(-30));	
		tr.setTranslation(new Vector3f(0,0,0.14f));
		tg = new TransformGroup(tr);
		tg.addChild(arms);
		this.addChild(tg);
		// neck		
		Primitive neck = new Cylinder(0.015f,0.05f,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3f(0,0.0675f,0.0675f));
		tg = new TransformGroup(tr);
		tg.addChild(neck);
		this.addChild(tg);
		
		//head		
		Primitive head  =new Sphere(0.041f,Primitive.GENERATE_NORMALS,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3f(0,0.115f,0.0675f));
		tg = new TransformGroup(tr);
		tg.addChild(head);
		this.addChild(tg);
		
		//body
		Primitive body  =new Cylinder(0.05f,0.15f,app);
		tr = new Transform3D();
		tr.setTranslation(new Vector3f(0,-0.0045f,0.07f));
		tg = new TransformGroup(tr);
		tg.addChild(body);
		this.addChild(tg);
		
		//left leg
		arms = new Cylinder(0.015f,0.15f,app);
		tr = new Transform3D();		
		tr.setTranslation(new Vector3f(0,-0.15f,0.04f));
		tg = new TransformGroup(tr);
		tg.addChild(arms);
		this.addChild(tg);
		
		//right leg
		arms = new Cylinder(0.015f,0.15f,app);
		tr = new Transform3D();		
		tr.setTranslation(new Vector3f(0,-0.15f,0.1f));
		tg = new TransformGroup(tr);
		tg.addChild(arms);
		this.addChild(tg);
		
		//righ foot
		Group foot = new Foot(app);
		tr = new Transform3D();
		tr.rotY(Math.toRadians(-180));
		tr.setScale(0.03);
		tr.setTranslation(new Vector3f(.05f,-0.23f,0.085f));
		tg = new TransformGroup(tr);
		tg.addChild(foot);
		this.addChild(tg);
		
		//left foot
		foot = new Foot(app);
		tr = new Transform3D();
		tr.rotY(Math.toRadians(-180));
		tr.setScale(0.03);
		tr.setTranslation(new Vector3f(.05f,-0.23f,0.025f));
		tg = new TransformGroup(tr);
		tg.addChild(foot);
		this.addChild(tg);
				
	}
	
	

}


	
