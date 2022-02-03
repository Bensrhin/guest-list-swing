package com.guestlist;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class GuestList extends JFrame {
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listGuests;
    private JButton addNewButton;
    private JButton editExistingButton;
    private JTextField textFirstName;
    private JTextField textLastName;
    private JTextField textAddress;
    private JLabel labelTotal;
    private JButton deleteExistingButton;
    private ArrayList<Guest> guests;
    private int length;
    private DefaultListModel listGuestModel;


    public GuestList(int length) {
        super("My Guest List");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.length = length;
        guests = new ArrayList<>();
        listGuestModel = new DefaultListModel();
        listGuests.setModel(listGuestModel);
        editExistingButton.setEnabled(false);
        deleteExistingButton.setEnabled(false);

        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guests.size() == getLength()) {
                    String response = JOptionPane.showInputDialog("The max of guests is reached !!!\nIf you want to increase the length of your list\nType the new value");
                    if (response != null) {
                        try {
                            int number = Integer.parseInt(response);
                            if (number <= getLength()) {
                                JOptionPane.showMessageDialog(null, "Please enter a value greater than : " + getLength());
                            } else {
                                setLength(number);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter an integer");
                        }
                    }

                } else {
                    if (Objects.equals(textFirstName.getText(), "") || Objects.equals(textLastName.getText(), "")) {
                        JOptionPane.showMessageDialog(null, "Please enter a name");
                    }
                    else {
                        Guest g = new Guest(textFirstName.getText(), textLastName.getText(), textAddress.getText());
                        addGuest(g);
                        refreshListGuest();

                    }

                }
            }
        });
        editExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guestIndex = listGuests.getSelectedIndex();
                if (guestIndex >= 0) {
                    Guest g = guests.get(guestIndex);
                    g.setFirstName(textFirstName.getText());
                    g.setLastName(textLastName.getText());
                    g.setAddress(textAddress.getText());
                    refreshListGuest();
                }
            }
        });
        listGuests.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int guestIndex = listGuests.getSelectedIndex();
                if (guestIndex >= 0) {
                    Guest g = guests.get(guestIndex);
                    textFirstName.setText(g.getFirstName());
                    textLastName.setText(g.getLastName());
                    textAddress.setText(g.getAddress());
                    editExistingButton.setEnabled(true);
                    deleteExistingButton.setEnabled(true);
                } else {
                    editExistingButton.setEnabled(false);
                    deleteExistingButton.setEnabled(false);
                }
            }
        });

        deleteExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guestIndex = listGuests.getSelectedIndex();
                if (guestIndex >= 0) {
                    Guest g = guests.get(guestIndex);
                    guests.remove(g);
                    refreshListGuest();
                }
            }
        });
    }

    public static void main(String[] args) {
        String response = JOptionPane.showInputDialog("Please specify the number of Guests");
        try {
            int number = Integer.parseInt(response);
            System.out.println(number); // output = 25
            GuestList guestList = new GuestList(number);
            guestList.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter an integer");
        }

    }

    public void refreshListGuest() {
        listGuestModel.removeAllElements();
        for (Guest g : guests) {
            listGuestModel.addElement(g);
        }
        labelTotal.setText("Total Guests : " + guests.size());
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
        refreshListGuest();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

//    public static void main(String[] args) {
//        String response = JOptionPane.showInputDialog("Please enter the num   ber of guests");
//        JOptionPane.showMessageDialog(null, response);
//        JFrame frame = new JFrame("GuestList");
//        frame.setContentPane(new GuestList().panelMain);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

}
