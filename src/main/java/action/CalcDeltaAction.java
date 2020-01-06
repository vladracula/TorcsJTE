package action;

import gui.EditorFrame;

import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class CalcDeltaAction extends AbstractEditorAction {

  public CalcDeltaAction(EditorFrame editorFrame) {
    super(editorFrame);
  }

  @Override
  public void actionToPerformed(ActionEvent actionEvent) {
    editorFrame.calculateDeltas();
  }

  @Override
  public String getName() {
    return "Delta's";
  }

  @Override
  public String getImageName() {
    return "Calc24";
  }

  @Override
  public int getMnemonic() {
    return 0;
  }

  @Override
  public String getDescription() {
    return "Calculate Delta's for x,y,z and angle.";
  }

}
