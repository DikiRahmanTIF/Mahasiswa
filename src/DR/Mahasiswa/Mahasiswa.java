package DR.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Mahasiswa {
    public int noUrut;
    private final DataMahasiswa objMhs;
    private DefaultTableModel tableModel;
    private JTextField textNama;
    private JTextField textUKT;
    private JButton simpanButton;
    private JTable tableData;

    private JPanel rootPanel;
    private JTextField textTanggalLahir;
    private JButton reset;

    public Mahasiswa() {
        this.objMhs = new DataMahasiswa();
        this.initComponents();
        this.noUrutTabel();
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textNama.getText();
                LocalDate tanggalLahir = null;
                try {
                    tanggalLahir = LocalDate.parse(textTanggalLahir.getText());
                } catch (DateTimeParseException ee) {
                    JOptionPane.showMessageDialog(null, "Tanggal Lahir Tidak Valid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                long ukt = 0;
                try {
                    ukt = Long.parseLong(textUKT.getText());
                } catch (NumberFormatException eee) {
                    JOptionPane.showMessageDialog(null, "UKT tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DataMahasiswa mhs = new DataMahasiswa(nama, tanggalLahir, ukt);
                noUrut= Integer.parseInt(noUrutTabel());
                noUrut++;
                String Result = noUrut + "#" + mhs.getNama() + "#" + mhs.getTanggalLahir() + "#" + mhs.hitungUsia() + "#" + mhs.getUkt() + "#" + mhs.kategorikanUkt() + "\n";

                tableModel.addRow(new Object[]{noUrut, nama, tanggalLahir, mhs.hitungUsia(), ukt, mhs.kategorikanUkt()});

                FileWriter fw;
                try {
                    fw = new FileWriter(System.getProperty("user.dir") + "/src/PseudoDB.txt", true);
                    fw.write(Result);
                    fw.close();
                    textNama.setText("");
                    textTanggalLahir.setText("");
                    textUKT.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/src/PseudoDB.txt", false);
                    PrintWriter pw = new PrintWriter(fw, false);
                    pw.flush();
                    pw.close();
                    fw.close();
                }catch(Exception exception) {
                    System.out.println("Exception have been caught");
                }
                Object[] tableColumn = {"No",
                        "Nama",
                        "Tanggal Lahir",
                        "Usia",
                        "UKT",
                        "Jenis UKT"};
                try {
                    tableModel = new DefaultTableModel(new DataMahasiswa().getObjPembacaData(), tableColumn);
                } catch (FileNotFoundException a) {
                    throw new RuntimeException(a);
                }
                tableData.setModel(tableModel);
                tableData.setAutoCreateRowSorter(true);
            }
        });
    }

    public String noUrutTabel() {
        int baris = tableModel.getRowCount();
        int Nomor = 0;
        for (int a = 0; a < baris; a++) {
            Nomor = Integer.parseInt(String.valueOf(a + 1));
        }
        return String.valueOf(Nomor);
    }
    private void initComponents() {
        Object[] tableColumn = {"No",
                "Nama",
                "Tanggal Lahir",
                "Usia",
                "UKT",
                "Jenis UKT"};
        /*Membuat tabel dan munculkan data di TempStrArr.txt ke tabel
        Serta membuat data tetap di tabel walau aplikasi telah di close
         */
        try {
            tableModel = new DefaultTableModel(new DataMahasiswa().getObjPembacaData(), tableColumn);
        } catch (FileNotFoundException a) {
            throw new RuntimeException(a);
        }
        tableData.setModel(tableModel);
        tableData.setAutoCreateRowSorter(true);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}