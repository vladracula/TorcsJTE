/*
 *   Project.java
 *   Created on 1  2005
 *
 *    The Project.java is part of TrackEditor-0.6.0.
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
package pl.com.lanpro.games.torcs.tools.utils;

/**
 * @author Charalampos Alexopoulos
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Project
{
	private int frameX = 0;
	private int frameY = 0;
	private int segmentEditorX =0;
	private int segmentEditorY =0;
	
	public Project()
	{
		
	}

	/**
	 * @return Returns the frameX.
	 */
	public int getFrameX()
	{
		return frameX;
	}
	/**
	 * @param frameX The frameX to set.
	 */
	public void setFrameX(int frameX)
	{
		this.frameX = frameX;
	}
	/**
	 * @return Returns the frameY.
	 */
	public int getFrameY()
	{
		return frameY;
	}
	/**
	 * @param frameY The frameY to set.
	 */
	public void setFrameY(int frameY)
	{
		this.frameY = frameY;
	}
	/**
	 * @return Returns the segmentEditorX.
	 */
	public int getSegmentEditorX()
	{
		return segmentEditorX;
	}
	/**
	 * @param segmentEditorX The segmentEditorX to set.
	 */
	public void setSegmentEditorX(int segmentEditorX)
	{
		this.segmentEditorX = segmentEditorX;
	}
	/**
	 * @return Returns the segmentEditorY.
	 */
	public int getSegmentEditorY()
	{
		return segmentEditorY;
	}
	/**
	 * @param segmentEditorY The segmentEditorY to set.
	 */
	public void setSegmentEditorY(int segmentEditorY)
	{
		this.segmentEditorY = segmentEditorY;
	}
}
