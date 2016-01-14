package nl.ekholabs.escode.action;

import nl.ekholabs.escode.app.EsCode;
import nl.ekholabs.escode.core.EsCodeParser;
import nl.ekholabs.escode.graphics.ParsedCanvas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ParseEsCodeAction {

  private final EsCode parent;
  private final File selectedFile;

  public ParseEsCodeAction(final EsCode parent, File selectedFile) {
    this.parent = parent;
    this.selectedFile = selectedFile;
  }

  public void parseFile() {
    final Frame frame = parent.getFrame();

    try {
      final BufferedImage bufferedImage = ImageIO.read(new java.io.FileInputStream(selectedFile.getAbsolutePath()));

      final EsCodeParser esCodeParser = new EsCodeParser();

      final List<Integer> byteBuffer = esCodeParser.processFile(bufferedImage);
      final BufferedImage clone = esCodeParser.clone(bufferedImage);
      esCodeParser.drawGraphics(clone.getGraphics(), bufferedImage);
      esCodeParser.persistImage(clone, selectedFile.getParent());
      esCodeParser.persistData(byteBuffer, selectedFile.getParent());

      final ParsedCanvas canvas = new ParsedCanvas(clone);

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