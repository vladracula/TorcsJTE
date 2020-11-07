package gui;

import action.ActionProvider;
import plugin.Plugin;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Adam Kubon
 */

public class EditorMenu extends JMenuBar {

  private final ActionProvider actionProvider;
  private final Plugin torcsPlugin;

  // UI
  private JMenu menuFile = new JMenu();
  private JMenuItem itemSaveCircuit = null;
  private JMenuItem itemCloseCircuit = new JMenuItem();
  private JMenu viewMenu = new JMenu();
  private JMenuItem menuItemAddBackground = new JMenuItem();
  private JMenuItem menuItemShoStartPoint = new JMenuItem();

  private JMenuItem newMenuItem = null;
  private JMenuItem openMenuItem = null;
  private JMenuItem zoomPlusMenuItem = null;
  private JMenuItem zoomMinusMenuItem = null;
  private JMenuItem zoomOneMenuItem = null;
  private JMenu segmentMenu = null;
  private JMenu helpMenu = null;
  private JMenuItem aboutMenuItem = null;
  private JMenuItem addStraightMenuItem = null;
  private JMenuItem addRightMenuItem = null;
  private JMenuItem addLeftMenuItem = null;
  private JMenuItem moveMenuItem = null;
  private JMenuItem deleteMenuItem = null;
  private JMenuItem showArrowsMenuItem = null;
  private JMenuItem showBackgroundMenuItem = null;

  private JMenu editMenu = null;
  private JMenuItem undoMenuItem = null;
  private JMenuItem redoMenuItem = null;
  private JMenu importMenu = null;
  private JMenu exportMenu = null;
  private JMenuItem exportAllMenuItem = null;
  private JMenuItem exportXmlMenuItem = null;
  private JMenuItem exportAC3MenuItem = null;
  private JMenuItem propertiesMenuItem = null;

