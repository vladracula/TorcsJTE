/*
 *   Curve.java
 *   Created on 9  2005
 *
 *    The Curve.java is part of TrackEditor-0.6.0.
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

import miscel.EPMath;
import utils.Editor;

import java.awt.geom.Point2D;

/**
 * @author Patrice Espie , Charalampos Alexopoulos
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class Curve extends Segment implements Cloneable {
  // miscel datas
//	static public final int	arc			= distFromCircuitStart + 1;
//	static public final int	radiusStart		= arc + 1;
//	static public final int	endRadius	= radiusStart + 1;
  protected double arc;
  protected double radiusStart;
  protected double radiusEnd;

  int nbSteps;
  public Point2D.Double center = new Point2D.Double();

  public Curve() {
    this("rgt", null);
  }

  public Curve(String type, Segment prev) {
    super(type);
    this.previousShape = prev;
  }

  public Segment copyTo(Segment _shape) throws CloneNotSupportedException {
    super.copyTo(_shape);

    Curve shape = (Curve) _shape;

    shape.nbSteps = nbSteps;

    return (shape);
  }

  public void calcShape(Segment previousShape) throws Exception {
    double currentX = Editor.getProperties().getCurrentX();
    double currentY = Editor.getProperties().getCurrentY();
//		double	currentZ	= Editor.getProperties().getCurrentZ();
    double currentA = Editor.getProperties().getCurrentA();
    double currentBanking = Editor.getProperties().getCurrentBanking();
//		double	leftBorderWidth = Editor.getProperties().getLeftBorderWidth();
//		double	rightBorderWidth = Editor.getProperties().getRightBorderWidth();
//		double	leftSideWidth = Editor.getProperties().getLeftSideWidth();
//		double	rightSideWidth = Editor.getProperties().getRightSideWidth();
    double showArrows = Editor.getProperties().getShowArrows();
    double trackStartDist = Editor.getProperties().getTrackStartDist();
//		double	profileStepLength = Editor.getProperties().getProfileStepLength();
    double trackWidth = Editor.getProperties().getTrackWidth();

    /**
     *
     * New code
     *
     *
     */

    // calc turn length
    length = arc * (radiusStart + radiusEnd) / 2;
    nbSteps = (int) (length / profilStepLength + 0.5) + 1;

    trackStartDist += length;

    double deltaRadiusStep;
    double stepLength = length / nbSteps;

    if (radiusEnd != radiusStart) {
      if (nbSteps != 1) {
        deltaRadiusStep = (radiusEnd - radiusStart) / (nbSteps - 1);
        double tmpAngle = 0;
        double tmpRadius = radiusStart;
        for (int curStep = 0; curStep < nbSteps; curStep++) {
          tmpAngle += stepLength / tmpRadius;
          tmpRadius += deltaRadiusStep;
        }
        stepLength *= arc / tmpAngle;
      }
      else
        deltaRadiusStep = (radiusEnd - radiusStart) / nbSteps;
    }
    else {
      deltaRadiusStep = 0;
    }

    if (points == null || points.length != 4 * (5 + (showArrows > 0.0 ? 1 : 0)) * nbSteps) {
      points = new Point2D.Double[4 * (5 + (showArrows > 0.0 ? 1 : 0)) * nbSteps];

      for (int i = 0; i < points.length; i++)
        points[i] = new Point2D.Double();

      trPoints = new Point2D.Double[4 * (5 + (showArrows > 0.0 ? 1 : 0)) * nbSteps];
      for (int i = 0; i < trPoints.length; i++)
        trPoints[i] = new Point2D.Double();
    }

    boolean dir = type.equals("rgt");

    double curRadius = radiusStart;

    double leftSideDeltaStep = (left.sideEndWidth - left.sideStartWidth) / nbSteps;
    double rightSideDeltaStep = (right.sideEndWidth - right.sideStartWidth) / nbSteps;

    int currentSubSeg = 0;

    for (int nStep = 0; nStep < nbSteps; nStep++) {
      double cosTrans = Math.cos(currentA + Math.PI / 2);
      double sinTrans = Math.sin(currentA + Math.PI / 2);

      double thisStepArc;
      double xCenter;
      double yCenter;

      if (dir) {
        xCenter = currentX - cosTrans * curRadius;
        yCenter = currentY - sinTrans * curRadius;
        thisStepArc = -stepLength / curRadius;
      }
      else {
        xCenter = currentX + cosTrans * curRadius;
        yCenter = currentY + sinTrans * curRadius;
        thisStepArc = stepLength / curRadius;
      }

      if (nStep == 0)
        //                center.setLocation( xCenter, -yCenter );
        center.setLocation(xCenter, yCenter);

      double cos = Math.cos(thisStepArc);
      double sin = Math.sin(thisStepArc);

      // update radiusStart
      curRadius += deltaRadiusStep;

      double cosTransLeft = cosTrans;
      double sinTransLeft = sinTrans;

      // track
      points[currentSubSeg + 0].x = currentX + cosTransLeft * trackWidth / 2;
      points[currentSubSeg + 0].y = currentY + sinTransLeft * trackWidth / 2;

      double x = points[currentSubSeg + 0].x - xCenter;
      double y = points[currentSubSeg + 0].y - yCenter;
      points[currentSubSeg + 1].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 1].y = y * cos + x * sin + yCenter;

      points[currentSubSeg + 3].x = currentX - cosTransLeft * trackWidth / 2;
      points[currentSubSeg + 3].y = currentY - sinTransLeft * trackWidth / 2;

      x = points[currentSubSeg + 3].x - xCenter;
      y = points[currentSubSeg + 3].y - yCenter;
      points[currentSubSeg + 2].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 2].y = y * cos + x * sin + yCenter;

      currentSubSeg += 4;

      // left border

      points[currentSubSeg + 0].x = currentX + cosTransLeft * (trackWidth / 2 + left.borderWidth);
      points[currentSubSeg + 0].y = currentY + sinTransLeft * (trackWidth / 2 + left.borderWidth);

      x = points[currentSubSeg + 0].x - xCenter;
      y = points[currentSubSeg + 0].y - yCenter;
      points[currentSubSeg + 1].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 1].y = y * cos + x * sin + yCenter;

      points[currentSubSeg + 3].x = currentX + cosTransLeft * trackWidth / 2;
      points[currentSubSeg + 3].y = currentY + sinTransLeft * trackWidth / 2;

      x = points[currentSubSeg + 3].x - xCenter;
      y = points[currentSubSeg + 3].y - yCenter;
      points[currentSubSeg + 2].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 2].y = y * cos + x * sin + yCenter;

      currentSubSeg += 4;

      // left side

      points[currentSubSeg + 0].x = currentX
          + cosTransLeft
          * (trackWidth / 2 + left.borderWidth + left.sideStartWidth + leftSideDeltaStep
          * nStep);
      points[currentSubSeg + 0].y = currentY
          + sinTransLeft
          * (trackWidth / 2 + left.borderWidth + left.sideStartWidth + leftSideDeltaStep
          * nStep);

      x = points[currentSubSeg + 0].x + cosTransLeft * leftSideDeltaStep - xCenter;
      y = points[currentSubSeg + 0].y + sinTransLeft * leftSideDeltaStep - yCenter;
      points[currentSubSeg + 1].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 1].y = y * cos + x * sin + yCenter;

      points[currentSubSeg + 3].x = currentX + cosTransLeft * (trackWidth / 2 + left.borderWidth);
      points[currentSubSeg + 3].y = currentY + sinTransLeft * (trackWidth / 2 + left.borderWidth);

      x = points[currentSubSeg + 3].x - xCenter;
      y = points[currentSubSeg + 3].y - yCenter;
      points[currentSubSeg + 2].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 2].y = y * cos + x * sin + yCenter;

      currentSubSeg += 4;

      // right border

      points[currentSubSeg + 0].x = currentX - cosTransLeft * (trackWidth / 2 + right.borderWidth);
      points[currentSubSeg + 0].y = currentY - sinTransLeft * (trackWidth / 2 + right.borderWidth);

      x = points[currentSubSeg + 0].x - xCenter;
      y = points[currentSubSeg + 0].y - yCenter;
      points[currentSubSeg + 1].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 1].y = y * cos + x * sin + yCenter;

      points[currentSubSeg + 3].x = currentX - cosTransLeft * trackWidth / 2;
      points[currentSubSeg + 3].y = currentY - sinTransLeft * trackWidth / 2;

      x = points[currentSubSeg + 3].x - xCenter;
      y = points[currentSubSeg + 3].y - yCenter;
      points[currentSubSeg + 2].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 2].y = y * cos + x * sin + yCenter;

      currentSubSeg += 4;

      // right side

      points[currentSubSeg + 0].x = currentX
          - cosTransLeft
          * (trackWidth / 2 + right.borderWidth + right.sideStartWidth + rightSideDeltaStep
          * nStep);
      points[currentSubSeg + 0].y = currentY
          - sinTransLeft
          * (trackWidth / 2 + right.borderWidth + right.sideStartWidth + rightSideDeltaStep
          * nStep);

      x = points[currentSubSeg + 0].x - cosTransLeft * rightSideDeltaStep - xCenter;
      y = points[currentSubSeg + 0].y - sinTransLeft * rightSideDeltaStep - yCenter;
      points[currentSubSeg + 1].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 1].y = y * cos + x * sin + yCenter;

      points[currentSubSeg + 3].x = currentX - cosTransLeft * (trackWidth / 2 + right.borderWidth);
      points[currentSubSeg + 3].y = currentY - sinTransLeft * (trackWidth / 2 + right.borderWidth);

      x = points[currentSubSeg + 3].x - xCenter;
      y = points[currentSubSeg + 3].y - yCenter;
      points[currentSubSeg + 2].x = x * cos - y * sin + xCenter;
      points[currentSubSeg + 2].y = y * cos + x * sin + yCenter;

      currentSubSeg += 4;

      if (showArrows > 0.0) {
        // arrow
        points[currentSubSeg + 0].x = currentX + cosTransLeft * trackWidth / 2;
        points[currentSubSeg + 0].y = currentY + sinTransLeft * trackWidth / 2;

        x = points[currentSubSeg + 0].x - xCenter - (cosTransLeft * trackWidth / 2) * 0.99999;
        y = points[currentSubSeg + 0].y - yCenter - (sinTransLeft * trackWidth / 2) * 0.99999;
        points[currentSubSeg + 1].x = x * cos - y * sin + xCenter;
        points[currentSubSeg + 1].y = y * cos + x * sin + yCenter;

        points[currentSubSeg + 3].x = currentX - cosTransLeft * trackWidth / 2;
        points[currentSubSeg + 3].y = currentY - sinTransLeft * trackWidth / 2;

        x = points[currentSubSeg + 3].x - xCenter + (cosTransLeft * trackWidth / 2) * 0.99999;
        y = points[currentSubSeg + 3].y - yCenter + (sinTransLeft * trackWidth / 2) * 0.99999;
        points[currentSubSeg + 2].x = x * cos - y * sin + xCenter;
        points[currentSubSeg + 2].y = y * cos + x * sin + yCenter;

        currentSubSeg += 4;
      }

      // move track center

      x = currentX - xCenter;
      y = currentY - yCenter;
      currentX = x * cos - y * sin + xCenter;
      currentY = y * cos + x * sin + yCenter;

      // inc track angle
      currentA += thisStepArc;
    }
    /*
     * // return along the X axis for ( int i = 0; i < points.length; i++ )
     * points[ i ].y = -points[ i ].y;
     */
    //        endTrackCenter.setLocation( datas[ 0 ], -datas[ 1 ] );
    endTrackCenter.setLocation(currentX, currentY);
    endTrackAlpha = currentA % EPMath.PI_MUL_2;
    while (endTrackAlpha < -Math.PI)
      endTrackAlpha += EPMath.PI_MUL_2;
    while (endTrackAlpha > Math.PI)
      endTrackAlpha -= EPMath.PI_MUL_2;

    Editor.getProperties().setCurrentA(currentA);
    Editor.getProperties().setCurrentX(currentX);
    Editor.getProperties().setCurrentY(currentY);
  }


//	public void draw(Graphics g, AffineTransform affineTransform)
//	{
//		calcShape();
//	}

  public void drag(Point2D.Double dragDelta) {
  }

  /**
   * @return Returns the arc.
   */
  public double getArc() {
    return arc;
  }

  /**
   * @param arc The arc to set.
   */
  public void setArc(double arc) {
    this.arc = arc;
  }

  /**
   * @return Returns the radiusEnd.
   */
  public double getRadiusEnd() {
    return radiusEnd;
  }

  /**
   * @param radiusEnd The radiusEnd to set.
   */
  public void setRadiusEnd(double radiusEnd) {
    this.radiusEnd = radiusEnd;
  }

  /**
   * @return Returns the radiusStart.
   */
  public double getRadiusStart() {
    return radiusStart;
  }

  /**
   * @param radiusStart The radiusStart to set.
   */
  public void setRadiusStart(double radiusStart) {
    this.radiusStart = radiusStart;
  }

  public Object clone() {
    Curve s;
    s = (Curve) super.clone();
    s.arc = this.arc;
    s.radiusStart = this.radiusStart;
    s.radiusEnd = this.radiusEnd;

    return s; // return the clone
  }
}