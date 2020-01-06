package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class NewAction extends AbstractEditorAction {

  public NewAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.newProjectDialog();
  }

  @Override
  public String getName() {
    return "New";
  }

  @Override
  public String getImageName() {
    return "New24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "New circuit.";
  }

}
