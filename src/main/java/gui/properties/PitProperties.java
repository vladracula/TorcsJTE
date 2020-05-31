/*
 *   PitProperties.java
 *   Created on 27  2005
 *
 *    The PitProperties.java is part of TrackEditor-0.3.1.
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
package gui.properties;

import utils.Editor;
import utils.TrackData;
import utils.circuit.Segment;
import utils.circuit.SegmentSide;

import javax.swing.*;
import java.util.Vector;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * pit properties form
 */
public class PitProperties extends JPanel {

  private JComboBox<String> jComboBox = null;
  private JTextField entryTextField = null;
  private JTextField startTextField = null;
  private JTextField endTextField = null;
  private JTextField exitTextField = null;
  private JTextField widthTextField = null;
  private JTextField lengthTextField = null;
  private JCheckBox generatePitsCheckBox = null;

  /**
   *
   */
  public PitProperties() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   */
  private void initialize() {
    JLabel generatePitsLabel = new JLabel();
    JLabel sideLabel = new JLabel();
    JLabel entryLabel = new JLabel();
    JLabel startLabel = new JLabel();
    JLabel endLabel = new JLabel();
    JLabel exitLabel = new JLabel();
    JLabel widthLabel = new JLabel();
    JLabel lengthLabel = new JLabel();
    this.setLayout(null);
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    this.setSize(362, 251);
    sideLabel.setText("Pits side");
    sideLabel.setBounds(10, 10, 70, 20);
    generatePitsLabel.setText("Generate Pits");
    generatePitsLabel.setBounds(220, 10, 100, 20);
    entryLabel.setText("Pit entry");
    entryLabel.setBounds(10, 35, 70, 20);
    exitLabel.setText("Pit exit");
    exitLabel.setBounds(220, 35, 70, 20);
    startLabel.setText("Pit start");
    startLabel.setBounds(10, 60, 70, 20);
    endLabel.setText("Pit end");
    endLabel.setBounds(220, 60, 70, 20);
    widthLabel.setText("Pit width");
    widthLabel.setBounds(10, 85, 70, 20);
    lengthLabel.setText("Pit length");
    lengthLabel.setBounds(220, 85, 70, 20);
    this.add(getLengthTextField(), null);
    this.add(getWidthTextField(), null);
    this.add(lengthLabel, null);
    this.add(getExitTextField(), null);
    this.add(widthLabel, null);
    this.add(getEntryTextField(), null);
    this.add(startLabel, null);
    this.add(getStartTextField(), null);
    this.add(endLabel, null);
    this.add(getEndTextField(), null);
    this.add(exitLabel, null);
    this.add(sideLabel, null);
    this.add(getJComboBox(), null);
    this.add(entryLabel, null);
    this.add(generatePitsLabel, null);
    this.add(getGeneratePitsCheckBox(), null);
  }

  /**
   * This method initializes jComboBox
   *
   * @return javax.swing.JComboBox
   */
  private JComboBox<String> getJComboBox() {
    if (jComboBox == null) {
      String[] items = {"right", "left"};
      jComboBox = new JComboBox<>(items);
      jComboBox.setSelectedItem(Editor.getProperties().getPitSide());
      jComboBox.setBounds(100, 10, 110, 20);
    }
    return jComboBox;
  }

