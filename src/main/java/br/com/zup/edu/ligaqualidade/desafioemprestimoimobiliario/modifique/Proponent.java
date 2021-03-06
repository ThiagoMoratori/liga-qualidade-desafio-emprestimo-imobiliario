package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

public class Proponent {

    private String idProposal;
    private String id;
    private String name;
    private Integer age;
    private Double monthlyIncome;
    private Boolean isMain;

    public Proponent(String idProposal, String id, String name, Integer age, Double monthlyIncome, Boolean isMain) {
        this.idProposal = idProposal;
        this.id = id;
        this.name = name;
        this.age = age;
        this.monthlyIncome = monthlyIncome;
        this.isMain = isMain;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }
}
