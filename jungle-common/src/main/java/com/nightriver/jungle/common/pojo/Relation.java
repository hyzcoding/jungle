package com.nightriver.jungle.common.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description: 关系实体
 * @Author: hyz
 * @CreateDate: 2019/3/2
 * @Version: 1.0
 **/
@Entity
public class Relation implements Serializable {
    @Id
    @GeneratedValue
    private Integer relationId;

    private Integer relationActive;

    private Integer relationPassive;

    private Byte relationType;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getRelationActive() {
        return relationActive;
    }

    public void setRelationActive(Integer relationActive) {
        this.relationActive = relationActive;
    }

    public Integer getRelationPassive() {
        return relationPassive;
    }

    public void setRelationPassive(Integer relationPassive) {
        this.relationPassive = relationPassive;
    }

    public Byte getRelationType() {
        return relationType;
    }

    public void setRelationType(Byte relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "relationId=" + relationId +
                ", relationActive=" + relationActive +
                ", relationPassive=" + relationPassive +
                ", relationType=" + relationType +
                '}';
    }
}