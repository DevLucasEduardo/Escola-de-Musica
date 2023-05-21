package classesUsuario;

public class SignupUserClass {
    
    String nome;
    String sobrenome;
    String cpf;
    String email;
    String senha;
    String dataNasc;
    String genero;
    String estado;
    String cidade;
    String bairro;
    String rua;
    int aula_aluno_fk;

    int numero;
    
    public SignupUserClass() {
        
    }
    public SignupUserClass(String nome, String sobrenome, 
            String cpf, String email, String senha, String 
                    dataNasc, String genero, String estado, String cidade, 
                    String bairro, String rua, int numero, int aula_aluno_fk) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.genero = genero;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.aula_aluno_fk = aula_aluno_fk;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String data_nasc) {
        this.dataNasc = data_nasc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAula_aluno_fk() {
        return aula_aluno_fk;
    }

    public void setAula_aluno_fk(int aula_aluno_fk) {
        this.aula_aluno_fk = aula_aluno_fk;
    }
    
    
}