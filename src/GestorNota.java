import java.util.Scanner;
import model.ItemNota;

public class GestorNota {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CalculadoraNota calculadora = new CalculadoraNota();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> adicionarItem();
                case "2" -> calcularTotal();
                case "3" -> listarPremium();
                case "4" -> encontrarMaisCaro();
                case "5" -> listarTodosItens();
                case "0" -> {
                    System.out.println("Saindo...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }

            System.out.println();
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("================================");
        System.out.println("       CÁLCULO DE NOTAS");
        System.out.println("1 - Adicionar item");
        System.out.println("2 - Calcular total geral");
        System.out.println("3 - Listar produtos premium");
        System.out.println("4 - Encontrar produto mais caro");
        System.out.println("5 - Listar todos os itens");
        System.out.println("0 - Sair");
        System.out.println("================================");
    }

    private static void adicionarItem() {
        try {
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Preço unitário: ");
            double preco = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));

            if (nome.isBlank() || quantidade <= 0 || !Double.isFinite(preco) || preco < 0) {
                System.out.println("Dados inválidos: informe produto, quantidade positiva e preço válido.");
                return;
            }

            calculadora.adicionarItem(new ItemNota(nome.trim(), quantidade, preco));
            System.out.println("Item adicionado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("ERRO: entrada numérica inválida.");
        }
    }

    private static void calcularTotal() {
        System.out.printf("Total geral da nota: R$ %.2f%n", calculadora.calularTotalGeral());
    }

    private static void listarPremium() {
        var premium = calculadora.listarProdutosPremium();

        if (premium.isEmpty()) {
            System.out.println("Nenhum produto premium encontrado.");
        } else {
            System.out.println("Produtos premium (preço unitário acima de R$ 100,00):");
            premium.forEach(System.out::println);
        }
    }

    private static void encontrarMaisCaro() {
        var maisCaro = calculadora.encontrarMaisCaro();
        if (maisCaro.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
            return;
        }
        var item = maisCaro.get();
        System.out.println("Produto mais caro: " + item.produto());
        System.out.println("Quantidade: " + item.quantidade());
        System.out.printf("Preço unitário: R$ %.2f%n", item.precoUnitario());
        System.out.printf("Subtotal: R$ %.2f%n", item.calcularSubTotal());
    }

    private static void listarTodosItens() {
        var itens = calculadora.getItens();
        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
            return;
        }

        for (ItemNota item : itens) {
            System.out.printf(
                    "Produto: %s | Quantidade: %d | Preço unitário: R$ %.2f | Subtotal: R$ %.2f%n",
                    item.produto(), item.quantidade(), item.precoUnitario(), item.calcularSubTotal()
            );
        }
    }
}
