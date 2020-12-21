package com.crowdvocate.taskmanager.data;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.crowdvocate.taskmanager.data.StatusEnum;;



@Entity
@Table(name="newTask")
public class TaskEntity {

	private Long id;
    private String name;
    private String description;
    private StatusEnum status;
    private Date startDate;
    private Date endDate;

    public TaskEntity() {
        this.status = StatusEnum.To_do;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TaskEntity [id=" + id + ", name=" + name + ", status=" + status + ", startDate=" + startDate
                + ", endDate=" + endDate + "]";
    }

}