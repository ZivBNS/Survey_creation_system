import java.io.Serializable;

public class Info implements Serializable{

	public String queString;
	public String opt1;
	public String opt2;
	public String opt3;
	public int count1;
	public int count2;
	public int count3;
	public int status;
	public String win;
	
	public Info(String queString,String opt1,String opt2,String opt3, int count1, int count2, int count3,int status,String win) {
		
		this.queString = queString;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.count1 = count1;
		this.count2 = count2;
		this.count3 = count3;
		this.status=status;
		this.win = win;

	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public String getQueString() {
		return queString;
	}

	public void setQueString(String queString) {
		this.queString = queString;
	}

	public String getOpt1() {
		return opt1;
	}

	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}

	public String getOpt2() {
		return opt2;
	}

	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}

	public String getOpt3() {
		return opt3;
	}

	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Info [queString=" + queString + ", opt1=" + opt1 + ", opt2=" + opt2 + ", opt3=" + opt3 + ", count1="
				+ count1 + ", count2=" + count2 + ", count3=" + count3 + ", status=" + status + "]";
	}


}
