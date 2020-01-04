package action;

import gui.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class OpenAction extends AbstractEditorAction {

  public OpenAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon, desc, mnemonic, editorFrame);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    editorFrame.openProject();
  }

}
