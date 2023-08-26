package utils;

import java.awt.AWTEvent;

import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;

import javax.media.j3d.WakeupOnElapsedTime;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3d;

public class OpenCellBehavior extends Behavior {
	private WakeupCondition wakeUpCondition;

	TransformGroup moveTg = new TransformGroup();
	Vector3d pos = new Vector3d();
	private boolean hasKey;
	private WakeupCondition timeEvt;

	public OpenCellBehavior(TransformGroup tg, Vector3d pos, boolean haskey) {
		moveTg = tg;
		this.pos = pos;
		this.hasKey = haskey;
	}

	@Override
	public void initialize() {

		WakeupCriterion[] events = new WakeupCriterion[2];

		events[0] = new WakeupOnAWTEvent(MouseEvent.MOUSE_PRESSED);
		events[1] = new WakeupOnAWTEvent(MouseEvent.MOUSE_RELEASED);

		wakeUpCondition = new WakeupOr(events);

		wakeupOn(wakeUpCondition);
	}

	@Override
	public void processStimulus(Enumeration criteria) {
		WakeupCriterion wakeUpCriterion;
		AWTEvent[] awtEvents;

		while (criteria.hasMoreElements()) {
			wakeUpCriterion = (WakeupCriterion) criteria.nextElement();

			if (wakeUpCriterion instanceof WakeupOnAWTEvent) {
				awtEvents = ((WakeupOnAWTEvent) wakeUpCriterion).getAWTEvent();
				for (int i = 0; i < awtEvents.length; i++) {
					if (awtEvents[i].getID() == MouseEvent.MOUSE_PRESSED) {
						if (hasKey) {
							Transform3D tr = new Transform3D();
							tr.setTranslation(new Vector3d(pos.x, pos.y, pos.z));
							moveTg.setTransform(tr);
							timeEvt = new WakeupOnElapsedTime(100);
							if (pos.y > -5) {
								pos.y -= 0.01f;
							}
							if (pos.y > -5) {
								wakeupOn(timeEvt);
							}

						}

					} else if (awtEvents[i].getID() == MouseEvent.MOUSE_RELEASED) {
						// Not Implemented in this example
					}
				}
			}

		}

	}
}
