package action;

import gui.EditorFrame;

import javax.swing.*;

/**
 * @author Adam Kubon
 */

public class ActionProvider {

  private EditorFrame editorFrame;
  private UndoAction undoAction;
  private RedoAction redoAction;
  private DeleteAction deleteAction;
  private ZoomPlusAction zoomPlusAction;
  private ZoomOneAction zoomOneAction;
  private ZoomMinusAction zoomMinusAction;
  private StraightAction straightAction;
  private RightAction rightAction;
  private LeftAction leftAction;
  private NewAction newAction;
  private OpenAction openAction;
  private SaveAction saveAction;
  private MoveAction moveAction;
  private ShowArrowsAction showArrowsAction;
  private ShowBackgroundAction showBackgroundAction;
  private HelpAction helpAction;
  private ExportAllAction allAction;
  private ExportAC3Action ac3Action;
  private PropertiesAction propertiesAction;
  private CalcDeltaAction calcDeltaAction;

  public ActionProvider(EditorFrame editorFrame) {
    this.editorFrame = editorFrame;
    undoAction = new UndoAction(editorFrame);
    redoAction = new RedoAction(editorFrame);
    deleteAction = new DeleteAction(editorFrame);
    zoomPlusAction = new ZoomPlusAction(editorFrame);
    zoomOneAction = new ZoomOneAction(editorFrame);
    zoomMinusAction = new ZoomMinusAction(editorFrame);
    straightAction = new StraightAction(editorFrame);
    rightAction = new RightAction(editorFrame);
    leftAction = new LeftAction(editorFrame);
    newAction = new NewAction(editorFrame);
    openAction = new OpenAction(editorFrame);
    saveAction = new SaveAction(editorFrame);
    moveAction = new MoveAction(editorFrame);
    showArrowsAction = new ShowArrowsAction(editorFrame);
    showBackgroundAction = new ShowBackgroundAction(editorFrame);
    helpAction = new HelpAction(editorFrame);
    allAction = new ExportAllAction(editorFrame);
    ac3Action = new ExportAC3Action(editorFrame);
    propertiesAction = new PropertiesAction(editorFrame);
    calcDeltaAction = new CalcDeltaAction(editorFrame);
  }

  public EditorFrame getEditorFrame() {
    return editorFrame;
  }

  public AbstractAction getUndoAction() {
    return undoAction.getAction();
  }

  public AbstractAction getRedoAction() {
    return redoAction.getAction();
  }

  public AbstractAction getZoomPlusAction() {
    return zoomPlusAction.getAction();
  }

  public AbstractAction getZoomOneAction() {
    return zoomOneAction.getAction();
  }

  public AbstractAction getDeleteAction() {
    return deleteAction.getAction();
  }

  public AbstractAction getZoomMinusAction() {
    return zoomMinusAction.getAction();
  }

  public AbstractAction getStraightAction() {
    return straightAction.getAction();
  }

  public AbstractAction getRightAction() {
    return rightAction.getAction();
  }

  public AbstractAction getLeftAction() {
    return leftAction.getAction();
  }

  public AbstractAction getNewAction() {
    return newAction.getAction();
  }

  public AbstractAction getOpenAction() {
    return openAction.getAction();
  }

  public AbstractAction getSaveAction() {
    return saveAction.getAction();
  }

  public AbstractAction getMoveAction() {
    return moveAction.getAction();
  }

  public AbstractAction getShowArrowsAction() {
    return showArrowsAction.getAction();
  }

  public AbstractAction getShowBackgroundAction() {
    return showBackgroundAction.getAction();
  }

  public AbstractAction getHelpAction() {
    return helpAction.getAction();
  }

  public AbstractAction getAllAction() {
    return allAction.getAction();
  }

  public AbstractAction getAc3Action() {
    return ac3Action.getAction();
  }

  public AbstractAction getPropertiesAction() {
    return propertiesAction.getAction();
  }

  public AbstractAction getCalcDeltaAction() {
    return calcDeltaAction.getAction();
  }

}
