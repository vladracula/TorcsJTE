/*
 *   CustomFileFilter.java
 *   Created on 1  2005
 *
 *    The CustomFileFilter.java is part of TrackEditor-0.5.0.
 *
 *    TrackEditor-0.5.0 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.5.0 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.5.0; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Vector;

/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CustomFileFilter extends FileFilter {

  private Vector<String> valid = new Vector<>();
  private Vector<String> invalid = new Vector<>();
  private String description = "";

  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    }

    boolean out = false;
    String filename = file.getName();
    int size = valid.size();
    for (int i = 0; i < size; i++) {
      if (filename.endsWith(valid.get(i))) {
        out = true;
      }
    }

    size = invalid.size();
    for (int i = 0; i < size; i++) {
      if (filename.endsWith(invalid.get(i))) {
        out = false;
      }
    }
    return out;
  }

  public String getDescription() {
    return description;
  }

  /**
   * @param description The description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  public void addValid(String str) {
    valid.addElement(str);
  }

  public void addInvalid(String str) {
    invalid.addElement(str);
  }
}
