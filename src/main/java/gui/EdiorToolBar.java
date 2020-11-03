package gui;

import action.ActionProvider;
import gui.view.CircuitView;
import gui.view.enumerator.CircuitState;

import javax.swing.*;

/**
 * @author Adam Kubon
 */

public class EdiorToolBar extends JToolBar {

  private ActionProvider actionProvider;
  private CircuitView view;
  // UI
  private JButton buttonZoomPlus = null;
  private JButton buttonZoomMinus = null;
  private JButton buttonZoomOne = null;
  private JToggleButton toggleButtonCreateStraightSegment = null;
  private JToggleButton toggleButtonCreateLeftSegment = null;
  private JToggleButton toggleButtonCreateRightSegment = null;
  private JToggleButton toggleButtonDelete = null;
  private JButton undoButton = null;
  private JButton redoButton = null;

  private JToolBar jToolBar = null;
  private JButton saveButton = null;
  private JButton openButton = null;
  private JButton helpButton = null;
  private JToggleButton moveButton = null;
  private JToggleButton showArrowsButton = null;
  private JToggleButton showBackgroundButton = null;
  private JButton newButton = null;
  private JButton calculateDeltaButton = null;

  public EdiorToolBar(EditorFrame editorFrame) {
    super();
    this.actionProvider = editorFrame.getActionProvider();
    this.view = editorFrame.getView();
  }

  /**
   * This method initializes jToolBar
   *
   * @return javax.swing.JToolBar
   */
  public JToolBar getJToolBar() {
    if (jToolBar == null) {
      jToolBar = new JToolBar();

      jToolBar.add(getNewButton());
      jToolBar.add(getOpenButton());
      jToolBar.add(getSaveButton());
      jToolBar.add(getUndoButton());
      jToolBar.add(getRedoButton());
      jToolBar.add(getToggleButtonDelete());
      jToolBar.add(getButtonZoomPlus());
      jToolBar.add(getButtonZoomOne());
      jToolBar.add(getButtonZoomMinus());
      jToolBar.add(getToggleButtonCreateStraightSegment());
      jToolBar.add(getToggleButtonCreateRightSegment());
      jToolBar.add(getToggleButtonCreateLeftSegment());
      jToolBar.add(getMoveButton());
      jToolBar.add(getShowArrowsButton());
      jToolBar.add(getShowBackgroundButton());
      jToolBar.add(getCalculateDeltaButton());
      jToolBar.add(getHelpButton());
    }
    return jToolBar;
  }

  /**
   * This method initializes undoButton
   *
   * @return javax.swing.JButton
   */
  private JButton getUndoButton() {
    if (undoButton == null) {
      undoButton = new JButton();
      undoButton.setAction(actionProvider.getUndoAction());
      if (undoButton.getIcon() != null) {
        undoButton.setText("");
      }
    }
    return undoButton;
  }

  /**
   * This method initializes redoButton
   *
   * @return javax.swing.JButton
   */
  private JButton getRedoButton() {
    if (redoButton == null) {
      redoButton = new JButton();
      redoButton.setAction(actionProvider.getRedoAction());
      if (redoButton.getIcon() != null) {
        redoButton.setText("");
      }
    }
    return redoButton;
  }

  /**
   * This method initializes deleteButton
   *
   * @return javax.swing.JToggleButton
   */
  public JToggleButton getToggleButtonDelete() {
    if (toggleButtonDelete == null) {
      toggleButtonDelete = new JToggleButton();
      toggleButtonDelete.setAction(actionProvider.getDeleteAction());
      if (toggleButtonDelete.getIcon() != null) {
        toggleButtonDelete.setText("");
      }
    }
    return toggleButtonDelete;
  }

  /**
   * This method initializes buttonZoomPlus
   *
   * @return javax.swing.JButton
   */
  private JButton getButtonZoomPlus() {
    if (buttonZoomPlus == null) {
      buttonZoomPlus = new JButton();
      buttonZoomPlus.setAction(actionProvider.getZoomPlusAction());
      if (buttonZoomPlus.getIcon() != null) {
        buttonZoomPlus.setText("");
      }
    }
    return buttonZoomPlus;
  }

  /**
   * This method initializes buttonZoomPlus
   *
   * @return javax.swing.JButton
   */
  private JButton getButtonZoomOne() {
    if (buttonZoomOne == null) {
      buttonZoomOne = new JButton();
      buttonZoomOne.setAction(actionProvider.getZoomOneAction());
      if (buttonZoomOne.getIcon() != null) {
        buttonZoomOne.setText("");
      }
    }
    return buttonZoomOne;
  }

  private JButton getButtonZoomMinus() {
    if (buttonZoomMinus == null) {
      buttonZoomMinus = new JButton();
      buttonZoomMinus.setAction(actionProvider.getZoomMinusAction());
      if (buttonZoomMinus.getIcon() != null) {
        buttonZoomMinus.setText("");
      }
    }
    return buttonZoomMinus;
  }

  private JToggleButton getToggleButtonCreateStraightSegment() {
    if (toggleButtonCreateStraightSegment == null) {
      toggleButtonCreateStraightSegment = new JToggleButton();
      toggleButtonCreateStraightSegment.setAction(actionProvider.getStraightAction());
      if (toggleButtonCreateStraightSegment.getIcon() != null) {
        toggleButtonCreateStraightSegment.setText("");
      }
    }
    return toggleButtonCreateStraightSegment;
  }

