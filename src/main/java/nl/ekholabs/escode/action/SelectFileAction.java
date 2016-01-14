package nl.ekholabs.escode.action;

import java.awt.Frame;
import java.io.File;
import java.util.Optional;

import javax.swing.JFileChooser;

public class SelectFileAction {

  private final Frame parent;

  public SelectFileAction(final Frame parent) {
    this.parent = parent;
  }

  public Optional<File> openFile() {
    final JFileChooser chooser = new JFileChooser();
    final int result = chooser.showOpenDialog(parent);

    if (result == JFileChooser.APPROVE_OPTION) {
      final Optional<File> file = Optional.of(chooser.getSelectedFile());
      return file;
    }

    return Optional.empty();
  }
}