package action;

import gui.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class ShowBackgroundAction extends AbstractEditorAction {

  public ShowBackgroundAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon, desc, mnemonic, editorFrame);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    editorFrame.toggleButtonShowBackground_actionPerformed(actionEvent);
  }

}