  private JToggleButton getToggleButtonCreateRightSegment() {
    if (toggleButtonCreateRightSegment == null) {
      toggleButtonCreateRightSegment = new JToggleButton();
      toggleButtonCreateRightSegment.setAction(actionProvider.getRightAction());
      if (toggleButtonCreateRightSegment.getIcon() != null) {
        toggleButtonCreateRightSegment.setText("");
      }
    }
    return toggleButtonCreateRightSegment;
  }

  private JToggleButton getToggleButtonCreateLeftSegment() {
    if (toggleButtonCreateLeftSegment == null) {
      toggleButtonCreateLeftSegment = new JToggleButton();
      toggleButtonCreateLeftSegment.setAction(actionProvider.getLeftAction());
      if (toggleButtonCreateLeftSegment.getIcon() != null) {
        toggleButtonCreateLeftSegment.setText("");
      }
    }
    return toggleButtonCreateLeftSegment;
  }

  /**
   * This method initializes moveButton
   *
   * @return javax.swing.JButton
   */
  public JToggleButton getMoveButton() {
    if (moveButton == null) {
      moveButton = new JToggleButton();
      moveButton.setAction(actionProvider.getMoveAction());
      if (moveButton.getIcon() != null) {
        moveButton.setText("");
      }
    }
    return moveButton;
  }

  /**
   * This method initializes showArrowsButton
   *
   * @return javax.swing.JButton
   */
  public JToggleButton getShowArrowsButton() {
    if (showArrowsButton == null) {
      showArrowsButton = new JToggleButton();
      showArrowsButton.setAction(actionProvider.getShowArrowsAction());
      if (showArrowsButton.getIcon() != null) {
        showArrowsButton.setText("");
      }
    }
    return showArrowsButton;
  }

  /**
   * This method initializes showBackgroundButton
   *
   * @return javax.swing.JButton
   */
  public JToggleButton getShowBackgroundButton() {
    if (showBackgroundButton == null) {
      showBackgroundButton = new JToggleButton();
      showBackgroundButton.setAction(actionProvider.getShowBackgroundAction());
      if (showBackgroundButton.getIcon() != null) {
        showBackgroundButton.setText("");
      }
    }
    return showBackgroundButton;
  }

  /**
   * This method initializes newButton
   *
   * @return javax.swing.JButton
   */
  private JButton getNewButton() {
    if (newButton == null) {
      newButton = new JButton();
      newButton.setAction(actionProvider.getNewAction());
      if (newButton.getIcon() != null) {
        newButton.setText("");
      }
    }
    return newButton;
  }

  /**
   * This method initializes saveButton
   *
   * @return javax.swing.JButton
   */
  private JButton getSaveButton() {
    if (saveButton == null) {
      saveButton = new JButton();
      saveButton.setAction(actionProvider.getSaveAction());
      if (saveButton.getIcon() != null) {
        saveButton.setText("");
      }
    }
    return saveButton;
  }

  /**
   * This method initializes openButton
   *
   * @return javax.swing.JButton
   */
  private JButton getOpenButton() {
    if (openButton == null) {
      openButton = new JButton();
      openButton.setAction(actionProvider.getOpenAction());
      if (openButton.getIcon() != null) {
        openButton.setText("");
      }
    }
    return openButton;
  }

  /**
   * This method initializes helpButton
   *
   * @return javax.swing.JButton
   */
  private JButton getHelpButton() {
    if (helpButton == null) {
      helpButton = new JButton();
      helpButton.setAction(actionProvider.getHelpAction());
      if (helpButton.getIcon() != null) {
        helpButton.setText("");
      }
    }
    return helpButton;
  }

  /**
   * This method initializes calculateDeltaButton
   *
   * @return javax.swing.JButton
   */
  private JButton getCalculateDeltaButton() {
    if (calculateDeltaButton == null) {
      calculateDeltaButton = new JButton();
      calculateDeltaButton.setAction(actionProvider.getCalcDeltaAction());
      if (calculateDeltaButton.getIcon() != null) {
        calculateDeltaButton.setText("");
      }
    }
    return calculateDeltaButton;
  }

  void checkButtons(CircuitState state) {
    JToggleButton button = null;
    switch (state) {
      case CREATE_STRAIGHT:
        button = toggleButtonCreateStraightSegment;
        break;
      case CREATE_LEFT_SEGMENT:
        button = toggleButtonCreateLeftSegment;
        break;
      case CREATE_RIGHT_SEGMENT:
        button = toggleButtonCreateRightSegment;
        break;
      case MOVE_SEGMENTS:
        button = getMoveButton();
        break;
      case DELETE:
        button = toggleButtonDelete;
        break;
      case SHOW_BGRD_START_POSITION:
        break;
    }

    if (button.isSelected()) {
      view.setState(state);

      if (toggleButtonCreateStraightSegment != button)
        toggleButtonCreateStraightSegment.setSelected(false);
      if (toggleButtonCreateLeftSegment != button)
        toggleButtonCreateLeftSegment.setSelected(false);
      if (toggleButtonCreateRightSegment != button)
        toggleButtonCreateRightSegment.setSelected(false);
      if (getMoveButton() != button)
        getMoveButton().setSelected(false);
      if (toggleButtonDelete != button)
        toggleButtonDelete.setSelected(false);
    }
    else {
      view.setState(CircuitState.NONE);
    }
  }

}
