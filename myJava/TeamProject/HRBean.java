package TeamProject;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HRBean {
	private int record_id;
	private int pet_id;
	private BigDecimal weight;
	private BigDecimal height;
	private String medical_history;
	private String vaccination_status;
	private String checkup_status;
	private String date;
	private Timestamp hr_date;
	
	
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public String getMedical_history() {
		return medical_history;
	}
	public void setMedical_history(String medical_history) {
		this.medical_history = medical_history;
	}
	public String getVaccination_status() {
		return vaccination_status;
	}
	public void setVaccination_status(String vaccination_status) {
		this.vaccination_status = vaccination_status;
	}
	public String getCheckup_status() {
		return checkup_status;
	}
	public void setCheckup_status(String checkup_status) {
		this.checkup_status = checkup_status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Timestamp getHr_date() {
		return hr_date;
	}
	public void setHr_date(Timestamp hr_date) {
		this.hr_date = hr_date;
	}
	
}
