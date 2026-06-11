public enum Step {
    CAR_TYPE, ENGINE, BRAKE, STEERING, RUN_TEST;

    public Step previous() {
        return ordinal() > 0 ? values()[ordinal() - 1] : this;
    }
}
