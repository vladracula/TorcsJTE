package gui.segment.listener;

import gui.view.CircuitView;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author Adam Kubon
 */

public class SegmentEditorDialogWindowListener implements WindowListener {

  private CircuitView circuitView;

  public SegmentEditorDialogWindowListener(CircuitView circuitView) {
    this.circuitView = circuitView;
  }

  @Override
  public void windowOpened(WindowEvent windowEvent) {

  }

  @Override
  public void windowClosing(WindowEvent windowEvent) {

  }

  @Override
  public void windowClosed(WindowEvent windowEvent) {
    circuitView.setSelectedShape(null);
    circuitView.redrawCircuit();
  }

  @Override
  public void windowIconified(WindowEvent windowEvent) {

  }

  @Override
  public void windowDeiconified(WindowEvent windowEvent) {

  }

  @Override
  public void windowActivated(WindowEvent windowEvent) {

  }

  @Override
  public void windowDeactivated(WindowEvent windowEvent) {

  }

}
