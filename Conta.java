public class Conta {
    private int numero;
    private String agencia;
    private String nomeCliente;
    private Double saldo;

    Conta(int numero, String agencia, String nomeCliente, Double saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double newSaldo) {
        saldo = newSaldo;
    }
}