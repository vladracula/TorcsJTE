package action;

import gui.EditorFrame;
import utils.TrackData;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class DeleteAction extends AbstractEditorAction {

  public DeleteAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (TrackData.getTrackData() == null) return;
    editorFrame.toggleButtonDelete_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Delete";
  }

  @Override
  public String getImageName() {
    return "Cut24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_L;
  }

  @Override
  public String getDescription() {
    return "Delete.";
  }

}
