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

import gui.properties.PropertiesDialog;
import gui.splash.SplashScreen;
import gui.view.CircuitView;
import plugin.Plugin;
import plugin.torcs.TorcsPlugin;
import utils.*;
import utils.circuit.Curve;
import utils.circuit.Segment;
import utils.circuit.Straight;
import utils.undo.Undo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EditorFrame extends JFrame {

  //private Properties			properties							= Properties.getInstance();
  private UndoAction undoAction = null;
  private RedoAction redoAction = null;
  private DeleteAction deleteAction = null;
  private ZoomPlusAction zoomPlusAction = null;
  private ZoomOneAction zoomOneAction = null;
  private ZoomMinusAction zoomMinusAction = null;
  private StraightAction straightAction = null;
  private RightAction rightAction = null;
  private LeftAction leftAction = null;
  private NewAction newAction = null;
  private OpenAction openAction = null;
  private SaveAction saveAction = null;
  private ShowArrowsAction showArrowsAction = null;
  private ShowBackgroundAction showBackgroundAction = null;
  private MoveAction moveAction = null;
  private HelpAction helpAction = null;
  private ExportAllAction allAction = null;
  private ExportAC3Action ac3Action = null;
  private PropertiesAction propertiesAction = null;
  private CalcDeltaAction calcDeltaAction = null;

  private JPanel jContentPane = null;

  public boolean documentIsModified;

  private CircuitView view;

  // UI
  private JMenuBar mainMenuBar = new JMenuBar();
  private JMenu menuFile = new JMenu();
  private JMenuItem itemSaveCircuit = null;
  private JMenuItem itemCloseCircuit = new JMenuItem();
  private JScrollPane mainScrollPane = new JScrollPane();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JButton buttonZoomPlus = null;
  private JButton buttonZoomMinus = null;
  private JButton buttonZoomOne = null;
  private  JToggleButton toggleButtonCreateStraightSegment = null;
  private  JToggleButton toggleButtonCreateLeftSegment = null;
  private  JToggleButton toggleButtonCreateRightSegment = null;
  public JToggleButton toggleButtonDelete = null;
  private JButton undoButton = null;
  private JButton redoButton = null;
  private JMenu viewMenu = new JMenu();
  private JMenuItem menuItemAddBackground = new JMenuItem();
  private JMenuItem menuItemShoStartPoint = new JMenuItem();

  private JToolBar jToolBar = null;
  private JMenuItem newMenuItem = null;
  private JMenuItem openMenuItem = null;
  private JButton saveButton = null;
  private JButton openButton = null;
  private JButton helpButton = null;
  private JMenuItem zoomPlusMenuItem = null;
  private JMenuItem zoomMinusMenuItem = null;
  private JMenuItem zoomOneMenuItem = null;
  private JMenu segmentMenu = null;
  private JMenu helpMenu = null;
  private JMenuItem aboutMenuItem = null;
  private JMenuItem addStraightMenuItem = null;
  private JMenuItem addRightMenuItem = null;
  private JMenuItem addLeftMenuItem = null;
  private JMenuItem moveMenuItem = null;
  private JMenuItem deleteMenuItem = null;
  private JMenuItem showArrowsMenuItem = null;
  private JMenuItem showBackgroundMenuItem = null;
  private JToggleButton moveButton = null;
  private JToggleButton showArrowsButton = null;
  private JToggleButton showBackgroundButton = null;
  private JButton newButton = null;
  //private DeltaPanel			deltaPanel							= null;

  /**
   * The splash screen shown at startup
   */
  private SplashScreen _splash;

  private JMenu editMenu = null;
  private JMenuItem undoMenuItem = null;
  private JMenuItem redoMenuItem = null;
  private JMenu importMenu = null;
  private JMenu exportMenu = null;
  private JMenuItem exportAllMenuItem = null;
  private JMenuItem exportXmlMenuItem = null;
  private JMenuItem exportAC3MenuItem = null;
  private JMenuItem propertiesMenuItem = null;

  public Plugin torcsPlugin = new TorcsPlugin(this);
  private Project prj;

  private String sep = System.getProperty("file.separator");

  private JButton calculateDeltaButton = null;

  public EditorFrame() {
    boolean doSplash = true;
    SplashScreen.setDoSplash(doSplash);
    if (doSplash) {
      _splash = SplashScreen.getInstance();
      _splash.setStatus("Initializing gui ...");
      _splash.incProgress(20);
      _splash.setVisible(true);
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException ex) {
        System.err.println("Thread interrupted: " + ex.getMessage());
      }
    }

    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

    ClassLoader cldr = this.getClass().getClassLoader();

    undoAction = new UndoAction("Undo", createNavigationIcon("Undo24", cldr), "Undo.", KeyEvent.VK_Z);
    redoAction = new RedoAction("Redo", createNavigationIcon("Redo24", cldr), "Redo.", KeyEvent.VK_R);
    deleteAction = new DeleteAction("Delete", createNavigationIcon("Cut24", cldr), "Delete.", KeyEvent.VK_L);
    zoomPlusAction = new ZoomPlusAction("Zoom in", createNavigationIcon("ZoomIn24", cldr), "Zoom in.", KeyEvent.VK_M);
    zoomOneAction = new ZoomOneAction("Zoom 1:1", createNavigationIcon("Zoom24", cldr), "Zoom 1:1.", KeyEvent.VK_N);
    zoomMinusAction = new ZoomMinusAction("Zoom out", createNavigationIcon("ZoomOut24", cldr), "Zoom out.", KeyEvent.VK_O);
    straightAction = new StraightAction("Add straight", createNavigationIcon("Straight24", cldr), "Add a straight segment.", KeyEvent.VK_P);
    rightAction = new RightAction("Add right", createNavigationIcon("TurnRight24", cldr), "Add a right turn segment.", KeyEvent.VK_Q);
    leftAction = new LeftAction("Add left", createNavigationIcon("TurnLeft24", cldr), "Add a left turn segment.", KeyEvent.VK_S);
    newAction = new NewAction("New", createNavigationIcon("New24", cldr), "New circuit.", KeyEvent.VK_S);
    openAction = new OpenAction("Open", createNavigationIcon("Open24", cldr), "Open existing circuit.", KeyEvent.VK_S);
    saveAction = new SaveAction("Save", createNavigationIcon("Save24", cldr), "Save the circuit.", KeyEvent.VK_S);
    moveAction = new MoveAction("Move", createNavigationIcon("Export24", cldr), "Move.", KeyEvent.VK_S);
    showArrowsAction = new ShowArrowsAction("Show arrows", createNavigationIcon("FindAgain24", cldr), "Show arrows.", KeyEvent.VK_S);
    showBackgroundAction = new ShowBackgroundAction("Show background", createNavigationIcon("Search24", cldr), "Show background image.", KeyEvent.VK_S);
    helpAction = new HelpAction("Help", createNavigationIcon("Help24", cldr), "Help.", KeyEvent.VK_S);
    /** ******************************************************************* */
    allAction = new ExportAllAction("All", null, "Export both XML file and AC3 file.", KeyEvent.VK_S);
    ac3Action = new ExportAC3Action("AC3", null, "Create AC3 file.", KeyEvent.VK_S);
    propertiesAction = new PropertiesAction("Properties", null, "Properties dialog.", KeyEvent.VK_S);
    calcDeltaAction = new CalcDeltaAction("Delta's", createNavigationIcon("Calc24", cldr), "Calculate Delta's for x,y,z and angle.", KeyEvent.VK_S);

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

  /**
   *
   */
  protected void openProject() {
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
  protected void saveProject() {
//		if (documentIsModified)
    if (true) {
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
  protected void newProjectDialog() {
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
    Vector<Segment> track = new Vector<Segment>();

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

  private void initialize() throws Exception {
    String title = Editor.getProperties().title + " " + Editor.getProperties().version;
    this.setTitle(title);

    ClassLoader cldr = this.getClass().getClassLoader();
    Image image = new ImageIcon(cldr.getResource("data/icons/icon.png")).getImage();

    this.setIconImage(image);
    setSize(new Dimension(800, 600));
    Point p = new Point();
    p.x = getProject().getFrameX();
    p.y = getProject().getFrameY();
    this.setLocation(p);
    menuFile.setText("File");
    itemCloseCircuit.setText("Exit");
    itemCloseCircuit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        itemCloseCircuit_actionPerformed(e);
      }
    });
    this.setJMenuBar(mainMenuBar);
    this.setContentPane(getJContentPane());
    mainScrollPane.setMaximumSize(new Dimension(100, 100));
    mainScrollPane.setMinimumSize(new Dimension(10, 10));
    mainScrollPane.setPreferredSize(new Dimension(10, 10));
    view.setMaximumSize(new Dimension(100, 100));
    view.setMinimumSize(new Dimension(10, 10));
    view.setPreferredSize(new Dimension(10, 10));
    view.setSize(new Dimension(32767, 32767));
    viewMenu.setText("View");
    _splash.incProgress(20);
    try {
      Thread.sleep(100);
    }
    catch (InterruptedException ex) {
      System.err.println("thread interrupted: " + ex.getMessage());
    }
    menuItemAddBackground.setText("Add ...");
    menuItemAddBackground.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuItemAddBackground_actionPerformed(e);
      }
    });
    menuItemShoStartPoint.setText("Show start point");
    menuItemShoStartPoint.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuItemShoStartPoint_actionPerformed(e);
      }
    });

    mainScrollPane.getViewport().add(view, null);
    mainMenuBar.add(menuFile);
    mainMenuBar.add(getEditMenu());
    mainMenuBar.add(viewMenu);
    mainMenuBar.add(getSegmentMenu());
    mainMenuBar.add(getHelpMenu());
    menuFile.add(getNewMenuItem());
    menuFile.add(getOpenMenuItem());
    menuFile.add(getItemSaveCircuit());
    menuFile.addSeparator();
    menuFile.add(getImportMenu());
    menuFile.add(getExportMenu());
    menuFile.addSeparator();
    menuFile.add(getPropertiesMenuItem());
    menuFile.addSeparator();
    menuFile.add(itemCloseCircuit);

    viewMenu.add(getZoomPlusMenuItem());
    viewMenu.add(getZoomMinusMenuItem());
    viewMenu.add(getZoomOneMenuItem());
    viewMenu.addSeparator();
    viewMenu.add(getShowArrowsMenuItem());
    viewMenu.add(getShowBackgroundMenuItem());
    viewMenu.add(menuItemShoStartPoint);
    viewMenu.add(menuItemAddBackground);
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
   * This method initializes newMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getNewMenuItem() {
    if (newMenuItem == null) {
      newMenuItem = new JMenuItem();
      newMenuItem.setAction(newAction);
      newMenuItem.setIcon(null);
    }
    return newMenuItem;
  }

  /**
   * This method initializes openMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getOpenMenuItem() {
    if (openMenuItem == null) {
      openMenuItem = new JMenuItem();
      openMenuItem.setAction(openAction);
      openMenuItem.setIcon(null);
    }
    return openMenuItem;
  }

  /**
   * This method initializes itemSaveCircuit
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getItemSaveCircuit() {
    if (itemSaveCircuit == null) {
      itemSaveCircuit = new JMenuItem();
      itemSaveCircuit.setAction(saveAction);
      itemSaveCircuit.setIcon(null);
    }
    return itemSaveCircuit;
  }

  /**
   * This method initializes zoomPlusMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getZoomPlusMenuItem() {
    if (zoomPlusMenuItem == null) {
      zoomPlusMenuItem = new JMenuItem();
      zoomPlusMenuItem.setAction(zoomPlusAction);
      zoomPlusMenuItem.setIcon(null);
    }
    return zoomPlusMenuItem;
  }

  /**
   * This method initializes zoomMinusMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getZoomMinusMenuItem() {
    if (zoomMinusMenuItem == null) {
      zoomMinusMenuItem = new JMenuItem();
      zoomMinusMenuItem.setAction(zoomMinusAction);
      zoomMinusMenuItem.setIcon(null);
    }
    return zoomMinusMenuItem;
  }

  /**
   * This method initializes zoomOneMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getZoomOneMenuItem() {
    if (zoomOneMenuItem == null) {
      zoomOneMenuItem = new JMenuItem();
      zoomOneMenuItem.setAction(zoomOneAction);
      zoomOneMenuItem.setIcon(null);
    }
    return zoomOneMenuItem;
  }

  /**
   * This method initializes segmentMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getSegmentMenu() {
    if (segmentMenu == null) {
      segmentMenu = new JMenu();
      segmentMenu.setText("Segment");
      segmentMenu.add(getAddStraightMenuItem());
      segmentMenu.add(getAddRightMenuItem());
      segmentMenu.add(getAddLeftMenuItem());
      segmentMenu.addSeparator();
      segmentMenu.add(getMoveMenuItem());
      segmentMenu.add(getDeleteMenuItem());
    }
    return segmentMenu;
  }

  /**
   * This method initializes helpMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getHelpMenu() {
    if (helpMenu == null) {
      helpMenu = new JMenu();
      helpMenu.setText("Help");
      helpMenu.add(getAboutMenuItem());
    }
    return helpMenu;
  }

  /**
   * This method initializes aboutMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAboutMenuItem() {
    if (aboutMenuItem == null) {
      aboutMenuItem = new JMenuItem();
      aboutMenuItem.setText("About");
      aboutMenuItem.setAction(helpAction);
    }
    return aboutMenuItem;
  }

  /**
   * This method initializes addStraightMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAddStraightMenuItem() {
    if (addStraightMenuItem == null) {
      addStraightMenuItem = new JMenuItem();
      addStraightMenuItem.setAction(straightAction);
      addStraightMenuItem.setIcon(null);
    }
    return addStraightMenuItem;
  }

  /**
   * This method initializes addRightMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAddRightMenuItem() {
    if (addRightMenuItem == null) {
      addRightMenuItem = new JMenuItem();
      addRightMenuItem.setAction(rightAction);
      addRightMenuItem.setIcon(null);
    }
    return addRightMenuItem;
  }

  /**
   * This method initializes addLeftMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAddLeftMenuItem() {
    if (addLeftMenuItem == null) {
      addLeftMenuItem = new JMenuItem();
      addLeftMenuItem.setAction(leftAction);
      addLeftMenuItem.setIcon(null);
    }
    return addLeftMenuItem;
  }

  /**
   * This method initializes moveMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getMoveMenuItem() {
    if (moveMenuItem == null) {
      moveMenuItem = new JMenuItem();
      moveMenuItem.setAction(moveAction);
      moveMenuItem.setIcon(null);
    }
    return moveMenuItem;
  }

  /**
   * This method initializes deleteMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getDeleteMenuItem() {
    if (deleteMenuItem == null) {
      deleteMenuItem = new JMenuItem();
      deleteMenuItem.setAction(deleteAction);
      deleteMenuItem.setIcon(null);
    }
    return deleteMenuItem;
  }

  /**
   * This method initializes showArrowsMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getShowArrowsMenuItem() {
    if (showArrowsMenuItem == null) {
      showArrowsMenuItem = new JMenuItem();
      showArrowsMenuItem.setAction(showArrowsAction);
      showArrowsMenuItem.setIcon(null);
    }
    return showArrowsMenuItem;
  }

  /**
   * This method initializes showBackgroundMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getShowBackgroundMenuItem() {
    if (showBackgroundMenuItem == null) {
      showBackgroundMenuItem = new JMenuItem();
      showBackgroundMenuItem.setAction(showBackgroundAction);
      showBackgroundMenuItem.setIcon(null);
    }
    return showBackgroundMenuItem;
  }

  /**
   * This method initializes editMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getEditMenu() {
    if (editMenu == null) {
      editMenu = new JMenu();
      editMenu.setText("Edit");
      editMenu.add(getUndoMenuItem());
      editMenu.add(getRedoMenuItem());
    }
    return editMenu;
  }

  /**
   * This method initializes undoMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getUndoMenuItem() {
    if (undoMenuItem == null) {
      undoMenuItem = new JMenuItem();
      undoMenuItem.setAction(undoAction);
      undoMenuItem.setIcon(null);
    }
    return undoMenuItem;
  }

  /**
   * This method initializes redoMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getRedoMenuItem() {
    if (redoMenuItem == null) {
      redoMenuItem = new JMenuItem();
      redoMenuItem.setAction(redoAction);
      redoMenuItem.setIcon(null);
    }
    return redoMenuItem;
  }

  /**
   * This method initializes importMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenu getImportMenu() {
    if (importMenu == null) {
      importMenu = new JMenu();
      importMenu.setText("Import");
      importMenu.add(torcsPlugin.getImportMenuItem());
    }
    return importMenu;
  }

  /**
   * This method initializes exportMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getExportMenu() {
    if (exportMenu == null) {
      exportMenu = new JMenu();
      exportMenu.setText("Export");
      exportMenu.add(getExportAllMenuItem());
      exportMenu.addSeparator();
      exportMenu.add(torcsPlugin.getExportMenuItem());
      //			exportMenu.add(getExportXmlMenuItem());
      exportMenu.add(getExportAC3MenuItem());
    }
    return exportMenu;
  }

  /**
   * This method initializes exportAllMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getExportAllMenuItem() {
    if (exportAllMenuItem == null) {
      exportAllMenuItem = new JMenuItem();
      exportAllMenuItem.setAction(allAction);
      exportAllMenuItem.setIcon(null);
    }
    return exportAllMenuItem;
  }

  /**
   * This method initializes exportAC3MenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getExportAC3MenuItem() {
    if (exportAC3MenuItem == null) {
      exportAC3MenuItem = new JMenuItem();
      exportAC3MenuItem.setAction(ac3Action);
      exportAC3MenuItem.setIcon(null);
    }
    return exportAC3MenuItem;
  }

  /**
   * This method initializes propertiesMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getPropertiesMenuItem() {
    if (propertiesMenuItem == null) {
      propertiesMenuItem = new JMenuItem();
      propertiesMenuItem.setAction(propertiesAction);
      propertiesMenuItem.setIcon(null);
    }
    return propertiesMenuItem;
  }

  /**
   * This method initializes undoButton
   *
   * @return javax.swing.JButton
   */
  private JButton getUndoButton() {
    if (undoButton == null) {
      undoButton = new JButton();
      undoButton.setAction(undoAction);
      if (undoButton.getIcon() != null) {
        undoButton.setText("");
      }
    }
    return undoButton;
  }

  /**
   * This method initializes redoButton
   *
   * @return javax.swing.JButton
   */
  private JButton getRedoButton() {
    if (redoButton == null) {
      redoButton = new JButton();
      redoButton.setAction(redoAction);
      if (redoButton.getIcon() != null) {
        redoButton.setText("");
      }
    }
    return redoButton;
  }

  /**
   * This method initializes deleteButton
   *
   * @return javax.swing.JToggleButton
   */
  private JToggleButton getToggleButtonDelete() {
    if (toggleButtonDelete == null) {
      toggleButtonDelete = new JToggleButton();
      toggleButtonDelete.setAction(deleteAction);
      if (toggleButtonDelete.getIcon() != null) {
        toggleButtonDelete.setText("");
      }
    }
    return toggleButtonDelete;
  }

  /**
   * This method initializes buttonZoomPlus
   *
   * @return javax.swing.JButton
   */
  private JButton getButtonZoomPlus() {
    if (buttonZoomPlus == null) {
      buttonZoomPlus = new JButton();
      buttonZoomPlus.setAction(zoomPlusAction);
      if (buttonZoomPlus.getIcon() != null) {
        buttonZoomPlus.setText("");
      }
    }
    return buttonZoomPlus;
  }

  /**
   * This method initializes buttonZoomPlus
   *
   * @return javax.swing.JButton
   */
  private JButton getButtonZoomOne() {
    if (buttonZoomOne == null) {
      buttonZoomOne = new JButton();
      buttonZoomOne.setAction(zoomOneAction);
      if (buttonZoomOne.getIcon() != null) {
        buttonZoomOne.setText("");
      }
    }
    return buttonZoomOne;
  }

  private JButton getButtonZoomMinus() {
    if (buttonZoomMinus == null) {
      buttonZoomMinus = new JButton();
      buttonZoomMinus.setAction(zoomMinusAction);
      if (buttonZoomMinus.getIcon() != null) {
        buttonZoomMinus.setText("");
      }
    }
    return buttonZoomMinus;
  }

  private JToggleButton getToggleButtonCreateStraightSegment() {
    if (toggleButtonCreateStraightSegment == null) {
      toggleButtonCreateStraightSegment = new JToggleButton();
      toggleButtonCreateStraightSegment.setAction(straightAction);
      if (toggleButtonCreateStraightSegment.getIcon() != null) {
        toggleButtonCreateStraightSegment.setText("");
      }
    }
    return toggleButtonCreateStraightSegment;
  }

  private JToggleButton getToggleButtonCreateRightSegment() {
    if (toggleButtonCreateRightSegment == null) {
      toggleButtonCreateRightSegment = new JToggleButton();
      toggleButtonCreateRightSegment.setAction(rightAction);
      if (toggleButtonCreateRightSegment.getIcon() != null) {
        toggleButtonCreateRightSegment.setText("");
      }
    }
    return toggleButtonCreateRightSegment;
  }

  private JToggleButton getToggleButtonCreateLeftSegment() {
    if (toggleButtonCreateLeftSegment == null) {
      toggleButtonCreateLeftSegment = new JToggleButton();
      toggleButtonCreateLeftSegment.setAction(leftAction);
      if (toggleButtonCreateLeftSegment.getIcon() != null) {
        toggleButtonCreateLeftSegment.setText("");
      }
    }
    return toggleButtonCreateLeftSegment;
  }

  /**
   * This method initializes moveButton
   *
   * @return javax.swing.JButton
   */
  private JToggleButton getMoveButton() {
    if (moveButton == null) {
      moveButton = new JToggleButton();
      moveButton.setAction(moveAction);
      if (moveButton.getIcon() != null) {
        moveButton.setText("");
      }
    }
    return moveButton;
  }

  /**
   * This method initializes showArrowsButton
   *
   * @return javax.swing.JButton
   */
  private JToggleButton getShowArrowsButton() {
    if (showArrowsButton == null) {
      showArrowsButton = new JToggleButton();
      showArrowsButton.setAction(showArrowsAction);
      if (showArrowsButton.getIcon() != null) {
        showArrowsButton.setText("");
      }
    }
    return showArrowsButton;
  }

  /**
   * This method initializes showBackgroundButton
   *
   * @return javax.swing.JButton
   */
  private JToggleButton getShowBackgroundButton() {
    if (showBackgroundButton == null) {
      showBackgroundButton = new JToggleButton();
      showBackgroundButton.setAction(showBackgroundAction);
      if (showBackgroundButton.getIcon() != null) {
        showBackgroundButton.setText("");
      }
    }
    return showBackgroundButton;
  }

  /**
   * This method initializes newButton
   *
   * @return javax.swing.JButton
   */
  private JButton getNewButton() {
    if (newButton == null) {
      newButton = new JButton();
      newButton.setAction(newAction);
      if (newButton.getIcon() != null) {
        newButton.setText("");
      }
    }
    return newButton;
  }

  /**
   * This method initializes saveButton
   *
   * @return javax.swing.JButton
   */
  private JButton getSaveButton() {
    if (saveButton == null) {
      saveButton = new JButton();
      saveButton.setAction(saveAction);
      if (saveButton.getIcon() != null) {
        saveButton.setText("");
      }
    }
    return saveButton;
  }

  /**
   * This method initializes openButton
   *
   * @return javax.swing.JButton
   */
  private JButton getOpenButton() {
    if (openButton == null) {
      openButton = new JButton();
      openButton.setAction(openAction);
      if (openButton.getIcon() != null) {
        openButton.setText("");
      }
    }
    return openButton;
  }

  /**
   * This method initializes helpButton
   *
   * @return javax.swing.JButton
   */
  private JButton getHelpButton() {
    if (helpButton == null) {
      helpButton = new JButton();
      helpButton.setAction(helpAction);
      if (helpButton.getIcon() != null) {
        helpButton.setText("");
      }
    }
    return helpButton;
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
      jContentPane.add(getJToolBar(), java.awt.BorderLayout.NORTH);
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

  void buttonZoomPlus_actionPerformed(ActionEvent e) {
    view.incZoomFactor();
    view.redrawCircuit();
  }

  void buttonZoomMinus_actionPerformed(ActionEvent e) {
    view.decZoomFactor();
    view.redrawCircuit();
  }

  void buttonZoomOne_actionPerformed(ActionEvent e) {
    view.setZoomFactor(1.0);
    view.redrawCircuit();
  }

  void checkButtons(JToggleButton button, int state) {
    if (button.isSelected()) {
      view.setState(state);

      if (toggleButtonCreateStraightSegment != button)
        toggleButtonCreateStraightSegment.setSelected(false);
      if (toggleButtonCreateLeftSegment != button)
        toggleButtonCreateLeftSegment.setSelected(false);
      if (toggleButtonCreateRightSegment != button)
        toggleButtonCreateRightSegment.setSelected(false);
      if (getMoveButton() != button)
        getMoveButton().setSelected(false);
      if (toggleButtonDelete != button)
        toggleButtonDelete.setSelected(false);
    }
    else {
      view.setState(CircuitView.STATE_NONE);
    }
  }

  void toggleButtonCreateStraightSegment_actionPerformed(ActionEvent e) {
    checkButtons(toggleButtonCreateStraightSegment, CircuitView.STATE_CREATE_STRAIGHT);
  }

  void toggleButtonCreateLeftSegment_actionPerformed(ActionEvent e) {
    checkButtons(toggleButtonCreateLeftSegment, CircuitView.STATE_CREATE_LEFT_SEGMENT);
  }

  void toggleButtonCreateRightSegment_actionPerformed(ActionEvent e) {
    checkButtons(toggleButtonCreateRightSegment, CircuitView.STATE_CREATE_RIGHT_SEGMENT);
  }

  void toggleButtonMoveSegments_actionPerformed(ActionEvent e) {
    checkButtons(getMoveButton(), CircuitView.STATE_MOVE_SEGMENTS);
  }

  void toggleButtonDelete_actionPerformed(ActionEvent e) {
    checkButtons(toggleButtonDelete, CircuitView.STATE_DELETE);
  }

  void toggleButtonShowArrow_actionPerformed(ActionEvent e) {
    //view.showArrows(getShowArrowsButton().isSelected());
  }

  void buttonUndo_actionPerformed(ActionEvent e) {
    Undo.undo();
    view.redrawCircuit();
  }

  void buttonRedo_actionPerformed(ActionEvent e) {
    Undo.redo();
    view.redrawCircuit();
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

  void menuItemShoStartPoint_actionPerformed(ActionEvent e) {
    checkButtons(null, CircuitView.STATE_SHOW_BGRD_START_POSITION);
  }

  void toggleButtonShowBackground_actionPerformed(ActionEvent e) {
    view.setShowBackground(getShowBackgroundButton().isSelected());
    view.invalidate();
    view.repaint();
  }

  /**
   * This method initializes jToolBar
   *
   * @return javax.swing.JToolBar
   */
  private JToolBar getJToolBar() {
    if (jToolBar == null) {
      jToolBar = new JToolBar();

      jToolBar.add(getNewButton());
      jToolBar.add(getOpenButton());
      jToolBar.add(getSaveButton());
      jToolBar.add(getUndoButton());
      jToolBar.add(getRedoButton());
      jToolBar.add(getToggleButtonDelete());
      jToolBar.add(getButtonZoomPlus());
      jToolBar.add(getButtonZoomOne());
      jToolBar.add(getButtonZoomMinus());
      jToolBar.add(getToggleButtonCreateStraightSegment());
      jToolBar.add(getToggleButtonCreateRightSegment());
      jToolBar.add(getToggleButtonCreateLeftSegment());
      jToolBar.add(getMoveButton());
      jToolBar.add(getShowArrowsButton());
      jToolBar.add(getShowBackgroundButton());
      jToolBar.add(getCalculateDeltaButton());
      jToolBar.add(getHelpButton());
    }
    return jToolBar;
  }

  private void exportAc3d() {
    TrackgenPanel tg = new TrackgenPanel(this);
    tg.setModal(true);
    tg.setVisible(true);
  }

  /**
   *
   */
  protected void propertiesDialog() {
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
   * Returns an ImageIcon, or null if the path was invalid.
   */
  protected static ImageIcon createNavigationIcon(String imageName, ClassLoader cldr) {
    String imgLocation = "data/icons/" + imageName + ".gif";
    ImageIcon image = new ImageIcon(cldr.getResource(imgLocation));
    if (image != null) return image;
    else System.err.println("Resource not found: " + imgLocation);
    return null;
  }

  /**
   *
   */
  public class UndoAction extends AbstractAction {
    public UndoAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      buttonUndo_actionPerformed(e);
    }
  }

  public class RedoAction extends AbstractAction {
    public RedoAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      buttonRedo_actionPerformed(e);
    }
  }

  public class DeleteAction extends AbstractAction {
    public DeleteAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonDelete_actionPerformed(e);
    }
  }

  public class ZoomPlusAction extends AbstractAction {
    public ZoomPlusAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      buttonZoomPlus_actionPerformed(e);
    }
  }

  public class ZoomOneAction extends AbstractAction {
    public ZoomOneAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      buttonZoomOne_actionPerformed(e);
    }
  }

  public class ZoomMinusAction extends AbstractAction {
    public ZoomMinusAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      buttonZoomMinus_actionPerformed(e);
    }
  }

  public class StraightAction extends AbstractAction {
    public StraightAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonCreateStraightSegment_actionPerformed(e);
    }
  }

  public class RightAction extends AbstractAction {
    public RightAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonCreateRightSegment_actionPerformed(e);
    }
  }

  public class LeftAction extends AbstractAction {
    public LeftAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonCreateLeftSegment_actionPerformed(e);
    }
  }

  public class NewAction extends AbstractAction {
    public NewAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      newProjectDialog();
    }
  }

  public class OpenAction extends AbstractAction {
    public OpenAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      openProject();
    }
  }

  public class SaveAction extends AbstractAction {
    public SaveAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      saveProject();
    }
  }

  public class MoveAction extends AbstractAction {
    public MoveAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonMoveSegments_actionPerformed(e);
    }
  }

  public class ShowArrowsAction extends AbstractAction {
    public ShowArrowsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonShowArrow_actionPerformed(e);
    }
  }

  public class ShowBackgroundAction extends AbstractAction {
    public ShowBackgroundAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      toggleButtonShowBackground_actionPerformed(e);
    }
  }

  public class HelpAction extends AbstractAction {
    public HelpAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      int type = JOptionPane.PLAIN_MESSAGE;
      String msg = "TrackEditor " + Editor.getProperties().version + "\n\n"
          + "Copyright Charalampos Alexopoulos\n";
      JOptionPane.showMessageDialog(null, msg, "About", type);
    }
  }

  public class ExportAllAction extends AbstractAction {
    public ExportAllAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      System.out.println("TODO : I have to write some code for this.");
    }
  }

  public class ExportAC3Action extends AbstractAction {
    public ExportAC3Action(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      exportAc3d();
    }
  }

  public class PropertiesAction extends AbstractAction {
    public PropertiesAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      propertiesDialog();
    }
  }

  public class CalcDeltaAction extends AbstractAction {
    public CalcDeltaAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      calculateDeltas();

    }
  }

  /**
   *
   */
  private void calculateDeltas() {
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

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  public void mouseReleased(MouseEvent e) {
    System.out.println("Mouse released");
    getProject().setFrameX(this.getX());
    getProject().setFrameY(this.getY());
  }

  /* (non-Javadoc)
   * @see java.awt.event.WindowStateListener#windowStateChanged(java.awt.event.WindowEvent)
   */
  public void windowStateChanged(WindowEvent e) {
    // TODO Auto-generated method stub
    System.out.println("windowStateChanged");
  }


  /**
   * This method initializes calculateDeltaButton
   *
   * @return javax.swing.JButton
   */
  private JButton getCalculateDeltaButton() {
    if (calculateDeltaButton == null) {
      calculateDeltaButton = new JButton();
      calculateDeltaButton.setAction(calcDeltaAction);
      if (calculateDeltaButton.getIcon() != null) {
        calculateDeltaButton.setText("");
      }
    }
    return calculateDeltaButton;
  }
}
