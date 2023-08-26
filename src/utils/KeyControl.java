package utils;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOr;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.*;


public class KeyControl extends Behavior{

	private TransformGroup moveTg = null;
	private Node node = null;
	private boolean collision = false; 
	private int lastKey;
	
	private WakeupCondition wakeUpCondition = null;
	public KeyControl(TransformGroup tg,Node n) {
		moveTg = tg;
		node = n;
	}
	
	@Override
	public void initialize() {
		
		WakeupCriterion[] events = new WakeupCriterion[4];
		events[0] = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
		
		events[1] = new WakeupOnAWTEvent(KeyEvent.KEY_RELEASED);
		events[2] = new WakeupOnCollisionEntry(node,WakeupOnCollisionEntry.USE_GEOMETRY);
		events[3] = new WakeupOnCollisionExit(node,WakeupOnCollisionExit.USE_GEOMETRY);
		
		// This wake up conditions is a combinations of the 4 wake up criteria
		wakeUpCondition =new WakeupOr(events);
		
		wakeupOn(wakeUpCondition);
		
	}

	@Override
	public void processStimulus(Enumeration criteria) {
		WakeupCriterion wakeUpCriterion;
		AWTEvent[] awtEvents;
		
		while(criteria.hasMoreElements()) {
			 wakeUpCriterion =(WakeupCriterion) criteria.nextElement();
			 
			 if (wakeUpCriterion instanceof WakeupOnAWTEvent) {
				 awtEvents = ((WakeupOnAWTEvent) wakeUpCriterion).getAWTEvent();
				 
				for( int i = 0; i < awtEvents.length ; i++) {
					if(awtEvents[i].getID() == KeyEvent.KEY_PRESSED) {
						
						processKey((KeyEvent)awtEvents[i]);
						
						
					}else if(awtEvents[i].getID() == KeyEvent.KEY_RELEASED) {
						// Not Implemented in this example
					}
				}
				 
				 
				 
			 }else if(wakeUpCriterion instanceof WakeupOnCollisionEntry) {
				 //System.out.println("Collision!!");
				 collision = true;
			 }else if(wakeUpCriterion instanceof WakeupOnCollisionExit) {
				 collision = false;
			 }
		}
		
		
		
		wakeupOn(wakeUpCondition);
		
	}
	
	private void processKey(KeyEvent event) {
		int keyCode = event.getKeyCode();
		float teta = 0.5f;
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
		if(!collision | (collision && lastKey != KeyEvent.VK_LEFT))
			doRotation(Math.toRadians(2.0f));
			break;
		case KeyEvent.VK_UP:
			//usamos um vector para caso necessitemos alterar as restantes coordenadas
			if(!collision | (collision && lastKey != KeyEvent.VK_UP)) 
			doTranslation(new Vector3f(0.09f, 0, 0));		
			break;
		case KeyEvent.VK_DOWN:
			if(!collision | (collision && lastKey != KeyEvent.VK_DOWN))
			doTranslation(new Vector3f(-0.09f,0, 0));
			break;
		case KeyEvent.VK_RIGHT:
			if(!collision | (collision && lastKey != KeyEvent.VK_RIGHT))
			doRotation(Math.toRadians(-2.0f));
			break;
		
		}
		lastKey = keyCode;
	}
	

	private void doRotation(double t) {
		Transform3D oldTr = new Transform3D();
		moveTg.getTransform(oldTr);			
		Transform3D newTr = new  Transform3D();
		newTr.rotY(t);		
		oldTr.mul(newTr);		
		moveTg.setTransform(oldTr);
	}

	private void doTranslation(Vector3f v) {
		Transform3D oldTr = new Transform3D();
		moveTg.getTransform(oldTr);	
		
		Transform3D newTr = new  Transform3D();
		newTr.setTranslation(v);
		oldTr.mul(newTr);
		
		moveTg.setTransform(oldTr);
		
		// get coordinates of the object on the scene
		
		Transform3D tr = new Transform3D();			
		node.getLocalToVworld(tr);
		Vector3f position = new Vector3f();
		tr.get(position);
		
		System.out.println(position);
	}
}
