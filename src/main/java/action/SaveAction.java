package action;

import gui.EditorFrame;
import utils.TrackData;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class SaveAction extends AbstractEditorAction {

  public SaveAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    if (TrackData.getTrackData() == null) return;
    editorFrame.saveProject();
  }

  @Override
  public String getName() {
    return "Save";
  }

  @Override
  public String getImageName() {
    return "Save24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Save the circuit.";
  }

}
