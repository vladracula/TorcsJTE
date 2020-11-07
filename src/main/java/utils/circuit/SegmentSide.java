/*
 *   SegmentSide.java
 *   Created on 24 ��� 2005
 *
 *    The SegmentSide.java is part of TrackEditor-0.6.0.
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
package utils.circuit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * @author Charalampos Alexopoulos
 * @author Adam Kubon
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SegmentSide implements Cloneable {

  private Vector<SegmentSideListener> sideListeners = new Vector<>();
  private SegmentSide previous = null;
  private SegmentSide props = null;

  //	Side
  protected double sideStartWidth = 4.0;
  private boolean prevSideStartWidthChanged = false;
  private boolean thisSideStartWidthChanged = false;
  protected double sideEndWidth = 4.0;
  private boolean prevSideEndWidthChanged = false;
  private boolean thisSideEndWidthChanged = false;
  protected String sideSurface = "grass";
  private boolean prevSideSurfaceChanged = false;
  private boolean thisSideSurfaceChanged = false;

  //	Barrier
  protected double barrierHeight = 1.0;
  private boolean prevBarrierHeightChanged = false;
  private boolean thisBarrierHeightChanged = false;
  protected double barrierWidth = 0.1;
  private boolean prevBarrierWidthChanged = false;
  private boolean thisBarrierWidthChanged = false;
  protected String barrierSurface = "barrier";
  private boolean prevBarrierSurfaceChanged = false;
  private boolean thisBarrierSurfaceChanged = false;
  protected String barrierStyle = "curb";
  private boolean prevBarrierStyleChanged = false;
  private boolean thisBarrierStyleChanged = false;

  // Border
  protected double borderWidth = 0.5;
  private boolean prevBorderWidthChanged = false;
  private boolean thisBorderWidthChanged = false;
  protected double borderHeight = 0.05;
  private boolean prevBorderHeightChanged = false;
  private boolean thisBorderHeightChanged = false;
  protected String borderSurface = "curb-5cm-r";
  private boolean prevBorderSurfaceChanged = false;
  private boolean thisBorderSurfaceChanged = false;
  protected String borderStyle = "plan";
  private boolean prevBorderStyleChanged = false;
  private boolean thisBorderStyleChanged = false;

  /**
   *
   */
  public SegmentSide() {

  }

  /**
   * @return Returns the barrierHeight.
   */
  public double getBarrierHeight() {
    return barrierHeight;
  }

  /**
   * @param barrierHeight The barrierHeight to set.
   */
  public void setBarrierHeight(double barrierHeight) {
    if (!Double.isNaN(barrierHeight)) {
      this.barrierHeight = barrierHeight;
      barrierHeightChanged();
    }
  }

  /**
   *
   */
  private void barrierHeightChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).barrierHeightChanged();
      }
    }
  }

  /**
   * @return Returns the barrierStyle.
   */
  public String getBarrierStyle() {
    return barrierStyle;
  }

  /**
   * @param barrierStyle The barrierStyle to set.
   */
  public void setBarrierStyle(String barrierStyle) {
    this.barrierStyle = barrierStyle;
    barrierStyleChanged();
  }

  /**
   *
   */
  private void barrierStyleChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).barrierStyleChanged();
      }
    }
  }

  /**
   * @return Returns the barrierSurface.
   */
  public String getBarrierSurface() {
    return barrierSurface;
  }

  /**
   * @param barrierSurface The barrierSurface to set.
   */
  public void setBarrierSurface(String barrierSurface) {
    this.barrierSurface = barrierSurface;
    barrierSurfaceChanged();
  }

  /**
   *
   */
  private void barrierSurfaceChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).barrierSurfaceChanged();
      }
    }
  }

  /**
   * @return Returns the barrierWidth.
   */
  public double getBarrierWidth() {
    return barrierWidth;
  }

  /**
   * @param barrierWidth The barrierWidth to set.
   */
  public void setBarrierWidth(double barrierWidth) {
    if (!Double.isNaN(barrierWidth)) {
      this.barrierWidth = barrierWidth;
      barrierWidthChanged();
    }
  }

  /**
   *
   */
  private void barrierWidthChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).barrierWidthChanged();
      }
    }
  }

  /**
   * @return Returns the borderHeight.
   */
  public double getBorderHeight() {
    return borderHeight;
  }

  /**
   * @param borderHeight The borderHeight to set.
   */
  public void setBorderHeight(double borderHeight) {
    if (!Double.isNaN(borderHeight)) {
      this.borderHeight = borderHeight;
      borderHeightChanged();
    }
  }

  /**
   *
   */
  private void borderHeightChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).borderHeightChanged();
      }
    }
  }

  /**
   * @return Returns the borderStyle.
   */
  public String getBorderStyle() {
    return borderStyle;
  }

  /**
   * @param borderStyle The borderStyle to set.
   */
  public void setBorderStyle(String borderStyle) {
    this.borderStyle = borderStyle;
    borderStyleChanged();
  }

  /**
   *
   */
  private void borderStyleChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).borderStyleChanged();
      }
    }
  }

  /**
   * @return Returns the borderSurface.
   */
  public String getBorderSurface() {
    return borderSurface;
  }

  /**
   * @param borderSurface The borderSurface to set.
   */
  public void setBorderSurface(String borderSurface) {
    this.borderSurface = borderSurface;
    borderSurfaceChanged();
  }

  /**
   *
   */
  private void borderSurfaceChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).borderSurfaceChanged();
      }
    }
  }

  /**
   * @return Returns the borderWidth.
   */
  public double getBorderWidth() {
    return borderWidth;
  }

  /**
   * @param borderWidth The borderWidth to set.
   */
  public void setBorderWidth(double borderWidth) {
    if (!Double.isNaN(borderWidth)) {
      this.borderWidth = borderWidth;
      borderWidthChanged();
    }
  }

  /**
   *
   */
  private void borderWidthChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).borderWidthChanged();
      }
    }
  }

  /**
   * @return Returns the sideEndWidth.
   */
  public double getSideEndWidth() {
    return sideEndWidth;
  }

  /**
   * @param sideEndWidth The sideEndWidth to set.
   */
  public void setSideEndWidth(double sideEndWidth) {
    if (!Double.isNaN(sideEndWidth)) {
      this.sideEndWidth = sideEndWidth;
      sideEndWidthChanged();
    }
  }

  /**
   *
   */
  private void sideEndWidthChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).sideEndWidthChanged();
      }
    }
  }

  /**
   * @return Returns the sideStartWidth.
   */
  public double getSideStartWidth() {
    return sideStartWidth;
  }

  /**
   * @param sideStartWidth The sideStartWidth to set.
   */
  public void setSideStartWidth(double sideStartWidth) {
    if (!Double.isNaN(sideStartWidth)) {
      this.sideStartWidth = sideStartWidth;
      sideStartWidthChanged();
    }
  }

  /**
   *
   */
  private void sideStartWidthChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).sideStartWidthChanged();
      }
    }
  }

  /**
   * @return Returns the sideSurface.
   */
  public String getSideSurface() {
    return sideSurface;
  }

  /**
   * @param sideSurface The sideSurface to set.
   */
  public void setSideSurface(String sideSurface) {
    this.sideSurface = sideSurface;
    sideSurfaceChanged();
  }

  /**
   *
   */
  private void sideSurfaceChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((SegmentSideListener) listeners.elementAt(i)).sideSurfaceChanged();
      }
    }
  }

  public synchronized void removeSideListener(ActionListener l) {

  }

  public synchronized void addSideListener(SegmentSideListener l) {
    Vector<SegmentSideListener> v = sideListeners == null ? new Vector<>(2) : new Vector<>(sideListeners);
    if (!v.contains(l)) {
      v.addElement(l);
      sideListeners = v;
    }
  }

  public void valueChanged() {
    if (sideListeners != null) {
      Vector listeners = sideListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((ActionListener) listeners.elementAt(i)).actionPerformed(null);
      }
    }
  }

  /**
   * @return Returns the prev.
   */
  public SegmentSide getPrevious() {
    return previous;
  }

  /**
   * @param previous The prev to set.
   */
  public void setPrevious(SegmentSide previous) {
    this.previous = previous;
  }

  /**
   * @return Returns the props.
   */
  public SegmentSide getProps() {
    return props;
  }

  /**
   * @param properties The props to set.
   */
  public void setProperties(SegmentSide properties) {
    this.props = properties;
    props.addSideListener(new SegmentSideListener() {

      public void barrierHeightChanged() {
        if (!prevBarrierHeightChanged && !thisBarrierHeightChanged) {
          barrierHeight = props.barrierHeight;
        }
      }

      public void barrierStyleChanged() {
        if (!prevBarrierStyleChanged && !thisBarrierStyleChanged) {
          barrierStyle = props.barrierStyle;
        }
      }

      public void barrierSurfaceChanged() {
        if (!prevBarrierSurfaceChanged && !thisBarrierSurfaceChanged) {
          barrierSurface = props.barrierSurface;
        }
      }

      public void barrierWidthChanged() {
        if (!prevBarrierWidthChanged && !thisBarrierWidthChanged) {
          barrierWidth = props.barrierWidth;
        }
      }

      public void borderHeightChanged() {
        if (!prevBorderHeightChanged && !thisBorderHeightChanged) {
          borderHeight = props.borderHeight;
        }
      }

      public void borderStyleChanged() {
        if (!prevBorderStyleChanged && !thisBorderStyleChanged) {
          borderStyle = props.borderStyle;
        }
      }

      public void borderSurfaceChanged() {
        if (!prevBorderSurfaceChanged && !thisBorderSurfaceChanged) {
          borderSurface = props.borderSurface;
        }
      }

      public void borderWidthChanged() {
        if (!prevBorderWidthChanged && !thisBorderWidthChanged) {
          borderWidth = props.borderWidth;
        }
      }

      public void sideEndWidthChanged() {
        if (!prevSideEndWidthChanged && !thisSideEndWidthChanged) {
          sideEndWidth = props.sideEndWidth;
        }
      }

      public void sideStartWidthChanged() {
        if (!prevSideStartWidthChanged && !thisSideStartWidthChanged) {
          sideStartWidth = props.sideStartWidth;
        }
      }

      public void sideSurfaceChanged() {
        if (!prevSideSurfaceChanged && !thisSideSurfaceChanged) {
          sideSurface = props.sideSurface;
        }
      }

      public void actionPerformed(ActionEvent arg0) {

      }

    });
  }

  protected Object clone() {
    SegmentSide s = null;
    try {
      s = (SegmentSide) super.clone();
      s.barrierHeight = this.barrierHeight;
      s.barrierStyle = this.barrierStyle;
      s.barrierSurface = this.barrierSurface;
      s.barrierWidth = this.barrierWidth;
      s.borderHeight = this.borderHeight;
      s.borderStyle = this.borderStyle;
      s.borderSurface = this.borderSurface;
      s.borderWidth = this.borderWidth;
      s.sideEndWidth = this.sideEndWidth;
      s.sideStartWidth = this.sideStartWidth;
      s.sideSurface = this.sideSurface;
      s.sideListeners = new Vector<>(this.sideListeners);
    }
    catch (CloneNotSupportedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return s; // return the clone
  }

}
