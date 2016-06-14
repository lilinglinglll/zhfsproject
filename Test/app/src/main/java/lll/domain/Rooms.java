package lll.domain;

public class Rooms {
	private String roomNum;
	public Rooms(){}
	public Rooms(String roomNum) {
		super();
		this.roomNum = roomNum;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	@Override
	public String toString() {
		return "Rooms [roomNum=" + roomNum + "]";
	}
	}
