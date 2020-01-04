package action;

import gui.EditorFrame;
import utils.undo.Undo;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class RedoAction extends AbstractEditorAction {

  public RedoAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon, desc, mnemonic, editorFrame);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    Undo.redo();
    view.redrawCircuit();
  }

}
