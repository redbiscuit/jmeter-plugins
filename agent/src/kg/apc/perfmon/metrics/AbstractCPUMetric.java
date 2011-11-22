package kg.apc.perfmon.metrics;

import java.util.Arrays;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.hyperic.sigar.SigarProxy;

/**
 *
 * @author undera
 */
abstract class AbstractCPUMetric extends AbstractPerfMonMetric {

    private static final Logger log = LoggingManager.getLoggerForClass();
    protected final MetricParams params;

    public static AbstractCPUMetric getMetric(SigarProxy sigar, String metricParams) {
        MetricParams params = MetricParams.createFromString(metricParams, "user", sigar);

        if (params.PID >= 0) {
            return new CPUProcMetric(sigar, params);
        } else {
            return new CPUTotalMetric(sigar, params);
        }
    }

    public AbstractCPUMetric(SigarProxy aSigar, MetricParams params) {
        super(aSigar);
        this.params = params;
    }
}