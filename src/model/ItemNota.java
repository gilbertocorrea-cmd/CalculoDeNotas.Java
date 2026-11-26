package model;

public record ItemNota(String produto,
        int quantidade,
        double precoUnitario) {
    public double calcularSubTotal() {
        return quantidade * precoUnitario;
    }

}
