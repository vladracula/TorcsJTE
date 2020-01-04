package action;

import gui.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class StraightAction extends AbstractEditorAction {

  public StraightAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon, desc, mnemonic, editorFrame);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    editorFrame.toggleButtonCreateStraightSegment_actionPerformed(actionEvent);
  }

}
