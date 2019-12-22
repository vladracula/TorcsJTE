package utils.circuit;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * <p>
 * Titre : Torcs Tune
 * </p>
 * <p>
 * Description : Torcs tuning
 * </p>
 * <p>
 * Copyright : Copyright (c) 2002 Patrice Espie
 * </p>
 * <p>
 * Socit :
 * </p>
 *
 * @author Patrice Espie
 * @version 0.1a
 */

public class ObjShapeTerrain extends Segment {

  public ObjShapeTerrain() {
    super("terrain");
  }

  public void calcShape(double datas[], Rectangle2D.Double boundingRectangle) {
    if (points == null) {
      points = new Point2D.Double[4]; // 4 points in 2D
      for (int i = 0; i < points.length; i++)
        points[i] = new Point2D.Double();

      trPoints = new Point2D.Double[4];
      for (int i = 0; i < trPoints.length; i++)
        trPoints[i] = new Point2D.Double();
    }

    points[0].x = boundingRectangle.getX();
    points[0].y = boundingRectangle.getY();

    points[1].x = boundingRectangle.getX() + boundingRectangle.getWidth();
    points[1].y = boundingRectangle.getY();

    points[2].x = boundingRectangle.getX() + boundingRectangle.getWidth();
    points[2].y = boundingRectangle.getY() + boundingRectangle.getHeight();

    points[3].x = boundingRectangle.getX();
    points[3].y = boundingRectangle.getY() + boundingRectangle.getHeight();
  }

}
