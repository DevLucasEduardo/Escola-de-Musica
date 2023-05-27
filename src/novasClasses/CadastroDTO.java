package novasClasses;

public class CadastroDTO {
    
    private String instrumento;
    private String categoria;
    private String marca;
    private String codigoInstrumento; 

    private String cnpj;
    private String fornecedor;
    private String razaoSocial;
    private String codigoFornecedor;
    
    private String pais;
    private String estado;
    private String cidade;
    private String codigoPostal;

    // Instrumento
    public CadastroDTO(String instrumento, String categoria, String marca, String codigoInstrumento, String cnpj, String fornecedor) {
        this.instrumento = instrumento;
        this.categoria = categoria;
        this.marca = marca;
        this.codigoInstrumento = codigoInstrumento;
        this.cnpj = cnpj;
        this.fornecedor = fornecedor;
    }

    // Fornecedor
    public CadastroDTO(String cnpj, String fornecedor, String razaoSocial, String codigoFornecedor, String pais, String estado, String cidade, String codigoPostal) {
        this.cnpj = cnpj;
        this.fornecedor = fornecedor;
        this.razaoSocial = razaoSocial;
        this.codigoFornecedor = codigoFornecedor;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.codigoPostal = codigoPostal;
    }
    
    

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoInstrumento() {
        return codigoInstrumento;
    }

    public void setCodigoInstrumento(String codigoInstrumento) {
        this.codigoInstrumento = codigoInstrumento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(String codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    
    
    
}

   