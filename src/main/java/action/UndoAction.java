package action;

import gui.EditorFrame;
import utils.TrackData;
import utils.undo.Undo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class UndoAction extends AbstractEditorAction {

  public UndoAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent e) {
    if (TrackData.getTrackData() == null) return;
    if (view == null) return;
    Undo.undo();
    view.redrawCircuit();
  }

  @Override
  public String getName() {
    return "Undo";
  }

  @Override
  public String getImageName() {
    return "Undo24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_Z;
  }

  @Override
  public String getDescription() {
    return "Undo.";
  }

}
