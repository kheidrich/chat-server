package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {

    private Socket conexao;

    public Cliente(Socket socket) {
        this.conexao = socket;
    }

    public static void main(String args[]) {
        String ip;
        int porta;
        Scanner ler = new Scanner(System.in);
//        System.out.print("Digite o IP do Servidor: ");
//        ip = ler.nextLine();
//        System.out.print("Digite a Porta do Servidor: ");
//        porta = ler.nextInt();
        ip = "localhost";
        porta = 8976;
        try {
            Socket socket = new Socket(ip, porta);
            PrintStream saida = new PrintStream(socket.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Requisição: ");
            String meuNome = teclado.readLine();
            while (!(meuNome.contains("ENTRAR")) || meuNome.length() < 8) {
                System.out.print("ERRO comando inválido!\nRequisição: ");
                meuNome = teclado.readLine();
            }
            if (meuNome.contains("ENTRAR")) {
                saida.println(meuNome);
            }

            Thread thread = new Cliente(socket);
            thread.start();
            String msg;
            while (true) {
                msg = teclado.readLine();
                if (msg.contains("MSG ") || (msg.contains("SAIR") && msg.length() == 4)) {
                    saida.println(msg);
                } else {
                    System.out.print("ERRO! Comando de chat errado favor utilizar MSG ou SAIR:\n");
                }

            }
        } catch (IOException e) {
            System.out.println("ERRO " + e);
        }
    }

    public void run() {
        try {
            BufferedReader entrada
                    = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
            String msg;
            while (true) {
                msg = entrada.readLine();
                if (msg == null) {
                    System.out.println("Conexão encerrada!");
                    System.exit(0);
                }
                System.out.println(msg);
            }
        } catch (IOException e) {
            System.out.println("ERRO " + e);
        }
    }
}
