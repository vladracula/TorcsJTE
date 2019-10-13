/*
 *   PitProperties.java
 *   Created on 27  2005
 *
 *    The PitProperties.java is part of TrackEditor-0.3.1.
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
package pl.com.lanpro.games.torcs.tools.gui.properties;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.com.lanpro.games.torcs.tools.utils.Editor;
import pl.com.lanpro.games.torcs.tools.utils.TrackData;
import pl.com.lanpro.games.torcs.tools.utils.circuit.Segment;
import pl.com.lanpro.games.torcs.tools.utils.circuit.SegmentSide;

/**
 * @author babis
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PitProperties extends JPanel
{
	//private Properties	properties		= Properties.getInstance();
	private JLabel		sideLabel		= null;
	private JComboBox	jComboBox		= null;
	private JLabel		entryLabel		= null;
	private JTextField	entryTextField	= null;
	private JLabel		startLabel		= null;
	private JTextField	startTextField	= null;
	private JLabel		endLabel		= null;
	private JTextField	endTextField	= null;
	private JLabel		exitLabel		= null;
	private JTextField	exitTextField	= null;
	private JLabel		widthLabel		= null;
	private JTextField	widthTextField	= null;
	private JLabel		lengthLabel		= null;
	private JTextField	lengthTextField	= null;
	private JLabel generatePitsLabel = null;
	private JCheckBox generatePitsCheckBox = null;
	private boolean generatePits		= false;
	/**
	 *  
	 */
	public PitProperties()
	{
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		generatePitsLabel = new JLabel();
		sideLabel = new JLabel();
		entryLabel = new JLabel();
		startLabel = new JLabel();
		endLabel = new JLabel();
		exitLabel = new JLabel();
		widthLabel = new JLabel();
		lengthLabel = new JLabel();
		this.setLayout(null);
		this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
		this.setSize(362, 251);
		sideLabel.setBounds(10, 10, 70, 20);
		sideLabel.setText("Pits side");
		entryLabel.setBounds(10, 35, 70, 20);
		entryLabel.setText("Pit entry");
		startLabel.setBounds(10, 60, 70, 20);
		startLabel.setText("Pit start");
		endLabel.setBounds(10, 85, 70, 20);
		endLabel.setText("Pit end");
		exitLabel.setBounds(10, 110, 70, 20);
		exitLabel.setText("Pit exit");
		widthLabel.setBounds(10, 135, 70, 20);
		widthLabel.setText("Pit width");
		lengthLabel.setBounds(10, 160, 70, 20);
		lengthLabel.setText("Pit length");
		generatePitsLabel.setBounds(215, 10, 99, 20);
		generatePitsLabel.setText("Generate Pits");
		this.add(getLengthTextField(), null);
		this.add(getWidthTextField(), null);
		this.add(lengthLabel, null);
		this.add(getExitTextField(), null);
		this.add(widthLabel, null);
		this.add(getEntryTextField(), null);
		this.add(startLabel, null);
		this.add(getStartTextField(), null);
		this.add(endLabel, null);
		this.add(getEndTextField(), null);
		this.add(exitLabel, null);
		this.add(sideLabel, null);
		this.add(getJComboBox(), null);
		this.add(entryLabel, null);

		this.add(generatePitsLabel, null);
		this.add(getGeneratePitsCheckBox(), null);
	}
	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox()
	{
		if (jComboBox == null)
		{
			String[] items =
			{"right", "left"};
			jComboBox = new JComboBox(items);
			jComboBox.setSelectedItem(Editor.getProperties().getPitSide());
			jComboBox.setBounds(100, 10, 80, 20);
		}
		return jComboBox;
	}
	/**
	 * This method initializes entryTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getEntryTextField()
	{
		if (entryTextField == null)
		{
			entryTextField = new JTextField();
			entryTextField.setText(Editor.getProperties().getPitEntry());
			entryTextField.setBounds(100, 35, 100, 20);
		}
		return entryTextField;
	}
	/**
	 * This method initializes startTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getStartTextField()
	{
		if (startTextField == null)
		{
			startTextField = new JTextField();
			startTextField.setText(Editor.getProperties().getPitStart());
			startTextField.setBounds(100, 60, 100, 20);
		}
		return startTextField;
	}
	/**
	 * This method initializes endTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getEndTextField()
	{
		if (endTextField == null)
		{
			endTextField = new JTextField();
			endTextField.setText(Editor.getProperties().getPitEnd());
			endTextField.setBounds(100, 85, 100, 20);
		}
		return endTextField;
	}
	/**
	 * This method initializes exitTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getExitTextField()
	{
		if (exitTextField == null)
		{
			exitTextField = new JTextField();
			exitTextField.setText(Editor.getProperties().getPitExit());
			exitTextField.setBounds(100, 110, 100, 20);
		}
		return exitTextField;
	}
	/**
	 * This method initializes widthTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getWidthTextField()
	{
		if (widthTextField == null)
		{
			widthTextField = new JTextField();
			widthTextField.setText(Double.toString(Editor.getProperties().getPitWidth()));
			widthTextField.setBounds(100, 135, 40, 20);
		}
		return widthTextField;
	}
	/**
	 * This method initializes lengthTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getLengthTextField()
	{
		if (lengthTextField == null)
		{
			lengthTextField = new JTextField();
			lengthTextField.setText(Double.toString(Editor.getProperties().getPitLength()));
			lengthTextField.setBounds(100, 160, 40, 20);
		}
		return lengthTextField;
	}

	/**
	 *  
	 */
	public void exit()
	{
		Editor.getProperties().setPitSide((String) getJComboBox().getSelectedItem());
		Editor.getProperties().setPitEntry(this.getEntryTextField().getText());
		Editor.getProperties().setPitStart(this.getStartTextField().getText());
		Editor.getProperties().setPitEnd(this.getEndTextField().getText());
		Editor.getProperties().setPitExit(this.getExitTextField().getText());
		try
		{
			Editor.getProperties().setPitWidth(Double.parseDouble(this.getWidthTextField().getText()));
		} catch (NumberFormatException e)
		{
			Editor.getProperties().setPitWidth(Double.NaN);
		}
		try
		{
			Editor.getProperties().setPitLength(Double.parseDouble(this.getLengthTextField().getText()));
		} catch (NumberFormatException e)
		{
			Editor.getProperties().setPitLength(Double.NaN);
		}
		if(this.getGeneratePitsCheckBox().isSelected())
		{
			createPits();
		}
		Editor.getProperties().valueChanged();
	}
	/**
	 * 
	 */
	private void createPits()
	{
		Vector data = TrackData.getTrackData();
		Segment pitEntry = null;
		Segment pitStart = null;
		Segment pitEnd = null;
		Segment pitExit = null;

		Iterator it = data.iterator();
		while (it.hasNext())
		{
			Segment obj = (Segment) it.next();
			String name = obj.getName();
			if (name.equals(Editor.getProperties().getPitEntry()))
			{
				pitEntry = obj;
			}else if (name.equals(Editor.getProperties().getPitStart()))
			{
				pitStart = obj;
			}else if (name.equals(Editor.getProperties().getPitEnd()))
			{
				pitEnd = obj;
			}else if (name.equals(Editor.getProperties().getPitExit()))
			{
				pitExit = obj;
			}
		}
		SegmentSide side = null;
		if(pitEntry == null)
		{
			System.out.println("No pit entry");
			return;
		}
		if(Editor.getProperties().getPitSide().equals("left"))
		{
			side = pitEntry.getLeft();
		}else
		{
			side = pitEntry.getRight();
		}
		side.setBorderHeight(0);
		side.setBorderWidth(0);
		side.setSideEndWidth(Editor.getProperties().getPitWidth()*3);
		side.setSideSurface("road1");
		side.setBarrierHeight(1);
		side.setBarrierWidth(0.1);

		if(pitExit == null)
		{
			System.out.println("No pit exit");
			return;
		}
		if(Editor.getProperties().getPitSide().equals("left"))
		{
			side = pitExit.getLeft();
		}else
		{
			side = pitExit.getRight();
		}
		side.setBorderHeight(0);
		side.setBorderWidth(0);
		side.setSideStartWidth(Editor.getProperties().getPitWidth()*3);
		side.setSideSurface("road1");
		side.setBarrierHeight(1);
		side.setBarrierWidth(0.1);

		if(pitStart == null || pitEnd == null)
		{
			System.out.println("No pit start or end");
			return;
		}

		int start = data.indexOf(pitEntry);
		int end = data.indexOf(pitExit);

		for(int i=start+1; i<end; i++)
		{
			if(Editor.getProperties().getPitSide().equals("left"))
			{
				side = ((Segment) data.get(i)).getLeft();
			}else
			{
				side = ((Segment) data.get(i)).getRight();
			}
			side.setBorderHeight(1);
			side.setBorderWidth(0.1);
			side.setBorderStyle("wall");
			side.setSideStartWidth(Editor.getProperties().getPitWidth()*3);
			side.setSideEndWidth(Editor.getProperties().getPitWidth()*3);
			side.setSideSurface("road1-pits");
			side.setBarrierHeight(1);
			side.setBarrierWidth(0.1);
		}
	}

	/**
	 * This method initializes generatePitsCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getGeneratePitsCheckBox() {
		if (generatePitsCheckBox == null) {
			generatePitsCheckBox = new JCheckBox();
			generatePitsCheckBox.setBounds(328, 10, 20, 20);
			generatePitsCheckBox.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					if(generatePitsCheckBox.isSelected())
					{
						generatePits = true;
					}else
					{
						generatePits = false;
					}
				}
			});
		}
		return generatePitsCheckBox;
	}
 } //  @jve:decl-index=0:visual-constraint="10,10"
