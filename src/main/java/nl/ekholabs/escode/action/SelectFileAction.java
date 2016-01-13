package nl.ekholabs.escode.action;

import nl.ekholabs.escode.core.EsCodeGenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectFileAction extends AbstractAction {

  private static final long serialVersionUID = -6244737874353836899L;

  private final Frame parent;

  public SelectFileAction(final Frame parent) {
    this.parent = parent;
  }

  public void actionPerformed(final ActionEvent event) {
    final JFileChooser chooser = new JFileChooser();
    final int result = chooser.showOpenDialog(parent);
    if (result == JFileChooser.APPROVE_OPTION) {
      final File file = chooser.getSelectedFile();

      try {
        final EsCodeGenerator esCodeGenerator = new EsCodeGenerator();
        final JComponent canvas = esCodeGenerator.processImage(file.getAbsolutePath());

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(canvas);

        parent.add(panel, BorderLayout.CENTER);
        parent.validate();
      } catch (final IOException e) {
        JOptionPane.showMessageDialog(parent, "Erro occurred when processing the");
      }
    } else {
      JOptionPane.showMessageDialog(parent, "Action was cancelled.");
    }
  }
}