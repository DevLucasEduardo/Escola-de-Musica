package novasClasses;

public class CompraDTO {

    String quantidade, idCompra, fkFuncionario, fkInstrumento;

    public CompraDTO(String quantidade, String idCompra, String fkFuncionario, String fkInstrumento) {
        this.quantidade = quantidade;
        this.idCompra = idCompra;
        this.fkFuncionario = fkFuncionario;
        this.fkInstrumento = fkInstrumento;
    }

    public CompraDTO() {
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(String fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public String getFkInstrumento() {
        return fkInstrumento;
    }

    public void setFkInstrumento(String fkInstrumento) {
        this.fkInstrumento = fkInstrumento;
    }
    
    
}