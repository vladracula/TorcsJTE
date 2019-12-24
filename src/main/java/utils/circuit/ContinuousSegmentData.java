package utils.circuit;

/*
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
    continuousDataValues(previous, newShape, next);
  }

  /**
   * making links between neighbours
   *
   * @param previous
   * @param next
   */
  private static void makeLinkedList(Segment previous, Segment next) {
    previous.setNextShape(next);
    next.setPreviousShape(previous);
    continuousDataValues(previous, next);
  }

  /**
   * find next segment in data
   *
   * @param data
   * @param pos
   * @return
   */
  private static Segment getNextSegment(Vector data, int pos) {
    Segment next = null;
    if (pos > data.size()) {
      next = (Segment) data.get(0);
    }
    else {
      next = (Segment) data.get(pos + 1);
    }
    return next;
  }

  /**
   * synchronize start/end values between three neighbours
   *
   * @param previous
   * @param current
   * @param next
   */

  public static void continuousDataValues(Segment previous, Segment current, Segment next) {
    current.setHeightStart(previous.getHeightEnd());
    current.setHeightEnd(next.getHeightStart());

    current.setSurface(previous.getSurface());
    current.setProfil(previous.getProfil());

    continuousSideValues(previous.getLeft(), current.getLeft());
    continuousSideValues(previous.getRight(), current.getRight());
  }

  /**
   * copying side values from previous segment to current for new segment
   *
   * @param previous
   * @param current
   */
  private static void continuousSideValues(SegmentSide previous, SegmentSide current) {
    current.setBarrierHeight(previous.getBarrierHeight());
    current.setBarrierWidth(previous.getBarrierWidth());
    current.setBarrierSurface(previous.getBarrierSurface());
    current.setBarrierStyle(previous.getBarrierStyle());

    current.setSideStartWidth(previous.getSideStartWidth());
    current.setSideEndWidth(previous.getSideEndWidth());
    current.setSideSurface(previous.getSideSurface());

    current.setBorderHeight(previous.getBorderHeight());
    current.setBorderWidth(previous.getBorderWidth());
    current.setBorderSurface(previous.getBorderSurface());
    current.setBorderStyle(previous.getBorderStyle());
  }

  /**
   * synchronize values between two neighbours
   *
   * @param previous
   * @param next
   */
  private static void continuousDataValues(Segment previous, Segment next) {
    next.setHeightStart(previous.getHeightEnd());
    previous.setHeightEnd(next.getHeightStart());
  }

}
