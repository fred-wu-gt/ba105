package com.rep_com.model;

public class Rep_comVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rc_no;
	private String com_no;
	private String mem_no;
	private String rc_cuz;
	private String rc_stat;
	private String rc_txt;
	public String getRc_no() {
		return rc_no;
	}
	public void setRc_no(String rc_no) {
		this.rc_no = rc_no;
		}
		public String getCom_no() {
			return com_no;
		}
		public void setCom_no(String com_no) {
			this.com_no = com_no;
		}
		public String getMem_no() {
			return mem_no;
		}
		public void setMem_no(String mem_no) {
			this.mem_no = mem_no;
		}
		public String getRc_cuz() {
			return rc_cuz;
		}
		public void setRc_cuz(String rc_cuz) {
			this.rc_cuz = rc_cuz;
		}
		public String getRc_stat() {
			return rc_stat;
		}
		public void setRc_stat(String rc_stat) {
			this.rc_stat = rc_stat;
		}
		public String getRc_txt() {
			return rc_txt;
	}
	public void setRc_txt(String rc_txt) {
		this.rc_txt = rc_txt;
	}
	
	

}
