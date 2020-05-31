/*
 *   GeneralProperties.java
 *   Created on 27  2005
 *
 *    The GeneralProperties.java is part of TrackEditor-0.3.1.
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
import java.awt.*;
import java.io.File;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GeneralProperties extends JPanel {

  private Frame frame;
  private JTextField projectNameTextField = null;
  private JComboBox<String> trackCategoryComboBox = null;
  private JTextField pathTextField = null;
  private JButton browseButton = null;
  private JTextField authorTextField = null;
  private JTextArea descriptionTextField = null;

  private String sep = System.getProperty("file.separator");

  /**
   *
   */
  public GeneralProperties(Frame frame) {
    super();
    this.frame = frame;
    initialize();
  }

  /**
   *
   */
  private void initialize() {
    JLabel authorLabel = new JLabel();
    JLabel descriptionLabel = new JLabel();
    JLabel pathLabel = new JLabel();
    JLabel projectNameLabel = new JLabel();
    JLabel trackTypeLabel = new JLabel();

    this.setLayout(null);
    projectNameLabel.setBounds(15, 15, 100, 20);
    projectNameLabel.setText("Track Name");
    trackTypeLabel.setBounds(15, 40, 110, 20);
    trackTypeLabel.setText("Track category");
    authorLabel.setBounds(15, 65, 60, 20);
    authorLabel.setText("Author");
    pathLabel.setBounds(15, 90, 105, 20);
    pathLabel.setText("Path");
    descriptionLabel.setBounds(15, 115, 90, 20);
    descriptionLabel.setText("Description");
    this.setSize(420, 230);
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    this.add(getPathTextField(), null);
    this.add(getBrowseButton(), null);
    this.add(getProjectNameTextField(), null);
    this.add(projectNameLabel, null);
    this.add(getTrackCategoryComboBox(), null);
    this.add(trackTypeLabel, null);

    this.add(pathLabel, null);
    this.add(authorLabel, null);
    this.add(getAuthorTextField(), null);
    this.add(getDescriptionTextField(), null);
    this.add(descriptionLabel, null);
  }

  /**
   * This method initializes projectNameTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getProjectNameTextField() {
    if (projectNameTextField == null) {
      projectNameTextField = new JTextField();
      projectNameTextField.setBounds(130, 15, 275, 20);
      projectNameTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {

        }
      });
    }
    return projectNameTextField;
  }

  /**
   * This method initializes trackTypeComboBox
   *
   * @return javax.swing.JComboBox
   */
  private JComboBox<String> getTrackCategoryComboBox() {
    if (trackCategoryComboBox == null) {
      String[] trackTypes = {"road", "oval", "dirt"};

      trackCategoryComboBox = new JComboBox<>(trackTypes);
      trackCategoryComboBox.setBounds(130, 40, 275, 20);
      trackCategoryComboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {

        }
      });
    }
    return trackCategoryComboBox;
  }

  /**
   * This method initializes authorTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getAuthorTextField() {
    if (authorTextField == null) {
      authorTextField = new JTextField();
      authorTextField.setBounds(130, 65, 275, 20);
    }
    return authorTextField;
  }

  /**
   * This method initializes pathTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getPathTextField() {
    if (pathTextField == null) {
      pathTextField = new JTextField();
      pathTextField.setBounds(65, 90, 240, 20);
      pathTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          System.out.println("actionToPerformed()"); // TODO
          // Auto-generated
          // Event stub
          // actionToPerformed()
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
  private JButton getBrowseButton() {
    if (browseButton == null) {
      browseButton = new JButton();
      browseButton.setBounds(315, 90, 90, 20);
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
   * This method initializes descriptionTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextArea getDescriptionTextField() {
    if (descriptionTextField == null) {
      descriptionTextField = new JTextArea();
      descriptionTextField.setBounds(15, 140, 390, 90);
    }
    return descriptionTextField;
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
    fc.setDialogTitle("Project path selection");
    fc.setVisible(true);
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fc.setCurrentDirectory(new File(System.getProperty("user.dir") + sep + "tracks"));
    int result = fc.showDialog(this, "Ok");
    if (result == JFileChooser.APPROVE_OPTION) {
      getPathTextField().setText(fc.getSelectedFile().toString());
    }
  }

  public void exit() {
    String tmpPath = getPathTextField().getText();
    String tmpName = getProjectNameTextField().getText();
    String tmpTrackType = (String) getTrackCategoryComboBox().getSelectedItem();

    Editor.getProperties().setTrackName(tmpName);
    Editor.getProperties().setPath(tmpPath + sep + tmpName);
    Editor.getProperties().setCategory(tmpTrackType);

    File path = new File(tmpPath + sep + tmpName);
    if (!path.exists()) {
      path.mkdirs();
    }
    Editor.getProperties().setAuthor(getAuthorTextField().getText());
    Editor.getProperties().setDescription(getDescriptionTextField().getText());

    Editor.getProperties().valueChanged();
  }

}
