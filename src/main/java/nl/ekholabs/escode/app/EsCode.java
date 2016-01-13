package nl.ekholabs.escode.app;

import nl.ekholabs.escode.action.SelectFileAction;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EsCode {

  public static void main(String... args) {
    final JFrame frame = new JFrame("ES-Code");

    final SelectFileAction fileAction = new SelectFileAction(frame);
    final JButton openButton = new JButton(fileAction);
    openButton.setText("Open");

    frame.getContentPane().setLayout(new BorderLayout());

    final JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel.add(openButton);

    frame.add(panel, BorderLayout.NORTH);

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }
}