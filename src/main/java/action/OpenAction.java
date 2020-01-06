package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class OpenAction extends AbstractEditorAction {

  public OpenAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.openProject();
  }

  @Override
  public String getName() {
    return "Open";
  }

  @Override
  public String getImageName() {
    return "Open24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Open existing circuit.";
  }

}
