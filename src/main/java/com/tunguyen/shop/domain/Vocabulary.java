package com.tunguyen.shop.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "vocabulary")
@Entity(name = "vocabulary")
public class Vocabulary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty
	@Column(name = "tuvung")
	private String tuvung;

	@Column(name = "nghia")
	private String nghia;

	@Column(name = "vidu1")
	private String vidu1;

	@Column(name = "vidu2")
	private String vidu2;

	@Column(name = "tuloai")
	private String tuloai;

	@Column(name = "noihoc")
	private String noihoc;

	@Column(name = "tudongnghia")
	private String tudongnghia;

	@Column(name = "tutrainghia")
	private String tutrainghia;

	@Column(name = "ngaynhap")
	private Date ngaynhap;

	@Column(name = "ghichu")
	private String ghichu;

	@Column(name = "dathuoc")
	private boolean dathuoc;

	@Column(name = "solanon")
	private int solanon = 0;

	@Column(name = "userid")
	private int userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTuvung() {
		return tuvung;
	}

	public void setTuvung(String tuvung) {
		this.tuvung = tuvung;
	}

	public String getNghia() {
		return nghia;
	}

	public void setNghia(String nghia) {
		this.nghia = nghia;
	}

	public String getVidu1() {
		return vidu1;
	}

	public void setVidu1(String vidu1) {
		this.vidu1 = vidu1;
	}

	public String getVidu2() {
		return vidu2;
	}

	public void setVidu2(String vidu2) {
		this.vidu2 = vidu2;
	}

	public String getTuloai() {
		return tuloai;
	}

	public void setTuloai(String tuloai) {
		this.tuloai = tuloai;
	}

	public String getNoihoc() {
		return noihoc;
	}

	public void setNoihoc(String noihoc) {
		this.noihoc = noihoc;
	}

	public String getTudongnghia() {
		return tudongnghia;
	}

	public void setTudongnghia(String tudongnghia) {
		this.tudongnghia = tudongnghia;
	}

	public String getTutrainghia() {
		return tutrainghia;
	}

	public void setTutrainghia(String tutrainghia) {
		this.tutrainghia = tutrainghia;
	}

	public Date getNgaynhap() {
		return ngaynhap;
	}

	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public boolean isDathuoc() {
		return dathuoc;
	}

	public void setDathuoc(boolean dathuoc) {
		this.dathuoc = dathuoc;
	}

	public int getSolanon() {
		return solanon;
	}

	public void setSolanon(int solanon) {
		this.solanon = solanon;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	

}
