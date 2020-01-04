package action;

import gui.EditorFrame;
import gui.view.CircuitView;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author Adam Kubon
 */

public class ActionProvider {

  private EditorFrame editorFrame;
  private CircuitView circuitView;
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
    this.circuitView = editorFrame.getView();
    ClassLoader cldr = this.getClass().getClassLoader();
    undoAction = new UndoAction("Undo", createNavigationIcon("Undo24", cldr), "Undo.", KeyEvent.VK_Z, editorFrame);
    redoAction = new RedoAction("Redo", createNavigationIcon("Redo24", cldr), "Redo.", KeyEvent.VK_R, editorFrame);
    deleteAction = new DeleteAction("Delete", createNavigationIcon("Cut24", cldr), "Delete.", KeyEvent.VK_L, editorFrame);
    zoomPlusAction = new ZoomPlusAction("Zoom in", createNavigationIcon("ZoomIn24", cldr), "Zoom in.", KeyEvent.VK_M, editorFrame);
    zoomOneAction = new ZoomOneAction("Zoom 1:1", createNavigationIcon("Zoom24", cldr), "Zoom 1:1.", KeyEvent.VK_N, editorFrame);
    zoomMinusAction = new ZoomMinusAction("Zoom out", createNavigationIcon("ZoomOut24", cldr), "Zoom out.", KeyEvent.VK_O, editorFrame);
    straightAction = new StraightAction("Add straight", createNavigationIcon("Straight24", cldr), "Add a straight segment.", KeyEvent.VK_P, editorFrame);
    rightAction = new RightAction("Add right", createNavigationIcon("TurnRight24", cldr), "Add a right turn segment.", KeyEvent.VK_Q, editorFrame);
    leftAction = new LeftAction("Add left", createNavigationIcon("TurnLeft24", cldr), "Add a left turn segment.", KeyEvent.VK_S, editorFrame);
    newAction = new NewAction("New", createNavigationIcon("New24", cldr), "New circuit.", KeyEvent.VK_S, editorFrame);
    openAction = new OpenAction("Open", createNavigationIcon("Open24", cldr), "Open existing circuit.", KeyEvent.VK_S, editorFrame);
    saveAction = new SaveAction("Save", createNavigationIcon("Save24", cldr), "Save the circuit.", KeyEvent.VK_S, editorFrame);
    moveAction = new MoveAction("Move", createNavigationIcon("Export24", cldr), "Move.", KeyEvent.VK_S, editorFrame);
    showArrowsAction = new ShowArrowsAction("Show arrows", createNavigationIcon("FindAgain24", cldr), "Show arrows.", KeyEvent.VK_S, editorFrame);
    showBackgroundAction = new ShowBackgroundAction("Show background", createNavigationIcon("Search24", cldr), "Show background image.", KeyEvent.VK_S, editorFrame);
    helpAction = new HelpAction("Help", createNavigationIcon("Help24", cldr), "Help.", KeyEvent.VK_S, editorFrame);
    /** ******************************************************************* */
    allAction = new ExportAllAction("All", null, "Export both XML file and AC3 file.", KeyEvent.VK_S, editorFrame);
    ac3Action = new ExportAC3Action("AC3", null, "Create AC3 file.", KeyEvent.VK_S, editorFrame);
    propertiesAction = new PropertiesAction("Properties", null, "Properties dialog.", KeyEvent.VK_S, editorFrame);
    calcDeltaAction = new CalcDeltaAction("Delta's", createNavigationIcon("Calc24", cldr), "Calculate Delta's for x,y,z and angle.", KeyEvent.VK_S, editorFrame);
  }

  /**
   * Returns an ImageIcon, or null if the path was invalid.
   */
  protected static ImageIcon createNavigationIcon(String imageName, ClassLoader cldr) {
    String imgLocation = "data/icons/" + imageName + ".gif";
    ImageIcon image = new ImageIcon(cldr.getResource(imgLocation));
    if (image != null) return image;
    else System.err.println("Resource not found: " + imgLocation);
    return null;
  }

  public UndoAction getUndoAction() {
    return undoAction;
  }

  public RedoAction getRedoAction() {
    return redoAction;
  }

  public ZoomPlusAction getZoomPlusAction() {
    return zoomPlusAction;
  }

  public ZoomOneAction getZoomOneAction() {
    return zoomOneAction;
  }

  public EditorFrame getEditorFrame() {
    return editorFrame;
  }

  public DeleteAction getDeleteAction() {
    return deleteAction;
  }

  public ZoomMinusAction getZoomMinusAction() {
    return zoomMinusAction;
  }

  public StraightAction getStraightAction() {
    return straightAction;
  }

  public RightAction getRightAction() {
    return rightAction;
  }

  public LeftAction getLeftAction() {
    return leftAction;
  }

  public NewAction getNewAction() {
    return newAction;
  }

  public OpenAction getOpenAction() {
    return openAction;
  }

  public SaveAction getSaveAction() {
    return saveAction;
  }

  public MoveAction getMoveAction() {
    return moveAction;
  }

  public ShowArrowsAction getShowArrowsAction() {
    return showArrowsAction;
  }

  public ShowBackgroundAction getShowBackgroundAction() {
    return showBackgroundAction;
  }

  public HelpAction getHelpAction() {
    return helpAction;
  }

  public ExportAllAction getAllAction() {
    return allAction;
  }

  public ExportAC3Action getAc3Action() {
    return ac3Action;
  }

  public PropertiesAction getPropertiesAction() {
    return propertiesAction;
  }

  public CalcDeltaAction getCalcDeltaAction() {
    return calcDeltaAction;
  }

}
