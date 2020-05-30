package gui.view;

import gui.EditorFrame;
import gui.segment.SegmentEditorDialog;
import gui.segment.listener.SegmentEditorDialogWindowListener;
import gui.view.enumerator.CircuitState;
import gui.view.listener.CircuitViewMouseListener;
import gui.view.listener.CircuitViewMouseMotionListener;
import gui.view.listener.CircuitViewMouseWheelListener;
import utils.Editor;
import utils.EditorPoint;
import utils.TrackData;
import utils.circuit.ObjShapeHandle;
import utils.circuit.ObjShapeTerrain;
import utils.circuit.Segment;
import utils.circuit.XmlObjPits;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * <p>
 * Titre : Torcs Tune
 * </p>
 * <p>
 * Description : Torcs tuning
 * </p>
 * <p>
 * Copyright : Copyright (c) 2002-2019 Patrice Espie, Adam Kubon
 * </p>
 * <p>
 * Soci�t� :
 * </p>
 *
 * @author Patrice Espie
 * @author Adam Kubon
 * @version 0.1a
 */

public class CircuitView extends JComponent {

  /**
   * zooming factor
   */
  private double zoomFactor = 1.0;
  /**
   * affine transformation
   */
  private AffineTransform affineTransform = new AffineTransform();
  /**
   * inverse affine transformation
   */
  private AffineTransform inverseAffineTransform = new AffineTransform();
  /** xml document containing current circuit */
  //	EPXMLDocument circuit;
  /**
   * bounding rectangle of all elements of the circuits, in meters
   */
  private Rectangle2D.Double boundingRectangle = new Rectangle2D.Double(0, 0, 0, 0);
  /**
   * showing outZone
   */
  private boolean showOutZone = false;
  /**
   * width of zone outside circuit terrain, shown on screen
   */
  double outZoneWidth = 0;                  // meters
  /**
   * height of zone outside circuit terrain, shown on screen
   */
  double outZoneHeight = 0;                  // meters
  /**
   * situation on circuit terrain of the screen center
   */
  private Point2D.Double screenCenter = new Point2D.Double(0, 0);      // meters
  /**
   * pits shape
   */
  XmlObjPits pits;
  /**
   * terrain shape
   */
  private ObjShapeTerrain terrain;
  /**
   * event to fire when selection has changed
   */
  private CircuitViewSelectionEvent selectionChangedEvent;
  /**
   * current selected shape
   */
  private Segment selectedShape = null;
  /**
   * current handled shape
   */
  private Segment handledShape = null;
  /**
   * current dragging state
   */
  private boolean dragging = false;
  /**
   * mouse pressed point, in meters
   */
  private Point2D.Double clickPoint = new Point2D.Double(0, 0);
  /**
   * mouse current point, in meters
   */
  private Point2D.Double mousePoint = new Point2D.Double(0, 0);
  /**
   * handles to be shown
   */
  private ArrayList<ObjShapeHandle> handles = new ArrayList<>();
  /**
   * temp handle for calculs
   */
  private ObjShapeHandle handle = new ObjShapeHandle();
  /**
   * dragging handle index
   */
  private int handleDragging = -1;
  /**
   * current moved shape, for undo management
   */
  Segment currentMovedShape = null;
  /**
   * flag for skipping recurrent events
   */
  boolean isAncestorResizing = false;
  /**
   * UI dialog
   */
  private SegmentEditorDialog segmentEditorDialog;
  /**
   * current operating state
   */
  private CircuitState currentState = CircuitState.NONE;
  /**
   * arrow showing state
   */
  public boolean showArrows = false;
  /**
   * undo list
   */
  protected ArrayList undoSteps = new ArrayList();
  /**
   * redo list
   */
  protected ArrayList redoSteps = new ArrayList();
  /**
   * show terrain border
   */
  private boolean terrainBorderMustBeShown = false;
  /**
   * selection listener management
   */
  private transient Vector<CircuitViewSelectionListener> selectionListeners;
  //private double				imgCo							= 1.0; //3.4;
  //private Point2D.Double		imgOffset						= new Point2D.Double(0, 0);
  private EditorPoint imgOffsetPrev = new EditorPoint();
  private EditorPoint imgOffsetStart = new EditorPoint();
  /**
   * background image
   */
  private ImageIcon backgroundImg = null;
  /**
   * background image position, in meters
   */
  private Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double();
  /**
   * background image showing state
   */
  private boolean showBackground = true;
  private int currentCount = 0;
  /**
   * upward link to parent frame
   */
  private EditorFrame editorFrame;

