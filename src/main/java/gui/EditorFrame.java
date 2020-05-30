/*
 *   EditorFrame.java
 *   Created on 9  2005
 *
 *    The EditorFrame.java is part of TrackEditor-0.6.0.
 *
 *    TrackEditor-0.6.0 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.6.0 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.6.0; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package gui;

import action.ActionProvider;
import gui.properties.PropertiesDialog;
import gui.splash.SplashScreen;
import gui.view.CircuitView;
import gui.view.enumerator.CircuitState;
import plugin.Plugin;
import plugin.torcs.TorcsPlugin;
import utils.*;
import utils.circuit.ContinuousSegment;
import utils.circuit.Curve;
import utils.circuit.Segment;
import utils.circuit.Straight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * @author Patrice Espie , Charalampos Alexopoulos
 * @auhor Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class EditorFrame extends JFrame {

  private ActionProvider actionProvider;
  private JPanel jContentPane = null;
  public boolean documentIsModified;
  private CircuitView view;

  // UI
  private JMenuBar mainMenuBar;
  private JScrollPane mainScrollPane = new JScrollPane();
  //  public JToggleButton toggleButtonDelete = null;
  private EdiorToolBar ediorToolBar;
  //private DeltaPanel			deltaPanel							= null;

  /**
   * The splash screen shown at startup
   */
  private SplashScreen _splash;
  public Plugin torcsPlugin = new TorcsPlugin(this);
  private Project prj;

  private String sep = System.getProperty("file.separator");

  public EditorFrame() {
    boolean doSplash = true;
    SplashScreen.setDoSplash(doSplash);
    if (doSplash) {
      _splash = SplashScreen.getInstance();
      _splash.setStatus("Initializing gui ...");
      _splash.incProgress(20);
      _splash.setVisible(true);
      Editor.getProperties().title = getClass().getPackage().getImplementationTitle();
      Editor.getProperties().version = getClass().getPackage().getImplementationVersion();

      try {
        Thread.sleep(100);
      }
      catch (InterruptedException ex) {
        System.err.println("Thread interrupted: " + ex.getMessage());
      }
    }

    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

    ClassLoader cldr = this.getClass().getClassLoader();

    try {
      view = new CircuitView(this);
      _splash.incProgress(20);
      Editor.getProperties().addPropertiesListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          //view.setBackgroundImage(properties.getImage());
          documentIsModified = true;
        }

      });
      initialize();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    _splash.dispose();
  }

  private void initialize() throws Exception {
    actionProvider = new ActionProvider(this);
    mainMenuBar = new EditorMenu(this);
    ediorToolBar = new EdiorToolBar(this);

    String title = this.getClass().getPackage().getImplementationTitle();
    String version = this.getClass().getPackage().getImplementationVersion();

    this.setTitle(title + " - " + version);

    ClassLoader cldr = this.getClass().getClassLoader();
    Image image = new ImageIcon(cldr.getResource("data/icons/icon.png")).getImage();

    this.setIconImage(image);
    setSize(new Dimension(800, 600));
    Point p = new Point();
    p.x = getProject().getFrameX();
    p.y = getProject().getFrameY();
    this.setLocation(p);
    this.setJMenuBar(mainMenuBar);
    this.setContentPane(getJContentPane());
    mainScrollPane.setMaximumSize(new Dimension(100, 100));
    mainScrollPane.setMinimumSize(new Dimension(10, 10));
    mainScrollPane.setPreferredSize(new Dimension(10, 10));
    view.setMaximumSize(new Dimension(100, 100));
    view.setMinimumSize(new Dimension(10, 10));
    view.setPreferredSize(new Dimension(10, 10));
    view.setSize(new Dimension(32767, 32767));
    _splash.incProgress(20);
    try {
      Thread.sleep(100);
    }
    catch (InterruptedException ex) {
      System.err.println("thread interrupted: " + ex.getMessage());
    }

    mainScrollPane.getViewport().add(view, null);
    _splash.incProgress(20);
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException ex) {
      System.err.println("Thread interrupted" + ex.getMessage());
    }
    this.setVisible(true);
  }

  /**
   *
   */
  public void openProject() {
    String tmp = "";
    String filename = Editor.getProperties().getPath() + sep + "project.xml";
    JFileChooser fc = new JFileChooser();
    fc.setSelectedFiles(null);
    fc.setSelectedFile(null);
    fc.rescanCurrentDirectory();
    fc.setApproveButtonMnemonic(0);
    fc.setDialogTitle("Project path selection");
    fc.setVisible(true);
    fc.setCurrentDirectory(new File(System.getProperty("user.dir") + sep + "tracks"));
    CustomFileFilter filter = new CustomFileFilter();
    filter.addValid(".prj.xml");
    filter.setDescription("*.prj.xml");
    fc.setFileFilter(filter);
    int result = fc.showDialog(this, "Ok");
    if (result == JFileChooser.APPROVE_OPTION) {
      tmp = fc.getSelectedFile().toString();
      filename = tmp;
      tmp = tmp.substring(0, tmp.lastIndexOf(sep));
      Editor.getProperties().setPath(tmp);
      tmp = Editor.getProperties().getPath().substring(0, tmp.lastIndexOf(sep));
      Editor.getProperties().setCategory(tmp.substring(tmp.lastIndexOf(sep) + 1));
      tmp = Editor.getProperties().getPath();
      tmp = tmp.substring(tmp.lastIndexOf(sep) + 1);
      Editor.getProperties().setTrackName(tmp);
      try {
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename));
        //setProject((Project) decoder.readObject());
        Editor.setProperties((Properties) decoder.readObject());
      }
      catch (FileNotFoundException ex) {
        System.out.println("not found");
      }
      catch (ClassCastException e) {
        //e.printStackTrace();
        System.out.println("This file can't be read");
      }
      tmp = Editor.getProperties().getPath() + sep + Editor.getProperties().getTrackName() + ".xml";
      File file = new File(tmp);
      torcsPlugin.readFile(file);
    }
  }

  /**
   * @param project
   */
  private void setProject(Project project) {
    this.prj = project;
  }

  /**
   *
   */
  public void saveProject() {
    if (!documentIsModified) return;
    if (!Editor.getProperties().getTrackName().equals("")) {
      String filename = Editor.getProperties().getPath() + sep + Editor.getProperties().getTrackName() + ".prj.xml";
      try {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename));
        encoder.setExceptionListener(new ExceptionListener() {
          public void exceptionThrown(Exception e) {
          }
        });
        encoder.writeObject(Editor.getProperties());
        encoder.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
      }
      torcsPlugin.exportTrack();
      documentIsModified = false;
    }
  }

  void itemCloseCircuit_actionPerformed(ActionEvent e) {
    if (canClose()) {
      System.exit(0);
    }
  }

  /**
   *
   */
  public void newProjectDialog() {
    NewProjectDialog newProject = new NewProjectDialog(this);
    newProject.setVisible(true);
    if (NewProjectDialog.APPROVE) {
      try {
        createNewCircuit();
      }
      catch (Exception e1) {
        e1.printStackTrace();
      }
      refresh();
    }
  }

  /**
   *
   */
  private void createNewCircuit() {
    Segment shape;
    Vector<Segment> track = new Vector<>();

    shape = new Straight();
    shape.setLength(100);
    shape.setProfilStepLength(4);
    track.add(shape);

    shape = new Curve();
    ((Curve) shape).setRadiusStart(100);
    ((Curve) shape).setRadiusEnd(100);
    ((Curve) shape).setArc(Math.PI);
    shape.setProfilStepLength(4);
    track.add(shape);

    shape = new Straight();
    shape.setLength(100);
    shape.setProfilStepLength(4);
    track.add(shape);

    shape = new Curve();
    ((Curve) shape).setRadiusStart(100);
    ((Curve) shape).setRadiusEnd(100);
    ((Curve) shape).setArc(Math.PI);
    shape.setProfilStepLength(4);
    track.add(shape);
    Segment previous = ContinuousSegment.getPreviousSegment(track, 0);
    Segment next = ContinuousSegment.getNextSegment(track, 3);
    ContinuousSegment.makeLinkedList(previous, next);
    TrackData.setTrackData(track);
  }

  /**
   * @return Returns the prj.
   */
  public Project getProject() {
    if (prj == null) {
      prj = new Project();
    }
    return prj;
  }

  /**
   * This method initializes jContentPane
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(mainScrollPane, java.awt.BorderLayout.CENTER);
      jContentPane.add(ediorToolBar.getJToolBar(), java.awt.BorderLayout.NORTH);
      //jContentPane.add(getDeltaPanel(), java.awt.BorderLayout.SOUTH);
    }
    return jContentPane;
  }

//	private DeltaPanel getDeltaPanel()
//	{
//	    if (deltaPanel == null)
//		{
//	        deltaPanel = new DeltaPanel();
//	        //deltaPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
//	        //deltaPanel.setBackground(new java.awt.Color(0,204,204));
//	        deltaPanel.setPreferredSize(new java.awt.Dimension(140,24));
//		}
//		return deltaPanel;
//	}

  protected boolean canClose() {
    if (documentIsModified) {
      // ask wether to save or not
      int Res = JOptionPane.showConfirmDialog(this, "The circuit was modified. Do you want to save it ?",
          "Closing circuit", JOptionPane.YES_NO_CANCEL_OPTION);
      if (Res == JOptionPane.CANCEL_OPTION) {
        // can't close
        return (false);
      }
      else if (Res == JOptionPane.YES_OPTION) {
        // save
        saveProject();

        // can close
        return (true);
      }
      else {
        // don't save, but can close
        return (true);
      }
    }
    else {
      // can close
      return (true);
    }
  }

  public void toggleButtonCreateStraightSegment_actionPerformed(ActionEvent e) {
    ediorToolBar.checkButtons(CircuitState.CREATE_STRAIGHT);
  }

  public void toggleButtonCreateLeftSegment_actionPerformed(ActionEvent e) {
    ediorToolBar.checkButtons(CircuitState.CREATE_LEFT_SEGMENT);
  }

  public void toggleButtonCreateRightSegment_actionPerformed(ActionEvent e) {
    ediorToolBar.checkButtons(CircuitState.CREATE_RIGHT_SEGMENT);
  }

  public void toggleButtonMoveSegments_actionPerformed(ActionEvent e) {
    ediorToolBar.checkButtons(CircuitState.MOVE_SEGMENTS);
  }

  public void toggleButtonDelete_actionPerformed(ActionEvent e) {
    ediorToolBar.checkButtons(CircuitState.DELETE);
  }

  public void toggleButtonShowArrow_actionPerformed(ActionEvent e) {
    //view.showArrows(getShowArrowsButton().isSelected());
  }

  void menuItemAddBackground_actionPerformed(ActionEvent e) {
    try {
      String tmp = "";
      //			String filename = Editor.getProperties().getPath();
      JFileChooser fc = new JFileChooser();
      fc.setSelectedFiles(null);
      fc.setSelectedFile(null);
      fc.rescanCurrentDirectory();
      fc.setApproveButtonMnemonic(0);
      fc.setDialogTitle("Background image selection");
      fc.setVisible(true);
      fc.setCurrentDirectory(new File(System.getProperty("user.dir") + "/tracks"));
      int result = fc.showDialog(this, "Ok");
      if (result == JFileChooser.APPROVE_OPTION) {
        tmp = fc.getSelectedFile().toString();
        //Editor.getProperties().setImage(tmp);
        //set view background image
        view.setBackgroundImage(tmp);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void menuItemShowStartPoint_actionPerformed(ActionEvent e) {
    //ediorToolBar.checkButtons(CircuitState.SHOW_BGRD_START_POSITION);
  }

  public void enableBackground(boolean enable){
    view.setShowBackground(enable);
    view.invalidate();
    view.repaint();
  }

  public void exportAc3d() {
    TrackgenPanel tg = new TrackgenPanel(this);
    tg.setModal(true);
    tg.setVisible(true);
  }

  /**
   *
   */
  public void propertiesDialog() {
    if (TrackData.getTrackData() != null) {
      PropertiesDialog properties = new PropertiesDialog();
      properties.setVisible(true);
      refresh();
    }
    else {
      message("No track", "Properties are not available");
    }
  }

  /**
   *
   */
  public void calculateDeltas() {
    DeltaPanel tg = new DeltaPanel(this, "", false);
    //tg.setModal(false);
    tg.setVisible(true);
  }

  public void refresh() {
    view.redrawCircuit();
    this.validate();
    this.repaint();
  }

  //	 Exit when window close
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING && documentIsModified) {
      // close request
      if (!canClose()) {
        // cancel close request
        return;
      }
    }
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      exit();
    }
  }

  private void message(String title, String msg) {
    if (title == null) {
      title = "Message";
    }
    JOptionPane.showMessageDialog(this, msg, title, JOptionPane.PLAIN_MESSAGE);
  }

  public void exit() {
    getProject().setFrameX(this.getX());
    getProject().setFrameY(this.getY());
    System.exit(0);
  }

  public CircuitView getView() {
    return view;
  }

  public ActionProvider getActionProvider() {
    return actionProvider;
  }

  public Plugin getTorcsPlugin() {
    return torcsPlugin;
  }

  public EdiorToolBar getEdiorToolBar() {
    return ediorToolBar;
  }

}
