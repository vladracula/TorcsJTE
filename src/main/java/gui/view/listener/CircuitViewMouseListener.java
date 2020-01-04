package gui.view.listener;

import gui.view.CircuitView;
import gui.view.enums.CircuitState;
import utils.Editor;
import utils.EditorPoint;
import utils.TrackData;
import utils.circuit.*;
import utils.undo.Undo;
import utils.undo.UndoAddSegment;
import utils.undo.UndoDeleteSegment;
import utils.undo.UndoSegmentChange;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Adam Kubon
 */

public class CircuitViewMouseListener implements MouseListener {

  private CircuitView circuitView;

  public CircuitViewMouseListener(CircuitView circuitView) {
    this.circuitView = circuitView;
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {

    if (mouseEvent.getButton() == 1) {
      circuitView.screenToReal(mouseEvent, circuitView.getClickPoint());
      circuitView.screenToReal(mouseEvent, circuitView.getMousePoint());
      try {
        switch (circuitView.getCurrentState()) {
          case NONE: {
          }
          break;

          case CREATE_LEFT_SEGMENT: {
            if (circuitView.getHandledShape() == null) return;

            // create a standard curve segment
            Vector<Segment> data = TrackData.getTrackData();
            int pos = data.indexOf(circuitView.getHandledShape());
            Curve newShape = new Curve("lft", circuitView.getHandledShape());
            newShape.setArc(Math.PI / 2);
            newShape.setRadiusStart(50);
            newShape.setRadiusEnd(50);
            newShape.setProfilStepLength(4);
            int count = Editor.getProperties().getCurveNameCount() + 1;
            Editor.getProperties().setCurveNameCount(count);
            newShape.setName("curve " + count);
            ContinuousSegment.makeLinkedList(data, pos, newShape);
            data.insertElementAt(newShape, pos + 1);
            Undo.add(new UndoAddSegment(newShape));
            circuitView.setSelectedShape(newShape);
            circuitView.openSegmentDialog(newShape);
//            view.getSegmentEditorDialog().addWindowListener(view);
            circuitView.getParentFrame().documentIsModified = true;
            circuitView.redrawCircuit();
          }
          break;

          case CREATE_RIGHT_SEGMENT: {
            if (circuitView.getHandledShape() == null)
              return;

            // create a standard curve segment
            Vector<Segment> data = TrackData.getTrackData();
            int pos = data.indexOf(circuitView.getHandledShape());
            Curve newShape = new Curve("rgt", circuitView.getHandledShape());
            newShape.setArc(Math.PI / 2);
            newShape.setRadiusStart(50);
            newShape.setRadiusEnd(50);
            newShape.setProfilStepLength(4);
            int count = Editor.getProperties().getCurveNameCount() + 1;
            Editor.getProperties().setCurveNameCount(count);
            newShape.setName("curve " + count);
            ContinuousSegment.makeLinkedList(data, pos, newShape);
            data.insertElementAt(newShape, pos + 1);
            Undo.add(new UndoAddSegment(newShape));
            circuitView.setSelectedShape(newShape);
            circuitView.openSegmentDialog(newShape);
  //          view.getSegmentEditorDialog().addWindowListener(view);
            circuitView.getParentFrame().documentIsModified = true;
            circuitView.redrawCircuit();
          }
          break;

          case CREATE_STRAIGHT: {
            if (circuitView.getHandledShape() == null)
              return;

            // create a standard straight segment
            Vector<Segment> data = TrackData.getTrackData();
            int pos = data.indexOf(circuitView.getHandledShape());
            Straight newShape = new Straight();
            newShape.setLength(50);
            int count = Editor.getProperties().getStraightNameCount() + 1;
            Editor.getProperties().setStraightNameCount(count);
            newShape.setName("straight " + count);
            ContinuousSegment.makeLinkedList(data, pos, newShape);
            data.insertElementAt(newShape, pos + 1);
            Undo.add(new UndoAddSegment(newShape));
            circuitView.setSelectedShape(newShape);
            circuitView.openSegmentDialog(newShape);
    //        view.getSegmentEditorDialog().addWindowListener(view);
            circuitView.getParentFrame().documentIsModified = true;
          }
          break;

          case DELETE: {
            try {
              boolean mustFireEvent = (circuitView.getSelectedShape() != null);

              circuitView.setSelectedShape(null);

              if (mustFireEvent)
                circuitView.fireSelectionChanged(circuitView.getSelectionChangedEvent());

              if (circuitView.getSegmentEditorDialog() != null) {
                if (!circuitView.getSegmentEditorDialog().dirty) {
                  //popUndo();
                }
                circuitView.getSegmentEditorDialog().dispose();
                circuitView.setSegmentEditorDialog(null);
              }

              // must check for a segment under the mouse
              Vector<Segment> data = TrackData.getTrackData();
              int pos = data.indexOf(circuitView.getHandledShape());
              Undo.add(new UndoDeleteSegment(circuitView.getHandledShape()));

              Segment previous = (Segment) data.get(pos - 1);
              Segment next = ContinuousSegment.getNextSegment(data, pos);
              data.remove(pos);
              ContinuousSegment.makeLinkedList(previous, next);

              circuitView.setHandledShape(null);
              //selectedShape = newShape;

              circuitView.getParentFrame().documentIsModified = true;

              circuitView.redrawCircuit();

              circuitView.setState(CircuitState.NONE);
              circuitView.getParentFrame().toggleButtonDelete.setSelected(false);

              circuitView.getParentFrame().documentIsModified = true;
            }
            catch (Exception ex) {
              ex.printStackTrace();
            }
          }
          break;
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    CircuitState currentState = circuitView.getCurrentState();
    EditorPoint imgOffsetStart = circuitView.getImgOffsetStart();
    EditorPoint imgOffsetPrev = circuitView.getImgOffsetPrev();

    if (mouseEvent.getButton() == 3) {
      Point2D.Double tmp = new Point2D.Double(imgOffsetStart.getX(), imgOffsetStart.getY());
      circuitView.screenToReal(mouseEvent, tmp);
      imgOffsetStart.setLocation(tmp.getX(), tmp.getY());
      imgOffsetPrev.setLocation(Editor.getProperties().getImgOffset());
    }
    if (mouseEvent.getButton() == 1) {
      try {
        circuitView.screenToReal(mouseEvent, circuitView.getClickPoint());

        // must check for a segment under the mouse
        Segment obj = circuitView.findObjAtMousePos();

        Segment lastSelectedShape = circuitView.getSelectedShape();

        boolean selectedShapeChanged = (circuitView.getSelectedShape() != obj);
        circuitView.setSelectedShape(obj);

        circuitView.setHandleDragging(-1);

        if (circuitView.getSelectedShape() != null) {
          circuitView.setDragging(true);

          int curHandle = 0;
          for (Iterator i = circuitView.getHandles().iterator(); i.hasNext(); curHandle++) {
            ObjShapeHandle h = (ObjShapeHandle) i.next();

            // is the mouse in the handledShape handle ?
            if (mouseEvent.getX() > h.trPoints[0].getX() - ObjShapeHandle.handleSize
                && mouseEvent.getX() < h.trPoints[0].getX() + ObjShapeHandle.handleSize
                && mouseEvent.getY() > h.trPoints[0].getY() - ObjShapeHandle.handleSize
                && mouseEvent.getY() < h.trPoints[0].getY() + ObjShapeHandle.handleSize) {
              circuitView.setHandleDragging(curHandle);
              circuitView.setSelectedShape(circuitView.getHandledShape());
              break;
            }
          }
        }

        if (lastSelectedShape != circuitView.getSelectedShape())
          circuitView.fireSelectionChanged(circuitView.getSelectionChangedEvent());

        switch (currentState) {
          case NONE: {
            if (circuitView.getSelectedShape() != null) {
              Undo.add(new UndoSegmentChange(circuitView.getSelectedShape()));
              circuitView.openSegmentDialog(circuitView.getSelectedShape());
            }

            if (selectedShapeChanged) {
              circuitView.invalidate();
              circuitView.repaint();
            }
          }
          break;

          case SHOW_BGRD_START_POSITION: {
            //						if (backgroundRectangle.contains(clickPoint))
            //						{
            //							backgroundRectangle.setRect(backgroundRectangle.getX()
            // - clickPoint.getX(),
            //									backgroundRectangle.getY() - clickPoint.getY(),
            // backgroundRectangle.getWidth(),
            //									backgroundRectangle.getHeight());
            //
            //							torcstuneSection.setAttributeOfPart("attnum", "name",
            // "bgrd img x", "val", Integer
            //									.toString((int) backgroundRectangle.getX()));
            //							torcstuneSection.setAttributeOfPart("attnum", "name",
            // "bgrd img y", "val", Integer
            //									.toString((int) backgroundRectangle.getY()));
            //							torcstuneSection.setAttributeOfPart("attnum", "name",
            // "bgrd img width", "val", Integer
            //									.toString((int) backgroundRectangle.getWidth()));
            //							torcstuneSection.setAttributeOfPart("attnum", "name",
            // "bgrd img height", "val", Integer
            //									.toString((int) backgroundRectangle.getHeight()));
            //
            //							parentFrame.documentIsModified = true;
            //							invalidate();
            //							repaint();
            //
            //							setState(STATE_NONE);
            //						}
          }
          break;
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * input events management
   */
  @Override
  public void mouseReleased(MouseEvent mouseEvent) {

    CircuitState currentState = circuitView.getCurrentState();

    if (mouseEvent.getButton() == 1) {
      switch (currentState) {
        case NONE: {
          if (circuitView.isDragging()) {
            try {
              circuitView.redrawCircuit();
            }
            catch (Exception ex) {
              ex.printStackTrace();
            }
          }
        }
        break;
      }
      circuitView.setDragging(false);
    }
  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }

}
