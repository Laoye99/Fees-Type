package com.xadmin.feestype.bean;

public class Type {
	private String fee_type_id;
	private String fee_type_desc;
	
	
	public Type(String fee_type_id, String fee_type_desc) {
		super();
		this.fee_type_id = fee_type_id;
		this.fee_type_desc = fee_type_desc;
	}
	
	public Type(String fee_type_desc) {
		super();
		this.fee_type_desc = fee_type_desc;
	}

	public String getFee_type_id() {
		return fee_type_id;
	}
	public void setFee_type_id(String fee_type_id) {
		this.fee_type_id = fee_type_id;
	}
	public String getFee_type_desc() {
		return fee_type_desc;
	}
	public void setFee_type_desc(String fee_type_desc) {
		this.fee_type_desc = fee_type_desc;
	}
	
	
}
