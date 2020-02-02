package action;

import gui.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ShowBackgroundAction extends AbstractEditorAction {

  private boolean enabled;

  public ShowBackgroundAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    enabled = !enabled;
    JToggleButton toggleButton = editorFrame.getEdiorToolBar().getShowBackgroundButton();
    toggleButton.setSelected(enabled);
    editorFrame.enableBackground(enabled);
  }

  @Override
  public String getName() {
    return "Show background";
  }

  @Override
  public String getImageName() {
    return "Search24";
  }

  @Override
  public int getMnemonic() {
    return KeyEvent.VK_S;
  }

  @Override
  public String getDescription() {
    return "Show background image.";
  }

}
