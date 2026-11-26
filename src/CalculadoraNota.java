import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.ItemNota;

public class CalculadoraNota {
    private final List<ItemNota> itens = new ArrayList<>();

    public void adicionarItem(ItemNota item) {
        itens.add(item);
    }

    public List<ItemNota> getItens() {
        return Collections.unmodifiableList(itens);
    }

    // Mantido por compatibilidade com a versão anterior do exercício.
    public List<ItemNota> gItemNotas() {
        return getItens();
    }

    public double calularTotalGeral() {
        return itens.stream()
                .mapToDouble(ItemNota::calcularSubTotal)
                .sum();
    }

    public List<String> listarProdutosPremium() {
        return itens.stream()
                .filter(item -> item.precoUnitario() > 100.00)
                .map(ItemNota::produto)
                .sorted()
                .collect(Collectors.toList());
    }

    public Optional<ItemNota> encontrarMaisCaro() {
        return itens.stream()
                .max(Comparator.comparingDouble(ItemNota::precoUnitario));
    }
}
