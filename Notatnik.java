import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Notatnik extends JFrame implements ActionListener{

	JMenuBar menuBar;
	JMenu menuPlik, menuNarz�dzia, menuOpcje, menuPomoc;
	JMenuItem mOtw�rz, mZapisz, mWyj�cie, mNarz1, mNarz2, mOpcja1, mOProgramie;
	JCheckBoxMenuItem chOpcja2;
	JTextArea notatnik;
	JScrollPane scrollPane;
	JButton bSzukaj;
	JTextField tSzukany;
	JLabel lPoszukiwany;
	
	public Notatnik()
	{
		setSize(800,800);
		setTitle("Notatnik");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//dodaje menuBar
		menuBar = new JMenuBar();
		
		//dodaje opcje do menuBar
		menuPlik = new JMenu("Plik");
		menuNarz�dzia = new JMenu("Narz�dzia");
		menuPomoc = new JMenu("Pomoc");
		
		//dodanie item�w do opcji "PLIK"
		mOtw�rz = new JMenuItem("Otw�rz",'O');
		mOtw�rz.addActionListener(this);
		
		mZapisz = new JMenuItem("Zapisz",'Z');
		mZapisz.addActionListener(this);
		
		mWyj�cie = new JMenuItem("Wyj�cie",'W');
		
		menuPlik.add(mOtw�rz);
		menuPlik.add(mZapisz);
		menuPlik.addSeparator();
		menuPlik.add(mWyj�cie);
		
		//dodanie Acceleratora, czyli skr�tu klawiszowego do "Wyj�cia"
		mWyj�cie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		mWyj�cie.addActionListener(this);
		
		//dodanie item�w do opcji "NARZ�DZIA"
		mNarz1 = new JMenuItem("Narz1");
		mNarz2 = new JMenuItem("Narz2");
		mNarz2.addActionListener(this);
		
		menuNarz�dzia.add(mNarz1);
		menuNarz�dzia.add(mNarz2);
		
		//opcja narz1 ustawiam jako niewidoczn�
		mNarz1.setEnabled(false);
		
			//dodanie podmenu do opcji "NARZ�DZIA"
			menuOpcje = new JMenu("Opcje");
			mOpcja1 = new JMenuItem("Opcja1");
			chOpcja2 = new JCheckBoxMenuItem("Opcja2");
			menuOpcje.add(mOpcja1);
			menuOpcje.add(chOpcja2);
			chOpcja2.addActionListener(this);
			
			menuNarz�dzia.add(menuOpcje);
			
		//dodanie item�w do opcji "POMOC"
		mOProgramie = new JMenuItem("O Programie");
		mOProgramie.addActionListener(this);
		
		menuPomoc.add(mOProgramie);
		
		//dodanie menuBar do ramki
		setJMenuBar(menuBar);
		
		//dodanie wszystkich opcji do menuBar
		menuBar.add(menuPlik);
		menuBar.add(menuNarz�dzia);
		menuBar.add(menuPomoc);
		
		//dodaje pole tekstowe na notatnik
		notatnik = new JTextArea();
		scrollPane = new JScrollPane(notatnik);
		scrollPane.setBounds(50,50,500,500);
		add(scrollPane);
		
		//dodanie przycisku
		bSzukaj = new JButton("Szukaj!");
		bSzukaj.setBounds(250,600,100,20);
		bSzukaj.addActionListener(this);
		add(bSzukaj);
		
		//dodanie textu na szukany wyraz
		tSzukany = new JTextField();
		tSzukany.setBounds(50,600,170,20);
		add(tSzukany);
		
		//dodanie etykiety tekstowej
		lPoszukiwany = new JLabel("Podaj wyraz, kt�rego szukasz: ");
		lPoszukiwany.setBounds(50,580,200,20);
		add(lPoszukiwany);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==mWyj�cie)
		{
			int odp = JOptionPane.showConfirmDialog(null,"Czy napewno wyj��?","Pytanie",JOptionPane.YES_NO_OPTION);
			if(odp==JOptionPane.YES_OPTION)
			{
				dispose();
			}else if(odp==JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(null, "Wiedzia�em, �e tak zrobisz!");
			}else if(odp == JOptionPane.CLOSED_OPTION)
			{
				JOptionPane.showMessageDialog(null,"Czy napewno zako�czy� dzia�anie programu?","Uwaga",JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(source==chOpcja2)
		{
			if(chOpcja2.isSelected())
			{
				mNarz1.setEnabled(true);
			}else if(!chOpcja2.isSelected())
			{
				mNarz1.setEnabled(false);
			}
		}else if(source==mOtw�rz)
		{
			JFileChooser fc = new JFileChooser();
			if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				File plik = fc.getSelectedFile();
				try 
				{
					Scanner odczyt = new Scanner(plik);
					while(odczyt.hasNextLine())
						notatnik.append(odczyt.nextLine() + "\n");
				} catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
			}
		}else if(source==mZapisz)
		{
			JFileChooser fc = new JFileChooser();
			if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				File plik = fc.getSelectedFile();
				try 
				{
					PrintWriter pw = new PrintWriter(plik);
					Scanner odczyt = new Scanner(notatnik.getText());
					while(odczyt.hasNextLine())
						
						pw.println(odczyt.hasNextLine() + "\n");
						pw.close();
					
				} catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
			}
		}else if(source==mOProgramie)
		{
			JOptionPane.showMessageDialog(this,"M�j pierwszy program okienkowy! \n Wersja 1.0","Informacja",JOptionPane.WARNING_MESSAGE);
		}else if(source==mNarz2)
		{
			String sMetry = JOptionPane.showInputDialog("Podaj d�ugo�� w metrach: ");
			double metry = Double.parseDouble(sMetry);
			double stopy = metry/0.3048;
			String sStopy = String.format("%.2f",stopy);
			JOptionPane.showMessageDialog(null,metry + "metr�w = " + sStopy + " st�p!");
		}else if(source==bSzukaj)
		{
			String tekst = notatnik.getText();
			String szukane = tSzukany.getText();
			String wyst�pienia = "";
			int i = 0;
			int index;
			int startIndex = 0;
			
			while((index = tekst.indexOf(szukane,startIndex)) != -1)
			{
				startIndex = index +szukane.length();
				i++;
				wyst�pienia = wyst�pienia + " " + index;
			}

		JOptionPane.showMessageDialog(null,szukane + " wyst�pi�o" + i + " razy! Na pozycji: " + wyst�pienia);
		}
	}

	public static void main(String[] args) 
	{
		Notatnik notatnik = new Notatnik();
		notatnik.setVisible(true);
	}

}
