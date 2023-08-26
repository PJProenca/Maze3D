package utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.*;
public class MazeMap extends Group {


	
	public MazeMap(Appearance app) {
		
		Transform3D tr = new Transform3D();
		//** outside Walls
		MazeWall wall = new MazeWall(app, 10, .5f, 0.05f);
		tr.setTranslation(new Vector3d(0,0,-0.05f));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		wall = new MazeWall(app, 10, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(10,0,0));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 10, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-180)));
		tr.setTranslation(new Vector3d(10,0,10.05f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 9, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-270)));
		tr.setTranslation(new Vector3d(0,0,10.05f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
	
		//*** inside Walls
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(8.75f,0.01f,0));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(2.75f,0.01f,0));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-180)));
		tr.setTranslation(new Vector3d(8.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(6.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-180)));
		tr.setTranslation(new Vector3d(6.75f,0.01f,2f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(5.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setTranslation(new Vector3d(3.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(1.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(0.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(0.75f,0.01f,1f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(1.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(3.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(4.75f,0.01f,2f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(6.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(90)));
		tr.setTranslation(new Vector3d(7.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(7.75f,0.01f,2f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
	
		tr = new Transform3D();
		wall = new MazeWall(app, 1.25f, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(8.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(8.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(7.75f,0.01f,4f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);		
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(6.75f,0.01f,3f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);		
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(5.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(90)));
		tr.setTranslation(new Vector3d(5.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setTranslation(new Vector3d(3.75f,0.01f,4f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(3.75f,0.01f,4f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(0.75f,0.01f,4f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
	
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(2.75f,0.01f,4f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(1.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(0.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 0.75f, .5f, 0.05f);
		tr.setTranslation(new Vector3d(0f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 0.75f, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(0f,0.01f,9f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);

		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(0.75f,0.01f,7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(0.75f,0.01f,7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(0.75f,0.01f,8f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(1.75f,0.01f,8f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(1.75f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(2.75f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setTranslation(new Vector3d(2.75f,0.01f,8f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setTranslation(new Vector3d(2.75f,0.01f,9f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(4.75f,0.01f,9f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
	
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(3.75f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setTranslation(new Vector3d(3.75f,0.01f,7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
	
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(4.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(4.75f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 3, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(6.75f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(6.75f,0.01f,9f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(7.75f,0.01f,9f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);		
		tr.setTranslation(new Vector3d(5.75f,0.01f,8f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(5.75f,0.01f,8f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(6.75f,0.01f,7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 3, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(7.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1, .5f, 0.05f);
		tr.setTranslation(new Vector3d(7.75f,0.01f,6f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1.25f, .5f, 0.05f);
		tr.setTranslation(new Vector3d(8.75f,0.01f,5f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 1.25f, .5f, 0.05f);
		tr.setTranslation(new Vector3d(8.75f,0.01f,7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		tr = new Transform3D();
		wall = new MazeWall(app, 2, .5f, 0.05f);
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-90)));
		tr.setTranslation(new Vector3d(8.75f,0.01f,7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		this.addChild(tg);
		
		//Key Stance
		
		Primitive KeyStance = new Box(0.09f, 0.09f, 0.09f, 
				Box.GENERATE_NORMALS,app);
		
		tr = new Transform3D();
		tr.rotY(Math.toRadians(90));
		tr.setTranslation(new Vector3d(0.15f,0.091f,9.55f));
		tg = new TransformGroup(tr);
		tg.addChild(KeyStance);
		this.addChild(tg);
		
		
		
	}
	
}
