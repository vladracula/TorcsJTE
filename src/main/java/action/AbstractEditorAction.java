package action;

import gui.EditorFrame;
import gui.view.CircuitView;

import javax.swing.*;

/**
 * @author Adam Kubon
 */

public abstract class AbstractEditorAction extends AbstractAction {

  protected EditorFrame editorFrame;
  protected CircuitView view;

  public AbstractEditorAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon);
    this.editorFrame = editorFrame;
    this.view = editorFrame.getView();
    putValue(SHORT_DESCRIPTION, desc);
    putValue(MNEMONIC_KEY, mnemonic);
  }

}
