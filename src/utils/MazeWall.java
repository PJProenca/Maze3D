package utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class MazeWall extends Shape3D {

	public MazeWall (Appearance app,float xDim,float yDim,float zDim) {
		IndexedQuadArray wall = new IndexedQuadArray(12, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2 , 24);
		Point3f[] coords = {new Point3f(0,0,0),new Point3f(xDim,0,0),
				new Point3f(xDim,yDim,0),new Point3f(0,yDim,0),
				new Point3f(0,0,zDim),new Point3f(xDim,0,zDim),
				new Point3f(xDim,yDim,zDim),new Point3f(0,yDim,zDim)};
		int[] coordIndices = {3,2,1,0, 2,3,7,6, 3,0,4,7, 4,0,5,1, 2,6,5,1, 6,7,4,5 } ;
		wall.setCoordinates(0, coords);
		wall.setCoordinateIndices(0, coordIndices);
		
		TexCoord2f[] tex = {new TexCoord2f(0, 1),new TexCoord2f(18f/20, 1),
				new TexCoord2f(19f/20, 1),new TexCoord2f(1, 1),
				new TexCoord2f(0, 0.5f),new TexCoord2f(18f/20f, 0.5f),
				new TexCoord2f(19f/20, 0.5f),new TexCoord2f(1, 0.5f),
				new TexCoord2f(0, 0),new TexCoord2f(18f/20, 0),
				new TexCoord2f(19f/20, 0),new TexCoord2f(1, 0)};
		int[] texIndices =
			{1,2,6,5, 5,6,10,9, 2,3,7,6, 6,7,10,11, 0,1,5,4, 4,5,8,9};
		
		wall.setTextureCoordinates(0,0,tex);
		wall.setTextureCoordinateIndices(0,0,texIndices);
		GeometryInfo gi = new GeometryInfo(wall);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		this.setGeometry(gi.getGeometryArray());
		this.setAppearance(app);
	}
}
