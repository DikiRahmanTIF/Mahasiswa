package DR.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Mahasiswa {
    private final DataMahasiswa objMhs;
    private JTextField textNama;
    private JTextField textUKT;
    private JButton simpanButton;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JPanel rootPanel;
    private JTextField textTanggalLahir;

    public Mahasiswa() {
        this.objMhs = new DataMahasiswa();
        this.initComponents();
        this.noUrutTabel();
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textNama.getText();
                LocalDate tanggalLahir = LocalDate.parse(textTanggalLahir.getText());
                long ukt = Long.parseLong(textUKT.getText());

                DataMahasiswa mhs = new DataMahasiswa(nama, tanggalLahir, ukt);

                String Result=noUrutTabel()+"#"+mhs.getNama()+"#"+mhs.getTanggalLahir()+"#"+mhs.hitungUsia()+"#"+mhs.getUkt()+"#"+mhs.kategorikanUkt()+ "\n";

                tableModel.addRow(new Object[]{noUrutTabel(),nama, tanggalLahir,mhs.hitungUsia(),ukt, mhs.kategorikanUkt()});

                FileWriter fw;
                try {
                    fw = new FileWriter(System.getProperty("user.dir") + "/src/TEMPStrArr.txt", true);
                    fw.write(Result);
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

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
            tableModel = new DefaultTableModel(new DataMahasiswa().getmObject(), tableColumn);
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