package eu.javaspecialists.perf.util;

import java.util.*;

public enum Memory {
    BYTES {
        public double toBytes(double d) {
            return d;
        }

        public double toKiloBytes(double d) {
            return toBytes(d) / 1024;
        }

        public double toMegaBytes(double d) {
            return toKiloBytes(d) / 1024;
        }

        public double toGigaBytes(double d) {
            return toMegaBytes(d) / 1024;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    KILOBYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * 1024;
        }

        public double toKiloBytes(double d) {
            return d;
        }

        public double toMegaBytes(double d) {
            return toKiloBytes(d) / 1024;
        }

        public double toGigaBytes(double d) {
            return toMegaBytes(d) / 1024;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    MEGABYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * 1024;
        }

        public double toKiloBytes(double d) {
            return toMegaBytes(d) * 1024;
        }

        public double toMegaBytes(double d) {
            return d;
        }

        public double toGigaBytes(double d) {
            return toMegaBytes(d) / 1024;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    GIGABYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * 1024;
        }

        public double toKiloBytes(double d) {
            return toMegaBytes(d) * 1024;
        }

        public double toMegaBytes(double d) {
            return toGigaBytes(d) * 1024;
        }

        public double toGigaBytes(double d) {
            return d;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    TERABYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * 1024;
        }

        public double toKiloBytes(double d) {
            return toMegaBytes(d) * 1024;
        }

        public double toMegaBytes(double d) {
            return toGigaBytes(d) * 1024;
        }

        public double toGigaBytes(double d) {
            return toTeraBytes(d) * 1024;
        }

        public double toTeraBytes(double d) {
            return d;
        }
    };

    public abstract double toBytes(double d);

    public abstract double toKiloBytes(double d);

    public abstract double toMegaBytes(double d);

    public abstract double toGigaBytes(double d);

    public abstract double toTeraBytes(double d);

    public static String format(double d, Memory unit, int decimals) {
        String unitStr;
        double val;
        double bytes = unit.toBytes(d);
        if (bytes < 1024) {
            val = bytes;
            unitStr = "B";
        } else if (bytes < 1024 * 1024) {
            val = BYTES.toKiloBytes(bytes);
            unitStr = "KB";
        } else if (bytes < 1024 * 1024 * 1024) {
            val = BYTES.toMegaBytes(bytes);
            unitStr = "MB";
        } else if (bytes < 1024 * 1024 * 1024 * 1024L) {
            val = BYTES.toGigaBytes(bytes);
            unitStr = "GB";
        } else {
            val = BYTES.toTeraBytes(bytes);
            unitStr = "TB";
        }
        return String.format(Locale.US, "%." + decimals + "f%s", val, unitStr);
    }
}
