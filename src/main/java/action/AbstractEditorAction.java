package action;

import gui.EditorFrame;
import gui.view.CircuitView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;

/**
 * @author Adam Kubon
 */

public abstract class AbstractEditorAction implements IEditorAction {

  private AbstractAction action;
  protected EditorFrame editorFrame;
  protected CircuitView view;

  public AbstractEditorAction(EditorFrame editorFrame) {
    super();
    this.editorFrame = editorFrame;
    this.view = editorFrame.getView();
    ClassLoader classLoader = this.getClass().getClassLoader();
    action = new InnerActionImpl(getName(), createNavigationIcon(getImageName(), classLoader));
    putDescription(getDescription());
    putMnemonic(getMnemonic());
  }

  @Override
  public AbstractAction getAction() {
    return action;
  }

  class InnerActionImpl extends AbstractAction{

    private InnerActionImpl(String name, Icon icon) {
      super(name, icon);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      actionToPerformed(actionEvent);
    }

  }

  /**
   * Returns an ImageIcon, or null if the path was invalid.
   */
  private static ImageIcon createNavigationIcon(String imageName, ClassLoader classLoader) {
    if (imageName == null) return null;
    String imgLocation = "data/icons/" + imageName + ".gif";
    try {
      URL url = classLoader.getResource(imgLocation);
      return new ImageIcon(url);
    }
    catch (NullPointerException npe) {
      System.err.println("Resource not found: " + imgLocation);
    }
    return null;
  }

  private void putMnemonic(Object keyEvent) {
    action.putValue(MNEMONIC_KEY, keyEvent);
  }

  private void putDescription(Object description) {
    action.putValue(SHORT_DESCRIPTION, description);
  }

}
