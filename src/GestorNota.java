import java.util.Scanner;
import model.ItemNota;

public class GestorNota {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CalculadoraNota calculadora = new CalculadoraNota();

    public static void main(String[] args) {
    }

    private static void exibirMenu() {
        System.out.println("\t\t=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("\t\t 1 - Acdicionar Item");
    }

    private static void execAdiionarI() {
        try {
            System.out.println("Nome do produto: ");
            var nome = scanner.nextLine();
            System.out.println("Quantidade: ");
            var qtd = Integer.parseInt(scanner.nextLine());
            System.out.println("Preço Unitario: ");
            var preco = Double.parseDouble(scanner.nextLine());

            var novoItem = new ItemNota(nome, qtd, preco);
            calculadora.adicionarItem(novoItem);
            System.out.println("Item adicionado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("ERRO: Entrada numerica invalida :(");
        }

    }

    private static void execCalcularTotal() {
        var total = calculadora.calularTotalGeral();
        System.out.printf("Total Geral da Nota: R$ %2f%n", total);
    }

    private static void execListarPremium() {
        var premium = calculadora.listarProdutosPremium();

        if (premium.isEmpty()) {
            System.out.println("Nenhum produto premium encontrado.");
        } else {
            premium.forEach(System.out::println);
        }
    }

    private static void execEncontrarMaisCaro() {

    }

}
