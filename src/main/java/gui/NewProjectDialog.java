/*
 *   NewProjectDialog.java
 *   Created on 27 ?? 2005
 *
 *    The NewProjectDialog.java is part of TrackEditor-0.3.1.
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
package gui;

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
public class NewProjectDialog extends JDialog {

  public static boolean APPROVE = false;
  private JPanel jPanel = null;
  private JTextField projectNameTextField = null;
  private JComboBox<String> trackCategoryComboBox = null;
  private JTextField pathTextField = null;
  private JButton browseButton = null;
  private JButton okButton = null;
  private JButton cancelButton = null;

  private final EditorFrame parent;
  private JTextField authorTextField = null;
  private JTextArea descriptionTextField = null;

  private final String sep = System.getProperty("file.separator");

  /**
   *
   */
  public NewProjectDialog(Frame parent) {
    super();
    this.parent = (EditorFrame) parent;
    initialize();
  }

  /**
   * This method initializes this
   */
  private void initialize() {
    this.setSize(420, 455);
    this.setContentPane(getJPanel());
    this.setModal(true);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("New Project");
    this.getContentPane().setSize(447, 321);
    setLocationRelativeTo(parent);
  }

  /**
   * This method initializes jPanel
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJPanel() {
    if (jPanel == null) {
      JLabel authorLabel = new JLabel();
      JLabel descriptionLabel = new JLabel();
      JLabel pathLabel = new JLabel();
      JLabel projectNameLabel = new JLabel();
      JLabel trackTypeLabel = new JLabel();

      jPanel = new JPanel();
      jPanel.setLayout(null);
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

      jPanel.add(getPathTextField(), null);
      jPanel.add(getBrowseButton(), null);
      jPanel.add(getOkButton(), null);
      jPanel.add(getCancelButton(), null);
      jPanel.add(getProjectNameTextField(), null);
      jPanel.add(projectNameLabel, null);

      //add
      jPanel.add(getTrackCategoryComboBox(), null);
      jPanel.add(trackTypeLabel, null);

      jPanel.add(pathLabel, null);
      jPanel.add(authorLabel, null);
      jPanel.add(getAuthorTextField(), null);
      jPanel.add(getDescriptionTextField(), null);
      jPanel.add(descriptionLabel, null);
    }
    return jPanel;
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

  /**
   * This method initializes cancelButton
   *
   * @return javax.swing.JButton
   */
  private JButton getCancelButton() {
    if (cancelButton == null) {
      cancelButton = new JButton();
      cancelButton.setBounds(105, 390, 90, 20);
      cancelButton.setText("Cancel");
      cancelButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          cancel();
        }
      });
    }
    return cancelButton;
  }

  /**
   * This method initializes okButton
   *
   * @return javax.swing.JButton
   */
  private JButton getOkButton() {
    if (okButton == null) {
      okButton = new JButton();
      okButton.setBounds(250, 390, 90, 20);
      okButton.setText("Ok");
      okButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          exit();
        }
      });
    }
    return okButton;
  }

  /**
   *
   */
  protected void exit() {
    String tmpPath = getPathTextField().getText();
    String tmpName = getProjectNameTextField().getText();
    String tmpTrackType = (String) getTrackCategoryComboBox().getSelectedItem();
    Editor.getProperties().setTrackName(tmpName);
    Editor.getProperties().setPath(tmpPath + sep + tmpName);
    Editor.getProperties().setCategory(tmpTrackType);
    File path = new File(tmpPath + sep + tmpName);
    if (!path.exists()) {
      boolean created = path.mkdirs();
      if (!created) System.err.println("directory " + path.toString() + " was not created");
    }
    Editor.getProperties().setAuthor(this.getAuthorTextField().getText());
    Editor.getProperties().setDescription(this.getDescriptionTextField().getText());
    APPROVE = true;
    cancel();
  }

  /**
   *
   */
  protected void cancel() {
    this.dispose();
  }

}
