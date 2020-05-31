package action;

import gui.EditorFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

/**
 * @author Adam Kubon
 */

public class ZoomMinusAction extends AbstractEditorAction {

  public ZoomMinusAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (view == null) return;
    Point2D screenCenter = view.getScreenCenter();
    view.setOrigin(new Point((int) screenCenter.getX(), (int) screenCenter.getY()));
    view.decZoomFactor();
    view.redrawCircuit();
  }

  @Override
  public String getName() {
    return "Zoom out";
  }

  @Override
  public String getImageName() {
    return "ZoomOut24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_O;
  }

  @Override
  public String getDescription() {
    return "Zoom out.";
  }

}
