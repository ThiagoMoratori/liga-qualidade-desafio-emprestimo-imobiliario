package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

import java.util.ArrayList;
import java.util.List;

public class Proposal {
    private String id;
    private Double loanValue;
    private Integer numberMonthlyInstallments;
    private List<Warranty> warranties;
    private List<Proponent> proponents;

    public Proposal(String id, Double loanValue, Integer numberMonthlyInstallments) {
        this.id = id;
        this.loanValue = loanValue;
        this.numberMonthlyInstallments = numberMonthlyInstallments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(Double loanValue) {
        this.loanValue = loanValue;
    }

    public Integer getNumberMonthlyInstallments() {
        return numberMonthlyInstallments;
    }

    public void setNumberMonthlyInstallments(Integer numberMonthlyInstallments) {
        this.numberMonthlyInstallments = numberMonthlyInstallments;
    }

    public List<Warranty> getWarranties() {
        if (warranties == null) {
            return new ArrayList<>();
        }
        return warranties;
    }

    public void setWarranties(List<Warranty> warranties) {
        this.warranties = warranties;
    }

    public List<Proponent> getProponents() {
        if (proponents == null) {
            return new ArrayList<>();
        }
        return proponents;
    }

    public void setProponents(List<Proponent> proponents) {
        this.proponents = proponents;
    }
}
