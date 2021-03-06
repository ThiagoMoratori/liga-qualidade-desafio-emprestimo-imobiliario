package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

import java.util.List;
import java.util.stream.Collectors;

public class ProposalValidator {

    private static final int MINIMUM_LOAN_AMOUNT = 30000;
    private static final int MAXIMUM_LOAN_AMOUNT = 3000000;
    private static final int MINIMUM_NUMBER_OF_MONTHLY_INSTALLMENTS = 24;
    private static final int MAXIMUM_NUMBER_OF_MONTHLY_INSTALLMENTS = 180;

    /**
     * - Regras:
     * O valor do empréstimo deve estar entre R$ 30.000,00 e R$ 3.000.000,00
     * O empréstimo deve ser pago em no mínimo 2 anos e no máximo 15 anos
     * Deve haver no mínimo 2 proponentes por proposta
     * Deve haver exatamente 1 proponente principal por proposta
     * Todos os proponentes devem ser maiores de 18 anos
     * Deve haver no mínimo 1 garantia de imóvel por proposta
     * A soma do valor das garantias deve ser maior ou igual ao dobro do valor do empréstimo
     * As garantias de imóvel dos estados PR, SC e RS não são aceitas
     * A renda do proponente principal deve ser pelo menos:
     * 4 vezes o valor da parcela do empréstimo, se a idade dele for entre 18 e 24 anos
     * 3 vezes o valor da parcela do empréstimo, se a idade dele for entre 24 e 50 anos
     * 2 vezes o valor da parcela do empréstimo, se a idade dele for acima de 50 anos
     *
     * @param proposals
     */

    public static void validate(List<Proposal> proposals) {
        filterByLoanValue(proposals);
        filterByNumberOfMonthlyInstallments(proposals);
        filterByNumberOfProponents(proposals);
        filterByNumberOfPrincipalProponents(proposals);
    }

    private static void filterByNumberOfPrincipalProponents(List<Proposal> proposals) {
        proposals.removeIf(proposal -> proposal.getProponents().stream()
                .filter(proponent -> Boolean.TRUE.equals(proponent.getMain())).count() > 1);
    }

    private static void filterByNumberOfProponents(List<Proposal> proposals) {
        proposals.removeIf(proposal -> proposal.getProponents().size() < 2);
    }

    private static void filterByNumberOfMonthlyInstallments(List<Proposal> proposals) {
        proposals.removeIf(proposal -> proposal.getNumberMonthlyInstallments() < MINIMUM_NUMBER_OF_MONTHLY_INSTALLMENTS
                || proposal.getNumberMonthlyInstallments() > MAXIMUM_NUMBER_OF_MONTHLY_INSTALLMENTS);
    }

    private static void filterByLoanValue(List<Proposal> proposals) {
        proposals.removeIf(proposal -> proposal.getLoanValue() < MINIMUM_LOAN_AMOUNT || proposal.getLoanValue() > MAXIMUM_LOAN_AMOUNT);
    }
}
