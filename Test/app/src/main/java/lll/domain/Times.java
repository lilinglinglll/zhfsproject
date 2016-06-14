package lll.domain;
public class Times {
	private String tagNum;
	private String roomNum;
	private String startDate;
	private String endDate;
	private String start;
	private String end;

	public Times(){}

	public Times(String tagNum, String roomNum, String startDate, String endDate, String start, String end) {
		this.tagNum = tagNum;
		this.roomNum = roomNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.start = start;
		this.end = end;
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

	@Override
	public String toString() {
		return "Times{" +
				"tagNum='" + tagNum + '\'' +
				", roomNum='" + roomNum + '\'' +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", start='" + start + '\'' +
				", end='" + end + '\'' +
				'}';
	}
}
