package gui.view.listener;

import gui.view.CircuitView;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author Adam Kubon
 */

public class CircuitViewMouseWheelListener implements MouseWheelListener {

  private final CircuitView circuitView;

  public CircuitViewMouseWheelListener(CircuitView circuitView) {
    this.circuitView = circuitView;
  }

  /**
   * input events management
   */
  @Override
  public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
    int n = mouseWheelEvent.getWheelRotation();
    for (int i = 0; i < Math.abs(n); i++) {
      circuitView.setOrigin(mouseWheelEvent.getPoint());
      if (n < 0) circuitView.incZoomFactor();
      else circuitView.decZoomFactor();
    }
  }

}
