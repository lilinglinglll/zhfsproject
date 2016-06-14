package lab516.zhfs.entity;

public class LastTime {
	private String tagNum;
	private String roomNum;
	private String startDate;
	private String endDate;
	private String start;
	private String end;
	
	private String cpid;
	private String rssi;
	private String wakeupNum;
	
	private String Name;
	
	public LastTime(){}

	public LastTime(String tagNum, String roomNum, String startDate, String endDate, String start, String end, String cpid,
			String rssi, String wakeupNum) {
		super();
		this.tagNum = tagNum;
		this.roomNum = roomNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.start = start;
		this.end = end;
		this.cpid = cpid;
		this.rssi = rssi;
		this.wakeupNum = wakeupNum;
	}

	public String getTagNum() {
		return tagNum;
	}

	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getWakeupNum() {
		return wakeupNum;
	}

	public void setWakeupNum(String wakeupNum) {
		this.wakeupNum = wakeupNum;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Times [tagNum=" + tagNum + ", roomNum=" + roomNum + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", start=" + start + ", end=" + end + ", cpid=" + cpid + ", rssi=" + rssi + ", wakeupNum=" + wakeupNum
				+ ", Name=" + Name + "]";
	}
}
