package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

public enum WarrantyProcessorEnum {

    PROPOSAL_ID(4),
    WARRANTY_ID(5),
    WARRANTY_VALUE(6),
    WARRANTY_PROVINCE(7);

    public int infoLocation;

    WarrantyProcessorEnum(int infoLocation) {
        this.infoLocation = infoLocation;
    }
}
