/*
 *   XmlReader.java
 *   Created on 24  2005
 *
 *    The XmlReader.java is part of TrackEditor-0.6.0.
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
package pl.com.lanpro.games.torcs.tools.plugin.torcs;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import pl.com.lanpro.games.torcs.tools.utils.Editor;
import pl.com.lanpro.games.torcs.tools.utils.TrackData;
import pl.com.lanpro.games.torcs.tools.utils.circuit.Curve;
import pl.com.lanpro.games.torcs.tools.utils.circuit.Segment;
import pl.com.lanpro.games.torcs.tools.utils.circuit.SegmentSide;
import pl.com.lanpro.games.torcs.tools.utils.circuit.Straight;

/**
 * @author Charalampos Alexopoulos
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class XmlReader
{
    //private static Properties properties = Properties.getInstance();
    private static List segments;

    public static void readXml(String filename)
    {
        Document doc;
        try
        {
            doc = readFromFile(filename);
            Element element = doc.getRootElement();
            setTrackData(element);
        } catch (JDOMException e)
        {
            e.printStackTrace();
        }
    }

    private static synchronized void setTrackData(Element root)
    {
        Element header = getChildWithName(root, "Header");
        setHeader(header);
        Element mainTrack = getChildWithName(root, "Main Track");
        Editor.getProperties().setTrackWidth(
                getAttrNumValue(mainTrack, "width"));
        setPits(mainTrack);
        segments = getChildWithName(mainTrack, "Track Segments").getChildren();
        setSegments();
    }

    /**
     * @param header
     */
    private static void setHeader(Element header)
    {
        String tmp = getAttrStrValue(header, "author");
        Editor.getProperties().setAuthor(tmp);
        tmp = getAttrStrValue(header, "description");
        Editor.getProperties().setDescription(tmp);
    }

    /**
     *
     */
    private static void setPits(Element track)
    {
        Element pits = getChildWithName(track, "Pits");
        String tmp = getAttrStrValue(pits, "side");
        Editor.getProperties().setPitSide(tmp);

        tmp = getAttrStrValue(pits, "entry");
        Editor.getProperties().setPitEntry(tmp);

        tmp = getAttrStrValue(pits, "start");
        Editor.getProperties().setPitStart(tmp);

        tmp = getAttrStrValue(pits, "end");
        Editor.getProperties().setPitEnd(tmp);

        tmp = getAttrStrValue(pits, "exit");
        Editor.getProperties().setPitExit(tmp);

        double val = getAttrNumValue(pits, "length");
        Editor.getProperties().setPitLength(val);

        val = getAttrNumValue(pits, "width");
        Editor.getProperties().setPitWidth(val);
    }

    private synchronized static void setSegments()
    {
        Vector trackData = new Vector();
        Iterator it;
        Segment prev = null;
        Segment shape = null;

        it = segments.iterator();
        while (it.hasNext())
        {
            Element e = (Element) it.next();
            String type = getAttrStrValue(e, "type");
            if (type.equals("str"))
            {
                shape = new Straight();
            } else
            {
                shape = new Curve(getAttrStrValue(e, "type"), null);
            }
            shape = setSegment(e, shape, prev);
            shape.setProfilStepLength(4);
            try
            {
                shape.calcShape(prev);
            } catch (Exception e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            trackData.add(shape);
            prev = shape;
        }
        TrackData.setTrackData(trackData);
    }

    private synchronized static Segment setSegment(Element seg, Segment shape,
            Segment prev)
    {
        SegmentSide left = shape.getLeft();
        SegmentSide right = shape.getRight();
        if (prev != null)
        {
            SegmentSide prevLeft = prev.getLeft();
            SegmentSide prevRight = prev.getRight();
        }

		// height values handle
        double startHeight = getAttrNumValue(seg, "z start");
        double endHeight = getAttrNumValue(seg, "z end");
		shape.setHeightStart(startHeight);
		shape.setHeightEnd(endHeight);
		// height values handle

        if (shape.getType().equals("str"))
        {
            shape.setLength(getAttrNumValue(seg, "lg"));
        } else
        {
            double arc = getAttrNumValue(seg, "arc");
            arc = (arc * Math.PI) / 180;
            ((Curve) shape).setArc(arc);
            double startRad = getAttrNumValue(seg, "radius");
            ((Curve) shape).setRadiusStart(startRad);
            double endRad = getAttrNumValue(seg, "end radius");
            if (Double.isNaN(endRad))
            {
                ((Curve) shape).setRadiusEnd(startRad);
            } else
            {
                ((Curve) shape).setRadiusEnd(endRad);
            }
        }
        String name = getSegmentName(seg);
        shape.setName(name);
        if (name.startsWith("curve "))
        {
            String tmp = name.substring(6);
            try
            {
                Integer tmpInt = new Integer(tmp);
                int i = tmpInt.intValue();
                if (i > Editor.getProperties().getCurveNameCount())
                {
                    Editor.getProperties().setCurveNameCount(i);
                }
            } catch (NumberFormatException e)
            {
                /* If what follows the word curve
                 * is not a number just ignore it */
            }
        }
        if (name.startsWith("straight "))
        {
            String tmp = name.substring(9);
            try
            {
                Integer tmpInt = new Integer(tmp);
                int i = tmpInt.intValue();
                if (i > Editor.getProperties().getStraightNameCount())
                {
                    Editor.getProperties().setStraightNameCount(i);
                }
            } catch (NumberFormatException e)
            {
                /* If what follows the word straight
                 * is not a number just ignore it */
            }
        }

        shape.setSurface(getAttrStrValue(seg, "surface"));
        setSide(seg, left, "Left");
        setSide(seg, right, "Right");

        return shape;
    }

    /**
     * @param el
     * @param left
     * @param string
     */
    private synchronized static void setSide(Element seg, SegmentSide part,
            String sPart)
    {
        String tmp;
        Element el = getChildWithName(seg, sPart + " Side");
        if (el != null)
        {
            part.setSideStartWidth(getAttrNumValue(el, "start width"));
            part.setSideEndWidth(getAttrNumValue(el, "end width"));
            tmp = getAttrStrValue(el, "surface");
            if (tmp != "")
            {
                part.setSideSurface(tmp);
            }

        }

        el = getChildWithName(seg, sPart + " Border");
        if (el != null)
        {
            part.setBorderWidth(getAttrNumValue(el, "width"));
            part.setBorderHeight(getAttrNumValue(el, "height"));
            tmp = getAttrStrValue(el, "surface");
            if (tmp != "")
            {
                part.setBorderSurface(tmp);
            }
            tmp = getAttrStrValue(el, "style");
            if (tmp != "")
            {
                part.setBorderStyle(tmp);
            }
        }

        el = getChildWithName(seg, sPart + " Barrier");
        if (el != null)
        {
            part.setBarrierWidth(getAttrNumValue(el, "width"));
            part.setBarrierHeight(getAttrNumValue(el, "height"));
            tmp = getAttrStrValue(el, "surface");
            if (tmp != "")
            {
                part.setBarrierSurface(tmp);
            }
            tmp = getAttrStrValue(el, "style");
            if (tmp != "")
            {
                part.setBarrierStyle(tmp);
            }
        }
    }

    private synchronized static Element getChildWithName(Element element,
            String name)
    {
        Element out = null;
        int count = 0;
        List all = element.getChildren();
        Iterator it;
        it = all.iterator();

        while (it.hasNext()
                && !((Element) it.next()).getAttribute("name").getValue()
                        .equals(name))
        {
            count++;
        }
        if (count < all.size())
        {
            out = (Element) all.get(count);
        }
        return out;
    }

    public synchronized static String getSegmentName(Element element)
    {
        String out = null;

        if (element == null || element.getParent() == null)
        {
            return out;
        }

        String tmp = element.getParentElement().getAttribute("name").getValue();
        if (tmp.equals("Track Segments"))
        {
            out = element.getAttribute("name").getValue();
        }
        return out;
    }

    public synchronized static String getAttrStrValue(Element element,
            String name)
    {
        String out = null;
        Element el = getChildWithName(element, name);

        try
        {
            if (el.getName().equals("attstr"))
            {
                out = el.getAttributeValue("val");
            }
        } catch (NullPointerException e)
        {

        }
        return out;
    }

    public synchronized static double getAttrNumValue(Element element,
            String name)
    {
        double out = Double.NaN;
        Element e = getChildWithName(element, name);
        if (e != null)
        {
            if (e.getName().equals("attnum"))
            {
                out = Double.parseDouble(e.getAttributeValue("val"));
            }
        }
        return out;
    }

    /**
     * @return Returns the segments.
     */
    public List getSegments()
    {
        return segments;
    }

    private static Document readFromFile(String fname) throws JDOMException
    {
        Document d = null;
        SAXBuilder sxb = new SAXBuilder(false);

        try
        {
            sxb.setEntityResolver(new NoOpEntityResolver());
            //sxb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
            // false);
            d = sxb.build(new File(fname));
        } catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

}