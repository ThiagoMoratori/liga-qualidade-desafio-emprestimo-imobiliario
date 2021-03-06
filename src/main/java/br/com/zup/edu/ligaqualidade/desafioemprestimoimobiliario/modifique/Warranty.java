package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

public class Warranty {
    private String idProposal;
    private String id;
    private Double value;
    private String province;

    public Warranty(String idProposal, String id, Double value, String province) {
        this.idProposal = idProposal;
        this.id = id;
        this.value = value;
        this.province = province;
    }

    public String getIdProposal() {
        return idProposal;
    }

    public void setIdProposal(String idProposal) {
        this.idProposal = idProposal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
