package gui.view.listener;

import gui.view.CircuitView;
import gui.view.enums.CircuitState;
import utils.Editor;
import utils.EditorPoint;
import utils.TrackData;
import utils.circuit.ObjShapeHandle;
import utils.circuit.Segment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Adam Kubon
 */
public class CircuitViewMouseMotionListener implements MouseMotionListener {

  private CircuitView circuitView;

  public CircuitViewMouseMotionListener(CircuitView circuitView) {
    this.circuitView = circuitView;
  }

  @Override
  public void mouseDragged(MouseEvent mouseEvent) {

    EditorPoint imgOffsetPrev = circuitView.getImgOffsetPrev();
    EditorPoint imgOffsetStart = circuitView.getImgOffsetStart();

    CircuitState currentState = circuitView.getCurrentState();
    //		System.out.println(e.getModifiers());
    if (mouseEvent.getModifiersEx() == 4) {
      EditorPoint offset = Editor.getProperties().getImgOffset();
      Point2D.Double tmp = new Point2D.Double(0, 0);
      circuitView.screenToReal(mouseEvent, tmp);
      int x = (int) (imgOffsetPrev.getX() + (tmp.getX() - imgOffsetStart.getX()));
      int y = (int) (imgOffsetPrev.getY() + (tmp.getY() - imgOffsetStart.getY()));
//			int x = (int) ((tmp.getX() - imgOffsetPrev.getX()));
//			int y = (int) ((tmp.getY() - imgOffsetPrev.getY()));
//			System.out.println("Previus "+imgOffsetPrev.getX()+","+imgOffsetPrev.getY());
//			System.out.println("Start "+imgOffsetStart.getX()+","+imgOffsetStart.getY());
//			System.out.println("tmp "+tmp.getX()+","+tmp.getY());
//			System.out.println("Image offset "+x+","+y);
      offset.setLocation(x, y);
      Editor.getProperties().setImgOffset(offset);
      circuitView.revalidate();
      circuitView.invalidate();
      circuitView.repaint();
    }
    else {
      circuitView.screenToReal(mouseEvent, circuitView.getMousePoint());

      switch (currentState) {
        case MOVE_SEGMENTS: {
          if (circuitView.getHandleDragging() == -1)
            return;

          if (circuitView.getHandledShape().getType().equals("str")) {
            //						dragStraightEnd();
          }
          else {
            //						dragCurveEnd();
          }
        }
        break;
      }
    }

  }

