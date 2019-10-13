/*
 *   Trackgen.java
 *   Created on 27  2005
 *
 *    The Trackgen.java is part of TrackEditor-0.3.1.
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
package pl.com.lanpro.games.torcs.tools.gui;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.com.lanpro.games.torcs.tools.utils.Editor;
/**
 * @author babis
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TrackgenPanel extends JDialog implements Runnable
{
	public static Vector	args			= new Vector();
	//private Properties properties = Properties.getInstance();
	public EditorFrame		parent;
	Thread ac3d = new Thread(this);

	private JPanel			jPanel			= null;
	private JLabel			nameLabel		= null;
	private JLabel			authorLabel		= null;
	private JLabel			fileNameLabel	= null;
	private JLabel			lengthLabel		= null;
	private JLabel			widthLabel		= null;
	private JLabel			xSizeLabel		= null;
	private JLabel			ySizeLabel		= null;
	private JTextField		nodesTextField	= null;
	private JPanel jPanel1 = null;
	private JLabel trackgenLabel = null;
	private JLabel waitLabel = null;

	public TrackgenPanel(Frame parent)
	{
		super();
		this.parent = (EditorFrame) parent;
		initialize();
		ac3d.start();
	}


	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setContentPane(getJPanel());
		this.setTitle("Trackgen");
		this.setSize(465, 320);

	}
	public void run()
	{
		String category = " -c " + Editor.getProperties().getCategory();
		String name = " -n " + Editor.getProperties().getTrackName();
		String args = " -a" + category + name;

		System.out.println(args);

		try
		{
			String ls_str;
			String tmp = "";

			Process ls_proc = Runtime.getRuntime().exec("trackgen" + args);
			// get its output (your input) stream
			BufferedReader ls_in = new BufferedReader(new InputStreamReader(ls_proc.getInputStream()));

			try
			{
				while ((ls_str = ls_in.readLine()) != null)
				{
					if (ls_str.indexOf(" ") != -1)
					{
						tmp = ls_str.substring(0, ls_str.indexOf(" "));
						if (tmp.equals("name"))
						{
							nameLabel.setText(ls_str);
						} else if (tmp.equals("author"))
						{
							this.authorLabel.setText(ls_str);
						} else if (tmp.equals("filename"))
						{
							this.fileNameLabel.setText(ls_str);
						}else if (tmp.equals("length"))
						{
							this.lengthLabel.setText(ls_str);
						}else if (tmp.equals("width"))
						{
							this.widthLabel.setText(ls_str);
						}
						else if (tmp.equals("XSize"))
						{
							this.xSizeLabel.setText(ls_str);
						}else if (tmp.equals("YSize"))
						{
							this.ySizeLabel.setText(ls_str);
						}else
						{
							this.nodesTextField.setText(ls_str);
						}
					}
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} catch (IOException e1)
		{
			System.err.println(e1);
		}
		this.waitLabel.setText("Track finished");
	}
	/**
	 * @return
	 */
	private static String getArgs()
	{
		String tmp = "";

		for (int i = 0; i < args.size(); i++)
		{
			tmp += args.get(i);
		}
		return tmp;
	}
	/**
	 * @param vector
	 */
	public static void setArgs(Vector vector)
	{
		args = vector;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			trackgenLabel = new JLabel();
			waitLabel = new JLabel();
			jPanel = new JPanel();
			jPanel.setLayout(null);
			trackgenLabel.setBounds(150, 10, 175, 20);
			trackgenLabel.setText("Track data");
			trackgenLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 18));
			waitLabel.setBounds(10, 260, 290, 25);
			waitLabel.setText("Constructing the .ac file. Please wait...");
			jPanel.add(trackgenLabel, null);
			jPanel.add(waitLabel, null);

			jPanel.add(getJPanel1(), null);
			jPanel.add(getNodesTextField(), null);
		}
		return jPanel;
	}
	/**
	 * This method initializes nodesTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getNodesTextField()
	{
		if (nodesTextField == null)
		{
			nodesTextField = new JTextField();
			nodesTextField.setBounds(10, 230, 220, 25);
			nodesTextField.setEditable(false);
			nodesTextField.setText("");
		}
		return nodesTextField;
	}
	/**
	 * This method initializes jPanel1
	 * 	
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(10, 40, 440, 180);
			jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
			nameLabel = new JLabel();
			authorLabel = new JLabel();
			fileNameLabel = new JLabel();
			lengthLabel = new JLabel();
			widthLabel = new JLabel();
			xSizeLabel = new JLabel();
			ySizeLabel = new JLabel();
			nameLabel.setText("");
			nameLabel.setBounds(5, 5, 420, 20);
			authorLabel.setText("");
			authorLabel.setBounds(5, 30, 420, 20);
			fileNameLabel.setText("");
			fileNameLabel.setBounds(5, 55, 420, 20);
			lengthLabel.setText("");
			lengthLabel.setBounds(5, 80, 420, 20);
			widthLabel.setText("");
			widthLabel.setBounds(5, 105, 420, 20);
			xSizeLabel.setText("");
			xSizeLabel.setBounds(5, 130, 420, 20);
			ySizeLabel.setText("");
			ySizeLabel.setBounds(5, 155, 420, 20);
			jPanel1.add(ySizeLabel, null);
			jPanel1.add(xSizeLabel, null);
			jPanel1.add(widthLabel, null);
			jPanel1.add(lengthLabel, null);
			jPanel1.add(fileNameLabel, null);
			jPanel1.add(authorLabel, null);
			jPanel1.add(nameLabel, null);
		}
		return jPanel1;
	}
 } //  @jve:decl-index=0:visual-constraint="10,10"

