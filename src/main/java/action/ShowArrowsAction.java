package action;

import gui.EditorFrame;
import utils.TrackData;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ShowArrowsAction extends AbstractEditorAction {

  public ShowArrowsAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (TrackData.getTrackData() == null) return;
    editorFrame.toggleButtonShowArrow_actionPerformed(actionEvent);
  }

  @Override
  public String getName() {
    return "Show arrows";
  }

  @Override
  public String getImageName() {
    return "FindAgain24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Show arrows.";
  }

}
