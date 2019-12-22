/*
 *   SegmentSlider.java
 *   Created on 28  2005
 *
 *    The SegmentSlider.java is part of TrackEditor-0.3.1.
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
package gui.segment;

import utils.SegmentSliderLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Vector;

/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SegmentSlider extends JPanel {

  private Vector<SliderListener> sliderListeners = new Vector<>();
  private JLabel sectionLabel = null;
  private JTextField textField = null;
  private JLabel attLabel = null;
  private JSlider slider = null;
  private JCheckBox checkBox = null;
  private boolean enabled = true;

  private String section;
  private String attr;
  private double min;
  private double max;
  private double extent;
  private double tickSpacing;
  private double realToTextCoeff;
  private String method;
  private Object parent;
  private double value;
  private DecimalFormatSymbols otherSymbols;
  private DecimalFormat decimalFormat;
  private String format;

  /**
   *
   */
  public SegmentSlider() {
    initialize();
    this.parent = this.getParent();
    new SliderLink();
  }

  /**
   *
   */
  private void initialize() {
    attLabel = new JLabel();
    sectionLabel = new JLabel();
    this.setLayout(new SegmentSliderLayout());
    this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    this.setSize(50, 250);
    sectionLabel.setText("Section");
    sectionLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
    sectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    sectionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    attLabel.setText("Attr");
    attLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
    attLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
    attLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
    this.add(sectionLabel, null);
    this.add(getCheckBox(), null);
    this.add(attLabel, null);
    this.add(getTextField(), null);
    this.add(getSlider(), null);
    this.format = "#.##";
    otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
    otherSymbols.setDecimalSeparator('.');
    decimalFormat = new DecimalFormat(format, otherSymbols);
  }

  /**
   * This method initializes textField
   *
   * @return javax.swing.JTextField
   */
  public JTextField getTextField() {
    if (textField == null) {
      textField = new JTextField();
      textField.setHorizontalAlignment(JTextField.RIGHT);
    }
    return textField;
  }

  /**
   * This method initializes slider
   *
   * @return javax.swing.JSlider
   */
  public JSlider getSlider() {
    if (slider == null) {
      slider = new JSlider();
      slider.setOrientation(javax.swing.JSlider.VERTICAL);
    }
    return slider;
  }

  /**
   * This method initializes checkBox
   *
   * @return javax.swing.JCheckBox
   */
  public JCheckBox getCheckBox() {
    if (checkBox == null) {
      checkBox = new JCheckBox();
    }
    return checkBox;
  }

  /**
   * @return Returns the attr.
   */
  public String getAttr() {
    return attr;
  }

  /**
   * @param attr The attr to set.
   */
  public void setAttr(String attr) {
    this.attr = attr;
    this.attLabel.setText(attr);
  }

  /**
   * @return Returns the section.
   */
  public String getSection() {
    return section;
  }

  /**
   * @param section The section to set.
   */
  public void setSection(String section) {
    this.section = section;
    this.sectionLabel.setText(section);
  }

  /**
   * @return Returns the attLabel.
   */
  public JLabel getAttLabel() {
    return attLabel;
  }

  /**
   * @param attLabel The attLabel to set.
   */
  public void setAttLabel(JLabel attLabel) {
    this.attLabel = attLabel;
  }

  /**
   * @return Returns the extent.
   */
  public double getExtent() {
    return extent;
  }

  /**
   * @param extent The extent to set.
   */
  public void setExtent(double extent) {
    this.extent = extent;
  }

  /**
   * @return Returns the max.
   */
  public double getMax() {
    return max;
  }

  /**
   * @param max The max to set.
   */
  public void setMax(double max) {
    getSlider().setMaximum((int) max);
    this.max = max;
  }

  /**
   * @return Returns the min.
   */
  public double getMin() {
    return min;
  }

  /**
   * @param min The min to set.
   */
  public void setMin(double min) {
    getSlider().setMinimum((int) min);
    this.min = min;
  }

  /**
   * @return Returns the realToTextCoeff.
   */
  public double getRealToTextCoeff() {
    return realToTextCoeff;
  }

  /**
   * @param realToTextCoeff The realToTextCoeff to set.
   */
  public void setRealToTextCoeff(double realToTextCoeff) {
    this.realToTextCoeff = realToTextCoeff;
  }

  /**
   * @return Returns the tickSpacing.
   */
  public double getTickSpacing() {
    return tickSpacing;
  }

  /**
   * @param tickSpacing The tickSpacing to set.
   */
  public void setTickSpacing(double val) {
    this.tickSpacing = val;
  }

  public void setEnabled(boolean value) {
    this.enabled = value;
    this.getCheckBox().setEnabled(value);
    this.getTextField().setEnabled(value);
    this.getSlider().setEnabled(value);
    this.sectionLabel.setEnabled(value);
    this.attLabel.setEnabled(value);
  }

  /**
   * @return Returns the method.
   */
  public String getMethod() {
    return method;
  }

  /**
   * @param method The method to set.
   */
  public void setMethod(String method) {
    this.method = method;
  }

  /**
   * @return Returns the enabled.
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * @return Returns the value.
   */
  public double getValue() {
    return value / this.realToTextCoeff;
  }

  /**
   * @param value The value to set.
   */
  public void setValue(double val) {
    this.value = val * this.realToTextCoeff;
    getTextField().setText(decimalFormat.format(value));
//    getTextField().setText(Double.toString(value));
    getSlider().setValue((int) value);

  }

  private void setValueInternal(double val) {
    this.value = val;
    getSlider().setValue((int) value);
    double textValue = 0;
    if (!getTextField().getText().equals("")) {
      textValue = Double.parseDouble(getTextField().getText());
    }
    if (textValue != value) {
      getTextField().setText(value + "");
    }
    valueChanged();
  }

  public synchronized void removeSliderListener(ActionListener l) {

  }

  public synchronized void addSliderListener(SliderListener l) {
    Vector<SliderListener> v = sliderListeners == null ? new Vector<>(2) : new Vector<>(sliderListeners);
    if (!v.contains(l)) {
      v.addElement(l);
      sliderListeners = v;
    }
  }

  public void valueChanged() {
    if (sliderListeners != null) {
      Vector listeners = sliderListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SliderListener) listeners.elementAt(i)).sliderChanged(this);
      }
    }
  }

  /**
   * ** Inner class SliderLink******
   */
  class SliderLink {
    double multCoeff;

    SliderLink() {
      multCoeff = 1;// / tickSpacing;

      if (checkBox != null)
        checkBox.addChangeListener(new ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            checkBoxChanged();
          }
        });
      getTextField().setEnabled(true);
      getTextField().addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
          textFieldChanged();
        }
      });

      getSlider().addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
          sliderChanged();
        }
      });
    }

    public void checkBoxChanged() {

    }

    public void sliderChanged() {



      try {
        if (!getTextField().getText().equals("")) {
          double tmp1 = getSlider().getValue();
          double tmp2 = Double.parseDouble(getTextField().getText());
          if (tmp1 != tmp2 && !(tmp2 >= tmp1 && tmp2 < (tmp1 + 1))) {
            setValueInternal(getSlider().getValue());
          }
        }

      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    /**
     *
     */
    protected void textFieldChanged() {
      if (!getTextField().getText().equals("")) {
        try {
          double tmp = Double.parseDouble(getTextField().getText()) * multCoeff;
          setValueInternal(tmp);
        }
        catch (NumberFormatException nfe) {
          System.err.println("Bad double value: " + nfe.getMessage());
        }
      }
    }

  }

  public void setFormat(String format) {
    this.format = format;
  }
}
