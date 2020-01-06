package action;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public interface IEditorAction {

  AbstractAction getAction();

  String getName();

  String getImageName();

  int getMnemonic();

  String getDescription();

  void actionToPerformed(ActionEvent actionEvent);

}
