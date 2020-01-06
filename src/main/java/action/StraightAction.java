package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class StraightAction extends AbstractEditorAction {

  public StraightAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.toggleButtonCreateStraightSegment_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Add straight";
  }

  @Override
  public String getImageName() {
    return "Straight24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_P;
  }

  @Override
  public String getDescription() {
    return "Add a straight segment.";
  }

}
