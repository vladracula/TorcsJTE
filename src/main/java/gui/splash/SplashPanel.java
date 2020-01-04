/*
 *   SplashPanel.java
 *   Created on Dec 4, 2004
 *
 *    The SplashPanel.java is part of TrackEditor-0.3.0.
 *
 *    TrackEditor-0.3.0 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.3.0 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.3.0; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package gui.splash;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * @author babis
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code
 * Templates
 */
public class SplashPanel extends JPanel {

  private ImageIcon image = null;
  private JButton splash = new JButton();
  private JLabel headerLabel = new JLabel();
  private String header = getClass().getPackage().getImplementationTitle();
  private String version = getClass().getPackage().getImplementationVersion();
  private StatusBar status = new StatusBar();

  /**
   *
   */
  public SplashPanel(String iconName) {
    this.setBorder(new EtchedBorder(BevelBorder.LOWERED));
    this.setLayout(new BorderLayout());


    ClassLoader cldr = this.getClass().getClassLoader();
    image = new ImageIcon(cldr.getResource(iconName));
    splash.setIcon(image);
    headerLabel.setOpaque(true);
    headerLabel.setBackground(Color.blue);
    headerLabel.setForeground(Color.white);
    headerLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
    headerLabel.setText(header + " " + version);
    this.add(headerLabel, BorderLayout.NORTH);
    this.add(splash, BorderLayout.CENTER);
    this.add(status, BorderLayout.SOUTH);
  }

  /**
   * @return Returns the image.
   */
  public ImageIcon getImage() {
    return image;
  }

  /**
   * @param image The image to set.
   */
  public void setImage(ImageIcon image) {
    this.image = image;
  }

  /**
   * @param status The status to set.
   */
  public void setStatus(String message) {
    this.status.setStatus(message);
  }

  /**
   * @param i
   */
  public void incProgress(int i) {
    this.status.incProgress(i);
  }

}
