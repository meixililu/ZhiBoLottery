package com.zhibo.lotterystore.dao;

public class Result {
	private String area;

	private String code;

	private String descr;

	private boolean high;

	private boolean hots;

	private String issuer;

	private String notes;

	private String serdescr;

	private String series;

	private String tcode;

	private String tdescr;

	private int times;
	
	private String expect;
	
	private String name;
	private String openCode;
	private String time;
	private String timestamp;
	
	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenCode() {
		return openCode;
	}

	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return this.area;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setHigh(boolean high) {
		this.high = high;
	}

	public boolean getHigh() {
		return this.high;
	}

	public void setHots(boolean hots) {
		this.hots = hots;
	}

	public boolean getHots() {
		return this.hots;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssuer() {
		return this.issuer;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setSerdescr(String serdescr) {
		this.serdescr = serdescr;
	}

	public String getSerdescr() {
		return this.serdescr;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSeries() {
		return this.series;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getTcode() {
		return this.tcode;
	}

	public void setTdescr(String tdescr) {
		this.tdescr = tdescr;
	}

	public String getTdescr() {
		return this.tdescr;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getTimes() {
		return this.times;
	}

}