  /**
   * constructor
   *
   * @param editorFrame Upward link
   */
  public CircuitView(EditorFrame editorFrame) {
    try {
      addMouseListener(new CircuitViewMouseListener(this));
      addMouseMotionListener(new CircuitViewMouseMotionListener(this));
      addMouseWheelListener(new CircuitViewMouseWheelListener(this));
      this.editorFrame = editorFrame;
      terrain = new ObjShapeTerrain();
      selectionChangedEvent = new CircuitViewSelectionEvent(this);
      Editor.getProperties().addPropertiesListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          setCircuit();
        }
      });
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   */
  protected void setCircuit() {
    //		System.out.println("CircuitView.setCircuit");
    //		terrain = new ObjShapeTerrain();
    //		terrain.calcShape(null, boundingRectangle);
  }

  /**
   * blank construtor
   */
  public CircuitView() {
    this(null);
  }

  /**
   * Identifies whether or not this component can receive the focus.
   *
   * @return
   */

  public void screenToReal(MouseEvent e, Point2D.Double point) {
    point.setLocation(e.getX(), e.getY());
    inverseAffineTransform.transform(point, point);
  }

  /**
   * Look for an object under mouse position
   *
   * @return Object under mouse pos, or null if none
   */
  public Segment findObjAtMousePos() {
    Segment out = null;
    // must look for an object under the mouse
    int count = 0;
    try {
      Iterator i = TrackData.getTrackData().iterator();
      while (i.hasNext()) {
        Segment obj = (Segment) i.next();

        if (Class.forName("utils.circuit.Segment").isAssignableFrom(obj.getClass())
            && obj.contains(mousePoint.getX(), mousePoint.getY())) {
          // object found !
          out = ((Segment) obj);
          currentCount = count;
        }
        count++;
      }
    }
    catch (NullPointerException npe) {
      ;
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return out;
  }

  //	/**
  //	 * Set zoom factor and
  //	 */
  //	public void setZoomFactorSeeAllTerrain()
  //	{
  //	}
  //

  /**
   * Augments zoom factor by multiplying it by 1.2
   */
  public void incZoomFactor() {
    setZoomFactor(zoomFactor * 1.2);
  }

  /**
   * Lowers zoom factor by dividing it by 1.2
   */
  public void decZoomFactor() {
    Rectangle r = getVisibleRect();
    Rectangle2D.Double visibleRect = new Rectangle2D.Double(
        r.getX() / zoomFactor + boundingRectangle.getX(),
        r.getY() / zoomFactor + boundingRectangle.getY(),
        r.getWidth() / zoomFactor,
        r.getHeight() / zoomFactor);
    // check if all track bounds fits in window
    if (boundingRectangle.getWidth() < visibleRect.getWidth()
        && boundingRectangle.getHeight() < visibleRect.getHeight())
      return;
    setZoomFactor(zoomFactor / 1.2);
  }

  /**
   * Define precise zoom factor
   *
   * @param newZoomFactor
   */
  public void setZoomFactor(double newZoomFactor) {
    try {
      // update internal zoom factor
      zoomFactor = newZoomFactor;

      // new visible part of screen in pixels
      Rectangle r = getVisibleRect();

      if (showOutZone) {
        // out zone size in meters
        outZoneWidth = (r.getWidth() / 2) * zoomFactor;
        outZoneHeight = (r.getHeight() / 2) * zoomFactor;
      }
      else {
        outZoneHeight = 0;
        outZoneWidth = 0;
      }

      // new visible part of screen in meters
      Rectangle2D.Double visibleRect = new Rectangle2D.Double(
          r.getX() / zoomFactor + boundingRectangle.getX() - outZoneWidth,
          r.getY() / zoomFactor + boundingRectangle.getY() - outZoneHeight,
          (r.getWidth() / zoomFactor) + 1,
          (r.getHeight() / zoomFactor) + 1);

      // set new screen size in pixels
      if (showOutZone) {
        setMaximumSize(new Dimension(
            (int) ((newZoomFactor * (boundingRectangle.getWidth() + visibleRect.getWidth())) + 1),
            (int) ((newZoomFactor * (boundingRectangle.getHeight() + visibleRect.getHeight()))) + 1));
      }
      else {
        setMaximumSize(new Dimension(
            (int) ((newZoomFactor * (boundingRectangle.getWidth())) + 1),
            (int) ((newZoomFactor * (boundingRectangle.getHeight()))) + 1));
      }

      setMinimumSize(getMaximumSize());
      setPreferredSize(getMaximumSize());
      setSize(getMaximumSize());

      // define affine transformation
      affineTransform.setToIdentity();
      affineTransform.translate(
          (outZoneWidth + boundingRectangle.getWidth() / 2) * zoomFactor,
          (outZoneHeight + boundingRectangle.getHeight() / 2) * zoomFactor);
      affineTransform.scale(zoomFactor, -zoomFactor);
      affineTransform.translate(
          -boundingRectangle.getX() - boundingRectangle.getWidth() / 2,
          -boundingRectangle.getY() - boundingRectangle.getHeight() / 2);
      inverseAffineTransform = affineTransform.createInverse();

      // scroll to keep same screen center as previously
      scrollRectToVisible(new Rectangle(
          (int) (zoomFactor * (screenCenter.getX() - boundingRectangle.getX()
              + outZoneWidth) - r.getWidth() / 2) + 1,
          (int) (zoomFactor * (screenCenter.getY() - boundingRectangle.getY()
              + outZoneHeight) - r.getHeight() / 2) + 1,
          (int) (r.getWidth()) - 2,
          (int) (r.getHeight()) - 2));

      // calculate and draw
      revalidate();
      invalidate();
      repaint();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Paint all paintable objects
   *
   * @param g
   */
  public void paint(Graphics g) {
    if (TrackData.getTrackData() != null) {

      // visible part of screen in pixels
      Rectangle r = getVisibleRect();

      Rectangle2D.Double visibleRect = new Rectangle2D.Double(
          r.getX() / zoomFactor + boundingRectangle.getX() - (outZoneWidth * zoomFactor),
          r.getY() / zoomFactor + boundingRectangle.getY() - (outZoneHeight * zoomFactor),
          (r.getWidth() / zoomFactor) + 1,
          (r.getHeight() / zoomFactor) + 1);

      // visible part of screen center in meters
      screenCenter = new Point2D.Double(
          visibleRect.getX() + visibleRect.getWidth() / 2,
          visibleRect.getY() + visibleRect.getHeight() / 2);

      // Paints background image
      if (showBackground && backgroundImg != null) {
        Point2D.Double p1 = new Point2D.Double(0, 0);
        Point2D.Double p2 = new Point2D.Double(
            backgroundRectangle.getWidth(), backgroundRectangle.getHeight());
/*
        //old solution
        Segment firstObj = (Segment) TrackData.getTrackData().get(0);
        Point2D.Double p1 = new Point2D.Double(0, 0);
        p1.setLocation(firstObj.points[0]);
        p1.setLocation(p1.getX() +
            Editor.getProperties().getImgOffset().getX(),
            p1.getY() + Editor.getProperties().getImgOffset().getY());
        Point2D.Double p2 = new Point2D.Double(
            backgroundRectangle.getWidth(),
            backgroundRectangle.getHeight());
        p2.setLocation(p2.getX(), p2.getY());

        p1 = (Point2D.Double) affineTransform.transform(p1, null);
*/
        //    p1 = (Point2D.Double) affineTransform.transform(p1, null);

        g.drawImage(backgroundImg.getImage(),
            (int) (p1.getX()),
            (int) (p1.getY()),
            (int) (p2.getX() * zoomFactor),
            (int) (p2.getY() * zoomFactor),
            null);
      }

      // paint the circuit
      //			Rectangle2D.Double br = boundingRectangle;
      //			g.drawRect((int)br.x,(int)br.y,(int)br.width,(int)br.height);

      Iterator i = TrackData.getTrackData().iterator();
      while (i.hasNext()) {
        Segment obj = (Segment) i.next();

        if (obj != selectedShape) {
          obj.draw(g, affineTransform);
        }
      }
      //		}
      //
      //			if (pits != null)
      //				pits.draw(g, affineTransform);

      if (terrain != null && terrainBorderMustBeShown) {
        terrain.calcShape(null, boundingRectangle);
        terrain.draw(g, affineTransform);
      }

      if (selectedShape != null) {
        g.setColor(Color.ORANGE);
        selectedShape.draw(g, affineTransform);
      }

      if (handledShape != null) {
        if (currentState.equals(CircuitState.DELETE)) {
          g.setColor(Color.RED);
          handledShape.draw(g, affineTransform);
        }
      }
      if (handledShape != null && !currentState.equals(CircuitState.DELETE)) {
        g.setColor(Color.CYAN);
        i = handles.iterator();
      }
      while (i.hasNext()) {
        ((Segment) i.next()).draw(g, affineTransform);
      }

    }
    Rectangle2D.Double br = boundingRectangle;
  }

//	public Dimension getPreferredScrollableViewportSize()
//	{
//		return (getPreferredSize());
//	}
//	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
//	{
//		return (10);
//	}
//	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
//	{
//		return (10);
//	}
//	public boolean getScrollableTracksViewportWidth()
//	{
//		return (false);
//	}
//	public boolean getScrollableTracksViewportHeight()
//	{
//		return (false);
//	}

  private void jbInit() throws Exception {
    this.setMinimumSize(new Dimension(32767, 32767));
    this.setPreferredSize(new Dimension(32767, 32767));
    this.setSize(new Dimension(32767, 32767));

    //		this.addHierarchyBoundsListener(new
    // java.awt.event.HierarchyBoundsAdapter()
    //		{
    //			public void ancestorResized(HierarchyEvent e)
    //			{
    //				this_ancestorResized(e);
    //			}
    //		});
  }

  //
  //	void this_ancestorResized(HierarchyEvent e)
  //	{
  //		if (isAncestorResizing)
  //			return;
  //
  //		isAncestorResizing = true;
  //
  //		try
  //		{
  //			if (circuit != null)
  //			{
  //				// create the geometric representation of the circuit
  //				calcGeometricObjects();
  //			}
  //		} catch (Exception ex)
  //		{
  //			ex.printStackTrace();
  //		}
  //
  //		isAncestorResizing = false;
  //	}
  //
  public void setState(CircuitState state) {
    currentState = state;

    boolean mustFire = (selectedShape != null);
    selectedShape = null;

    if (mustFire)
      fireSelectionChanged(selectionChangedEvent);

    if (segmentEditorDialog != null) {
      if (!segmentEditorDialog.dirty) {
        //popUndo();
      }

      segmentEditorDialog.dispose();
      segmentEditorDialog = null;
    }
  }
/*
  	public void showArrows(boolean show)
  	{
  		showArrows = show;

  		try
  		{
  			calcGeometricObjects();
  		} catch (Exception e)
  		{
  			e.printStackTrace();
  		}
  	}
*/
//	public void popUndo()
//	{
//		//undoSteps.remove(undoSteps.size() - 1);
//	}

//	public void undo()
//	{
//		if (undoSteps.size() > 0)
//		{
//			UndoStep undoStep = (UndoStep) undoSteps.remove(undoSteps.size() - 1);
//			redoSteps.add(undoStep);
//
//			undoStep.undo();
//
//			try
//			{
//				this.redrawCircuit();
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//
//			if (undoSteps.size() == 0)
//				editorFrame.documentIsModified = false;
//		}
//	}
//
//	public void redo()
//	{
//		if (redoSteps.size() > 0)
//		{
//			UndoStep undoStep = (UndoStep) redoSteps.remove(redoSteps.size() - 1);
//			undoSteps.add(undoStep);
//
//			undoStep.redo();
//
//			try
//			{
//				this.redrawCircuit();
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//
//			editorFrame.documentIsModified = true;
//		}
//	}

  public void setBackgroundImage(String fileName) {
    double scale = Editor.getProperties().getImageScale();
    try {
      backgroundImg = new ImageIcon(fileName);
      int width = (int) (backgroundImg.getIconWidth());
      int height = (int) (backgroundImg.getIconHeight());
      backgroundRectangle.setFrame(0, 0, width * scale, height * scale);
      //System.out.println("Zoom = " + zoomFactor);
      editorFrame.documentIsModified = true;

      invalidate();
      repaint();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * @return Returns the showBackground.
   */
  public boolean isShowBackground() {
    return showBackground;
  }

  /**
   * @param showBackground The showBackground to set.
   */
  public void setShowBackground(boolean showBackground) {
    this.showBackground = showBackground;
    if (showBackground && Editor.getProperties().getImage() != null) {
      setBackgroundImage(Editor.getProperties().getImage());
    }
  }

  public void showTerrainBorder(boolean terrainBorderMustBeShown) {
    this.terrainBorderMustBeShown = terrainBorderMustBeShown;
  }

  public synchronized void removeSelectionListener(CircuitViewSelectionListener l) {
    if (selectionListeners != null && selectionListeners.contains(l)) {
      Vector<CircuitViewSelectionListener> v = new Vector<>(selectionListeners);
      v.removeElement(l);
      selectionListeners = v;
    }
  }

  public synchronized void addSelectionListener(CircuitViewSelectionListener l) {
    Vector<CircuitViewSelectionListener> v =
        selectionListeners == null ? new Vector<>(2) : new Vector<>(selectionListeners);
    if (!v.contains(l)) {
      v.addElement(l);
      selectionListeners = v;
    }
  }

  public void fireSelectionChanged(CircuitViewSelectionEvent e) {
    if (selectionListeners != null) {
      Vector listeners = selectionListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((CircuitViewSelectionListener) listeners.elementAt(i)).circuitViewSelectionChanged(e);
      }
    }
  }

  public void redrawCircuit() {
    Vector track = TrackData.getTrackData();
    if (track == null) return;
    int size = track.size();

    Editor.getProperties().setCurrentA(0);
    Editor.getProperties().setCurrentY(0);
    Editor.getProperties().setCurrentX(0);

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      obj.setCount(i + 1);
      try {
        obj.calcShape(null);
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    // calculate the bounding rectangle
    boundingRectangle = null;

    for (int i = 0; i < size; i++) {
      Rectangle2D.Double r = ((Segment) track.get(i)).getBounds();

      if (boundingRectangle == null)
        boundingRectangle = r;
      else
        boundingRectangle.add(r);
    }
//		 zoom
    setZoomFactor(zoomFactor);
  }

  public void openSegmentDialog(Segment shape) {
    if (segmentEditorDialog != null) {
      segmentEditorDialog.setShape(shape);
      segmentEditorDialog.setVisible(true);
    }

    if (segmentEditorDialog == null) {
      segmentEditorDialog = new SegmentEditorDialog(this, editorFrame, "", false, shape);
      segmentEditorDialog.addWindowListener(new SegmentEditorDialogWindowListener(this));
    }
  }

  public CircuitState getCurrentState() {
    return currentState;
  }

  public Segment getHandledShape() {
    return handledShape;
  }

  public Segment getSelectedShape() {
    return selectedShape;
  }

  public void setSelectedShape(Segment selectedShape) {
    this.selectedShape = selectedShape;
  }

  public void setHandledShape(Segment handledShape) {
    this.handledShape = handledShape;
  }

  public EditorFrame getEditorFrame() {
    return editorFrame;
  }

  public void setEditorFrame(EditorFrame editorFrame) {
    this.editorFrame = editorFrame;
  }

  public boolean isDragging() {
    return dragging;
  }

  public void setDragging(boolean dragging) {
    this.dragging = dragging;
  }

  public int getHandleDragging() {
    return handleDragging;
  }

  public void setHandleDragging(int handleDragging) {
    this.handleDragging = handleDragging;
  }

  public ArrayList<ObjShapeHandle> getHandles() {
    return handles;
  }

  public ObjShapeHandle getHandle() {
    return handle;
  }

  public Point2D.Double getMousePoint() {
    return mousePoint;
  }

  public Point2D.Double getClickPoint() {
    return clickPoint;
  }

  public CircuitViewSelectionEvent getSelectionChangedEvent() {
    return selectionChangedEvent;
  }

  public EditorPoint getImgOffsetPrev() {
    return imgOffsetPrev;
  }

  public void setImgOffsetPrev(EditorPoint imgOffsetPrev) {
    this.imgOffsetPrev = imgOffsetPrev;
  }

  public EditorPoint getImgOffsetStart() {
    return imgOffsetStart;
  }

  public void setImgOffsetStart(EditorPoint imgOffsetStart) {
    this.imgOffsetStart = imgOffsetStart;
  }

  public SegmentEditorDialog getSegmentEditorDialog() {
    return segmentEditorDialog;
  }

  public void setSegmentEditorDialog(SegmentEditorDialog segmentEditorDialog) {
    this.segmentEditorDialog = segmentEditorDialog;
  }

}
