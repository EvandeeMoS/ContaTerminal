import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaTerminal {

    static String AGENCIA_GENERICA = "33445-2";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Conta> contasCadastradas = new ArrayList<Conta>();
        int idContas = 0;
        mainLoop:
        while (true) {
            System.out.println("""
            =========================================
                      Acesso Banco Terminal
            =========================================
                 
                1 - Criar conta no banco
                2 - Ver Saldo
                3 - Realizar Transferência
                4 - Realizar Depósito

                0 - Sair

            =========================================""");

            int opt = Integer.parseInt(input.nextLine());

            switch(opt) {
                case 1 -> {
                    System.out.print("Informe seu nome: ");
                    String nomeCliente = input.nextLine();
                    if (nomeCliente.trim().length() > 0) {
                        Conta novaConta = new Conta(idContas + 1, AGENCIA_GENERICA, nomeCliente, 0.0);
                        contasCadastradas.add(novaConta);
                        idContas++;
                        System.out.printf("""
                        Sua conta foi criada com sucesso:
                        Dados da conta: 
                        - Numero da conta: %d
                        - Agencia: %s
                        - Nome do cliente: %s
                        - Saldo em conta: %.2f
                        """, novaConta.getNumero(), novaConta.getAgencia(), novaConta.getNomeCliente(), novaConta.getSaldo());
                    }
                    else {
                        System.out.println("Nome inválido");
                    }
                }
                case 2 -> {
                    System.out.print("Informe sua agência: ");
                    String agencia = input.nextLine();
                    if (agencia.trim().length() > 0) {
                        System.out.print("Informe o número da sua conta: ");
                        int numeroConta = Integer.parseInt(input.nextLine());
                        if (numeroConta > 0) {
                            Conta conta = contasCadastradas.stream()
                            .filter(item -> (item.getNumero() == numeroConta))
                            .findFirst()
                            .orElseThrow(() -> new InputMismatchException("Numero da conta não encontrado no banco de dados"));
                            System.out.println("Saldo em conta: " + conta.getSaldo() + "\n");
                        }
                        else {
                            System.out.println("Numero da conta inválido");
                        }
                    }
                    else {
                        System.out.println("Agencia inválida");
                    }
                }
                case 3 -> {
                    System.out.print("Informe sua agência: ");
                    String agencia = input.nextLine();
                    if (agencia.trim().length() > 0) {
                        System.out.print("Informe o número da sua conta: ");
                        int numeroConta = Integer.parseInt(input.nextLine());
                        if (numeroConta > 0) {
                            Conta contaOrigem = contasCadastradas.stream()
                            .filter(item -> (item.getNumero() == numeroConta))
                            .findFirst()
                            .orElseThrow(() -> new InputMismatchException("Numero da conta não encontrado no banco de dados"));

                            System.out.print("Informe o número da conta de destino: ");
                            int numeroContaDestino = Integer.parseInt(input.nextLine());
                            if (numeroConta > 0) {
                                Conta contaDestino = contasCadastradas.stream()
                                .filter(item -> (item.getNumero() == numeroContaDestino))
                                .findFirst()
                                .orElseThrow(() -> new InputMismatchException("Numero da conta não encontrado no banco de dados"));
                                
                                System.out.println("Saldo disponível: " + contaOrigem.getSaldo() + 
                                "\nInforme o valor para transferência: ");
                                Double valor = Double.parseDouble(input.nextLine());
                                if (contaOrigem.getSaldo() >= valor && valor > 0) {
                                    int indexContaDestino = contasCadastradas.indexOf(contaDestino);
                                    int indexContaOrigem = contasCadastradas.indexOf(contaOrigem);
                                    contaDestino.setSaldo(contaDestino.getSaldo() + valor);
                                    contasCadastradas.set(indexContaDestino, contaDestino);
                                    contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
                                    contasCadastradas.set(indexContaOrigem, contaOrigem);
                                }
                                else {
                                    System.out.println("Valor inválido para transferência");
                                }

                            }
                            else {
                                System.out.println("Numero da conta inválido");
                            }
                        }
                        else {
                            System.out.println("Numero da conta inválido");
                        }
                    }
                    else {
                        System.out.println("Agencia inválida");
                    }
                }
                case 4 -> {
                    System.out.print("Informe sua agência: ");
                    String agencia = input.nextLine();
                    if (agencia.trim().length() > 0) {
                        System.out.print("Informe o número da sua conta: ");
                        int numeroConta = Integer.parseInt(input.nextLine());
                        if (numeroConta > 0) {
                            Conta conta = contasCadastradas.stream()
                            .filter(item -> (item.getNumero() == numeroConta))
                            .findFirst()
                            .orElseThrow(() -> new InputMismatchException("Numero da conta não encontrado no banco de dados"));

                            System.out.println("Saldo disponível: " + conta.getSaldo() + 
                            "\nInforme o valor para depósito: ");
                            Double valor = Double.parseDouble(input.nextLine());
                            if (valor > 0) {
                                int indexContaOrigem = contasCadastradas.indexOf(conta);
                                conta.setSaldo(conta.getSaldo() + valor);
                                contasCadastradas.set(indexContaOrigem, conta);
                            }
                            else {
                                System.out.println("Valor inválido para transferência");
                            }
                        }
                    }
                }
                case 0 -> {
                    break mainLoop;
                }
                default -> {
                    System.out.println("Opção inválida");
                }
            }
        }

    }


}
