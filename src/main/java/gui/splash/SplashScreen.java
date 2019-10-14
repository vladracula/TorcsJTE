/*
 *   SplashWindow.java
 *   Created on Dec 4, 2004
 *
 *    The SplashWindow.java is part of TrackEditor-0.3.0.
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
import java.awt.*;

/**
 * @author babis
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SplashScreen extends JWindow {
  private static SplashScreen instance;

  private SplashPanel panel = null;
  private static boolean doSplash;

  public static synchronized SplashScreen getInstance() {
    if (!doSplash)
      return null;
    if (instance == null) {
      instance = new SplashScreen("Loading TrackEditor...", "data/icons/Splash.png");
    }
    return instance;
  }

  private SplashScreen(String title, String iconName) {
    super();

    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    getContentPane().setLayout(new BorderLayout(0, 0));

    panel = new SplashPanel(iconName);
    if (panel.getImage() != null) {
      int imgWidth = panel.getImage().getIconWidth();
      int imgHeight = panel.getImage().getIconHeight();
      Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation(scrSize.width / 2 - imgWidth / 2, scrSize.height / 2 - imgHeight / 2);
    }

    getContentPane().add(panel);
    Dimension contentPaneSize = getContentPane().getPreferredSize();
    setSize(contentPaneSize.width, contentPaneSize.height);

    pack();
  }

  ////////////////////////////////////////////////////////////////

  public void setStatus(String s) {
    panel.setStatus(s);
  }

  /**
   * @param i
   */
  public void incProgress(int i) {
    panel.incProgress(i);

  }

  public void setVisible(boolean b) {
    super.setVisible(b);
  }

  public static void setDoSplash(boolean dosplash) {
    doSplash = dosplash;
  }

  public static boolean getDoSplash() {
    return doSplash;
  }

}