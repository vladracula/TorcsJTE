package utils.circuit;

/**
 * @author Adam Kubon
 */

import java.util.Vector;

public class ContinuousSegment {

  /**
   * making links with neighbours for new Segment
   *
   * @param data
   * @param pos
   * @param newShape
   */
  public static void makeLinkedList(Vector data, int pos, Segment newShape) {
    Segment previous = (Segment) data.get(pos);
    Segment next = getNextSegment(data, pos);
    previous.setNextShape(newShape);
    next.setPreviousShape(newShape);
    newShape.setPreviousShape(previous);
    newShape.setNextShape(next);
    next.setPreviousShape(newShape);
    ContinuousSegmentData.continuousDataValues(previous, newShape, next);
  }

  /**
   * making links between neighbours
   *
   * @param previous
   * @param next
   */
  public static void makeLinkedList(Segment previous, Segment next) {
    previous.setNextShape(next);
    next.setPreviousShape(previous);
    ContinuousSegmentData.continuousDataValues(previous, next);
  }

  /**
   * find next segment in data
   *
   * @param data
   * @param pos
   * @return
   */
  public static Segment getNextSegment(Vector data, int pos) {
    Segment next = null;
    if (pos >= data.size()) {
      next = (Segment) data.get(0);
    }
    else {
      next = (Segment) data.get(pos + 1);
    }
    return next;
  }

}
