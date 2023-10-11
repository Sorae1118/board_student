/*================================================
 * user : sorae
 * date : 2023. 10. 10.
 * file_name : CommentsDto.java
 * comments :
================================================*/
package cs.dit.comments;

import java.sql.Date;

public class CommentsDto {
	private int num;
	private int ccode;
	private int bcode;
	private String content;
	private Date regDate;
	
	public CommentsDto(int num, int ccode, int bcode, String content, Date regDate) {
		super();
		this.num = num;
		this.ccode = ccode;
		this.bcode = bcode;
		this.content = content;
		this.regDate = regDate;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getCcode() {
		return ccode;
	}

	public void setCcode(int ccode) {
		this.ccode = ccode;
	}

	public int getBcode() {
		return bcode;
	}

	public void setBcode(int bcode) {
		this.bcode = bcode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
