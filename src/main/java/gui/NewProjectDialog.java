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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NewProjectDialog extends JDialog {

  //private Properties properties					= Properties.getInstance();
  public static boolean APPROVE = false;
  private JPanel jPanel = null;
  private JTextField projectNameTextField = null;
  private JLabel projectNameLabel = null;

  //add
  private JComboBox<String> trackTypeComboBox = null;
  private JLabel trackTypeLabel = null;

  private JLabel pathLabel = null;
  private JTextField pathTextField = null;
  private JButton browseButton = null;
  private JButton okButton = null;
  private JButton cancelButton = null;

  private EditorFrame parent;
  private JLabel authorLabel = null;
  private JTextField authorTextField = null;
  private JLabel descriptionLabel = null;
  private JTextField descriptionTextField = null;

  private String sep = System.getProperty("file.separator");

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
   *
   * @return void
   */
  private void initialize() {
    this.setSize(440, 455);
    this.setContentPane(getJPanel());
    this.setModal(true);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("New Project");
    this.getContentPane().setSize(447, 321);
  }

  /**
   * This method initializes jPanel
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJPanel() {
    if (jPanel == null) {
      authorLabel = new JLabel();
      descriptionLabel = new JLabel();
      pathLabel = new JLabel();
      projectNameLabel = new JLabel();

      //add
      trackTypeLabel = new JLabel();

      jPanel = new JPanel();
      jPanel.setLayout(null);
      projectNameLabel.setBounds(15, 15, 100, 30);
      projectNameLabel.setText("Track Name");

      //add
      trackTypeLabel.setBounds(15, 60, 100, 30);
      trackTypeLabel.setText("Track Type");

      pathLabel.setBounds(15, 105, 105, 30);
      pathLabel.setText("Path");
      authorLabel.setBounds(15, 150, 60, 30);
      authorLabel.setText("Author");
      descriptionLabel.setBounds(15, 195, 80, 30);
      descriptionLabel.setText("Description");
      jPanel.add(getPathTextField(), null);
      jPanel.add(getBrowseButton(), null);
      jPanel.add(getOkButton(), null);
      jPanel.add(getCancelButton(), null);
      jPanel.add(getProjectNameTextField(), null);
      jPanel.add(projectNameLabel, null);

      //add
      jPanel.add(getTrackTypeComboBox(), null);
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
      projectNameTextField.setBounds(135, 15, 170, 30);
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
  private JComboBox getTrackTypeComboBox() {
    if (trackTypeComboBox == null) {
      String trackTypes[] = {"road", "oval", "dirt"};

      trackTypeComboBox = new JComboBox<>(trackTypes);
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
  private JTextField getPathTextField() {
    if (pathTextField == null) {
      pathTextField = new JTextField();
      pathTextField.setBounds(65, 105, 240, 30);
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
  private JButton getBrowseButton() {
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
  private JTextField getAuthorTextField() {
    if (authorTextField == null) {
      authorTextField = new JTextField();
      authorTextField.setBounds(65, 150, 240, 30);
    }
    return authorTextField;
  }

  /**
   * This method initializes descriptionTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getDescriptionTextField() {
    if (descriptionTextField == null) {
      descriptionTextField = new JTextField();
      descriptionTextField.setBounds(90, 195, 330, 30);
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
   * This method initializes okButton
   *
   * @return javax.swing.JButton
   */
  private JButton getOkButton() {
    if (okButton == null) {
      okButton = new JButton();
      okButton.setBounds(105, 390, 78, 30);
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
   * This method initializes cancelButton
   *
   * @return javax.swing.JButton
   */
  private JButton getCancelButton() {
    if (cancelButton == null) {
      cancelButton = new JButton();
      cancelButton.setBounds(250, 390, 78, 30);
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
   *
   */
  protected void exit() {
    String tmpPath = getPathTextField().getText();
    String tmpName = getProjectNameTextField().getText();
    String tmpTrackType = (String) getTrackTypeComboBox().getSelectedItem();
    Editor.getProperties().setTrackName(tmpName);
    Editor.getProperties().setPath(tmpPath + sep + tmpName);
    Editor.getProperties().setCategory(tmpTrackType);
    File path = new File(tmpPath + sep + tmpName);
    if (!path.exists()) {
      path.mkdirs();
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
} //  @jve:decl-index=0:visual-constraint="6,6"