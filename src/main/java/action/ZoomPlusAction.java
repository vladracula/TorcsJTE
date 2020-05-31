package action;

import gui.EditorFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

/**
 * @author Adam Kubon
 */

public class ZoomPlusAction extends AbstractEditorAction {

  public ZoomPlusAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (view == null) return;
    Point2D screenCenter = view.getScreenCenter();
    view.setOrigin(new Point((int) screenCenter.getX(), (int) screenCenter.getY()));
    view.incZoomFactor();
    view.redrawCircuit();
  }

  @Override
  public String getName() {
    return "Zoom in";
  }

  @Override
  public String getImageName() {
    return "ZoomIn24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_M;
  }

  @Override
  public String getDescription() {
    return "Zoom in.";
  }

}
