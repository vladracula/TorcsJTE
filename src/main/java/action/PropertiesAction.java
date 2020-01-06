package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class PropertiesAction extends AbstractEditorAction {

  public PropertiesAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.propertiesDialog();
  }

  @Override
  public String getName() {
    return "Properties";
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
    return "Properties dialog.";
  }

}
