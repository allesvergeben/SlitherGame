import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Slithergame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
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

	public static int emptyLabel() {
		// diese methode sucht das leere Feld
		int empty = 0;
		int buttonText;
		// while Schleife läuft so lange bis sie den leeren button gefunden hat
		// und bricht dann ab
		while (empty < 16) {
			buttonText = Integer.parseInt(labels.get(empty).getText());
			if (buttonText == 0) {
				break;
			} else {
				empty++;
			}

		}
		return empty;
	}

	public static void Generator() {

		int generatorInt = 0;
		// leert das array vor der neubefüllung, damit Gerneratorboolean
		// funktioniert!
		for (int j = 0; j < picrandom.length; j++) {
			picrandom[j] = 0;
		}
		// "vermischt" die zahlen in picrandom
		for (int i = 0; i < picrandom.length; i++) // Befüllen des Arrays
		{
			do {
				generatorInt = (int) (Math.random() * 17);
			} while (Generatorboolean(picrandom, generatorInt));
			picrandom[i] = generatorInt;
		}

		// System.out.println(Arrays.toString(picrandom));
	}

	public static void ini() {
		// befüllt das array, das der user zu beginn das fertige puzzel sieht
		for (int i = 0; i < picrandom.length; i++) {

			picrandom[i] = i + 1;
		}
	}

	public static boolean Generatorboolean(int[] arr, int generatorInt) {
		// ist dafür da das nichts doppelt generiert wird
		for (int w : arr) {
			if (w == generatorInt)
				return true;
		}
		return false;
	}

	public static boolean winningCondition() {
		boolean winner = false;
		int[] win = new int[16];
		int[] buttonText = new int[16];
		for (int i = 0; i < picrandom.length; i++) {
			buttonText[i] = Integer.parseInt(labels.get(i).getText()); // befüllen
			// von
			// buttonText[]

			win[i] = i; // befüllen von win[]
			if (win[i] != buttonText[i]) { // vergleichen von win[] & button[]
				winner = false;
				break;
			} else {
				winner = true;
			}
		}
		return winner;
	}

	public static void switchIcon(int i) {
		String tempUp;
		String tempEmpty;
		Icon tempUpIcon;
		Icon tempEmptyIcon;
		int empty = emptyLabel();

		tempUp = labels.get(empty + i).getText();
		tempEmpty = labels.get(empty).getText();
		labels.get(empty + i).setText(tempEmpty);
		labels.get(empty).setText(tempUp);

		tempUpIcon = labels.get(empty + i).getIcon();
		tempEmptyIcon = labels.get(empty).getIcon();
		labels.get(empty + i).setIcon(tempEmptyIcon);
		labels.get(empty).setIcon(tempUpIcon);
	}

	public static void winner() {

		JOptionPane.showMessageDialog(null, "You won", "!WINNER!", JOptionPane.INFORMATION_MESSAGE);

	}
	// methodenende

	// keyevent

	public void label(JPanel contentPane) {

		for (int i = 0; i < 16; i++) {

			// set icon
			Image image = new ImageIcon(getClass().getResource("./assets/L" + picrandom[i] + ".jpg")).getImage();
			ImageIcon icon = new ImageIcon(image.getScaledInstance(labels.get(i).getWidth() - 10,
					labels.get(i).getHeight() - 10, Image.SCALE_FAST));
			labels.get(i).setIcon(icon);
			// set Text
			labels.get(i).setText("" + (picrandom[i] - 1));
			// System.out.println("Width: "+labels.get(i).getWidth()+" Height:
			// "+labels.get(i).getHeight());
		}
	}

	@Override

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			stopcheating = true;
			Generator();
			label(contentPane);

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (stopcheating) {
				if (winningCondition()) {
					winner();
				} else {
					try {
						switchIcon(-4);
					} catch (IndexOutOfBoundsException i) {
						error(1);
					}
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (stopcheating) {
				if (winningCondition()) {
					winner();
				} else {
					int empty = emptyLabel();
					if (empty != 4 && empty != 8 && empty != 12) {
						try {
							switchIcon(-1);
						} catch (IndexOutOfBoundsException i) {
							error(1);
						}

					} else {
						error(0);
					}
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (stopcheating) {
				if (winningCondition()) {
					winner();
				} else {
					int empty = emptyLabel();
					if (empty != 3 && empty != 7 && empty != 11) {
						try {
							switchIcon(1);
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
			if (stopcheating) {
				if (winningCondition()) {
					winner();
				} else {

					try {
						switchIcon(4);
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
