package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Visitors")
public class Visitor {
	@Id
	@Column(name = "Id")
	private Integer id;

	@Column(name = "VisitCount")
	private Integer visitCount;

	public Visitor() {
		// TODO Auto-generated constructor stub
	}

	public Visitor(Integer id, Integer visitCount) {
		super();
		this.id = id;
		this.visitCount = visitCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
}
