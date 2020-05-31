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
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class GeneralProperties extends JPanel {

  private JTextField projectNameTextField = null;
  private JComboBox<String> trackCategoryComboBox = null;
  private JTextField pathTextField = null;
  private JButton browseButton = null;
  private JTextField authorTextField = null;
  private JTextArea descriptionTextField = null;

  private final String sep = System.getProperty("file.separator");

  /**
   *
   */
  public GeneralProperties() {
    super();
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
    projectNameLabel.setBounds(10, 10, 100, 20);
    projectNameLabel.setText("Track Name");
    trackTypeLabel.setBounds(10, 35, 110, 20);
    trackTypeLabel.setText("Track category");
    authorLabel.setBounds(10, 60, 60, 20);
    authorLabel.setText("Author");
    pathLabel.setBounds(10, 85, 105, 20);
    pathLabel.setText("Path");
    descriptionLabel.setBounds(10, 110, 90, 20);
    descriptionLabel.setText("Description");

    this.setSize(425, 179);
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
      projectNameTextField.setBounds(130, 10, 286, 21);
      if (Editor.getProperties().getTrackName() != null) {
        projectNameTextField.setText(Editor.getProperties().getTrackName());
      }
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
      trackCategoryComboBox.setBounds(130, 35, 285, 20);
      if (Editor.getProperties().getCategory() != null) {
        trackCategoryComboBox.setSelectedItem(Editor.getProperties().getCategory());
      }
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
      authorTextField.setBounds(130, 60, 286, 21);
      String author = Editor.getProperties().getAuthor();
      if (author != null) {
        authorTextField.setText(author);
      }
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
      pathTextField.setBounds(65, 85, 253, 21);
      String path = Editor.getProperties().getPath();
      if (path != null) {
        path = path.substring(0, path.lastIndexOf(sep));
        pathTextField.setText(path);
      }
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
      browseButton.setBounds(325, 85, 90, 20);
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
      descriptionTextField.setBorder(BorderFactory.createLineBorder(Color.gray, 1));;
      descriptionTextField.setBounds(9, 135, 406, 33);
      String description = Editor.getProperties().getDescription();
      if (description != null) {
        descriptionTextField.setText(description);
      }
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

  public String getTrackName() {
    return getProjectNameTextField().getText();
  }

  public String getCategory() {
    return (String) getTrackCategoryComboBox().getSelectedItem();
  }

  public String getTrackAuthor() {
    return getAuthorTextField().getText();
  }

  public String getTrackPath() {
    return getPathTextField().getText();
  }

  public String getTrackDescription() {
    return getDescriptionTextField().getText();
  }

  public void exit() {
    String tmpPath = getTrackPath();
    String tmpName = getTrackName();
    String tmpTrackType = getCategory();
    Editor.getProperties().setTrackName(tmpName);
    Editor.getProperties().setPath(tmpPath + sep + tmpName);
    Editor.getProperties().setCategory(tmpTrackType);

    File path = new File(tmpPath + sep + tmpName);
    if (!path.exists()) {
      boolean created = path.mkdirs();
      if (!created) System.err.println("directory " + path.toString() + " was not created");
    }
    Editor.getProperties().setAuthor(getTrackAuthor());
    Editor.getProperties().setDescription(getTrackDescription());
    Editor.getProperties().valueChanged();
  }

}
