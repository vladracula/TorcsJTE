/*
 *   ImageProperties.java
 *   Created on 27  2005
 *
 *    The ImageProperties.java is part of TrackEditor-0.3.1.
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
import javax.swing.border.EtchedBorder;
import java.io.File;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class ImageProperties extends JPanel {

  private JTextField pathTextField = null;
  private JButton browseButton = null;
  private JTextField imageScaleTextField = null;

  private String sep = System.getProperty("file.separator");

  /**
   *
   */
  public ImageProperties() {
    super();
    initialize();
  }

  /**
   *
   */
  private void initialize() {
    JLabel imageScaleLabel = new JLabel();
    JLabel pathLabel = new JLabel();
    this.setLayout(null);
    pathLabel.setBounds(10, 10, 60, 21);
    pathLabel.setText("Path");
    imageScaleLabel.setBounds(10, 35, 90, 21);
    imageScaleLabel.setText("Image scale");
    this.setSize(420, 230);
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    this.add(getPathTextField(), null);
    this.add(getBrowseButton(), null);
    this.add(pathLabel, null);
    this.add(imageScaleLabel, null);
    this.add(getImageScaleTextField(), null);
  }

  /**
   * This method initializes pathTextField
   *
   * @return javax.swing.JTextField
   */
  public JTextField getPathTextField() {
    if (pathTextField == null) {
      pathTextField = new JTextField();
      pathTextField.setBounds(75, 10, 240, 21);
      pathTextField.setText(Editor.getProperties().getImage());
      pathTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {

        }
      });
    }
    return pathTextField;
  }

  /**
   * This method initializes browseButton
   *
   * @return javax.swing.JButton
   */
  public JButton getBrowseButton() {
    if (browseButton == null) {
      browseButton = new JButton();
      browseButton.setBounds(320, 10, 95, 20);
      browseButton.setText("Browse");
      browseButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          selectPath();
        }
      });
    }
    return browseButton;
  }

  /**
   * This method initializes imageScaleTextField
   *
   * @return javax.swing.JTextField
   */
  public JTextField getImageScaleTextField() {
    if (imageScaleTextField == null) {
      imageScaleTextField = new JTextField();
      imageScaleTextField.setBounds(105, 35, 90, 21);
      imageScaleTextField.setText(Double.toString(Editor.getProperties().getImageScale()));
    }
    return imageScaleTextField;
  }

  /**
   *
   */
  protected void selectPath() {
    JFileChooser fc = new JFileChooser();
    fc.setSelectedFiles(null);
    fc.setSelectedFile(null);
    fc.rescanCurrentDirectory();
    fc.setApproveButtonMnemonic(0);
    fc.setDialogTitle("Background image selection");
    fc.setVisible(true);
    if (Editor.getProperties().getImage().equals("")) {
      fc.setCurrentDirectory(new File(System.getProperty("user.dir") + sep + "tracks"));
    }
    else {
      String tmpFile = Editor.getProperties().getImage().substring(0, Editor.getProperties().getImage().lastIndexOf(sep));
      File file = new File(tmpFile);
      fc.setCurrentDirectory(file);
    }
    int result = fc.showDialog(this, "Ok");
    if (result == JFileChooser.APPROVE_OPTION) {
      getPathTextField().setText(fc.getSelectedFile().toString());
    }
  }

  public void exit() {
    double scale = Double.parseDouble(getImageScaleTextField().getText());
    String tmpPath = getPathTextField().getText();
    Editor.getProperties().setImage(tmpPath);
    Editor.getProperties().setImageScale(scale);
    Editor.getProperties().valueChanged();
  }

}
