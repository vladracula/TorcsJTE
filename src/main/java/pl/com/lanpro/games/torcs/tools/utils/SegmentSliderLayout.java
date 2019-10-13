/*
 *   SegmentSliderLayout.java
 *   Created on 28  2005
 *
 *    The SegmentSliderLayout.java is part of TrackEditor-0.3.1.
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
package pl.com.lanpro.games.torcs.tools.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.Serializable;

/**
 * @author babis
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SegmentSliderLayout implements LayoutManager, Serializable
{

	/**
	 * 
	 */
	public SegmentSliderLayout()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public void removeLayoutComponent(Component comp)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	public void layoutContainer(Container parent)
	{
		int width = parent.getWidth();
		int heigth = parent.getHeight();
		
		if(heigth < 108)
		{
			heigth = 108;
			parent.setSize(width,heigth);
		}


		//		label.setFont(new java.awt.Font("SansSerif", 0, 8));
		Component[] comps = parent.getComponents();
		
		comps[0].setBounds(2, 2, width-4, 20);
		comps[1].setBounds(2, 22, 20, 20);
		comps[2].setBounds(22, 22, width-26, 20);
		comps[3].setBounds(2, 44, width-4, 20);
		comps[4].setBounds(width/3, 66, 20, heigth-68);
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
	 */
	public void addLayoutComponent(String name, Component comp)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	public Dimension minimumLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	public Dimension preferredLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

