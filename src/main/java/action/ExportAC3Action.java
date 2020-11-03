package action;

import gui.EditorFrame;
import utils.TrackData;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ExportAC3Action extends AbstractEditorAction {

  public ExportAC3Action(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (TrackData.getTrackData() == null) return;
    editorFrame.exportAc3d();

  }

  @Override
  public String getName() {
    return "AC3";
  }

  @Override
  public String getImageName() {
    return null;
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Create AC3 file.";
  }

}
