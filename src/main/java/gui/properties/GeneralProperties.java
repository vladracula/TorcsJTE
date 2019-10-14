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
import java.io.File;

/**
 * @author babis
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GeneralProperties extends JPanel {
  //private Properties	properties				= Properties.getInstance();
  private JTextField projectNameTextField = null;
  private JLabel projectNameLabel = null;

  //add
  private JComboBox trackTypeComboBox = null;
  private JLabel trackTypeLabel = null;

  private JLabel pathLabel = null;
  private JTextField pathTextField = null;
  private JButton browseButton = null;
  private JLabel authorLabel = null;
  private JTextField authorTextField = null;
  private JLabel descriptionLabel = null;
  private JTextField descriptionTextField = null;

  private String sep = System.getProperty("file.separator");

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
    authorLabel = new JLabel();
    descriptionLabel = new JLabel();
    pathLabel = new JLabel();
    projectNameLabel = new JLabel();

    //add
    trackTypeLabel = new JLabel();

    this.setLayout(null);
    projectNameLabel.setBounds(15, 15, 100, 30);
    projectNameLabel.setText("Track Name");

    //add
    trackTypeLabel.setBounds(15, 60, 100, 30);
    trackTypeLabel.setText("Track Type");

    pathLabel.setBounds(15, 105, 60, 30);
    pathLabel.setText("Path");

    authorLabel.setBounds(15, 150, 60, 30);
    authorLabel.setText("Author");
    descriptionLabel.setBounds(15, 195, 80, 30);
    descriptionLabel.setText("Description");
    this.setSize(420, 230);
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    this.add(getPathTextField(), null);
    this.add(getBrowseButton(), null);
    this.add(getProjectNameTextField(), null);
    this.add(projectNameLabel, null);

    //add
    this.add(getTrackTypeComboBox(), null);
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
  public JTextField getProjectNameTextField() {
    if (projectNameTextField == null) {
      projectNameTextField = new JTextField();
      projectNameTextField.setBounds(135, 15, 170, 30);
      projectNameTextField.setText(Editor.getProperties().getTrackName());
      projectNameTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {

        }
      });
    }
    return projectNameTextField;
  }


  /**
   * This method initializes trackTypeComboBox
   * .
   *
   * @return javax.swing.JComboBox
   */
  private JComboBox getTrackTypeComboBox() {
    if (trackTypeComboBox == null) {
      String trackTypes[] = {"road", "oval", "dirt"};

      trackTypeComboBox = new JComboBox(trackTypes);
      trackTypeComboBox.setBounds(135, 60, 170, 30);
      trackTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {

        }
      });
    }
    return trackTypeComboBox;
  }


  /**
   * This method initializes pathTextField
   *
   * @return javax.swing.JTextField
   */
  public JTextField getPathTextField() {
    if (pathTextField == null) {
      pathTextField = new JTextField();
      pathTextField.setBounds(65, 105, 240, 30);
      pathTextField.setText(Editor.getProperties().getPath().substring(0, Editor.getProperties().getPath().lastIndexOf(sep)));
      pathTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          System.out.println("actionPerformed()"); // TODO
          // Auto-generated
          // Event stub
          // actionPerformed()
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
      browseButton.setBounds(320, 105, 95, 30);
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
   * This method initializes authorTextField
   *
   * @return javax.swing.JTextField
   */
  public JTextField getAuthorTextField() {
    if (authorTextField == null) {
      authorTextField = new JTextField();
      authorTextField.setBounds(65, 150, 240, 30);
      authorTextField.setText(Editor.getProperties().getAuthor());
    }
    return authorTextField;
  }

  /**
   * This method initializes descriptionTextField
   *
   * @return javax.swing.JTextField
   */
  public JTextField getDescriptionTextField() {
    if (descriptionTextField == null) {
      descriptionTextField = new JTextField();
      descriptionTextField.setBounds(90, 195, 330, 30);
      descriptionTextField.setText(Editor.getProperties().getDescription());
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
    String tmpTrackType = (String) getTrackTypeComboBox().getSelectedItem();

    Editor.getProperties().setTrackName(tmpName);
    Editor.getProperties().setPath(tmpPath + sep + tmpName);
    int index = tmpPath.lastIndexOf(sep) + 1;
    Editor.getProperties().setCategory(tmpTrackType);

    File path = new File(tmpPath + sep + tmpName);
    if (!path.exists()) {
      path.mkdirs();
    }
    Editor.getProperties().setAuthor(getAuthorTextField().getText());
    Editor.getProperties().setDescription(getDescriptionTextField().getText());

    Editor.getProperties().valueChanged();
  }

} //  @jve:decl-index=0:visual-constraint="10,10"
