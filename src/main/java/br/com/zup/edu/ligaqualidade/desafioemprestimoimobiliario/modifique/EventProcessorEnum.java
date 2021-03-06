package br.com.zup.edu.ligaqualidade.desafioemprestimoimobiliario.modifique;

public enum EventProcessorEnum {

    EVENT_ID(0),
    EVENT_SCHEMA(1),
    EVENT_ACTION(2),
    EVENT_TIMESTAMP(3);

    public int infoLocation;

    EventProcessorEnum(int infoLocation) {
        this.infoLocation = infoLocation;
    }
}