  public EditorMenu(EditorFrame editorFrame) {
    super();
    this.actionProvider = editorFrame.getActionProvider();
    this.torcsPlugin = editorFrame.getTorcsPlugin();
    menuFile.setText("File");
    itemCloseCircuit.setText("Exit");
    itemCloseCircuit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editorFrame.itemCloseCircuit_actionPerformed(e);
      }
    });
    viewMenu.setText("View");
    try {
      Thread.sleep(100);
    }
    catch (InterruptedException ex) {
      System.err.println("thread interrupted: " + ex.getMessage());
    }
    menuItemAddBackground.setText("Add ...");
    menuItemAddBackground.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editorFrame.menuItemAddBackground_actionPerformed(e);
      }
    });
    menuItemShoStartPoint.setText("Show start point");
    menuItemShoStartPoint.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editorFrame.menuItemShowStartPoint_actionPerformed(e);
      }
    });

    add(menuFile);
    add(getEditMenu());
    add(viewMenu);
    add(getSegmentMenu());
    add(getHelpMenu());
    menuFile.add(getNewMenuItem());
    menuFile.add(getOpenMenuItem());
    menuFile.add(getItemSaveCircuit());
    menuFile.addSeparator();
    menuFile.add(getImportMenu());
    menuFile.add(getExportMenu());
    menuFile.addSeparator();
    menuFile.add(getPropertiesMenuItem());
    menuFile.addSeparator();
    menuFile.add(itemCloseCircuit);

    viewMenu.add(getZoomPlusMenuItem());
    viewMenu.add(getZoomMinusMenuItem());
    viewMenu.add(getZoomOneMenuItem());
    viewMenu.addSeparator();
    viewMenu.add(getShowArrowsMenuItem());
    viewMenu.add(getShowBackgroundMenuItem());
    viewMenu.add(menuItemShoStartPoint);
    viewMenu.add(menuItemAddBackground);
  }

  /**
   * This method initializes newMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getNewMenuItem() {
    if (newMenuItem == null) {
      newMenuItem = new JMenuItem();
      newMenuItem.setAction(actionProvider.getNewAction());
      newMenuItem.setIcon(null);
    }
    return newMenuItem;
  }

  /**
   * This method initializes openMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getOpenMenuItem() {
    if (openMenuItem == null) {
      openMenuItem = new JMenuItem();
      openMenuItem.setAction(actionProvider.getOpenAction());
      openMenuItem.setIcon(null);
    }
    return openMenuItem;
  }

  /**
   * This method initializes itemSaveCircuit
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getItemSaveCircuit() {
    if (itemSaveCircuit == null) {
      itemSaveCircuit = new JMenuItem();
      itemSaveCircuit.setAction(actionProvider.getSaveAction());
      itemSaveCircuit.setIcon(null);
    }
    return itemSaveCircuit;
  }

  /**
   * This method initializes zoomPlusMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getZoomPlusMenuItem() {
    if (zoomPlusMenuItem == null) {
      zoomPlusMenuItem = new JMenuItem();
      zoomPlusMenuItem.setAction(actionProvider.getZoomPlusAction());
      zoomPlusMenuItem.setIcon(null);
    }
    return zoomPlusMenuItem;
  }

  /**
   * This method initializes zoomMinusMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getZoomMinusMenuItem() {
    if (zoomMinusMenuItem == null) {
      zoomMinusMenuItem = new JMenuItem();
      zoomMinusMenuItem.setAction(actionProvider.getZoomMinusAction());
      zoomMinusMenuItem.setIcon(null);
    }
    return zoomMinusMenuItem;
  }

  /**
   * This method initializes zoomOneMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getZoomOneMenuItem() {
    if (zoomOneMenuItem == null) {
      zoomOneMenuItem = new JMenuItem();
      zoomOneMenuItem.setAction(actionProvider.getZoomOneAction());
      zoomOneMenuItem.setIcon(null);
    }
    return zoomOneMenuItem;
  }

  /**
   * This method initializes segmentMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getSegmentMenu() {
    if (segmentMenu == null) {
      segmentMenu = new JMenu();
      segmentMenu.setText("Segment");
      segmentMenu.add(getAddStraightMenuItem());
      segmentMenu.add(getAddRightMenuItem());
      segmentMenu.add(getAddLeftMenuItem());
      segmentMenu.addSeparator();
      segmentMenu.add(getMoveMenuItem());
      segmentMenu.add(getDeleteMenuItem());
    }
    return segmentMenu;
  }

  /**
   * This method initializes helpMenu
   *
   * @return javax.swing.JMenu
   */
  @Override
  public JMenu getHelpMenu() {
    if (helpMenu == null) {
      helpMenu = new JMenu();
      helpMenu.setText("Help");
      helpMenu.add(getAboutMenuItem());
    }
    return helpMenu;
  }

  /**
   * This method initializes aboutMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAboutMenuItem() {
    if (aboutMenuItem == null) {
      aboutMenuItem = new JMenuItem();
      aboutMenuItem.setText("About");
      aboutMenuItem.setAction(actionProvider.getHelpAction());
    }
    return aboutMenuItem;
  }

  /**
   * This method initializes addStraightMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAddStraightMenuItem() {
    if (addStraightMenuItem == null) {
      addStraightMenuItem = new JMenuItem();
      addStraightMenuItem.setAction(actionProvider.getStraightAction());
      addStraightMenuItem.setIcon(null);
    }
    return addStraightMenuItem;
  }

  /**
   * This method initializes addRightMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAddRightMenuItem() {
    if (addRightMenuItem == null) {
      addRightMenuItem = new JMenuItem();
      addRightMenuItem.setAction(actionProvider.getRightAction());
      addRightMenuItem.setIcon(null);
    }
    return addRightMenuItem;
  }

  /**
   * This method initializes addLeftMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getAddLeftMenuItem() {
    if (addLeftMenuItem == null) {
      addLeftMenuItem = new JMenuItem();
      addLeftMenuItem.setAction(actionProvider.getLeftAction());
      addLeftMenuItem.setIcon(null);
    }
    return addLeftMenuItem;
  }

  /**
   * This method initializes moveMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getMoveMenuItem() {
    if (moveMenuItem == null) {
      moveMenuItem = new JMenuItem();
      moveMenuItem.setAction(actionProvider.getMoveAction());
      moveMenuItem.setIcon(null);
    }
    return moveMenuItem;
  }

  /**
   * This method initializes deleteMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getDeleteMenuItem() {
    if (deleteMenuItem == null) {
      deleteMenuItem = new JMenuItem();
      deleteMenuItem.setAction(actionProvider.getDeleteAction());
      deleteMenuItem.setIcon(null);
    }
    return deleteMenuItem;
  }

  /**
   * This method initializes showArrowsMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getShowArrowsMenuItem() {
    if (showArrowsMenuItem == null) {
      showArrowsMenuItem = new JMenuItem();
      showArrowsMenuItem.setAction(actionProvider.getShowArrowsAction());
      showArrowsMenuItem.setIcon(null);
    }
    return showArrowsMenuItem;
  }

  /**
   * This method initializes showBackgroundMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getShowBackgroundMenuItem() {
    if (showBackgroundMenuItem == null) {
      showBackgroundMenuItem = new JMenuItem();
      showBackgroundMenuItem.setAction(actionProvider.getShowBackgroundAction());
      showBackgroundMenuItem.setIcon(null);
    }
    return showBackgroundMenuItem;
  }

  /**
   * This method initializes editMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getEditMenu() {
    if (editMenu == null) {
      editMenu = new JMenu();
      editMenu.setText("Edit");
      editMenu.add(getUndoMenuItem());
      editMenu.add(getRedoMenuItem());
    }
    return editMenu;
  }

  /**
   * This method initializes undoMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getUndoMenuItem() {
    if (undoMenuItem == null) {
      undoMenuItem = new JMenuItem();
      undoMenuItem.setAction(actionProvider.getUndoAction());
      undoMenuItem.setIcon(null);
    }
    return undoMenuItem;
  }

  /**
   * This method initializes redoMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getRedoMenuItem() {
    if (redoMenuItem == null) {
      redoMenuItem = new JMenuItem();
      redoMenuItem.setAction(actionProvider.getRedoAction());
      redoMenuItem.setIcon(null);
    }
    return redoMenuItem;
  }

  /**
   * This method initializes importMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenu getImportMenu() {
    if (importMenu == null) {
      importMenu = new JMenu();
      importMenu.setText("Import");
      importMenu.add(torcsPlugin.getImportMenuItem());
    }
    return importMenu;
  }

  /**
   * This method initializes exportMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getExportMenu() {
    if (exportMenu == null) {
      exportMenu = new JMenu();
      exportMenu.setText("Export");
      exportMenu.add(getExportAllMenuItem());
      exportMenu.addSeparator();
      exportMenu.add(torcsPlugin.getExportMenuItem());
      //			exportMenu.add(getExportXmlMenuItem());
      exportMenu.add(getExportAC3MenuItem());
    }
    return exportMenu;
  }

  /**
   * This method initializes exportAllMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getExportAllMenuItem() {
    if (exportAllMenuItem == null) {
      exportAllMenuItem = new JMenuItem();
      exportAllMenuItem.setAction(actionProvider.getAllAction());
      exportAllMenuItem.setIcon(null);
    }
    return exportAllMenuItem;
  }

  /**
   * This method initializes exportAC3MenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getExportAC3MenuItem() {
    if (exportAC3MenuItem == null) {
      exportAC3MenuItem = new JMenuItem();
      exportAC3MenuItem.setAction(actionProvider.getAc3Action());
      exportAC3MenuItem.setIcon(null);
    }
    return exportAC3MenuItem;
  }

  /**
   * This method initializes propertiesMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getPropertiesMenuItem() {
    if (propertiesMenuItem == null) {
      propertiesMenuItem = new JMenuItem();
      propertiesMenuItem.setAction(actionProvider.getPropertiesAction());
      propertiesMenuItem.setIcon(null);
    }
    return propertiesMenuItem;
  }

}
