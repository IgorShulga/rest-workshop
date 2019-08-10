package ua.skillsup.practice.restworkshop.models;

public class SubTask {

	private String title;
	private int order;

	public SubTask() {
	}

	public SubTask(String title, int order) {
		this.title = title;
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "SubTask{" +
				"title='" + title + '\'' +
				", order=" + order +
				'}';
	}
}