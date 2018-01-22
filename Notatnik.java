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
	JMenu menuPlik, menuNarzêdzia, menuOpcje, menuPomoc;
	JMenuItem mOtwórz, mZapisz, mWyjœcie, mNarz1, mNarz2, mOpcja1, mOProgramie;
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
		menuNarzêdzia = new JMenu("Narzêdzia");
		menuPomoc = new JMenu("Pomoc");
		
		//dodanie itemów do opcji "PLIK"
		mOtwórz = new JMenuItem("Otwórz",'O');
		mOtwórz.addActionListener(this);
		
		mZapisz = new JMenuItem("Zapisz",'Z');
		mZapisz.addActionListener(this);
		
		mWyjœcie = new JMenuItem("Wyjœcie",'W');
		
		menuPlik.add(mOtwórz);
		menuPlik.add(mZapisz);
		menuPlik.addSeparator();
		menuPlik.add(mWyjœcie);
		
		//dodanie Acceleratora, czyli skrótu klawiszowego do "Wyjœcia"
		mWyjœcie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		mWyjœcie.addActionListener(this);
		
		//dodanie itemów do opcji "NARZÊDZIA"
		mNarz1 = new JMenuItem("Narz1");
		mNarz2 = new JMenuItem("Narz2");
		mNarz2.addActionListener(this);
		
		menuNarzêdzia.add(mNarz1);
		menuNarzêdzia.add(mNarz2);
		
		//opcja narz1 ustawiam jako niewidoczn¹
		mNarz1.setEnabled(false);
		
			//dodanie podmenu do opcji "NARZÊDZIA"
			menuOpcje = new JMenu("Opcje");
			mOpcja1 = new JMenuItem("Opcja1");
			chOpcja2 = new JCheckBoxMenuItem("Opcja2");
			menuOpcje.add(mOpcja1);
			menuOpcje.add(chOpcja2);
			chOpcja2.addActionListener(this);
			
			menuNarzêdzia.add(menuOpcje);
			
		//dodanie itemów do opcji "POMOC"
		mOProgramie = new JMenuItem("O Programie");
		mOProgramie.addActionListener(this);
		
		menuPomoc.add(mOProgramie);
		
		//dodanie menuBar do ramki
		setJMenuBar(menuBar);
		
		//dodanie wszystkich opcji do menuBar
		menuBar.add(menuPlik);
		menuBar.add(menuNarzêdzia);
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
		lPoszukiwany = new JLabel("Podaj wyraz, którego szukasz: ");
		lPoszukiwany.setBounds(50,580,200,20);
		add(lPoszukiwany);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==mWyjœcie)
		{
			int odp = JOptionPane.showConfirmDialog(null,"Czy napewno wyjœæ?","Pytanie",JOptionPane.YES_NO_OPTION);
			if(odp==JOptionPane.YES_OPTION)
			{
				dispose();
			}else if(odp==JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(null, "Wiedzia³em, ¿e tak zrobisz!");
			}else if(odp == JOptionPane.CLOSED_OPTION)
			{
				JOptionPane.showMessageDialog(null,"Czy napewno zakoñczyæ dzia³anie programu?","Uwaga",JOptionPane.WARNING_MESSAGE);
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
		}else if(source==mOtwórz)
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
			JOptionPane.showMessageDialog(this,"Mój pierwszy program okienkowy! \n Wersja 1.0","Informacja",JOptionPane.WARNING_MESSAGE);
		}else if(source==mNarz2)
		{
			String sMetry = JOptionPane.showInputDialog("Podaj d³ugoœæ w metrach: ");
			double metry = Double.parseDouble(sMetry);
			double stopy = metry/0.3048;
			String sStopy = String.format("%.2f",stopy);
			JOptionPane.showMessageDialog(null,metry + "metrów = " + sStopy + " stóp!");
		}else if(source==bSzukaj)
		{
			String tekst = notatnik.getText();
			String szukane = tSzukany.getText();
			String wyst¹pienia = "";
			int i = 0;
			int index;
			int startIndex = 0;
			
			while((index = tekst.indexOf(szukane,startIndex)) != -1)
			{
				startIndex = index +szukane.length();
				i++;
				wyst¹pienia = wyst¹pienia + " " + index;
			}

		JOptionPane.showMessageDialog(null,szukane + " wyst¹pi³o" + i + " razy! Na pozycji: " + wyst¹pienia);
		}
	}

	public static void main(String[] args) 
	{
		Notatnik notatnik = new Notatnik();
		notatnik.setVisible(true);
	}

}
