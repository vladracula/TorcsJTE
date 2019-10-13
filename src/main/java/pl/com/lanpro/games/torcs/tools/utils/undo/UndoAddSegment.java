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
package pl.com.lanpro.games.torcs.tools.utils.undo;

import java.util.Vector;

import pl.com.lanpro.games.torcs.tools.utils.TrackData;
import pl.com.lanpro.games.torcs.tools.utils.circuit.Segment;


/**
 * @author Charalampos Alexopoulos
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UndoAddSegment implements UndoInterface
{
	//private Properties			properties						= Properties.getInstance();
	private Segment undo;
	private Segment redo;
	private int pos;

	/**
	 * 
	 */
	public UndoAddSegment(Segment segment)
	{
		this.undo = segment;
		this.redo = null;
	}

	/* (non-Javadoc)
	 * @see undo.UndoInterface#undo()
	 */
	public void undo()
	{
		Vector data = TrackData.getTrackData();
		pos = data.indexOf(undo);
		data.remove(undo);
		redo = undo;
		undo = null;
	}

	/* (non-Javadoc)
	 * @see undo.UndoInterface#redo()
	 */
	public void redo()
	{
		Vector data = TrackData.getTrackData();
		data.insertElementAt(redo,pos);
		undo = redo;
		redo = null;
	}

}
