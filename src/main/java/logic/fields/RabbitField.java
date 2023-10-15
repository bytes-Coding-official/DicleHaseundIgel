package logic.fields;

public class RabbitField extends Field {

    private boolean startField = false;
    private boolean endField = false;

    public RabbitField(int ID, int x, int y) {
        super(ID, x, y);
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

    public boolean isEndField() {
        return endField;
    }

    public void setEndField(boolean endField) {
        this.endField = endField;
    }
}
