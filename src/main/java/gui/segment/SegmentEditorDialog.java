/*
 *   SegmentEditorDialog.java
 *   Created on 28  2005
 *
 *    The SegmentEditorDialog.java is part of TrackEditor-0.3.1.
 *
 *    TrackEditor-0.3.1 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.3.1 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.3.1; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package gui.segment;

import bsh.EvalError;
import bsh.Interpreter;
import gui.EditorFrame;
import gui.view.CircuitView;
import utils.circuit.Curve;
import utils.circuit.Segment;
import utils.circuit.Straight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code
 * Templates
 */
public class SegmentEditorDialog extends JDialog implements SliderListener {

  public Segment shape;
  private CircuitView view;
  private EditorFrame frame;
  public boolean dirty = false;

  private JPanel jContentPane = null;

  private JTabbedPane jTabbedPane = null;
  private JPanel centerPanel = null;
  private SegmentSlider radiusStartSlider = null;
  private SegmentSlider radiusEndSlider = null;
  private SegmentSlider arcSlider = null;
  private SegmentSlider lengthSlider = null;
  private JTextField nameTextField = null;
  private JComboBox<String> surfaceComboBox = null;
  private JComboBox<String> profilComboBox = null;

  private SegmentSlider heightStartSlider = null;
  private SegmentSlider heightEndSlider = null;
  private SegmentSideProperties rightPanel = null;
  private SegmentSideProperties leftPanel = null;

  private GroupButton groupButton = null;

  private String[] profilItems = {"spline", "linear"};
  private String[] roadSurfaceItems =
      {"asphalt-lines", "asphalt-l-left", "asphalt-l-right",
          "asphalt-l-both", "asphalt-pits", "asphalt", "dirt", "dirt-b", "asphalt2", "road1", "road1-pits",
          "road1-asphalt", "asphalt-road1", "b-road1", "b-road1-l2", "b-road1-l2p", "concrete", "concrete2",
          "concrete3", "b-asphalt", "b-asphalt-l1", "b-asphalt-l1p", "asphalt2-lines", "asphalt2-l-left",
          "asphalt2-l-right", "asphalt2-l-both", "grass", "grass3", "grass5", "grass6", "grass7", "gravel", "sand3",
          "sand", "curb-5cm-r", "curb-5cm-l", "curb-l", "tar-grass3-l", "tar-grass3-r", "tar-sand", "b-road1-grass6",
          "b-road1-grass6-l2", "b-road1-gravel-l2", "b-road1-sand3", "b-road1-sand3-l2", "b-asphalt-grass7",
          "b-asphalt-grass7-l1", "b-asphalt-grass6", "b-asphalt-grass6-l1", "b-asphalt-sand3", "b-asphalt-sand3-l1",
          "barrier", "barrier2", "barrier-turn", "barrier-grille", "wall", "wall2", "tire-wall"};

  /**
   *
   */
  public SegmentEditorDialog() {
    super((Frame) null, "", true);
    Arrays.sort(roadSurfaceItems);
  }

