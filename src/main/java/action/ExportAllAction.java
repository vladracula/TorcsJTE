package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ExportAllAction extends AbstractEditorAction {

  public ExportAllAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    System.out.println("TODO : I have to write some code for this.");
  }

  @Override
  public String getName() {
    return "All";
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
    return "Export both XML file and AC3 file.";
  }

}
