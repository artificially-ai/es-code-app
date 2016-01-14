package nl.ekholabs.escode.app;

import nl.ekholabs.escode.action.GenerateEsCodeAction;
import nl.ekholabs.escode.action.ParseEsCodeAction;
import nl.ekholabs.escode.action.SelectFileAction;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EsCode {

  private final JFrame frame;
  private Optional<File> selectedFile;

  public EsCode() {
    frame = new JFrame("ES-Code");
  }

  public void setSelectedFile(Optional<File> selectedFile) {
    this.selectedFile = selectedFile;
  }

  public Optional<File> getSelectedFile() {
    return selectedFile;
  }

  public JFrame getFrame() {
    return frame;
  }

  public static void main(String... args) {
    final EsCode esCodeWindow = new EsCode();

    final JFrame frame = esCodeWindow.getFrame();

    final JButton generateButton = new JButton("Generate ES-Code...");
    final JButton parseButton = new JButton("Parse ES-Code...");

    generateButton.addActionListener(e -> {
      esCodeWindow.setSelectedFile(new SelectFileAction(frame).openFile());
      new GenerateEsCodeAction(esCodeWindow, esCodeWindow.getSelectedFile().get()).generateFile();
    });

    parseButton.addActionListener(e -> {
      esCodeWindow.setSelectedFile(new SelectFileAction(frame).openFile());
      new ParseEsCodeAction(esCodeWindow, esCodeWindow.getSelectedFile().get()).parseFile();
    });

    frame.getContentPane().setLayout(new BorderLayout());

    final JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel.add(generateButton);
    panel.add(parseButton);

    frame.add(panel, BorderLayout.NORTH);

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }
}