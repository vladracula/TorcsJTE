package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class RightAction extends AbstractEditorAction {

  public RightAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.toggleButtonCreateRightSegment_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Add right";
  }

  @Override
  public String getImageName() {
    return "TurnRight24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_Q;
  }

  @Override
  public String getDescription() {
    return "Add a right turn segment.";
  }

}
