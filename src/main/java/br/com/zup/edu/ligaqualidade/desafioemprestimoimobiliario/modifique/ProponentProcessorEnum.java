package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

public enum ProponentProcessorEnum {

    PROPOSAL_ID(4),
    PROPONENT_ID(5),
    PROPONENT_NAME(6),
    PROPONENT_AGE(7),
    PROPONENT_MONTHLY_INCOME(8),
    PROPONENT_IS_MAIN(9);

    public int infoLocation;

    ProponentProcessorEnum(int infoLocation) {
        this.infoLocation = infoLocation;
    }
}
