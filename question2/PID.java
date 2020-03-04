class PID {
    private double kp, ki, kd;
    private double target;
    private double e, de;

    private static final int INTEG_MAX = 50;
    private double[] integ = new double[INTEG_MAX];
    private int integCur = 0;

    public PID(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.target = 0.0;
    }

    private double integral() {
        double sum = 0.0;
        for (double v : integ)
            sum += v;
        return sum;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void step(double v, double dv) {
        this.e = v - this.target;
        this.de = dv;

        integ[integCur++] = this.e;
        if (integCur == INTEG_MAX)
            integCur = 0;
    }

    public double calculate() {
        System.out.printf("P: %.08f, I: %.08f, D: %.08f\n", kp * e, ki * integral(), kd * de);
        return kp * e +
               ki * integral() +
               kd * de;
    }
}