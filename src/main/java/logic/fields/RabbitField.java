package logic.fields;

public class RabbitField extends Field {

    private boolean startField = false;

    protected RabbitField(int ID) {
        super(ID);
    }

    @Override
    public void performAction() {

    }

    public boolean isStartField() {
        return startField;
    }

    public void setStartField(boolean startField) {
        this.startField = startField;
    }
}
