/*
 *   SegmentComboBox.java
 *   Created on 31  2005
 *
 *    The SegmentComboBox.java is part of TrackEditor-0.3.1.
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

import utils.circuit.Segment;

import javax.swing.*;

/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SegmentComboBox extends JComboBox<String> {

  private SegmentEditorDialog editor;
  private String sectionName;
  private String attType;
  private String attName;
  private String attUnit;
  private Segment shape;
  private int dataIdx;

  /**
   *
   */
  public SegmentComboBox() {
  }

  /**
   * @return Returns the attName.
   */
  public String getAttName() {
    return attName;
  }

  /**
   * @param attName The attName to set.
   */
  public void setAttName(String attName) {
    this.attName = attName;
  }

  /**
   * @return Returns the attType.
   */
  public String getAttType() {
    return attType;
  }

  /**
   * @param attType The attType to set.
   */
  public void setAttType(String attType) {
    this.attType = attType;
  }

  /**
   * @return Returns the attUnit.
   */
  public String getAttUnit() {
    return attUnit;
  }

  /**
   * @param attUnit The attUnit to set.
   */
  public void setAttUnit(String attUnit) {
    this.attUnit = attUnit;
  }

  /**
   * @param editor The editor to set.
   */
  public void setEditor(SegmentEditorDialog editor) {
    this.editor = editor;
  }

  /**
   * @return Returns the sectionName.
   */
  public String getSectionName() {
    return sectionName;
  }

  /**
   * @param sectionName The sectionName to set.
   */
  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  /**
   * @return Returns the shape.
   */
  public Segment getShape() {
    return shape;
  }

  /**
   * @param shape The shape to set.
   */
  public void setShape(Segment shape) {
    this.shape = shape;
  }

  /**
   * @return Returns the dataIdx.
   */
  public int getDataIdx() {
    return dataIdx;
  }

  /**
   * @param dataIdx The dataIdx to set.
   */
  public void setDataIdx(int dataIdx) {
    this.dataIdx = dataIdx;
  }

}
