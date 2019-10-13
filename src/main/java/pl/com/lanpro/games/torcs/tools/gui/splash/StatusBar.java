/*
 *   StatusBar.java
 *   Created on 5  2004
 *
 *    The StatusBar.java is part of TrackEditor-0.3.0.
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
package pl.com.lanpro.games.torcs.tools.gui.splash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

/**
 * @author babis
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatusBar extends JPanel
{
	private JLabel msg = new JLabel("Label");
	private JProgressBar progress = new JProgressBar();
	protected String statusText;

	/**
	 * 
	 */
	public StatusBar()
	{
		super();
		progress.setMinimum(0);
		progress.setMaximum(100);
		progress.setMinimumSize(new Dimension(100, 20));
		progress.setSize(new Dimension(100, 20));

		msg.setMinimumSize(new Dimension(300, 20));
		msg.setSize(new Dimension(300, 20));
		msg.setFont(new Font("Dialog", Font.PLAIN, 10));
		msg.setForeground(Color.black);

		setLayout(new BorderLayout());
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		add(msg, BorderLayout.CENTER);
		add(progress, BorderLayout.EAST);
	}

	/**
	 * @param msg The msg to set.
	 */
	public void setStatus(String status)
	{
		this.msg.setText(status);
	}
	/**
	 * @param progress The progress to set.
	 */
	public void incProgress(int value)
	{
		int prev = this.progress.getValue();
		this.progress.setValue(prev+value);
	}

}