  /**
   * This method initializes entryTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getEntryTextField() {
    if (entryTextField == null) {
      entryTextField = new JTextField();
      entryTextField.setText(Editor.getProperties().getPitEntry());
      entryTextField.setBounds(100, 35, 110, 21);
    }
    return entryTextField;
  }

  /**
   * This method initializes exitTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getExitTextField() {
    if (exitTextField == null) {
      exitTextField = new JTextField();
      exitTextField.setText(Editor.getProperties().getPitExit());
      exitTextField.setBounds(300, 35, 110, 21);
    }
    return exitTextField;
  }

  /**
   * This method initializes startTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getStartTextField() {
    if (startTextField == null) {
      startTextField = new JTextField();
      startTextField.setText(Editor.getProperties().getPitStart());
      startTextField.setBounds(100, 60, 110, 21);
    }
    return startTextField;
  }

  /**
   * This method initializes endTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getEndTextField() {
    if (endTextField == null) {
      endTextField = new JTextField();
      endTextField.setText(Editor.getProperties().getPitEnd());
      endTextField.setBounds(300, 60, 110, 21);
    }
    return endTextField;
  }

  /**
   * This method initializes widthTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getWidthTextField() {
    if (widthTextField == null) {
      widthTextField = new JTextField();
      widthTextField.setText(Double.toString(Editor.getProperties().getPitWidth()));
      widthTextField.setBounds(100, 85, 40, 20);
    }
    return widthTextField;
  }

  /**
   * This method initializes lengthTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getLengthTextField() {
    if (lengthTextField == null) {
      lengthTextField = new JTextField();
      lengthTextField.setText(Double.toString(Editor.getProperties().getPitLength()));
      lengthTextField.setBounds(300, 85, 40, 20);
    }
    return lengthTextField;
  }

  /**
   *
   */
  public void exit() {
    Editor.getProperties().setPitSide((String) getJComboBox().getSelectedItem());
    Editor.getProperties().setPitEntry(this.getEntryTextField().getText());
    Editor.getProperties().setPitStart(this.getStartTextField().getText());
    Editor.getProperties().setPitEnd(this.getEndTextField().getText());
    Editor.getProperties().setPitExit(this.getExitTextField().getText());
    try {
      Editor.getProperties().setPitWidth(Double.parseDouble(this.getWidthTextField().getText()));
    }
    catch (NumberFormatException e) {
      Editor.getProperties().setPitWidth(Double.NaN);
    }
    try {
      Editor.getProperties().setPitLength(Double.parseDouble(this.getLengthTextField().getText()));
    }
    catch (NumberFormatException e) {
      Editor.getProperties().setPitLength(Double.NaN);
    }
    if (this.getGeneratePitsCheckBox().isSelected()) {
      createPits();
    }
    Editor.getProperties().valueChanged();
  }

  /**
   *
   */
  private void createPits() {
    Vector<Segment> data = TrackData.getTrackData();
    Segment pitEntry = null;
    Segment pitStart = null;
    Segment pitEnd = null;
    Segment pitExit = null;

    for (Segment datum : data) {
      String name = datum.getName();
      if (name.equals(Editor.getProperties().getPitEntry())) {
        pitEntry = datum;
      }
      else if (name.equals(Editor.getProperties().getPitStart())) {
        pitStart = datum;
      }
      else if (name.equals(Editor.getProperties().getPitEnd())) {
        pitEnd = datum;
      }
      else if (name.equals(Editor.getProperties().getPitExit())) {
        pitExit = datum;
      }
    }
    SegmentSide side;
    if (pitEntry == null) {
      System.out.println("No pit entry");
      return;
    }
    if (Editor.getProperties().getPitSide().equals("left")) {
      side = pitEntry.getLeft();
    }
    else {
      side = pitEntry.getRight();
    }
    side.setBorderHeight(0);
    side.setBorderWidth(0);
    side.setSideEndWidth(Editor.getProperties().getPitWidth() * 3 + 1);
    side.setSideSurface("road1");
    side.setBarrierHeight(1);
    side.setBarrierSurface("tire-wall");
    side.setBarrierWidth(1);

    if (pitExit == null) {
      System.out.println("No pit exit");
      return;
    }
    if (Editor.getProperties().getPitSide().equals("left")) {
      side = pitExit.getLeft();
    }
    else {
      side = pitExit.getRight();
    }
    side.setBorderHeight(0);
    side.setBorderWidth(0.0);
    side.setSideStartWidth(Editor.getProperties().getPitWidth() * 3 + 1);
    side.setSideSurface("road1");
    side.setBarrierHeight(1);
    side.setBarrierSurface("tire-wall");
    side.setBarrierWidth(1);

    if (pitStart == null || pitEnd == null) {
      System.out.println("No pit start or end");
      return;
    }

    int start = data.indexOf(pitEntry);
    int end = data.indexOf(pitExit);

    for (int i = start + 1; i < end; i++) {
      if (Editor.getProperties().getPitSide().equals("left")) {
        side = data.get(i).getLeft();
      }
      else {
        side = data.get(i).getRight();
      }
      side.setBorderHeight(0);
      side.setBorderWidth(1);
      side.setBorderSurface("asphalt-pits");
      side.setBorderStyle("plan");
      side.setSideStartWidth(Editor.getProperties().getPitWidth() * 3);
      side.setSideEndWidth(Editor.getProperties().getPitWidth() * 3);
      side.setSideSurface("road1-pits");
      side.setBarrierStyle("plan");
      side.setBarrierSurface("barrier");
      side.setBarrierHeight(1);
      side.setBarrierWidth(0.1);
    }
  }

  /**
   * This method initializes generatePitsCheckBox
   *
   * @return javax.swing.JCheckBox
   */
  private JCheckBox getGeneratePitsCheckBox() {
    if (generatePitsCheckBox == null) {
      generatePitsCheckBox = new JCheckBox();
      generatePitsCheckBox.setBounds(328, 10, 20, 20);
    }
    return generatePitsCheckBox;
  }

}
