package action;

import gui.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class HelpAction extends AbstractEditorAction {

  public HelpAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    int type = JOptionPane.PLAIN_MESSAGE;
    String title = getClass().getPackage().getImplementationTitle();
    String version = getClass().getPackage().getImplementationVersion();
    String msg = title + " - " + version + "\n\n"
        + "Copyright Charalampos Alexopoulos\n"
        + "Copyright Adam Kubon";
    JOptionPane.showMessageDialog(editorFrame, msg, "About", type);
  }

  @Override
  public String getName() {
    return "Help";
  }

  @Override
  public String getImageName() {
    return "Help24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Help.";
  }

}
