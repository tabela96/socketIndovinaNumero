package gastaldo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(9999);
		boolean giusto = false;
		int n = (int) (Math.random() * 99);
		int nRicevuto = 0;
		while (true) {
			System.out.println("" + n);
			Socket s = ss.accept();
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(n);

			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			nRicevuto = Integer.parseInt(in.readLine());
			if (!giusto) {
				if (nRicevuto == n) {
					out.println("COMPLIMENTI! NUMERO ESATTO");
					giusto = true;
				}
				if (nRicevuto > n) {
					out.println("PIÙ PICCOLO");
				}
				if (nRicevuto < n) {
					out.println("PIÙ GRANDE");
				}
			} else {
				out.println("È GIÀ STATO INDOVINATO. ERA IL " + n);
			}
			s.close();
		}
	}
}
