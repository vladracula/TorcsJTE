/*
 *   SegmentSideProperties.java
 *   Created on Aug 25, 2004
 *
 *    The SegmentSideProperties.java is part of TrackEditor-0.2.0.
 *
 *    TrackEditor-0.2.0 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.2.0 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.2.0; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package gui.segment;

import bsh.EvalError;
import bsh.Interpreter;
import utils.circuit.SegmentSide;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SegmentSideProperties extends JPanel implements SliderListener {

  private SegmentEditorDialog editor;
  private SegmentSide side;
  private SegmentEditorDialog parent;

  private String[] styleItems =
      {"plan", "wall", "fence", "curb"};
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
  private String[] borderSurfaceItems =
      {"curb-5cm-r", "curb-5cm-l", "curb-l", "tar-grass3-l",
          "tar-grass3-r", "tar-sand", "b-road1-grass6", "b-road1-grass6-l2", "b-road1-gravel-l2", "b-road1-sand3",
          "b-road1-sand3-l2", "b-asphalt-grass7", "b-asphalt-grass7-l1", "b-asphalt-grass6", "b-asphalt-grass6-l1",
          "b-asphalt-sand3", "b-asphalt-sand3-l1", "grass", "grass3", "grass5", "grass6", "grass7", "gravel",
          "sand3", "sand", "asphalt-lines", "asphalt-l-left", "asphalt-l-right", "asphalt-l-both", "asphalt-pits",
          "asphalt", "dirt", "dirt-b", "asphalt2", "road1", "road1-pits", "road1-asphalt", "asphalt-road1",
          "b-road1", "b-road1-l2", "b-road1-l2p", "concrete", "concrete2", "concrete3", "b-asphalt", "b-asphalt-l1",
          "b-asphalt-l1p", "asphalt2-lines", "asphalt2-l-left", "asphalt2-l-right", "asphalt2-l-both", "barrier",
          "barrier2", "barrier-turn", "barrier-grille", "wall", "wall2", "tire-wall"};
  private String[] sideSurfaceItems =
      {"grass", "grass3", "grass5", "grass6", "grass7", "gravel",
          "sand3", "sand", "asphalt-lines", "asphalt-l-left", "asphalt-l-right", "asphalt-l-both", "asphalt-pits",
          "asphalt", "dirt", "dirt-b", "asphalt2", "road1", "road1-pits", "road1-asphalt", "asphalt-road1",
          "b-road1", "b-road1-l2", "b-road1-l2p", "concrete", "concrete2", "concrete3", "b-asphalt", "b-asphalt-l1",
          "b-asphalt-l1p", "asphalt2-lines", "asphalt2-l-left", "asphalt2-l-right", "asphalt2-l-both", "curb-5cm-r",
          "curb-5cm-l", "curb-l", "tar-grass3-l", "tar-grass3-r", "tar-sand", "b-road1-grass6", "b-road1-grass6-l2",
          "b-road1-gravel-l2", "b-road1-sand3", "b-road1-sand3-l2", "b-asphalt-grass7", "b-asphalt-grass7-l1",
          "b-asphalt-grass6", "b-asphalt-grass6-l1", "b-asphalt-sand3", "b-asphalt-sand3-l1", "barrier", "barrier2",
          "barrier-turn", "barrier-grille", "wall", "wall2", "tire-wall"};
  private String[] fenceSurfaceItems =
      {"barrier", "barrier2", "barrier-turn", "barrier-grille",
          "wall", "wall2", "tire-wall", "asphalt-lines", "asphalt-l-left", "asphalt-l-right", "asphalt-l-both",
          "asphalt-pits", "asphalt", "dirt", "dirt-b", "asphalt2", "road1", "road1-pits", "road1-asphalt",
          "asphalt-road1", "b-road1", "b-road1-l2", "b-road1-l2p", "concrete", "concrete2", "concrete3", "b-asphalt",
          "b-asphalt-l1", "b-asphalt-l1p", "asphalt2-lines", "asphalt2-l-left", "asphalt2-l-right",
          "asphalt2-l-both", "curb-5cm-r", "curb-5cm-l", "curb-l", "tar-grass3-l", "tar-grass3-r", "tar-sand",
          "b-road1-grass6", "b-road1-grass6-l2", "b-road1-gravel-l2", "b-road1-sand3", "b-road1-sand3-l2",
          "b-asphalt-grass7", "b-asphalt-grass7-l1", "b-asphalt-grass6", "b-asphalt-grass6-l1", "b-asphalt-sand3",
          "b-asphalt-sand3-l1", "grass", "grass3", "grass5", "grass6", "grass7", "gravel", "sand3", "sand"};
  private String[] surfaceItems =
      {};

  private JPanel panel = null;
  private JLabel titleLabel = null;
  private SegmentSlider barrierHeightSlider = null;
  private SegmentComboBox borderSurfaceComboBox = null;
  private SegmentComboBox borderStyleComboBox = null;
  private SegmentComboBox sideSurfaceComboBox = null;
  private SegmentComboBox barrierSurfaceComboBox = null;
  private SegmentComboBox barrierStyleComboBox = null;
  private SegmentSlider sideStartWidthSlider = null;
  private SegmentSlider borderWidthSlider = null;
  private SegmentSlider sideEndWidthSlider = null;
  private SegmentSlider barrierWidthSlider = null;
  private SegmentSlider borderHeightSlider = null;

  /**
   *
   */
  public SegmentSideProperties(SegmentEditorDialog parent, SegmentSide side) {
    this.parent = parent;
    setSide(side);
    Arrays.sort(roadSurfaceItems);
    Arrays.sort(borderSurfaceItems);
    Arrays.sort(sideSurfaceItems);
    Arrays.sort(fenceSurfaceItems);
    initialize();
  }

  /**
   * This method initializes this
   *
   * @return void
   */
  private void initialize() {
    titleLabel = new JLabel();
    this.setLayout(null);
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    this.setBounds(0, 0, 440, 471);
    titleLabel.setBounds(175, 5, 70, 20);
    titleLabel.setText("Right");
    titleLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 18));
    this.add(getPanel(), null);
    this.add(titleLabel, null);
  }

  /**
   * This method initializes panel
   *
   * @return javax.swing.JPanel
   */
  public JPanel getPanel() {
    if (panel == null) {
      JLabel borderLabel = new JLabel();
      JLabel borderStyleLabel = new JLabel();
      JLabel borderSurfaceLabel = new JLabel();
      JLabel sideSurfaceLabel = new JLabel();
      JLabel sideLabel = new JLabel();
      JLabel barrierSurfaceLabel = new JLabel();
      JLabel barrierStyleLabel = new JLabel();
      JLabel barrierLabel = new JLabel();

      panel = new JPanel();
      panel.setLayout(null);
      panel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));

      borderLabel.setBounds(315, 2, 55, 20);
      borderLabel.setText("Border");

      borderSurfaceLabel.setBounds(305, 325, 55, 20);
      borderSurfaceLabel.setText("Surface");

      borderStyleLabel.setBounds(315, 375, 55, 20);
      borderStyleLabel.setText("Style");

      sideLabel.setText("Side");
      sideLabel.setBounds(185, 2, 55, 20);

      sideSurfaceLabel.setText("Surface");
      sideSurfaceLabel.setBounds(165, 325, 55, 20);

      barrierLabel.setText("Barrier");
      barrierLabel.setBounds(35, 2, 55, 20);

      barrierStyleLabel.setText("Style");
      barrierStyleLabel.setBounds(40, 375, 55, 20);

      barrierSurfaceLabel.setText("Surface");
      barrierSurfaceLabel.setBounds(35, 325, 55, 20);

      panel.add(borderSurfaceLabel, null);
      panel.add(borderStyleLabel, null);
      panel.add(borderLabel, null);
      panel.add(sideLabel, null);
      panel.add(sideSurfaceLabel, null);
      panel.add(barrierLabel, null);
      panel.add(barrierStyleLabel, null);
      panel.add(barrierSurfaceLabel, null);
      panel.setLocation(2, 35);
      panel.setSize(395, 432);

      panel.add(getBarrierHeightSlider(), null);
      panel.add(getBorderSurfaceComboBox(), null);
      panel.add(getBorderStyleComboBox(), null);
      panel.add(getSideSurfaceComboBox(), null);
      panel.add(getBarrierSurfaceComboBox(), null);
      panel.add(getBarrierStyleComboBox(), null);
      panel.add(getSideStartWidthSlider(), null);
      panel.add(getBorderWidthSlider(), null);
      panel.add(getSideEndWidthSlider(), null);
      panel.add(getBarrierWidthSlider(), null);
      panel.add(getBorderHeightSlider(), null);
    }
    return panel;
  }

  /**
   * This method initializes borderSurfaceComboBox
   *
   * @return gui.segment.SegmentComboBox
   */
  private SegmentComboBox getBorderSurfaceComboBox() {
    if (borderSurfaceComboBox == null) {
      borderSurfaceComboBox = new SegmentComboBox();
      borderSurfaceComboBox.setBounds(270, 350, 120, 20);
      borderSurfaceComboBox.setModel(new DefaultComboBoxModel<>(borderSurfaceItems));
      borderSurfaceComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          side.setBorderSurface(borderSurfaceComboBox.getSelectedItem() + "");
        }
      });

    }
    return borderSurfaceComboBox;
  }

  /**
   * This method initializes borderStyleComboBox
   *
   * @return gui.segment.SegmentComboBox
   */
  private SegmentComboBox getBorderStyleComboBox() {
    if (borderStyleComboBox == null) {
      borderStyleComboBox = new SegmentComboBox();
      borderStyleComboBox.setBounds(270, 400, 120, 20);
      borderStyleComboBox.setModel(new DefaultComboBoxModel<>(styleItems));
      borderStyleComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          side.setBorderStyle(borderStyleComboBox.getSelectedItem() + "");
        }

      });

    }
    return borderStyleComboBox;
  }

  /**
   * This method initializes sideSurfaceComboBox
   *
   * @return gui.segment.SegmentComboBox
   */
  private SegmentComboBox getSideSurfaceComboBox() {
    if (sideSurfaceComboBox == null) {
      sideSurfaceComboBox = new SegmentComboBox();
      sideSurfaceComboBox.setBounds(137, 350, 120, 20);
      sideSurfaceComboBox.setModel(new DefaultComboBoxModel<>(sideSurfaceItems));
      sideSurfaceComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          side.setSideSurface(sideSurfaceComboBox.getSelectedItem() + "");
        }

      });
    }
    return sideSurfaceComboBox;
  }

  /**
   * This method initializes barrierSurfaceComboBox
   *
   * @return gui.segment.SegmentComboBox
   */
  private SegmentComboBox getBarrierSurfaceComboBox() {
    if (barrierSurfaceComboBox == null) {
      barrierSurfaceComboBox = new SegmentComboBox();
      barrierSurfaceComboBox.setBounds(5, 350, 120, 20);
      barrierSurfaceComboBox.setModel(new DefaultComboBoxModel<>(fenceSurfaceItems));
      barrierSurfaceComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          side.setBarrierSurface(barrierSurfaceComboBox.getSelectedItem() + "");
        }

      });
    }
    return barrierSurfaceComboBox;
  }

  /**
   * This method initializes barrierStyleComboBox
   *
   * @return gui.segment.SegmentComboBox
   */
  private SegmentComboBox getBarrierStyleComboBox() {
    if (barrierStyleComboBox == null) {
      barrierStyleComboBox = new SegmentComboBox();
      barrierStyleComboBox.setBounds(5, 400, 120, 20);
      barrierStyleComboBox.setModel(new DefaultComboBoxModel<>(styleItems));
      barrierStyleComboBox.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          side.setBarrierStyle(barrierStyleComboBox.getSelectedItem() + "");
        }

      });

    }
    return barrierStyleComboBox;
  }

  /**
   * This method initializes barrierHeightSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getBarrierHeightSlider() {
    if (barrierHeightSlider == null) {
      barrierHeightSlider = new SegmentSlider();
      barrierHeightSlider.setBounds(5, 25, 50, 290);
      barrierHeightSlider.setSection("Height");
      barrierHeightSlider.setAttr("");
      barrierHeightSlider.setMin(0);
      barrierHeightSlider.setMax(50);
      barrierHeightSlider.setExtent(2);
      barrierHeightSlider.setTickSpacing(0.5);
      barrierHeightSlider.setRealToTextCoeff(1);
      barrierHeightSlider.setMethod("BarrierHeight");
      barrierHeightSlider.addSliderListener(this);
    }
    return barrierHeightSlider;
  }

  /**
   * This method initializes sideStartWidthSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getSideStartWidthSlider() {
    if (sideStartWidthSlider == null) {
      sideStartWidthSlider = new SegmentSlider();
      sideStartWidthSlider.setBounds(135, 25, 60, 290);
      sideStartWidthSlider.setSection("Width");
      sideStartWidthSlider.setAttr("Start");
      sideStartWidthSlider.setMin(0);
      sideStartWidthSlider.setMax(50);
      sideStartWidthSlider.setExtent(2);
      sideStartWidthSlider.setTickSpacing(0.5);
      sideStartWidthSlider.setRealToTextCoeff(1);
      sideStartWidthSlider.setMethod("SideStartWidth");
      sideStartWidthSlider.addSliderListener(this);
    }
    return sideStartWidthSlider;
  }

  /**
   * This method initializes borderWidthSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getBorderWidthSlider() {
    if (borderWidthSlider == null) {
      borderWidthSlider = new SegmentSlider();
      borderWidthSlider.setBounds(340, 25, 50, 290);
      borderWidthSlider.setSection("Width");
      borderWidthSlider.setAttr("");
      borderWidthSlider.setMin(0);
      borderWidthSlider.setMax(5);
      borderWidthSlider.setExtent(1);
      borderWidthSlider.setTickSpacing(0.05);
      borderWidthSlider.setRealToTextCoeff(1);
      borderWidthSlider.setMethod("BorderWidth");
      borderWidthSlider.addSliderListener(this);
    }
    return borderWidthSlider;
  }

  /**
   * This method initializes sideEndWidthSlider
   *
   * @return gui.SegmentSlider
   */
  private SegmentSlider getSideEndWidthSlider() {
    if (sideEndWidthSlider == null) {
      sideEndWidthSlider = new SegmentSlider();
      sideEndWidthSlider.setBounds(200, 25, 60, 290);
      sideEndWidthSlider.setSection("Width");
      sideEndWidthSlider.setAttr("End");
      sideEndWidthSlider.setMin(0);
      sideEndWidthSlider.setMax(50);
      sideEndWidthSlider.setExtent(2);
      sideEndWidthSlider.setTickSpacing(0.5);
      sideEndWidthSlider.setRealToTextCoeff(1);
      sideEndWidthSlider.setMethod("SideEndWidth");
      sideEndWidthSlider.addSliderListener(this);
    }
    return sideEndWidthSlider;
  }

  /**
   * This method initializes barrierWidthSlider
   *
   * @return gui.segment.SegmentSlider
   */
  private SegmentSlider getBarrierWidthSlider() {
    if (barrierWidthSlider == null) {
      barrierWidthSlider = new SegmentSlider();
      barrierWidthSlider.setBounds(60, 25, 50, 290);
      barrierWidthSlider.setSection("Width");
      barrierWidthSlider.setAttr("");
      barrierWidthSlider.setMin(0);
      barrierWidthSlider.setMax(50);
      barrierWidthSlider.setExtent(2);
      barrierWidthSlider.setTickSpacing(0.5);
      barrierWidthSlider.setRealToTextCoeff(1);
      barrierWidthSlider.setMethod("BarrierWidth");
      barrierWidthSlider.addSliderListener(this);
    }
    return barrierWidthSlider;
  }

  /**
   * This method initializes borderHeightSlider
   *
   * @return gui.segment.SegmentSlider
   */
  private SegmentSlider getBorderHeightSlider() {
    if (borderHeightSlider == null) {
      borderHeightSlider = new SegmentSlider();
      borderHeightSlider.setBounds(285, 25, 50, 290);
      borderHeightSlider.setSection("Height");
      borderHeightSlider.setAttr("");
      borderHeightSlider.setMin(0);
      borderHeightSlider.setMax(50);
      borderHeightSlider.setExtent(2);
      borderHeightSlider.setTickSpacing(0.5);
      borderHeightSlider.setRealToTextCoeff(1);
      borderHeightSlider.setMethod("BorderHeight");
      borderHeightSlider.addSliderListener(this);
    }
    return borderHeightSlider;
  }

  /**
   * @param editor The editor to set.
   */
  public void setEditor(SegmentEditorDialog editor) {
    this.editor = editor;
  }

  /**
   * @return Returns the titleLabel.
   */
  public String getTitle() {
    return titleLabel.getText();
  }

  /**
   * @param titleLabel The titleLabel to set.
   */
  public void setTitle(String title) {
    this.titleLabel.setText(title);
  }

  public void setSide(SegmentSide side) {
    this.side = side;

    // update all fields
    this.getBorderSurfaceComboBox().setSelectedItem(side.getBorderSurface());
    this.getBorderStyleComboBox().setSelectedItem(side.getBorderStyle());
    this.getSideSurfaceComboBox().setSelectedItem(side.getSideSurface());
    this.getBarrierSurfaceComboBox().setSelectedItem(side.getBarrierSurface());
    this.getBarrierStyleComboBox().setSelectedItem(side.getBarrierStyle());
    this.getBarrierHeightSlider().setValue(side.getBarrierHeight());
    this.getBarrierWidthSlider().setValue(side.getBarrierWidth());
    this.getSideStartWidthSlider().setValue(side.getSideStartWidth());
    this.getSideEndWidthSlider().setValue(side.getSideEndWidth());
    this.getBorderWidthSlider().setValue(side.getBorderWidth());
    this.getBorderHeightSlider().setValue(side.getBorderHeight());

    this.validate();
    this.repaint();
  }

  /*
   * (non-Javadoc)
   *
   * @see gui.segment.SliderListener#valueChanged(gui.segment.SegmentSlider)
   */
  public void sliderChanged(SegmentSlider slider) {
    Interpreter line = new Interpreter();
    String command = "";

    try {

      line.set("side", side);

      //String tmp = "((" + shapeType + ")shape)";
      command = "side.set" + slider.getMethod() + "(" + slider.getValue() + ")";

      line.eval(command);
      side = (SegmentSide) line.get("side");
    }
    catch (EvalError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    parent.update();
  }

}
