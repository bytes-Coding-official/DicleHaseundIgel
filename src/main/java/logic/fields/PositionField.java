package logic.fields;

public class PositionField extends Field {

    private final int value;


    protected PositionField(int ID, int value) {
        super(ID);
        if (value < 1 || value > 6) throw new IllegalArgumentException("Value must be between 1 and 6");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void checkValue(int position) {
        //TODO: implement
    }

    @Override
    public void performAction() {

    }
}
