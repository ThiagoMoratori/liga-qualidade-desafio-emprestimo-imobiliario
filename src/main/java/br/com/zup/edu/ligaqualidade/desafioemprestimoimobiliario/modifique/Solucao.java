package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Solucao {
	
  public static String processMessages(List<String> messages) {
      List<Proposal> proposals = new LinkedList<>();

      messages.forEach(message -> {
        String[] actualEvent = message.split(",");
        String action = actualEvent[EventProcessorEnum.EVENT_ACTION.infoLocation];
        String schema = actualEvent[EventProcessorEnum.EVENT_SCHEMA.infoLocation];

        if ("created".equals(action)) {
            if ("proposal".equals(schema)) {
                createProposal(proposals, actualEvent);
            }
        } else if ("added".equals(action)) {
            if ("warranty".equals(schema)) {
                createWarranty(proposals, actualEvent);
            } else if ("proponent".equals(schema)) {
                createProponent(proposals, actualEvent);
            }
        }
        else if ("updated".equals(action)) {
            if ("proposal".equals(schema)) {
                updateProposal(proposals, actualEvent);
            } else if ("warranty".equals(schema)) {
                updateWarranty(proposals, actualEvent);
            }
        } else if ("deleted".equals(action)) {
            if ("proposal".equals(schema)) {
                deleteProposal(proposals, actualEvent);
            }
        } else if ("removed".equals(action)) {
            if ("warranty".equals(schema)) {
                removeWarranty(proposals, actualEvent);
            } else if ("proponent".equals(schema)) {
                removeProponent(proposals, actualEvent);
            }
        }
      });
        // todo - rever a lógica do processamento dos eventos
       // OBS: Não deu tempo de entrar nas validações das propostas e também de separar os métodos em classes menores

	  return "";
  }

    private static void createProposal(List<Proposal> proposals, String[] actualEvent) {
        if (!proposalExists(proposals, actualEvent[ProposalProcessorEnum.PROPOSAL_ID.infoLocation])) {
             proposals.add(
                     new Proposal(
                          actualEvent[ProposalProcessorEnum.PROPOSAL_ID.infoLocation],
                          Double.parseDouble(actualEvent[ProposalProcessorEnum.PROPOSAL_LOAN_VALUE.infoLocation]),
                          Integer.parseInt(actualEvent[ProposalProcessorEnum.PROPOSAL_NUMBER_OF_MONTHLY_INSTALLMENTS.infoLocation])
                     )
             );
        }
    }

    private static void updateProposal(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[WarrantyProcessorEnum.PROPOSAL_ID.infoLocation];

        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();
        if (proposalOp.isPresent()) {
            proposalOp.get().setLoanValue(Double
                    .parseDouble(actualEvent[ProposalProcessorEnum.PROPOSAL_LOAN_VALUE.infoLocation]));
            proposalOp.get().setNumberMonthlyInstallments(Integer
                    .parseInt(actualEvent[ProposalProcessorEnum.PROPOSAL_NUMBER_OF_MONTHLY_INSTALLMENTS.infoLocation]));
        }
    }

    private static void deleteProposal(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[WarrantyProcessorEnum.PROPOSAL_ID.infoLocation];
        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();

        if (proposalOp.isPresent()) {
            proposals.remove(proposalOp.get());
        }
    }

    private static boolean proposalExists(List<Proposal> proposals, String idProposal) {
      return proposals.stream().anyMatch(proposal -> idProposal.equals(proposal.getId()));
    }

    private static boolean warrantyExists(List<Warranty> warranties, String idWarranty) {
        return warranties.stream().anyMatch(warranty -> idWarranty.equals(warranty.getId()));
    }

    private static boolean proponentExists(List<Proponent> proponents, String idProponent) {
        return proponents.stream().anyMatch(proponent -> idProponent.equals(proponent.getId()));
    }

    private static void createWarranty(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[WarrantyProcessorEnum.PROPOSAL_ID.infoLocation];

        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();
        if (proposalOp.isPresent()) {
            if (!warrantyExists(proposalOp.get().getWarranties(), actualEvent[WarrantyProcessorEnum.WARRANTY_ID.infoLocation])) {
                proposalOp
                        .get()
                        .getWarranties()
                        .add(
                          new Warranty(
                                  actualEvent[WarrantyProcessorEnum.PROPOSAL_ID.infoLocation],
                                  actualEvent[WarrantyProcessorEnum.WARRANTY_ID.infoLocation],
                                  Double.parseDouble(actualEvent[WarrantyProcessorEnum.WARRANTY_VALUE.infoLocation]),
                                  actualEvent[WarrantyProcessorEnum.WARRANTY_PROVINCE.infoLocation])
                          );
            }
        }
    }

    private static void updateWarranty(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[WarrantyProcessorEnum.PROPOSAL_ID.infoLocation];

        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();
        if (proposalOp.isPresent()) {
            String warrantyId = actualEvent[WarrantyProcessorEnum.WARRANTY_ID.infoLocation];
            Optional<Warranty> warrantyOp = proposalOp.get()
                    .getWarranties()
                    .stream()
                    .filter(warranty -> warrantyId.equals(warranty.getId())).findFirst();

            if (warrantyOp.isPresent()) {
                warrantyOp.get().setValue(Double.parseDouble(actualEvent[WarrantyProcessorEnum.WARRANTY_VALUE.infoLocation]));
                warrantyOp.get().setProvince(actualEvent[WarrantyProcessorEnum.WARRANTY_PROVINCE.infoLocation]);
            }
        }
    }

    private static void removeWarranty(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[WarrantyProcessorEnum.PROPOSAL_ID.infoLocation];

        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();
        if (proposalOp.isPresent()) {
            String warrantyId = actualEvent[WarrantyProcessorEnum.WARRANTY_ID.infoLocation];
            Optional<Warranty> warrantyOp = proposalOp.get()
                    .getWarranties()
                    .stream()
                    .filter(warranty -> warrantyId.equals(warranty.getId())).findFirst();

            if (warrantyOp.isPresent()) {
                proposalOp.get().getWarranties().remove(warrantyOp.get());
            }
        }
    }

    private static void createProponent(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[ProponentProcessorEnum.PROPOSAL_ID.infoLocation];

        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();
        if (proposalOp.isPresent()) {
            if (!proponentExists(proposalOp.get().getProponents(), actualEvent[ProponentProcessorEnum.PROPONENT_ID.infoLocation])) {
                proposalOp
                        .get()
                        .getProponents()
                        .add(
                                new Proponent(
                                        actualEvent[ProponentProcessorEnum.PROPOSAL_ID.infoLocation],
                                        actualEvent[ProponentProcessorEnum.PROPONENT_ID.infoLocation],
                                        actualEvent[ProponentProcessorEnum.PROPONENT_NAME.infoLocation],
                                        Integer.parseInt(actualEvent[ProponentProcessorEnum.PROPONENT_AGE.infoLocation]),
                                        Double.parseDouble(actualEvent[ProponentProcessorEnum.PROPONENT_MONTHLY_INCOME.infoLocation]),
                                        Boolean.getBoolean(actualEvent[ProponentProcessorEnum.PROPONENT_IS_MAIN.infoLocation]))
                        );
            }
        }
    }

    private static void removeProponent(List<Proposal> proposals, String[] actualEvent) {
        String proposalId = actualEvent[ProponentProcessorEnum.PROPOSAL_ID.infoLocation];

        Optional<Proposal> proposalOp = proposals.stream().filter(proposal -> proposalId.equals(proposal.getId())).findFirst();
        if (proposalOp.isPresent()) {
            String proponentId = actualEvent[ProponentProcessorEnum.PROPONENT_ID.infoLocation];
            Optional<Proponent> proponentOp = proposalOp.get()
                    .getProponents()
                    .stream()
                    .filter(proponent -> proponentId.equals(proponent.getId())).findFirst();

            if (proponentOp.isPresent()) {
                proposalOp.get().getProponents().remove(proponentOp.get());
            }
        }
    }
}