  public SegmentEditorDialog(CircuitView view, EditorFrame frame, String title, boolean modal, Segment shape) {
    super(frame, title, modal);
    Arrays.sort(roadSurfaceItems);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

    try {
      this.view = view;
      this.frame = frame;
      setShape(shape);
      initialize();
      this.setVisible(true);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   *
   */
  private void initialize() {
    this.setTitle("Segment Editor");
    this.setSize(405, 510);
    Point p = frame.getLocation();
    p.x = frame.getProject().getSegmentEditorX();
    p.y = frame.getProject().getSegmentEditorY();
    this.setLocation(p);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setContentPane(getJContentPane());
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
      jContentPane.add(getJTabbedPane(), null);
    }
    return jContentPane;
  }

  /**
   * This method initializes jTabbedPane
   *
   * @return javax.swing.JTabbedPane
   */
  private JTabbedPane getJTabbedPane() {
    if (jTabbedPane == null) {
      jTabbedPane = new JTabbedPane();
      jTabbedPane.addTab("Left", null, getLeftPanel(), null);
      jTabbedPane.addTab("Center", null, getCenterPanel(), null);
      jTabbedPane.addTab("Right", null, getRightPanel(), null);
      jTabbedPane.setSelectedIndex(1);
    }
    return jTabbedPane;
  }

  /**
   * This method initializes centerPanel
   *
   * @return javax.swing.JPanel
   */
  private JPanel getCenterPanel() {
    if (centerPanel == null) {
      JLabel nameLabel = new JLabel();
      centerPanel = new JPanel();
      centerPanel.setLayout(null);
      nameLabel.setBounds(10, 10, 45, 20);
      nameLabel.setText("Name");
      nameLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
      centerPanel.add(nameLabel, null);
      JLabel profilLabel = new JLabel();
      profilLabel.setBounds(10, 440, 55, 20);
      profilLabel.setText("Profile");
      profilLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
      centerPanel.add(profilLabel, null);
      centerPanel.add(getNameTextField(), null);
      centerPanel.add(getSurfaceComboBox(), null);
      centerPanel.add(getRadiusStartSlider(), null);
      centerPanel.add(getRadiusEndSlider(), null);
      centerPanel.add(getArcSlider(), null);
      centerPanel.add(getLengthSlider(), null);
      centerPanel.add(getHeightStartSlider(), null);
      centerPanel.add(getHeightEndSlider(), null);
      centerPanel.add(getGroupButton(), null);
      centerPanel.add(getProfilComboBox(), null);
    }
    return centerPanel;
  }

  /**
   * This method initializes radiusStartSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getRadiusStartSlider() {
    if (radiusStartSlider == null) {
      radiusStartSlider = new SegmentSlider();
      radiusStartSlider.setBounds(140, 40, 60, 390);
      radiusStartSlider.setSection("Radius");
      radiusStartSlider.setAttr("Start");
      radiusStartSlider.setMin(1);
      radiusStartSlider.setMax(1000);
      radiusStartSlider.setExtent(10);
      radiusStartSlider.setTickSpacing(1);
      radiusStartSlider.setFormat("#.####");
      radiusStartSlider.setRealToTextCoeff(1);
      radiusStartSlider.addSliderListener(this);
//			if (!shape.getType().equals("str"))
//			{
      radiusStartSlider.setMethod("RadiusStart");
//			}
    }
    return radiusStartSlider;
  }

  /**
   * This method initializes radiusEndSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getRadiusEndSlider() {
    if (radiusEndSlider == null) {
      radiusEndSlider = new SegmentSlider();
      radiusEndSlider.setBounds(205, 40, 60, 390);
      radiusEndSlider.setSection("Radius");
      radiusEndSlider.setAttr("End");
      radiusEndSlider.setMin(1);
      radiusEndSlider.setMax(1000);
      radiusEndSlider.setExtent(10);
      radiusEndSlider.setTickSpacing(1);
      radiusEndSlider.setFormat("#.####");
      radiusEndSlider.setRealToTextCoeff(1);
      radiusEndSlider.addSliderListener(this);
//			if (!shape.getType().equals("str"))
//			{
      radiusEndSlider.setMethod("RadiusEnd");
      //radiusEndSlider.setValue(167);
//			}
    }
    return radiusEndSlider;
  }

  /**
   * This method initializes arcSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getArcSlider() {
    if (arcSlider == null) {
      arcSlider = new SegmentSlider();
      arcSlider.setBounds(70, 40, 65, 390);
      arcSlider.setSection("Arc");
      arcSlider.setAttr("");
      arcSlider.setMin(1);
      arcSlider.setMax(360);
      arcSlider.setExtent(10);
      arcSlider.setTickSpacing(1);
      arcSlider.setFormat("#.####");
      arcSlider.setRealToTextCoeff(180 / Math.PI);
//			if (!shape.getType().equals("str"))
//			{
      arcSlider.setMethod("Arc");
//			}
      arcSlider.addSliderListener(this);
    }
    return arcSlider;
  }

  /**
   * This method initializes lengthSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getLengthSlider() {
    if (lengthSlider == null) {
      lengthSlider = new SegmentSlider();
      lengthSlider.setBounds(5, 40, 60, 390);
      lengthSlider.setSection("Length");
      lengthSlider.setAttr("");
      lengthSlider.setMin(0.001);
      lengthSlider.setMax(1000);
      lengthSlider.setExtent(1.0);
      lengthSlider.setTickSpacing(0.1);
      lengthSlider.setRealToTextCoeff(1);
      lengthSlider.setMethod("Length");
      lengthSlider.setFormat("#.###");
      lengthSlider.setValue(shape.getLength());
      lengthSlider.addSliderListener(this);
    }
    return lengthSlider;
  }

  /**
   * This method initializes heightStartSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getHeightStartSlider() {
    if (heightStartSlider == null) {
      heightStartSlider = new SegmentSlider();
      heightStartSlider.setBounds(270, 40, 60, 390);
      heightStartSlider.setSection("Height");
      heightStartSlider.setAttr("Start");
      heightStartSlider.setMin(0);
      heightStartSlider.setMax(100);
      heightStartSlider.setExtent(2);
      heightStartSlider.setTickSpacing(0.5);
      heightStartSlider.setRealToTextCoeff(1);
      heightStartSlider.setFormat("#.##");
      heightStartSlider.setMethod("HeightStart");
      heightStartSlider.addSliderListener(this);
    }
    return heightStartSlider;
  }

  /**
   * This method initializes heightEndSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getHeightEndSlider() {
    if (heightEndSlider == null) {
      heightEndSlider = new SegmentSlider();
      heightEndSlider.setBounds(335, 40, 60, 390);
      heightEndSlider.setSection("Height");
      heightEndSlider.setAttr("End");
      heightEndSlider.setMin(0);
      heightEndSlider.setMax(100);
      heightEndSlider.setExtent(2);
      heightEndSlider.setTickSpacing(0.5);
      heightEndSlider.setRealToTextCoeff(1);
      heightEndSlider.setFormat("#.##");
      heightEndSlider.setMethod("HeightEnd");
      heightEndSlider.addSliderListener(this);
    }
    return heightEndSlider;
  }

  /**
   * This method initializes nameTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getNameTextField() {
    if (nameTextField == null) {
      nameTextField = new JTextField();
      nameTextField.setBounds(75, 10, 110, 20);
      nameTextField.setText(shape.getName());
      nameTextField.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
          shape.setName(nameTextField.getText());
        }
      });
    }
    return nameTextField;
  }

  /**
   * This method initializes surfaceComboBox
   *
   * @return javax.swing.JComboBox
   */
  private JComboBox getSurfaceComboBox() {
    if (surfaceComboBox == null) {
      surfaceComboBox = new JComboBox<>();
      surfaceComboBox.setModel(new DefaultComboBoxModel<>(roadSurfaceItems));
      surfaceComboBox.setBounds(190, 10, 130, 20);
      surfaceComboBox.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          shape.setSurface((String) surfaceComboBox.getSelectedItem());
          try {
            view.redrawCircuit();
          }
          catch (Exception e1) {
            e1.printStackTrace();
          }
          frame.documentIsModified = true;
        }
      });
    }
    return surfaceComboBox;
  }

  /**
   * This method initializes profilComboBox
   *
   * @return javax.swing.JComboBox
   */
  private JComboBox getProfilComboBox() {
    if (profilComboBox == null) {
      profilComboBox = new JComboBox<>();
      profilComboBox.setModel(new DefaultComboBoxModel<>(profilItems));
      profilComboBox.setBounds(60, 440, 120, 20);
      profilComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          shape.setProfil((String) profilComboBox.getSelectedItem());
          try {
            view.redrawCircuit();
          }
          catch (Exception e1) {
            e1.printStackTrace();
          }
          frame.documentIsModified = true;
        }
      });
    }
    return profilComboBox;
  }

  public void setShape(Segment shape) {
    this.shape = shape;
    this.getRightPanel().setSide(shape.getRight());
    this.getLeftPanel().setSide(shape.getLeft());

    // update all fields

    try {
      if (!shape.getType().equals("str")) {
        Curve curve = (Curve) shape;
        this.getRadiusStartSlider().setEnabled(true);
        this.getRadiusEndSlider().setEnabled(true);
        this.getArcSlider().setEnabled(true);
        this.getLengthSlider().setEnabled(false);
        getGroupButton().setEnabled(true);
        getGroupButton().setSelected(curve.getType());

        this.getArcSlider().setValue(curve.getArc());
        this.getRadiusStartSlider().setValue(curve.getRadiusStart());
        this.getRadiusEndSlider().setValue(curve.getRadiusEnd());
      }
      else {
        this.getRadiusStartSlider().setEnabled(false);
        this.getRadiusEndSlider().setEnabled(false);
        this.getArcSlider().setEnabled(false);
        this.getLengthSlider().setEnabled(true);
        getGroupButton().setEnabled(false);

        this.getLengthSlider().setValue(shape.getLength());
      }
      getNameTextField().setText(shape.getName());
      getSurfaceComboBox().setSelectedItem(shape.getSurface());
      this.getHeightStartSlider().setValue(shape.getHeightStart());
      this.getHeightEndSlider().setValue(shape.getHeightEnd());
      getProfilComboBox().setSelectedItem(shape.getProfil());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.validate();
    this.repaint();
  }

  /**
   * @return Returns the shape.
   */
  public Segment getSegment() {
    return shape;
  }

  public void update() {
    try {
      view.redrawCircuit();
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    frame.documentIsModified = true;
    dirty = true;
  }

  /**
   * This method initializes rightPanel
   *
   * @return gui.segment.SegmentSideProperties
   */
  private SegmentSideProperties getRightPanel() {
    if (rightPanel == null) {
      rightPanel = new SegmentSideProperties(this, shape.getRight());
    }
    return rightPanel;
  }

  /**
   * This method initializes leftPanel
   *
   * @return gui.segment.SegmentSideProperties
   */
  private SegmentSideProperties getLeftPanel() {
    if (leftPanel == null) {
      leftPanel = new SegmentSideProperties(this, shape.getLeft());
      leftPanel.setTitle("Left");
    }
    return leftPanel;
  }

  /**
   *
   */
  public void sideChanged() {
    view.redrawCircuit();
    frame.documentIsModified = true;
    dirty = true;
  }

  /**
   * This method initializes groupButton
   *
   * @return gui.segment.GroupButton
   */
  private GroupButton getGroupButton() {
    if (groupButton == null) {
      groupButton = new GroupButton();
      groupButton.setBounds(325, 2, 70, 33);
      groupButton.setParent(this);
    }
    return groupButton;
  }

  public void groupChanged() {
    shape.setType(getGroupButton().getSelected());
    frame.documentIsModified = true;
    view.redrawCircuit();
  }

  public void windowClosing(java.awt.event.WindowEvent anEvent) {
    System.out.println("JDialog is closing");
  }

  /* (non-Javadoc)
   * @see gui.segment.SliderListener#valueChanged(gui.segment.SegmentSlider)
   */
  public void sliderChanged(SegmentSlider slider) {
    Interpreter line = new Interpreter();
    String command = "";
    String method = null;
    try {
      if (shape.getType().equals("str")) {
        line.set("shape", (Straight) shape);
      }
      else {
        line.set("shape", (Curve) shape);
      }

      method = slider.getMethod();
      /*
			if(method == null){
			  System.err.println(" NullMethod : "+method);
			  System.err.println(" In  slider : "+slider);
			  return; }
      */
      command = "shape.set" + method + "(" + slider.getValue() + ")";

      line.eval(command);
      shape = (Segment) line.get("shape");
    }
    catch (EvalError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    view.redrawCircuit();
    frame.documentIsModified = true;
    dirty = true;
  }

  //	 Exit when window close
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      exit();
    }
  }

  private void exit() {
    frame.getProject().setSegmentEditorX(this.getX());
    frame.getProject().setSegmentEditorY(this.getY());
  }

}
