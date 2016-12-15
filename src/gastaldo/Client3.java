package gastaldo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Client3 {

	protected Shell shlIndovinaNumero;
	private Text txtNumero;
	private Socket s;
	private int nCasuale;
	private int nInserito;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Client1 window = new Client1();
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlIndovinaNumero.open();
		shlIndovinaNumero.layout();
		while (!shlIndovinaNumero.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlIndovinaNumero = new Shell();
		shlIndovinaNumero.setSize(266, 105);
		shlIndovinaNumero.setText("Giocatore 1");

		txtNumero = new Text(shlIndovinaNumero, SWT.BORDER);
		txtNumero.setBounds(10, 12, 76, 21);

		Label lblSuggerimento = new Label(shlIndovinaNumero, SWT.NONE);
		lblSuggerimento.setAlignment(SWT.CENTER);
		lblSuggerimento.setBounds(10, 42, 230, 15);

		Button btnConferma = new Button(shlIndovinaNumero, SWT.NONE);
		btnConferma.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					s = new Socket("localhost", 9999);
					InputStreamReader isr = new InputStreamReader(s.getInputStream());
					BufferedReader in = new BufferedReader(isr);
					nCasuale = Integer.parseInt(in.readLine());
					if (txtNumero.getText() != "") {
						nInserito = Integer.parseInt(txtNumero.getText());
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);
						out.println(nInserito);
						if (nInserito == nCasuale) {
							lblSuggerimento.setText(in.readLine());
							s.close();
						}
						if (nInserito > nCasuale) {
							lblSuggerimento.setText(in.readLine());
						}
						if (nInserito < nCasuale) {
							lblSuggerimento.setText(in.readLine());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Inserisci un numero");
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConferma.setBounds(165, 10, 75, 25);
		btnConferma.setText("Conferma");

	}
}
