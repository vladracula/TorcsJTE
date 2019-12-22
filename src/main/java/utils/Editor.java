/*
 *   Editor.java
 *   Created on Aug 26, 2004
 *
 *    The Editor.java is part of TrackEditor-0.6.2.
 *
 *    TrackEditor-0.6.2 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.6.2 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.6.2TrackEditor; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package utils;

/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Editor {

  /**
   * @return Returns the properties.
   */
  public static Properties getProperties() {
    return Properties.getInstance();
  }

  /**
   * @param properties The properties to set.
   */
  public static void setProperties(Properties props) {
    Properties.setInstance(props);
  }

  /**
   *
   */
  public Editor() {
    super();
    // TODO Auto-generated constructor stub
  }

}
