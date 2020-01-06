package action;

import gui.EditorFrame;
import utils.undo.Undo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class RedoAction extends AbstractEditorAction {

  public RedoAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (view == null) return;
    Undo.redo();
    view.redrawCircuit();
  }

  @Override
  public String getName() {
    return "Redo";
  }

  @Override
  public String getImageName() {
    return "Redo24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_R;
  }

  @Override
  public String  getDescription() {
    return "Redo.";
  }

}
