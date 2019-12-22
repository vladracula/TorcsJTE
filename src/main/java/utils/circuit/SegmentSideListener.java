/*
 *   SegmentSideListener.java
 *   Created on 25  2005
 *
 *    The SegmentSideListener.java is part of TrackEditor-0.6.0.
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

import java.awt.event.ActionListener;

/**
 * @author Charalampos Alexopoulos
 * @author Aadm Kubon
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SegmentSideListener extends ActionListener {

  void barrierHeightChanged();

  void barrierStyleChanged();

  void barrierSurfaceChanged();

  void barrierWidthChanged();

  void borderHeightChanged();

  void borderStyleChanged();

  void borderSurfaceChanged();

  void borderWidthChanged();

  void sideEndWidthChanged();

  void sideStartWidthChanged();

  void sideSurfaceChanged();

}
