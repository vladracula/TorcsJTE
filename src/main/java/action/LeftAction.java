package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class LeftAction extends AbstractEditorAction {

  public LeftAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.toggleButtonCreateLeftSegment_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Add left";
  }

  @Override
  public String getImageName() {
    return "TurnLeft24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Add a left turn segment.";
  }

}
