package hu.bme.mit.theta.cloud.repository.datamodel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "configuration_id")
    private UUID configurationId;

    @Column(name = "domain_name")
    private String domainName;

    @Column
    private String refinement;

    @Column(name = "pred_split")
    private String predSplit;

    @Column(name = "error_loc")
    private String errorLoc;

    @Column(name = "prec_granularity")
    private String precGranularity;

    private String search;

    @Column
    private String encoding;

    @Column(name = "max_enum")
    private Integer maxEnum;

    @Column(name = "init_prec")
    private String initPrec;

    @Column(name = "prune_strategy")
    private String pruneStrategy;

    @Column(name = "log_level")
    private String logLevel;

    @Column(name = "benchmark_mode")
    private Boolean benchmarkMode;

    @Column(name = "cex_file")
    private Boolean cexFile;

    @Column
    private Boolean stacktrace;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, name = "creation_date")
    private OffsetDateTime creationDate;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getRefinement() {
        return refinement;
    }

    public void setRefinement(String refinement) {
        this.refinement = refinement;
    }

    public String getPredSplit() {
        return predSplit;
    }

    public void setPredSplit(String predSplit) {
        this.predSplit = predSplit;
    }

    public String getErrorLoc() {
        return errorLoc;
    }

    public void setErrorLoc(String errorLoc) {
        this.errorLoc = errorLoc;
    }

    public String getPrecGranularity() {
        return precGranularity;
    }

    public void setPrecGranularity(String precGranularity) {
        this.precGranularity = precGranularity;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getInitPrec() {
        return initPrec;
    }

    public void setInitPrec(String initPrec) {
        this.initPrec = initPrec;
    }

    public String getPruneStrategy() {
        return pruneStrategy;
    }

    public void setPruneStrategy(String pruneStrategy) {
        this.pruneStrategy = pruneStrategy;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Boolean getBenchmarkMode() {
        return benchmarkMode;
    }

    public void setBenchmarkMode(Boolean benchmarkMode) {
        this.benchmarkMode = benchmarkMode;
    }

    public Boolean getCexFile() {
        return cexFile;
    }

    public void setCexFile(Boolean cexFile) {
        this.cexFile = cexFile;
    }

    public Boolean getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(Boolean stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getMaxEnum() {
        return maxEnum;
    }

    public void setMaxEnum(Integer maxEnum) {
        this.maxEnum = maxEnum;
    }
}