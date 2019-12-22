/*
 *   TrackProperties.java
 *   Created on 27  2005
 *
 *    The TrackProperties.java is part of TrackEditor-0.3.1.
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

import javax.swing.*;

/**
 * @author babis
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TrackProperties extends JPanel {

  private JLabel widthLabel = null;
  private JTextField widthTextField = null;

  /**
   *
   */
  public TrackProperties() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   *
   * @return void
   */
  private void initialize() {
    widthLabel = new JLabel();
    this.setLayout(null);
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    this.setSize(356, 259);
    widthLabel.setText("Width");
    widthLabel.setBounds(10, 10, 60, 20);
    this.add(widthLabel, null);
    this.add(getWidthTextField(), null);

  }

  /**
   * This method initializes widthTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getWidthTextField() {
    if (widthTextField == null) {
      widthTextField = new JTextField();
      widthTextField.setText(Double.toString(Editor.getProperties().getTrackWidth()));
      widthTextField.setBounds(75, 10, 50, 20);
    }
    return widthTextField;
  }

  /**
   *
   */
  public void exit() {
    try {
      Editor.getProperties().setTrackWidth(Double.parseDouble(this.getWidthTextField().getText()));
    }
    catch (NumberFormatException e) {
      Editor.getProperties().setTrackWidth(Double.NaN);
    }
  }

}
