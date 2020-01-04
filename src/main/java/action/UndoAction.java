package action;

import gui.EditorFrame;
import utils.undo.Undo;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class UndoAction extends AbstractEditorAction {

  public UndoAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon, desc, mnemonic, editorFrame);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Undo.undo();
    view.redrawCircuit();
  }

}