  @Override
  public void mouseMoved(MouseEvent mouseEvent) {
    circuitView.screenToReal(mouseEvent, circuitView.getMousePoint());
    ArrayList<ObjShapeHandle> handles = circuitView.getHandles();
    //Segment handledShape = view.getHandledShape();
    CircuitState currentState = circuitView.getCurrentState();
    try {
      switch (currentState) {
        case NONE: {
        }
        break;

        case CREATE_LEFT_SEGMENT:
        case CREATE_RIGHT_SEGMENT:
        case CREATE_STRAIGHT:
        case MOVE_SEGMENTS:
        case DELETE: {
          if (circuitView.isDragging())
            return;

          // must check for a segment under the mouse
          Segment obj = circuitView.findObjAtMousePos();

          if (obj == null) {
            if (handles.size() > 0) {
              handles.clear();
              circuitView.setHandledShape(null);

              circuitView.invalidate();
              circuitView.repaint();
            }
          }
          else {
            handles.clear();
            circuitView.setHandledShape(obj);

            double maxDist = Double.MAX_VALUE;
            Iterator i = TrackData.getTrackData().iterator();
            while (i.hasNext()) {
              Segment o = (Segment) i.next();

              if (maxDist > o.endTrackCenter.distance(circuitView.getMousePoint())) {
                maxDist = o.endTrackCenter.distance(circuitView.getMousePoint());
                obj = o;
              }
            }

            circuitView.setHandledShape(obj);
            circuitView.getHandle().calcShape(circuitView.getHandledShape().endTrackCenter);

            circuitView.getHandles().add(circuitView.getHandle());
            circuitView.invalidate();
            circuitView.repaint();
          }
        }
        break;

//				case DELETE :
//				{
//				}
//					break;
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  //	/** input events management */
  //	/*
  //	 * public void mouseWheelMoved( MouseWheelEvent e ) { int n =
  //	 * e.getWheelRotation();
  //	 *
  //	 * for ( int i = 0; i < Math.abs( n ); i++ ) { if ( n < 0 )
  // incZoomFactor();
  //	 * else decZoomFactor(); } }
  //	 */
  //
  //	/**
  //	 * Drags end of handledShape (straight segment)
  //	 *
  //	 * @param currentPoint
  //	 * Point to move end of handledShape to (in meters)
  //	 */
  //	void dragStraightEnd()
  //	{
  //		try
  //		{
  //			// must find the curve segment concerned by this movement
  //
  //			XmlObjShape prevSegment = handledShape;
  //
  //			for (;;)
  //			{
  //				if (prevSegment == paintTrackSegments.get(0))
  //					return;
  //
  //				prevSegment = prevSegment.previousShape;
  //
  //				if (prevSegment == handledShape)
  //					return;
  //
  //				if (prevSegment.getClass().getName().indexOf("Curve") != -1)
  //					break;
  //			}
  //
  //			Curve curveSegment = (Curve) prevSegment;
  //
  //			if (curveSegment.allDatas[Curve.radiusStart] !=
  // curveSegment.allDatas[Curve.endRadius])
  //				return; // this curve segment doesn't have a constant radiusStart
  //
  //			XmlObjShape straightSegment = handledShape;
  //
  //			// define points O, B, C;
  //			Point2D.Double O, B, C;
  //
  //			O = curveSegment.center;
  //			B = mousePoint;
  //			C = curveSegment.startTrackCenter;
  //
  //			if (O.distance(B) == 0)
  //				return;
  //
  //			// calc A point (2 solutions)
  //			double alpha = Math.acos(Curve.radiusStart / O.distance(B));
  //			while (alpha < -Math.PI)
  //				alpha += EPMath.PI_MUL_2;
  //			while (alpha > Math.PI)
  //				alpha -= EPMath.PI_MUL_2;
  //
  //			double beta = Math.atan2(B.getY() - O.getY(), B.getX() - O.getX());
  //			while (beta < -Math.PI)
  //				beta += EPMath.PI_MUL_2;
  //			while (beta > Math.PI)
  //				beta -= EPMath.PI_MUL_2;
  //
  //			Point2D.Double A1 = new Point2D.Double();
  //			Point2D.Double A2 = new Point2D.Double();
  //
  //			double alphaToA1 = beta + alpha;
  //			double alphaToA2 = beta - alpha;
  //
  //			A1.setLocation(O.getX() + Math.cos(alphaToA1) * Curve.radiusStart,
  // O.getY() +
  // Math.sin(alphaToA1)
  //					* Curve.radiusStart);
  //			A2.setLocation(O.getX() + Math.cos(alphaToA2) * Curve.radiusStart,
  // O.getY() +
  // Math.sin(alphaToA2)
  //					* Curve.radiusStart);
  //
  //			// select 'good' solution
  //
  //			double d1 = A1.distance(curveSegment.endTrackCenter);
  //			double d2 = A2.distance(curveSegment.endTrackCenter);
  //
  //			double arc;
  //
  //			if (d1 < d2)
  //				arc = Math.atan2(C.getY() - O.getY(), C.getX() - O.getX())
  //						- Math.atan2(A1.getY() - O.getY(), A1.getX() - O.getX());
  //			else
  //				arc = Math.atan2(C.getY() - O.getY(), C.getX() - O.getX())
  //						- Math.atan2(A2.getY() - O.getY(), A2.getX() - O.getX());
  //			while (arc < 0)
  //				arc += EPMath.PI_MUL_2;
  //			while (arc >= EPMath.PI_MUL_2)
  //				arc -= EPMath.PI_MUL_2;
  //
  //			if (currentMovedShape != curveSegment)
  //			{
  //				// install undo step
  //				UndoStep undoStep = new UndoStepModifyTrackSegment(curveSegment);
  //				undoSteps.add(undoStep);
  //				redoSteps.clear();
  //				undoStep.redo();
  //
  //				currentMovedShape = curveSegment;
  //			}
  //
  //			curveSegment.segment.setAttributeOfPart("attnum", "name", "arc", "unit",
  // "rad");
  //			curveSegment.segment.setAttributeOfPartDouble("attnum", "name", "arc",
  // "val", Math.abs(arc));
  //
  //			calcGeometricObjects();
  //
  //			parentFrame.documentIsModified = true;
  //		} catch (Exception e)
  //		{
  //			e.printStackTrace();
  //		}
  //	}
  //
  //	/**
  //	 * Drags end of handledShape (curve segment)
  //	 *
  //	 * @param currentPoint
  //	 * Point to move end of handledShape to (in meters)
  //	 */
  //	void dragCurveEnd()
  //	{
  //		try
  //		{
  //			Point2D.Double a = handledShape.startTrackCenter;
  //			Point2D.Double b = mousePoint;
  //
  //			double dAB = b.distance(a);
  //
  //			if (dAB == 0)
  //				return;
  //
  //			double alpha = handledShape.startTrackAlpha;
  //			while (alpha < -Math.PI)
  //				alpha += EPMath.PI_MUL_2;
  //			while (alpha > Math.PI)
  //				alpha -= EPMath.PI_MUL_2;
  //
  //			double beta = Math.atan2(b.getY() - a.getY(), b.getX() - a.getX());
  //			while (beta < -Math.PI)
  //				beta += EPMath.PI_MUL_2;
  //			while (beta > Math.PI)
  //				beta -= EPMath.PI_MUL_2;
  //
  //			double gamma = (beta - alpha);
  //			while (gamma < -Math.PI)
  //				gamma += EPMath.PI_MUL_2;
  //			while (gamma > Math.PI)
  //				gamma -= EPMath.PI_MUL_2;
  //
  //			if (gamma == 0)
  //				return;
  //
  //			double arc = Math.abs(2 * gamma);
  //
  //			double r = dAB / (2 * Math.sin(gamma));
  //
  //			boolean rgt = handledShape.segment.getAttributeOfPart("attstr", "name",
  // "type", "val").equals("rgt");
  //
  //			if (Math.abs(r) < trackWidth
  //					/ 2
  //					+ handledShape.allDatas[rgt ? XmlObjShape.rightBorderWidth :
  // XmlObjShape.leftBorderWidth]
  //					+ handledShape.allDatas[rgt ? Math.max(XmlObjShape.rightSideStartWidth,
  //							XmlObjShape.rightSideEndWidth) : Math.max(XmlObjShape.leftSideStartWidth,
  //							XmlObjShape.leftSideEndWidth)])
  //				return; // radiusStart would be too little
  //
  //			if (!((r < 0 && rgt) || (r > 0 && !rgt)))
  //				return; // the curvature would be inversed
  //
  //			if (arc >= 360)
  //				return; // the arc would be to big
  //
  //			if (arc * Math.abs(r) > 2000)
  //				return; // the segment would be too long
  //
  //			if (currentMovedShape != handledShape)
  //			{
  //				// install undo step
  //				UndoStep undoStep = new UndoStepModifyTrackSegment(handledShape);
  //				undoSteps.add(undoStep);
  //				redoSteps.clear();
  //				undoStep.redo();
  //
  //				currentMovedShape = handledShape;
  //			}
  //
  //			handledShape.segment.setAttributeOfPart("attnum", "name", "radiusStart",
  // "unit", "m");
  //			handledShape.segment.setAttributeOfPartDouble("attnum", "name",
  // "radiusStart",
  // "val", Math.abs(r));
  //			handledShape.segment.setAttributeOfPartDouble("attnum", "name", "end
  // radiusStart", "val", Math.abs(r)); // constant
  //			// rayon
  //
  //			handledShape.segment.setAttributeOfPart("attnum", "name", "arc", "unit",
  // "rad");
  //			handledShape.segment.setAttributeOfPartDouble("attnum", "name", "arc",
  // "val", arc);
  //
  //			calcGeometricObjects();
  //
  //			parentFrame.documentIsModified = true;
  //		} catch (Exception e)
  //		{
  //			e.printStackTrace();
  //		}
  //	}
  //

}
