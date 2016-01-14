package nl.ekholabs.escode.action;

import nl.ekholabs.escode.app.EsCode;
import nl.ekholabs.escode.core.EsCodeGenerator;
import nl.ekholabs.escode.graphics.EsCodeColour;
import nl.ekholabs.escode.graphics.GeneratedCanvas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GenerateEsCodeAction {

  private final EsCode parent;
  private final File selectedFile;

  public GenerateEsCodeAction(final EsCode parent, File selectedFile) {
    this.parent = parent;
    this.selectedFile = selectedFile;
  }

  public void generateFile() {
    final Frame frame = parent.getFrame();

    try {
      final EsCodeGenerator esCodeGenerator = new EsCodeGenerator();

      final List<EsCodeColour> colours = esCodeGenerator.processFile(selectedFile.getAbsolutePath());

      final BufferedImage generateImage = esCodeGenerator.createImage(colours);
      esCodeGenerator.drawGraphics(colours, generateImage.getGraphics());
      esCodeGenerator.persistImage(generateImage, selectedFile.getParent());

      final GeneratedCanvas canvas = new GeneratedCanvas(colours);

      final JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout(FlowLayout.LEFT));
      panel.add(canvas);

      frame.add(panel, BorderLayout.CENTER);
      frame.validate();
    } catch (final IOException e) {
      JOptionPane.showMessageDialog(frame, "Erro occurred when processing the");
    }
  }
}