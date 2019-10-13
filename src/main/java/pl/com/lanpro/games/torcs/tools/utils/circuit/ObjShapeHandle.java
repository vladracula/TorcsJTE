package pl.com.lanpro.games.torcs.tools.utils.circuit;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;



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

public class ObjShapeHandle extends Segment
{
	static public final int	handleSize	= 6;

	public ObjShapeHandle()
	{
		super("handle");
	}

	public void calcShape(Point2D.Double location)
	{
		if (points == null)
		{
			points = new Point2D.Double[1]; // 1 points in 2D
			for (int i = 0; i < points.length; i++)
				points[i] = new Point2D.Double();

			trPoints = new Point2D.Double[1];
			for (int i = 0; i < trPoints.length; i++)
				trPoints[i] = new Point2D.Double();
		}

		points[0].x = location.getX();
		points[0].y = location.getY();
	}

	public void draw(Graphics g, AffineTransform affineTransform)
	{
		if (points == null)
			return;

		affineTransform.transform(points, 0, trPoints, 0, points.length);

		// clip calcul
		if (g.getClipBounds().contains(trPoints[0]))
		{
			xToDraw[0] = (int) (trPoints[0].x - handleSize);
			yToDraw[0] = (int) (trPoints[0].y - handleSize);

			xToDraw[1] = (int) (trPoints[0].x + handleSize);
			yToDraw[1] = (int) (trPoints[0].y - handleSize);

			xToDraw[2] = (int) (trPoints[0].x + handleSize);
			yToDraw[2] = (int) (trPoints[0].y + handleSize);

			xToDraw[3] = (int) (trPoints[0].x - handleSize);
			yToDraw[3] = (int) (trPoints[0].y + handleSize);

			g.drawPolygon(xToDraw, yToDraw, 4);
		}
	}
}