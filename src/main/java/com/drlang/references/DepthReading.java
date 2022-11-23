package com.drlang.references;

public class DepthReading implements Cloneable {
    private double depth;

    public DepthReading(double depth) {
        this.depth = depth;
    }

    @Override
    public DepthReading clone() {
        try {
            return (DepthReading) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return String.valueOf(depth);
    }

  static   public class TemperatureReading implements Cloneable {
        private long time;
        private double temperature;

        public TemperatureReading(double temperature) {
            this.temperature = temperature;
            time = System.currentTimeMillis();
        }

        @Override
        protected TemperatureReading clone() {
            try {
                return (TemperatureReading) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        @Override
        public String toString() {
            return String.valueOf(temperature);
        }
    }

   static public class OceanReading implements Cloneable {
        private DepthReading depth;
        private TemperatureReading temperature;

        public OceanReading(double tdata, double ddata) {
            temperature = new TemperatureReading(tdata);
            depth = new DepthReading(ddata);
        }

        @Override
        protected OceanReading clone() {
            OceanReading or = null;
            try {
                or = (OceanReading) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            or.depth = or.depth.clone();
            or.temperature = or.temperature.clone();
            return or;
        }

        public void setTemperature(TemperatureReading temperature) {
            this.temperature = temperature;
        }

        public TemperatureReading getTemperature() {
            return temperature;
        }

        public DepthReading getDepth() {
            return depth;
        }

        public void setDepth(DepthReading depth) {
            this.depth = depth;
        }

        public String toString() {
            return "OceanReading{" +
                    "depth=" + depth +
                    ", temperature=" + temperature +
                    '}';
        }
    }

    public static void main(String[] args) {
        OceanReading reading = new OceanReading(33.9, 100.5);
        OceanReading clone = reading.clone();
        TemperatureReading tr = clone.getTemperature();
        tr.setTemperature(tr.getTemperature() + 1);
        clone.setTemperature(tr);
        DepthReading dr = clone.getDepth();
        dr.setDepth(dr.getDepth() + 1);
        clone.setDepth(dr);

    }
}

