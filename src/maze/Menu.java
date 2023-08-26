package maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Billboard;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.universe.SimpleUniverse;

import cg3d.appearance.Materials;
import cg3d.appearance.TextureAppearance;
import cg3d.shapes.Axes;
import cg3d.shapes.ImagePanel;
import utils.CreateBG;

public class Menu extends Frame implements KeyListener {
	BoundingSphere bounds = new BoundingSphere(new Point3d(5, 0, 5), 15.0f);
	PickCanvas pc = null;

	public static void main(String[] args) {
		Frame frame = new Menu();
		frame.setPreferredSize(new Dimension(900, 800));
		frame.setTitle("Menu");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	public Menu() {
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
		cv.addKeyListener(this);
		setLayout(new BorderLayout());
		add(cv, BorderLayout.CENTER);

		SimpleUniverse su = new SimpleUniverse(cv, 2);
		BranchGroup bg = createSceneGraph(cv, su.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0));
		bg.compile();

		Transform3D viewTr = new Transform3D();
		// ** testing views
		// **Sky View
		viewTr.lookAt(new Point3d(-5.5, 0, 0), new Point3d(10, 0, 0), new Vector3d(1, 1, 0));

		viewTr.invert();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
		pc = new PickCanvas(cv, bg);
		pc.setMode(PickTool.GEOMETRY);
		su.addBranchGraph(bg);

		OrbitBehavior ob = new OrbitBehavior(cv, OrbitBehavior.DISABLE_ZOOM | OrbitBehavior.REVERSE_ALL);
		ob.setSchedulingBounds(bounds);

		su.getViewingPlatform().setViewPlatformBehavior(ob);

		WindowListener wl = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		addWindowListener(wl);
	}

	private BranchGroup createSceneGraph(Canvas3D cv, TransformGroup transformGroup) {
		// root
		BranchGroup root = new BranchGroup();

		Appearance textApp = new Appearance();
		textApp.setMaterial(new Materials(Materials.CHROME));
		Font3D font3d = new Font3D(new Font("Arial", Font.PLAIN, 1), new FontExtrusion());
		Text3D text3d = new Text3D(font3d, "Press Space to play");
		Shape3D shape = new Shape3D();
		shape.setGeometry(text3d);
		shape.setAppearance(textApp);
		text3d.setAlignment(Text3D.ALIGN_CENTER);
		Transform3D tr = new Transform3D();

		tr.setScale(.3f);
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(shape);

		TransformGroup bbTg = new TransformGroup();
		bbTg.addChild(tg);

		ImagePanel menu = new ImagePanel(this, "images/Maze.jpeg", 01f);
		tr = new Transform3D();
		tg = new TransformGroup(tr);
		tg.addChild(menu);
		bbTg.addChild(tg);

		bbTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Billboard bb = new Billboard(bbTg, Billboard.ROTATE_ABOUT_AXIS, new Point3f(0f, 0.5f, 0f));
		bb.setSchedulingBounds(bounds);
		bbTg.addChild(bb);
		root.addChild(bbTg);

	
		// light
		AmbientLight aLight = new AmbientLight(true, new Color3f(240, 240, 240));
		aLight.setInfluencingBounds(bounds);
		root.addChild(aLight);

		// Background
		Appearance bgApp = new TextureAppearance(this, "images/sky-night-star.jpg", new Material(), false, false);
		Group group = new CreateBG(bounds, bgApp);
		root.addChild(group);

		return root;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
//		int keyCode = e.getKeyCode();
//		if (keyCode == KeyEvent.VK_SPACE) {
//			this.dispose();
//			Frame maze = new Maze();
//			maze.setPreferredSize(new Dimension(800, 800));
//			maze.setResizable(false);
//			maze.pack();
//			maze.setLocationRelativeTo(null);
//			maze.setVisible(true);
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
