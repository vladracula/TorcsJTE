package action;

import gui.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class HelpAction extends AbstractEditorAction {

  public HelpAction(String name, Icon icon, String desc, Integer mnemonic, EditorFrame editorFrame) {
    super(name, icon, desc, mnemonic, editorFrame);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    int type = JOptionPane.PLAIN_MESSAGE;
    String title = getClass().getPackage().getImplementationTitle();
    String version = getClass().getPackage().getImplementationVersion();
    String msg = title + " - " + version + "\n\n"
        + "Copyright Charalampos Alexopoulos\n"
        + "Copyright Adam Kubon";
    JOptionPane.showMessageDialog(null, msg, "About", type);
  }

}
