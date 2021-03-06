package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

public enum ProposalProcessorEnum {

    PROPOSAL_ID(4),
    PROPOSAL_LOAN_VALUE(5),
    PROPOSAL_NUMBER_OF_MONTHLY_INSTALLMENTS(6);

    public int infoLocation;

    ProposalProcessorEnum(int infoLocation) {
        this.infoLocation = infoLocation;
    }
}
