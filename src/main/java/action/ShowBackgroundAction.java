package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ShowBackgroundAction extends AbstractEditorAction {

  public ShowBackgroundAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.toggleButtonShowBackground_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Show background";
  }

  @Override
  public String getImageName() {
    return "Search24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Show background image.";
  }

}
