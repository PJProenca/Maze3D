package utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import com.sun.j3d.utils.geometry.*;

public class CreateBG extends Group {

	
	public CreateBG(Bounds bound, Appearance app) {
		BranchGroup bkBranch = new BranchGroup();
		Background bk = new Background();
		bk .setApplicationBounds(bound);
		BranchGroup bkGeom = new BranchGroup();
		
		Sphere sphere = new Sphere(1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,app);
		bkGeom.addChild(sphere);
		bk.setGeometry(bkGeom);
		bkBranch.addChild(bk);
		this.addChild(bkBranch);
	}
}
