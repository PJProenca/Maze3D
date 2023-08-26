package maze;

import com.sun.j3d.utils.geometry.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.AudioDevice;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.LinearFog;
import javax.media.j3d.Material;
import javax.media.j3d.MediaContainer;
import javax.media.j3d.Node;
import javax.media.j3d.PointLight;
import javax.media.j3d.PointSound;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.swing.JFileChooser;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.universe.SimpleUniverse;

import cg3d.appearance.Materials;
import cg3d.appearance.TextureAppearance;
import utils.KeyControl;

import utils.KeyShape;
import utils.MazeMap;
import utils.OpenCellBehavior;
import utils.Player;
import utils.CellDoor;
import utils.CreateBG;
import utils.Floor;

import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.audioengines.javasound.JavaSoundMixer;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;

public class Maze extends Frame implements MouseListener, KeyListener, ActionListener {

	BoundingSphere bounds = new BoundingSphere(new Point3d(5, 0, 5), 15.0f);
	PickCanvas pc = null;
	boolean isOn = false;
	public static boolean hasKey = false;
	PointSound sound;
	// OpenCellBehavior cb;

	private Canvas3D cv;
	private Canvas3D offScreenCanvas;
	private View view;

	public static void main(String[] args) {
		Frame frame = new Maze();
		frame.setPreferredSize(new Dimension(900, 800));
		frame.setTitle("Maze");
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public Maze() {

		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		cv = new Canvas3D(gc);
		cv.addMouseListener(this);
		setLayout(new BorderLayout());
		add(cv, BorderLayout.CENTER);

		SimpleUniverse su = new SimpleUniverse(cv, 2);
		AudioDevice audioDev = new JavaSoundMixer(su.getViewer().getPhysicalEnvironment());
		audioDev.initialize();
		BranchGroup bg = createSceneGraph(cv, su.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0));
		bg.compile();

		Transform3D viewTr = new Transform3D();
		// ** testing views
		// **Sky View
		// viewTr.lookAt(new Point3d(5, 10, 5f), new Point3d(5, 5, 5), new Vector3d(1,
		// 0, 0));
		// ** Key Local View
		// viewTr.lookAt(new Point3d(2, 0.5, 9.5), new Point3d(0, 0.5, 9.5), new
		// Vector3d(0, 1, 0));

		// ** cellDoor view
//		 viewTr.lookAt(new Point3d(9, 0.5, 8.5), new Point3d(0, 0.5, 6), new
//		 Vector3d(0, 1, 0));
//		 viewTr.lookAt(new Point3d(8.5, 10, 8.5f), new Point3d(9, 5, 8.5), new
//		 Vector3d(1, 0, 0));

		// game view
		viewTr.lookAt(new Point3d(-0.75, 0.350, 0), new Point3d(10, -0.5, 0), new Vector3d(1, 1, 0));
		viewTr.invert();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
		pc = new PickCanvas(cv, bg);
		pc.setMode(PickTool.GEOMETRY);
		su.addBranchGraph(bg);

		// OrbitBehavior ob = new OrbitBehavior(cv);

		OrbitBehavior ob = new OrbitBehavior(cv, OrbitBehavior.DISABLE_ZOOM | OrbitBehavior.REVERSE_ALL);
		ob.setSchedulingBounds(bounds);

		su.getViewingPlatform().setViewPlatformBehavior(ob);

		view = su.getViewer().getView();
		offScreenCanvas = new Canvas3D(gc, true);
		Screen3D sOn = cv.getScreen3D();
		Screen3D sOff = offScreenCanvas.getScreen3D();
		Dimension dim = sOn.getSize();
		sOff.setSize(dim);
		sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth());
		sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight());
		Point loc = cv.getLocationOnScreen();
		offScreenCanvas.setOffScreenLocation(loc);
		WindowListener wl = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		addWindowListener(wl);

		MenuBar menubar = new MenuBar();
		setMenuBar(menubar);

		Menu menu = new Menu("Off-Screen");
		menubar.add(menu);

		MenuItem item = new MenuItem("Save Img");
		item.addActionListener(this);
		menu.add(item);

	}

	private BranchGroup createSceneGraph(Canvas3D cv, TransformGroup viewTg) {

		// Root Branch
		BranchGroup root = new BranchGroup();

		// Sound
		sound = new PointSound();
		URL url = this.getClass().getClassLoader().getResource("images/Bg_sound.wav");
		MediaContainer mc = new MediaContainer(url);
		sound.setSoundData(mc);
		sound.setInitialGain(1f);
		sound.setCapability(PointSound.ALLOW_ENABLE_WRITE);
		float[] distances = { 1f, 20f };
		float[] gains = { 1f, 0.001f };
		sound.setDistanceGain(distances, gains);
		BoundingSphere soundBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		sound.setSchedulingBounds(soundBounds);
		root.addChild(sound);

		// Appearances
		Appearance wallApp = new TextureAppearance(this, "images/floorTexture.png",
				new Materials(Materials.MYMATERIAL_1), true, true);
		Appearance floorApp = new TextureAppearance(this, "images/floor_texture.jpg",
				new Materials(Materials.MYMATERIAL_2), true, true);
		Appearance keyApp = new Appearance();
		keyApp.setMaterial(new Materials(Materials.POLISHED_GOLD));
		Appearance cellApp = new Appearance();
		cellApp.setMaterial(new Materials(Materials.CHROME));
		Appearance headApp = new Appearance();
		headApp.setColoringAttributes(new ColoringAttributes(new Color3f(Color.BLACK), ColoringAttributes.SHADE_FLAT));
		Appearance altarApp = new Appearance();
		altarApp.setMaterial(new Materials(Materials.COPPER));
		Appearance bodyApp = new Appearance();
		headApp.setColoringAttributes(new ColoringAttributes(new Color3f(Color.red), ColoringAttributes.SHADE_FLAT));

		// Fog
		LinearFog fog = new LinearFog(0.6f, 0.6f, 0.6f, 2f, 3f);
		fog.setInfluencingBounds(bounds);
		root.addChild(fog);

		// Shapes
		// ** Floor
		Shape3D floor = new Floor(10, floorApp);
		Transform3D tr = new Transform3D();
		tr.rotX(Math.toRadians(90));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(floor);
		root.addChild(tg);

		// Maze Wall disposition

		MazeMap map = new MazeMap(wallApp);
		root.addChild(map);

		// Key

		KeyShape key = new KeyShape(keyApp);
		tr = new Transform3D();
		tr.rotY(Math.toRadians(180));
		tr.setTranslation(new Vector3d(0.15f, 0.35f, 9.55f));
		tr.setScale(0.5);
		tg = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(key);
		root.addChild(tg);

		// CellDoor

		CellDoor cellDoor = new CellDoor(cellApp, 39);
		tr = new Transform3D();
		tr.rotY(Math.toRadians(90));
		tr.setTranslation(new Vector3d(8.8, 0, 8.95));
		tg = new TransformGroup(tr);
		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(cellDoor);
		root.addChild(tg);

//		CellDoor cellDoor = new CellDoor(cellApp, 39);
//		tr = new Transform3D();
//		Vector3d pos = new Vector3d(8.8, 0, 8.95);
//		tr.rotY(Math.toRadians(90));
//		tr.setTranslation(new Vector3d(8.8, 0, 8.95));
//
//		tg = new TransformGroup(tr);
//		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
//		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		tg.addChild(cellDoor);
//
//		cb = new OpenCellBehavior(tg, pos, hasKey);
//		cb.setEnable(true);
//		cb.setSchedulingBounds(new BoundingSphere());
//		tg.addChild(cb);
//		root.addChild(tg);

		// Prize Obj
		
		BranchGroup prize = loadModelOBJ("images/Diamond.obj");
		tr = new Transform3D();
		tr.setScale(0.1);
		tr.setTranslation(new Vector3f(9.45f, 0.35f, 7.5f));
		TransformGroup rotTg = new TransformGroup(tr);
		tg = new TransformGroup();
		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Alpha alpha = new Alpha(-1, 4000);
		RotationInterpolator rotator = new RotationInterpolator(alpha, tg);
		rotator.setSchedulingBounds(new BoundingSphere());
		root.addChild(rotator);
		tg.addChild(prize);
		rotTg.addChild(tg);
		root.addChild(rotTg);

		// Altar
		Primitive altar = new Cylinder(0.06f, 0.2f, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS,
				altarApp);
		tr = new Transform3D();
		tr.setTranslation(new Vector3d(9.45f, 0.12f, 7.5f));
		tg = new TransformGroup(tr);
		tg.addChild(altar);
		root.addChild(tg);

		// **Player
		BranchGroup bg = new BranchGroup();
		Player player = new Player(headApp);
		tr = new Transform3D();
		tr.setScale(0.8);
		tr.setTranslation(new Vector3f(0.5f, 0.25f, 0.3f));
		// tr.setTranslation(new Vector3f(9f, 0.5f, 8.5f));
		viewTg.setTransform(tr);
		viewTg.addChild(player);
//
//		// move player
//
		KeyControl kc = new KeyControl(viewTg, player);
		kc.setSchedulingBounds(bounds);
		root.addChild(kc);

		// light
		AmbientLight aLight = new AmbientLight(true, new Color3f(240, 240, 240));
		aLight.setInfluencingBounds(bounds);
		root.addChild(aLight);

		PointLight pLight = new PointLight(new Color3f(Color.white), new Point3f(5f, 4f, 5f), new Point3f(1f, 0, 0));
		pLight.setInfluencingBounds(bounds);
		root.addChild(pLight);

		// key spotlight
		SpotLight sLight = new SpotLight(new Color3f(0, 100f, 235f), new Point3f(0.25f, 0.6f, 9.55f),
				new Point3f(1f, 0, 0), new Vector3f(0.f, -.5f, 0f), (float) (Math.PI / 8.0), 100f);
		sLight.setInfluencingBounds(bounds);
		root.addChild(sLight);

		// prize spotlight and pointlight
		sLight = new SpotLight(new Color3f(Color.WHITE), new Point3f(9.5f, 0.65f, 7.5f), new Point3f(.5f, 0, 0),
				new Vector3f(0.f, -.5f, 0f), (float) (Math.PI / 4.0), 100f);
		sLight.setInfluencingBounds(bounds);
		root.addChild(sLight);
		BoundingSphere prizeBound = new BoundingSphere(new Point3d(9.35f, 0.25f, 7.5f), 0.65f);
		pLight = new PointLight(new Color3f(Color.red), new Point3f(9.45f, 0.55f, 7.5f), new Point3f(0.05f, 0, 0));
		pLight.setInfluencingBounds(prizeBound);
		root.addChild(pLight);

		// Background
		Appearance bgApp = new TextureAppearance(this, "images/sky-night-star.jpg", new Material(), false, false);
		Group group = new CreateBG(bounds, bgApp);
		root.addChild(group);

		return root;
	}

	private BranchGroup loadModelOBJ(String fileName) {

		ObjectFile file = new ObjectFile();
		Scene scene = null;
		URL url = getClass().getClassLoader().getResource(fileName);
		try {
			scene = file.load(url);
		} catch (FileNotFoundException | IncorrectFormatException | ParsingErrorException e) {
			e.printStackTrace();
		}
		TextureAppearance app1 = new TextureAppearance(this, "images/Gem1 - Specular.jpg", new Material(), true, true);
		Shape3D part1 = (Shape3D) scene.getSceneGroup().getChild(0);
		part1.setAppearance(app1);
		return scene.getSceneGroup();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		boolean is = false;
		pc.setShapeLocation(e);
		PickResult result = pc.pickClosest();
		if (result != null) {
			TransformGroup nodeTg = (TransformGroup) result.getNode(PickResult.TRANSFORM_GROUP);
			Node id = (Node) nodeTg.getChild(0);
			if (nodeTg != null && id.toString().contains("KeyShape")) {
				hasKey = true;
				isOn = true;
				if (isOn) {
					sound.setEnable(true);
				}
				Transform3D tr = new Transform3D();
				nodeTg.getTransform(tr);
				tr.setTranslation(new Vector3d(0, -10, 0));
				nodeTg.setTransform(tr);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public BufferedImage capture() {
		// render off screen image
		Dimension dim = cv.getSize();
		view.stopView();
		view.addCanvas3D(offScreenCanvas);
		BufferedImage bImage = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		ImageComponent2D buffer = new ImageComponent2D(ImageComponent.FORMAT_RGB, bImage);
		offScreenCanvas.setOffScreenBuffer(buffer);
		view.startView();
		offScreenCanvas.renderOffScreenBuffer();
		offScreenCanvas.waitForOffScreenRendering();
		bImage = offScreenCanvas.getOffScreenBuffer().getImage();
		view.removeCanvas3D(offScreenCanvas);
		return bImage;
	}

	public void save(BufferedImage bImage) {
		// save image to file
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File oFile = chooser.getSelectedFile();
			try {
				ImageIO.write(bImage, "jpeg", oFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String options = e.getActionCommand();

		if (options.equals("Save Img")) {
			BufferedImage bi = capture();
			save(bi);
		}

	}

}
