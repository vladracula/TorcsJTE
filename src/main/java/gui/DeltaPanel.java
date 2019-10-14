/*
 *   Trackgen.java
 *   Created on Aug 26, 2004
 *
 *    The Trackgen.java is part of TrackEditor-0.6.2.
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
package gui;

import utils.Editor;
import utils.TrackData;
import utils.circuit.Curve;
import utils.circuit.Segment;
import utils.circuit.Straight;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * @author Charalampos Alexopoulos
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DeltaPanel extends JDialog implements Runnable {
  public static Vector args = new Vector();
  private EditorFrame parent;
  //private Properties properties = Properties.getInstance();
  Thread ac3d;

  private JPanel jPanel = null;
  private JLabel nameLabel = null;
  private JLabel authorLabel = null;
  private JLabel fileNameLabel = null;
  private JLabel lengthLabel = null;
  private JLabel widthLabel = null;
  private JLabel xSizeLabel = null;
  private JLabel ySizeLabel = null;
  private JPanel jPanel1 = null;
  private JLabel trackgenLabel = null;
  private JLabel waitLabel = null;

  private JButton calcButton = null;
  private JLabel deltaXLabel = null;
  private JLabel deltaYLabel = null;
  private JLabel deltaAngLabel = null;

  private JButton adjustButton = null;
  private JButton lengthButton = null;
  private JTextField jTextField = null;

  private boolean finish = true;

  private double length = 0;
  private double dX = 0;
  private double dY = 0;
  private double angle = 0;

  /**
   *
   */
  public DeltaPanel(EditorFrame frame, String title, boolean modal) {
    super(frame, title, modal);
    parent = frame;
    initialize();
  }


  /**
   * This method initializes this
   *
   * @return void
   */
  private void initialize() {
    this.setContentPane(getJPanel());
    this.setTitle("Trackgen");
    this.setSize(379, 415);

  }

  public synchronized void run() {
    //while(!finish);
    finish = false;
    waitLabel.setText("Calculating track data. Please wait...");
    String category = " -c " + Editor.getProperties().getCategory();
    String name = " -n " + Editor.getProperties().getTrackName();
    String args = " -z" + category + name;

    //System.out.println(args);

    try {
      String ls_str;
      String tmp = "";

      Process ls_proc = Runtime.getRuntime().exec("trackgen" + args);
      // get its output (your input) stream
      BufferedReader ls_in = new BufferedReader(new InputStreamReader(
          ls_proc.getInputStream()));

      try {
        while ((ls_str = ls_in.readLine()) != null) {
          if (ls_str.indexOf("=") != -1) {
            tmp = ls_str.substring(0, ls_str.indexOf("="));
            if (tmp.equals("name      ")) {
              nameLabel.setText(ls_str);
            }
            else if (tmp.equals("author    ")) {
              this.authorLabel.setText(ls_str);
            }
            else if (tmp.equals("filename  ")) {
              this.fileNameLabel.setText(ls_str);
            }
            else if (tmp.equals("length    ")) {
              String len = ls_str;
              this.lengthLabel.setText(len);
              len = len.substring(len.indexOf("=") + 2);
              length = Double.valueOf(len).doubleValue();
            }
            else if (tmp.equals("width     ")) {
              this.widthLabel.setText(ls_str);
            }
            else if (tmp.equals("XSize     ")) {
              this.xSizeLabel.setText(ls_str);
            }
            else if (tmp.equals("YSize     ")) {
              this.ySizeLabel.setText(ls_str);
            }
            else if (tmp.equals("Delta X   ")) {
              String len = ls_str;
              len = len.substring(len.indexOf("=") + 2);
              dX = Double.valueOf(len).doubleValue();
              this.deltaXLabel.setText(ls_str);
            }
            else if (tmp.equals("Delta Y   ")) {
              String len = ls_str;
              len = len.substring(len.indexOf("=") + 2);
              dY = Double.valueOf(len).doubleValue();
              this.deltaYLabel.setText(ls_str);
            }
            else if (tmp.equals("Delta Ang ")) {
              String ang = ls_str;
              ang = ang.substring(ang.indexOf("(") + 1, ang.indexOf(")"));
              angle = Double.valueOf(ang).doubleValue();
              this.deltaAngLabel.setText(ls_str);
            }
          }
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    catch (IOException e1) {
      System.err.println(e1);
    }
    this.waitLabel.setText("Calculation finished");
    finish = true;
    notifyAll();
  }

  /**
   * @return
   */
  private static String getArgs() {
    String tmp = "";

    for (int i = 0; i < args.size(); i++) {
      tmp += args.get(i);
    }
    return tmp;
  }

  /**
   * @param vector
   */
  public static void setArgs(Vector vector) {
    args = vector;
  }

  /**
   * This method initializes jPanel
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJPanel() {
    if (jPanel == null) {
      trackgenLabel = new JLabel();
      waitLabel = new JLabel();
      jPanel = new JPanel();
      jPanel.setLayout(null);
      trackgenLabel.setBounds(103, 19, 113, 20);
      trackgenLabel.setText("Track data");
      trackgenLabel.setFont(new java.awt.Font("Dialog",
          java.awt.Font.BOLD, 18));
      waitLabel.setBounds(15, 355, 290, 25);
      waitLabel.setText("");
      jPanel.add(trackgenLabel, null);
      jPanel.add(waitLabel, null);

      jPanel.add(getJPanel1(), null);
      jPanel.add(getCalcButton(), null);
      jPanel.add(getAdjustButton(), null);
      jPanel.add(getLengthButton(), null);
      jPanel.add(getJTextField(), null);
    }
    return jPanel;
  }

  /**
   * This method initializes jPanel1
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJPanel1() {
    if (jPanel1 == null) {
      deltaAngLabel = new JLabel();
      deltaYLabel = new JLabel();
      deltaXLabel = new JLabel();
      jPanel1 = new JPanel();
      jPanel1.setLayout(null);
      jPanel1.setBounds(29, 60, 310, 255);
      jPanel1
          .setBorder(javax.swing.BorderFactory
              .createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
      nameLabel = new JLabel();
      authorLabel = new JLabel();
      fileNameLabel = new JLabel();
      lengthLabel = new JLabel();
      widthLabel = new JLabel();
      xSizeLabel = new JLabel();
      ySizeLabel = new JLabel();
      nameLabel.setText("");
      nameLabel.setBounds(5, 5, 300, 20);
      nameLabel.setBackground(new java.awt.Color(255, 255, 153));
      authorLabel.setText("");
      authorLabel.setBounds(5, 30, 300, 20);
      authorLabel.setBackground(new java.awt.Color(255, 255, 153));
      fileNameLabel.setText("");
      fileNameLabel.setBounds(5, 55, 300, 20);
      lengthLabel.setText("");
      lengthLabel.setBounds(5, 80, 300, 20);
      widthLabel.setText("");
      widthLabel.setBounds(5, 105, 300, 20);
      xSizeLabel.setText("");
      xSizeLabel.setBounds(5, 130, 300, 20);
      ySizeLabel.setText("");
      ySizeLabel.setBounds(5, 155, 300, 20);
      deltaXLabel.setBounds(5, 180, 300, 20);
      deltaXLabel.setText("");
      deltaYLabel.setBounds(5, 205, 300, 20);
      deltaYLabel.setText("");
      deltaAngLabel.setBounds(5, 230, 300, 20);
      deltaAngLabel.setText("");
      jPanel1.setBackground(new java.awt.Color(255, 255, 153));
      jPanel1.setForeground(new java.awt.Color(51, 51, 255));
      jPanel1.add(ySizeLabel, null);
      jPanel1.add(xSizeLabel, null);
      jPanel1.add(widthLabel, null);
      jPanel1.add(lengthLabel, null);
      jPanel1.add(fileNameLabel, null);
      jPanel1.add(authorLabel, null);
      jPanel1.add(nameLabel, null);
      jPanel1.add(deltaXLabel, null);
      jPanel1.add(deltaYLabel, null);
      jPanel1.add(deltaAngLabel, null);
    }
    return jPanel1;
  }

  /**
   * This method initializes calcButton
   *
   * @return javax.swing.JButton
   */
  private JButton getCalcButton() {
    if (calcButton == null) {
      calcButton = new JButton();
      calcButton.setBounds(12, 320, 60, 25);
      calcButton.setText("Calc");
      calcButton.setToolTipText("Calculates the track data");
      calcButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          getCalcButton().setEnabled(false);
          getAdjustButton().setEnabled(false);
          getLengthButton().setEnabled(false);
          calculate();
        }
      });
    }
    return calcButton;
  }

  /**
   * This method initializes adjustButton
   *
   * @return javax.swing.JButton
   */
  private JButton getAdjustButton() {
    if (adjustButton == null) {
      adjustButton = new JButton();
      adjustButton.setBounds(82, 320, 65, 25);
      adjustButton.setText("Auto");
      adjustButton.setToolTipText("Auto adjust the start/finish segments of the track");
      adjustButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          getCalcButton().setEnabled(false);
          getAdjustButton().setEnabled(false);
          getLengthButton().setEnabled(false);
          int options = JOptionPane.OK_CANCEL_OPTION;
          String msg = "This option will change your track permantly.\n"
              + "The result will be writed on xml file and no undo has implemented yet.\n\n"
              + "If you decide to continue keep in mind that the adjust method is\n"
              + "experimental and not propertly tested. As far as i know doing the job but\n"
              + "need to use the adjust button two or three times";
          int out = JOptionPane.showConfirmDialog(null, msg, "Warning", options);
          if (out == JOptionPane.CANCEL_OPTION) {
            return;
          }
          adjust();
        }
      });
    }
    return adjustButton;
  }

  /**
   * This method initializes lengthButton
   *
   * @return javax.swing.JButton
   */
  private JButton getLengthButton() {
    if (lengthButton == null) {
      lengthButton = new JButton();
      lengthButton.setBounds(169, 320, 85, 25);
      lengthButton.setText("Length");
      lengthButton.setToolTipText("Adjust the length of the track to match the value in the text field on the rigth");
      lengthButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          getCalcButton().setEnabled(false);
          getAdjustButton().setEnabled(false);
          getLengthButton().setEnabled(false);

          int options = JOptionPane.OK_CANCEL_OPTION;
          String msg = "This option will change your track permantly.\n"
              + "The result will be writed on xml file and no undo has implemented yet.\n\n"
              + "If you decide to continue keep in mind that the adjust method is\n"
              + "experimental and not propertly tested.";
          int out = JOptionPane.showConfirmDialog(null, msg, "Warning", options);
          if (out == JOptionPane.CANCEL_OPTION) {
            return;
          }

          adjustLength();
        }
      });
    }
    return lengthButton;
  }

  /**
   * This method initializes jTextField
   *
   * @return javax.swing.JTextField
   */
  private JTextField getJTextField() {
    if (jTextField == null) {
      jTextField = new JTextField();
      jTextField.setBounds(266, 320, 85, 25);
      jTextField.setToolTipText("The length value used from Length button");
    }
    return jTextField;
  }

  /**
   *
   */
  protected void startTrackgen() {

    parent.torcsPlugin.exportTrack();
    ac3d = new Thread(this);
    ac3d.start();
  }

  private synchronized void calculate() {
    finish = false;
    startTrackgen();
    waitTrackgen();

    this.getCalcButton().setEnabled(true);
    this.getAdjustButton().setEnabled(true);
    this.getLengthButton().setEnabled(true);
  }

  private synchronized void adjustLength() {
    double newLength = 0;
    double co = 0;

    finish = false;
    startTrackgen();
    waitTrackgen();
    try {
      String tmp = getJTextField().getText();
      newLength = Double.valueOf(tmp).doubleValue();
    }
    catch (Exception e) {
      System.out.println("Its not a number in length field");
      return;
    }
    co = newLength / length;
    Vector track = TrackData.getTrackData();
    int size = track.size();

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      try {
        if (obj.getType().equals("str")) {
          obj.setLength(obj.getLength() * co);
        }
        else {
          Curve curve = (Curve) obj;
          curve.setRadiusStart(curve.getRadiusStart() * co);
          curve.setRadiusEnd(curve.getRadiusEnd() * co);
        }
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    finish = false;
    startTrackgen();
    waitTrackgen();
    parent.refresh();
    this.getCalcButton().setEnabled(true);
    this.getAdjustButton().setEnabled(true);
    this.getLengthButton().setEnabled(true);
  }

  private synchronized void adjust() {
    finish = false;
    startTrackgen();
    waitTrackgen();
    double co = 360 / (360 + angle);

    Vector track = TrackData.getTrackData();
    int size = track.size();

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      try {
        if (!obj.getType().equals("str")) {
          Curve curve = (Curve) obj;
          curve.setArc(curve.getArc() * co);
        }
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    finish = false;
    startTrackgen();
    waitTrackgen();

    double totalY = 0;

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      try {
        if (obj.getType().equals("str")) {
          Straight str = (Straight) obj;
          double x = str.getDx();
          double y = str.getDy();
          if (y > x) {
            totalY += y;
          }
        }
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    co = (totalY + dY) / totalY;

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      try {
        if (obj.getType().equals("str")) {
          Straight str = (Straight) obj;
          double x = str.getDx();
          double y = str.getDy();
          if (y > x) {
            str.setLength(str.getLength() * co);
          }
        }
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    finish = false;
    startTrackgen();
    waitTrackgen();

    double totalX = 0;

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      try {
        if (obj.getType().equals("str")) {
          Straight str = (Straight) obj;
          double x = str.getDx();
          double y = str.getDy();
          if (y == 0) {
            totalX += x;
          }
        }
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    co = (totalX + dX) / totalX;

    for (int i = 0; i < size; i++) {
      Segment obj = (Segment) track.get(i);
      try {
        if (obj.getType().equals("str")) {
          Straight str = (Straight) obj;
          double x = str.getDx();
          double y = str.getDy();
          if (y == 0) {
            str.setLength(str.getLength() * co);
          }
        }
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    finish = false;
    startTrackgen();
    waitTrackgen();
    parent.refresh();

    this.getCalcButton().setEnabled(true);
    this.getAdjustButton().setEnabled(true);
    this.getLengthButton().setEnabled(true);
  }

  private synchronized void waitTrackgen() {
    while (!finish) {
      try {
        wait();
        //System.out.println("Pass");
      }
      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
} //  @jve:decl-index=0:visual-constraint="10,10"
