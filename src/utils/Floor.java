package utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.jogamp.opengl.util.texture.TextureCoords;

public class Floor extends Shape3D {

	public Floor(int divisions, float min, float max, Color3f color1, Color3f color2, boolean fill) {

		// Range for X
		int m = divisions;
		float a = min;
		float b = max;
		float divX = (b - a) / m;

		// Range for Z
		int n = divisions;
		float c = min;
		float d = max;
		float divZ = (d - c) / n;

		int totalPoints = m * n * 4;
		
		Point3f[] pts = new Point3f[totalPoints];
		Color3f[] col = new Color3f[totalPoints];
		boolean invert = true;
		int idx = 0;
		for(int i = 0; i < m ; i++) {
			for(int j = 0; j < n ; j++) {
				
				// Vertex 0
				float x = a + i * divX;
				float y = 0;
				float z = c + j * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;
				
				// Vertex 1
				x = a + i * divX;
				y = 0;
				z = c + (j +1 ) * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;
				
				// Vertex 2
				x = a + (i + 1) * divX;
				y = 0;
				z = c + (j + 1) * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;
				
				// Vertex 3
				x = a + (i + 1) * divX;
				y = 0;
				z = c + j * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;
				
				invert = !invert;
			}
			if(divisions % 2 == 0)
				invert = !invert;
		}

		QuadArray geom = null;
		if (fill == true) {
			geom = new QuadArray(totalPoints, QuadArray.COORDINATES | QuadArray.COLOR_3);
			geom.setCoordinates(0, pts);
			geom.setColors(0, col);
		} else {
			geom = new QuadArray(totalPoints, QuadArray.COORDINATES);
			geom.setCoordinates(0, pts);
		}

	
		Appearance app = new Appearance();
		PolygonAttributes pa = new PolygonAttributes();
		if(fill == true) {
			pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		}
		else {
			pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
			app.setColoringAttributes(new ColoringAttributes(color1, ColoringAttributes.SHADE_FLAT));
		}
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		app.setPolygonAttributes(pa);
		
		this.setGeometry(geom);
		this.setAppearance(app);
	}

	
	public Floor(float size, Appearance app) {
		
		QuadArray geom = new QuadArray(4, QuadArray.COORDINATES | QuadArray.NORMALS);
		
		Point3f[] coords = new Point3f[4];
		coords[0] = new Point3f(0f,0f,0f);
		coords[1] = new Point3f(size,0f,0f);
		coords[2] = new Point3f(size,size,0f);
		coords[3] = new Point3f(0f,size,0f);
		geom.setCoordinates(0, coords);
	
		Vector3f[] normals = new Vector3f[4];
		normals[0] = new Vector3f(0f,0f,1f);
		normals[1] = new Vector3f(0f,0f,1f);
		normals[2] = new Vector3f(0f,0f,1f);
		normals[3] = new Vector3f(0f,0f,1f);
		geom.setNormals(0,normals);
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		app.setPolygonAttributes(pa);
		Transform3D tr = new Transform3D();
		tr.rotX(Math.toRadians(90));
		
		this.setGeometry(geom);
		this.setAppearance(app);
	}

}
