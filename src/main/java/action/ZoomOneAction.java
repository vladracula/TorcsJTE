package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ZoomOneAction extends AbstractEditorAction {

  public ZoomOneAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (view == null) return;
    view.setZoomFactor(1.0);
    view.redrawCircuit();
  }

  @Override
  public String getName() {
    return "Zoom 1:1";
  }

  @Override
  public String getImageName() {
    return "Zoom24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_N;
  }

  @Override
  public String getDescription() {
    return "Zoom 1:1.";
  }

}
