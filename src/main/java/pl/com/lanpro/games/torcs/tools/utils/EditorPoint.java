/*
 *   EditorPoint.java
 *   Created on Aug 26, 2004
 *
 *    The EditorPoint.java is part of TrackEditor-0.6.2.
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
package pl.com.lanpro.games.torcs.tools.utils;

/**
 * @author Charalampos Alexopoulos
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditorPoint
{
    double x;
    double y;

    /**
     * 
     */
    public EditorPoint()
    {
        this(0, 0);
    }

    /**
     * @param x
     * @param y
     */
    public EditorPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return Returns the x.
     */
    public double getX()
    {
        return x;
    }
    /**
     * @param x The x to set.
     */
    public void setX(double x)
    {
        this.x = x;
    }
    /**
     * @return Returns the y.
     */
    public double getY()
    {
        return y;
    }
    /**
     * @param y The y to set.
     */
    public void setY(double y)
    {
        this.y = y;
    }
    
    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setLocation(EditorPoint point)
    {
        this.x = point.getX();
        this.y = point.getY();
    }
}
