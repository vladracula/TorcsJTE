package action;

import gui.EditorFrame;
import utils.TrackData;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class MoveAction extends AbstractEditorAction {

  public MoveAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (TrackData.getTrackData() == null) return;
    editorFrame.toggleButtonMoveSegments_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Move";
  }

  @Override
  public String getImageName() {
    return "Export24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Move.";
  }

}
