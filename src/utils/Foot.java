package utils;

import javax.media.j3d.Appearance;

import javax.media.j3d.Group;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;

import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class Foot extends Group {

	public Foot(Appearance app) {
		
		Shape3D square = new Shape3D();
		IndexedQuadArray quad = new IndexedQuadArray(6, QuadArray.COORDINATES ,12);
		Point3f[] coordsQuad = {new Point3f(0,0,0), new Point3f(0,0,-1),new Point3f(1,0,-1),new Point3f(1,0,0),
							new Point3f(1,1,-1),new Point3f(1,1,0)};
		
		int[] coordIndices1 = {0,1,2,3, 2,4,5,3, 0,5,4,1};
		
		quad.setCoordinates(0, coordsQuad);
		quad.setCoordinateIndices(0, coordIndices1);
		GeometryInfo gi = new GeometryInfo(quad);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		square.setGeometry(gi.getGeometryArray());
		square.setAppearance(new Appearance());
		this.addChild(square);
		
		Shape3D triangle = new Shape3D();
		IndexedTriangleArray triang = new IndexedTriangleArray(6, TriangleArray.COORDINATES ,6);
		Point3f[] coordsTriangle = {new Point3f(0,0,0), new Point3f(1,0,0),new Point3f(1,1,0),new Point3f(0,0,-1),
				new Point3f(1,0,-1),new Point3f(1,1,-1)};
		int[] coordIndicesTriangle = {0,1,2, 3,5,4};
		
		triang.setCoordinates(0, coordsTriangle);
		triang.setCoordinateIndices(0, coordIndicesTriangle);
		GeometryInfo gi2 = new GeometryInfo(triang);
		NormalGenerator ng2 = new NormalGenerator();
		ng2.generateNormals(gi2);
		triangle.setGeometry(gi2.getGeometryArray());
		triangle.setAppearance(new Appearance());
		this.addChild(triangle);
		
		Shape3D cube = new Shape3D();
		IndexedQuadArray box = new IndexedQuadArray(12, QuadArray.COORDINATES , 24);
		Point3f[] coordsBox = {new Point3f(1,0,0),new Point3f(1,0,-1),
				new Point3f(1,1,-1),new Point3f(1,1,0),
				new Point3f(2f,1,-1),new Point3f(2f,0,-1),
				new Point3f(2f,1,0),new Point3f(2f,0,0)};
		int[] coordIndicesBox = {0,3,2,1, 1,2,4,5, 5,4,6,7, 4,2,3,6, 6,3,0,7, 0,1,5,7} ;
		box.setCoordinates(0, coordsBox);
		box.setCoordinateIndices(0, coordIndicesBox);
		
		gi2 = new GeometryInfo(box);
		ng2 = new NormalGenerator();
		ng2.generateNormals(gi2);
		cube.setGeometry(gi2.getGeometryArray());
		cube.setAppearance(app);
		Transform3D tr = new Transform3D();
		
		this.addChild(cube);
		
				
	}
}
