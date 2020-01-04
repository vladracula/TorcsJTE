package utils.circuit;

/**
 * @author Adam Kubon
 */

public class ContinuousSegmentData {

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
    current.setSideEndWidth(previous.getSideStartWidth());
    current.setSideSurface(previous.getSideSurface());

    current.setBorderHeight(previous.getBorderHeight());
    current.setBorderWidth(previous.getBorderWidth());
    current.setBorderSurface(previous.getBorderSurface());
    current.setBorderStyle(previous.getBorderStyle());
  }

  /**
   * synchronize start/end data values between two neighbours
   *
   * @param previous
   * @param next
   */
  public static void continuousDataValues(Segment previous, Segment next) {
    next.setHeightStart(previous.getHeightEnd());
    previous.setHeightEnd(next.getHeightStart());
  }

}
