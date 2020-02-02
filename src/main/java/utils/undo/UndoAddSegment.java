/*
 *   UndoAddSegment.java
 *   Created on 29  2005
 *
 *    The UndoAddSegment.java is part of TrackEditor-0.6.0.
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

import utils.TrackData;
import utils.circuit.ContinuousSegment;
import utils.circuit.Segment;

import java.util.Vector;

/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UndoAddSegment implements UndoInterface {

  private Segment undo;
  private Segment redo;
  private int pos;

  /**
   *
   */
  public UndoAddSegment(Segment segment) {
    this.undo = segment;
    this.redo = null;
  }

  /* (non-Javadoc)
   * @see undo.UndoInterface#undo()
   */
  public void undo() {
    Vector<Segment> data = TrackData.getTrackData();
    pos = data.indexOf(undo);
    if(pos < 0) return;
    ContinuousSegment.makeLinkedList(data, pos, undo);
    data.insertElementAt(undo, pos);
    data.remove(undo);
    redo = undo;
    undo = null;
  }

  /* (non-Javadoc)
   * @see undo.UndoInterface#redo()
   */
  public void redo() {
    Vector<Segment> data = TrackData.getTrackData();
    if (pos < 0) return;
    Segment previous = ContinuousSegment.getPreviousSegment(data, pos);
    Segment next = ContinuousSegment.getNextSegment(data, pos);
    data.insertElementAt(redo, pos);
    ContinuousSegment.makeLinkedList(previous, next);
    undo = redo;
    redo = null;
  }

}
