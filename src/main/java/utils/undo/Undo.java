/*
 *   Undo.java
 *   Created on 29  2005
 *
 *    The Undo.java is part of TrackEditor-0.6.0.
 *
 *    TrackEditor-0.6.0 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.6.0 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.6.0; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package utils.undo;

import java.util.Vector;


/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Undo {

  private static Vector<UndoInterface> undoSegments = new Vector<>();
  private static Vector<UndoInterface> redoSegments = new Vector<>();

  /**
   *
   */
  public Undo() {

  }

  /*
   * (non-Javadoc)
   *
   * @see undo.UndoInterface#undo()
   */
  public static void undo() {
    if (undoSegments.size() > 0) {
      UndoInterface last = undoSegments.lastElement();
      undoSegments.remove(last);
      redoSegments.add(last);
      last.undo();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see undo.UndoInterface#redo()
   */
  public static void redo() {
    if (redoSegments.size() > 0) {
      UndoInterface last = redoSegments.lastElement();
      redoSegments.remove(last);
      undoSegments.add(last);
      last.redo();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see undo.UndoInterface#add(utils.circuit.Segment)
   */
  public static void add(UndoInterface undo) {
    undoSegments.add(undo);
    redoSegments.clear();
  }

}
