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

import gui.properties.GeneralProperties;
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
  private JButton okButton = null;
  private JButton cancelButton = null;
  private final EditorFrame parent;
  private GeneralProperties generalProperties;

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
    this.generalProperties = new GeneralProperties();
    this.setSize(424, 238);
    this.setContentPane(getJPanel());
    this.setModal(true);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("New Project");
    this.getContentPane().setSize(424, 238);
    setLocationRelativeTo(parent);
  }

  /**
   * This method initializes jPanel
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJPanel() {
    if (jPanel == null) {
      jPanel = new JPanel();
      jPanel.add(generalProperties);
      jPanel.add(getCancelButton());
      jPanel.add(getOkButton());
      jPanel.setLayout(null);
    }
    return jPanel;
  }


  /**
   * This method initializes cancelButton
   *
   * @return javax.swing.JButton
   */
  private JButton getCancelButton() {
    if (cancelButton == null) {
      cancelButton = new JButton();
      cancelButton.setBounds(224, 190, 90, 20);
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
      okButton.setBounds(324, 190, 90, 20);
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
    String tmpPath = generalProperties.getTrackPath();
    String tmpName = generalProperties.getTrackName();
    String tmpTrackType = generalProperties.getCategory();
    Editor.getProperties().setTrackName(tmpName);
    Editor.getProperties().setPath(tmpPath + sep + tmpName);
    Editor.getProperties().setCategory(tmpTrackType);
    File path = new File(tmpPath + sep + tmpName);
    if (!path.exists()) {
      boolean created = path.mkdirs();
      if (!created) System.err.println("directory " + path.toString() + " was not created");
    }
    Editor.getProperties().setAuthor(generalProperties.getTrackAuthor());
    Editor.getProperties().setDescription(generalProperties.getTrackDescription());
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
