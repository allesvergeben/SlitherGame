import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays; // für Arrays.toSting ausgaben
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Slithergame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L; // keine Ahnung was das
														// macht musste
														// autogeneriert werden
	public static int picrandom[] = new int[16]; // array für JLabel txt und
													// JLabel Icon
	private static List<JLabel> labels = new ArrayList<>(); // hier sind alle
															// JLabels
															// gespeichert
	private JPanel contentPane; // GUI

	public static boolean stopcheating = false;
	// Methodenanfang
	public static void error(int i) {
		if (i == 0) {
			// wird noch nicht benötigt
		}
		if (i == 1) {
			// wird noch nicht benötigt
		}
	}

	public void label(JPanel contentPane) {

		for (int i = 0; i < 16; i++) {

			// set icon
			Image image = new ImageIcon(getClass().getResource("L" + picrandom[i] + ".jpg")).getImage();
			ImageIcon icon = new ImageIcon(image.getScaledInstance(labels.get(i).getWidth() - 10,
					labels.get(i).getHeight() - 10, Image.SCALE_FAST));
			labels.get(i).setIcon(icon);
			// set Text
			labels.get(i).setText("" + (picrandom[i] - 1));
			// System.out.println("Width: "+labels.get(i).getWidth()+" Height:
			// "+labels.get(i).getHeight());
		}
	}

	public static int emptylabel() {
		// diese methode sucht das leere Feld
		int empty = 0;
		int buttontxt;
		// while Schleife läuft so lange bis sie den leeren button gefunden hat
		// und bricht dann ab
		while (empty < 16) {
			buttontxt = Integer.parseInt(labels.get(empty).getText());
			if (buttontxt == 0) {
				break;
			} else {
				empty++;
			}

		}
		return empty;
	}

	public static void ini() {
		// befüllt das array, das der user zu beginn das fertige puzzel sieht
		for (int i = 0; i < picrandom.length; i++) {

			picrandom[i] = i + 1;
		}
	}

	public static void Generator() {

		int Generatorint = 0;
		// leert das array vor der neubefüllung, damit Gerneratorboolean
		// funktioniert!
		for (int j = 0; j < picrandom.length; j++) {
			picrandom[j] = 0;
		}
		// "vermischt" die zahlen in picrandom
		for (int i = 0; i < picrandom.length; i++) // Befüllen des Arrays
		{
			do {
				Generatorint = (int) (Math.random() * 17);
			} while (Generatorboolean(picrandom, Generatorint));
			picrandom[i] = Generatorint;
		}

		// System.out.println(Arrays.toString(picrandom));
	}

	public static boolean Generatorboolean(int[] arr, int Generatorint) {
		// ist dafür da das nichts doppelt generiert wird
		for (int w : arr) {
			if (w == Generatorint)
				return true;
		}
		return false;
	}

	public static boolean winningcondition() {
		boolean winner = false;
		int win[] = new int[16];
		int buttontxt[] = new int[16];
		for (int i = 0; i < picrandom.length; i++) {
			buttontxt[i] = Integer.parseInt(labels.get(i).getText()); // befüllen
																		// von
																		// buttontxt[]

			win[i] = i; // befüllen von win[]
			if (win[i] != buttontxt[i]) { // vergleichen von win[] & button[]
				winner = false;
				break;
			} else {
				winner = true;
			}
		}
		return winner;
	}

	public static void winner() {

		JOptionPane.showMessageDialog(null, "You won", "!WINNER!", JOptionPane.INFORMATION_MESSAGE);

	}
	// methodenende

	// keyevent

	public static void switchIcon(int i) {
		String tempup;
		String tempempty;
		Icon tempupicon;
		Icon tempemptyicon;
		int empty = emptylabel();

		tempup = labels.get(empty + i).getText();
		tempempty = labels.get(empty).getText();
		labels.get(empty + i).setText(tempempty);
		labels.get(empty).setText(tempup);

		tempupicon = labels.get(empty + i).getIcon();
		tempemptyicon = labels.get(empty).getIcon();
		labels.get(empty + i).setIcon(tempemptyicon);
		labels.get(empty).setIcon(tempupicon);
	}

	@Override

	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			stopcheating = true;
			Generator();
			label(contentPane);

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (stopcheating == true) {
				if (winningcondition() == true) {
					winner();
				} else {
					try {
						switchIcon(-4);
					} catch (ArrayIndexOutOfBoundsException i) {
						error(1);
					}
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (stopcheating == true) {
				if (winningcondition() == true) {
					winner();
				} else {
					int empty = emptylabel();
					if (empty != 4 && empty != 8 && empty != 12) {
						try {
							switchIcon(-1);
						} catch (ArrayIndexOutOfBoundsException i) {
							error(1);
						}

					} else {
						error(0);
					}
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (stopcheating == true) {
				if (winningcondition() == true) {
					winner();
				} else {
					int empty = emptylabel();
					if (empty != 3 && empty != 7 && empty != 11) {
						try {
							switchIcon(1);
						} catch (ArrayIndexOutOfBoundsException i) {
							error(1);
						} catch (IndexOutOfBoundsException i) {
							error(1);
						}
					} else {
						error(0);
					}
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (stopcheating == true) {
				if (winningcondition() == true) {
					winner();
				} else {

					try {
						switchIcon(4);
					} catch (ArrayIndexOutOfBoundsException i) {
						error(1);
					} catch (IndexOutOfBoundsException i) {
						error(1);
					}
				}

			}
		}
	}

	// main
	public static void main(String[] args) {

		ini(); // befüllen des picrandom arrays
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Slithergame frame = new Slithergame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// frame
	public Slithergame() {
		// frame Inizialisierung
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 100, 650, 675); // erst breite dann höhe
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 4, 0, 0));
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setFocusable(true);
		contentPane.addKeyListener(this);
		contentPane.setBackground(Color.DARK_GRAY);

		// labels werden in eine Arraylist & in das JPanel geladen
		for (int i = 0; i < 16; i++) {
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 150, 150);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			label.setForeground(Color.DARK_GRAY);
			labels.add(label);
			contentPane.add(label);
		}
		label(contentPane);
	}

	// musste generiert werden, brauch ich aber nicht
	@Override

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
