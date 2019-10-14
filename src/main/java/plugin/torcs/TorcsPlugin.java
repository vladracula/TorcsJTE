/*
 *   TorcsPlugin.java
 *   Created on 9  2005
 *
 *    The TorcsPlugin.java is part of TrackEditor-0.6.0.
 *
 *    TrackEditor-0.6.0 is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    TrackEditor-0.6.0 is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with TrackEditor-0.6.0; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package plugin.torcs;

import gui.EditorFrame;
import plugin.Plugin;
import utils.CustomFileFilter;
import utils.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

/**
 * @author Charalampos Alexopoulos
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TorcsPlugin implements Plugin {
  //private Properties		properties	= Properties.getInstance();
  protected EditorFrame editor;
  private Vector trackData = new Vector();
  ;
  private ImportAction importAction;
  private ExportAction exportAction;
  private JMenuItem importMenuItem;
  private JMenuItem exportMenuItem;

  /**
   *
   */
  public TorcsPlugin(EditorFrame editor) {
    this.editor = editor;
    importAction = new ImportAction("Torcs", null, "Torcs xml file", null);
    exportAction = new ExportAction("Torcs", null, "Torcs xml file", null);
  }

  public void importTrack() {
    String tmp = "";
    JFileChooser fc = new JFileChooser();
    fc.setSelectedFiles(null);
    fc.setSelectedFile(null);
    fc.rescanCurrentDirectory();
    fc.setApproveButtonMnemonic(0);
    fc.setDialogTitle("Import track from Xml");
    fc.setVisible(true);
    fc.setCurrentDirectory(new File(System.getProperty("user.dir") + "/tracks"));
    //fc.setCurrentDirectory(new File("/usr/share/games/torcs/tracks"));
    CustomFileFilter filter = new CustomFileFilter();

    filter.addValid(".xml");
    filter.addInvalid(".prj.xml");
    filter.setDescription("*.xml");
    fc.setFileFilter(filter);
    int result = fc.showDialog(editor, "Ok");
    if (result == JFileChooser.APPROVE_OPTION) {
      tmp = fc.getSelectedFile().toString();
      Editor.getProperties().setTrackName(tmp.substring(tmp.lastIndexOf("/") + 1, tmp.lastIndexOf(".")));
      tmp = tmp.substring(0, tmp.lastIndexOf("/"));
      Editor.getProperties().setPath(tmp);
      tmp = Editor.getProperties().getPath().substring(0, tmp.lastIndexOf("/"));
      tmp = tmp.substring(tmp.lastIndexOf("/") + 1, tmp.length());
      Editor.getProperties().setCategory(tmp.substring(tmp.lastIndexOf("/") + 1));
      File file;
      file = new File(Editor.getProperties().getPath() + "/" + Editor.getProperties().getTrackName() + ".xml");
      readFile(file);
    }
  }

  public void readFile(File file) {
    try {
      XmlReader.readXml(file.getAbsolutePath());
    }
    catch (Exception e) {
      //				message(e.getMessage(), "The file " + file.getAbsolutePath()
      // + " is not valid");
      e.printStackTrace();
    }
    editor.refresh();
  }

  public void exportTrack() {
    XmlWriter.writeXml();
  }

  /**
   * This method initializes importMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  public JMenuItem getImportMenuItem() {
    if (importMenuItem == null) {
      importMenuItem = new JMenuItem();
      importMenuItem.setAction(importAction);
      importMenuItem.setIcon(null);
    }
    return importMenuItem;
  }

  /**
   * This method initializes exportMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  public JMenuItem getExportMenuItem() {
    if (exportMenuItem == null) {
      exportMenuItem = new JMenuItem();
      exportMenuItem.setAction(exportAction);
      exportMenuItem.setIcon(null);
    }
    return exportMenuItem;
  }

  public class ImportAction extends AbstractAction {
    public ImportAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      importTrack();
    }
  }

  private class ExportAction extends AbstractAction {
    public ExportAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
      super(text, icon);
      putValue(SHORT_DESCRIPTION, desc);
      putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
      System.out.println("Call exportXml");
      exportTrack();
    }
  }

}
