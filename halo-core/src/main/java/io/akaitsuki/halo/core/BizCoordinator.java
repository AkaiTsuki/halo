package io.akaitsuki.halo.core;


import java.util.Objects;

public class BizCoordinator {

    private String bizId;
    private String useCase;
    private String scenario;
    private String clazz;

    public BizCoordinator() {
    }

    public BizCoordinator(String bizId) {
        this.bizId = bizId;
    }

    public BizCoordinator(String bizId, String useCase) {
        this.bizId = bizId;
        this.useCase = useCase;
    }

    public BizCoordinator(String bizId, String useCase, String scenario) {
        this.bizId = bizId;
        this.useCase = useCase;
        this.scenario = scenario;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizCoordinator that = (BizCoordinator) o;
        return bizId.equals(that.bizId) &&
                Objects.equals(useCase, that.useCase) &&
                Objects.equals(scenario, that.scenario) &&
                clazz.equals(that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bizId, useCase, scenario, clazz);
    }

    @Override
    public String toString() {
        return "BizCoordinator{" +
                "bizId='" + bizId + '\'' +
                ", useCase='" + useCase + '\'' +
                ", scenario='" + scenario + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}

