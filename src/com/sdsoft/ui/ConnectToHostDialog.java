package com.sdsoft.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectToHostDialog extends JDialog {
	JTextField ip1 = new JTextField("192", 3);
	JTextField ip2 = new JTextField("168", 3);
	JTextField ip3 = new JTextField("1", 3);
	JTextField ip4 = new JTextField("4", 3);
	JTextField port = new JTextField("12345", 5);
	JTextField name = new JTextField("", 20);
	boolean isOkPressed = false;

	public ConnectToHostDialog() {
		super(MainUI.getInstance(), "Connect To Server");
		this.setLayout(new BorderLayout());
		JPanel wrapper = new JPanel();
		wrapper.add(name);
		wrapper.add(new JLabel("Name: "));
		wrapper.add(name);
		this.add(wrapper, BorderLayout.NORTH);
		wrapper = new JPanel();
		wrapper.add(new JLabel("IP:"));
		wrapper.add(ip1);
		wrapper.add(ip2);
		wrapper.add(ip3);
		wrapper.add(ip4);
		wrapper.add(new JLabel(" port:"));
		wrapper.add(port);
		this.add(wrapper, BorderLayout.CENTER);
		wrapper = new JPanel();
		final JButton ok = new JButton("OK");
		wrapper.add(ok);
		final JButton cancel = new JButton("Cancel");
		wrapper.add(cancel);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				okPressed();
				dispose();
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		setLocationRelativeTo(MainUI.getInstance());
		this.add(wrapper, BorderLayout.SOUTH);
		this.setSize(300, 130);
		this.setModal(true);
	}

	private void okPressed() {
		this.isOkPressed = true;
	}

	public boolean isOKPressed() {
		return this.isOkPressed;
	}

	public String getIPAddress() {
		return ip1.getText() + "." + ip2.getText() + "." + ip3.getText() + "." + ip4.getText();
	}

	public int getPort() {
		return Integer.valueOf(port.getText());
	}

	public String getPlayerName() {
		return name.getText();
	}
}
