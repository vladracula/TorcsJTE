/*
 *   PropertiesDialog.java
 *   Created on 27  2005
 *
 *    The PropertiesDialog.java is part of TrackEditor-0.3.1.
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

import javax.swing.*;
import java.awt.*;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class PropertiesDialog extends JDialog {

  private final Frame frame;
  private JTabbedPane tabbedPane = null;
  private GeneralProperties generalProperties = null;
  private JPanel pane = null;
  private JPanel footerPanel = null;
  private JButton okButton = null;
  private JButton cancelButton = null;
  private TrackProperties trackProperties = null;
  private PitProperties pitProperties = null;
  private ImageProperties imageProperties = null;

  /**
   * constructor
   */
  public PropertiesDialog(Frame frame) {
    super();
    this.frame = frame;
    initialize();
  }

  /**
   * This method initializes PropertiesDialog
   */
  private void initialize() {
    this.setContentPane(getPane());
    this.setSize(423, 252);
    this.setModal(true);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("Properties");
    this.getPane().setSize(423, 214);
    setLocationRelativeTo(frame);
  }

  /**
   * This method initializes tabbedPane
   *
   * @return javax.swing.JTabbedPane
   */
  private JTabbedPane getTabbedPane() {
    if (tabbedPane == null) {
      tabbedPane = new JTabbedPane();
      tabbedPane.addTab("General", null, getGeneralProperties(), null);
      tabbedPane.addTab("Track", null, getTrackProperties(), null);
      tabbedPane.addTab("Pit", null, getPitProperties(), null);
      tabbedPane.addTab("Image", null, getImageProperties(), null);
    }
    return tabbedPane;
  }

  /**
   * This method initializes generalProperties
   *
   * @return gui.properties.GeneralProperties
   */
  private GeneralProperties getGeneralProperties() {
    if (generalProperties == null) {
      generalProperties = new GeneralProperties();
    }
    return generalProperties;
  }

  /**
   * This method initializes pane
   *
   * @return javax.swing.JPanel
   */
  private JPanel getPane() {
    if (pane == null) {
      pane = new JPanel();
      pane.setLayout(new BorderLayout());
      pane.add(getTabbedPane(), java.awt.BorderLayout.CENTER);
      pane.add(getFooterPanel(), java.awt.BorderLayout.SOUTH);
    }
    return pane;
  }

  /**
   * This method initializes footerPanel
   *
   * @return javax.swing.JPanel
   */
  private JPanel getFooterPanel() {
    if (footerPanel == null) {
      footerPanel = new JPanel();
      footerPanel.setLayout(null);
      footerPanel.setPreferredSize(new java.awt.Dimension(423, 38));
      footerPanel.add(getOkButton(), null);
      footerPanel.add(getCancelButton(), null);
    }
    return footerPanel;
  }

  /**
   * This method initializes okButton
   *
   * @return javax.swing.JButton
   */
  private JButton getOkButton() {
    if (okButton == null) {
      okButton = new JButton();
      okButton.setBounds(325, 8, 90, 20);
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
      cancelButton.setBounds(225, 8, 90, 20);
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
   * This method initializes trackProperties
   *
   * @return gui.properties.TrackProperties
   */
  private TrackProperties getTrackProperties() {
    if (trackProperties == null) {
      trackProperties = new TrackProperties();
    }
    return trackProperties;
  }

  /**
   * This method initializes pitProperties
   *
   * @return gui.properties.PitProperties
   */
  private PitProperties getPitProperties() {
    if (pitProperties == null) {
      pitProperties = new PitProperties();
    }
    return pitProperties;
  }

  /**
   * This method initializes imageProperties
   *
   * @return gui.properties.ImageProperties
   */
  private ImageProperties getImageProperties() {
    if (imageProperties == null) {
      imageProperties = new ImageProperties();
    }
    return imageProperties;
  }

  /**
   * handled logic on ok action
   */
  protected void exit() {
    this.generalProperties.exit();
    this.trackProperties.exit();
    this.pitProperties.exit();
    this.imageProperties.exit();
    cancel();
  }

  /**
   * hide this form
   */
  protected void cancel() {
    this.dispose();
  }

}
