public class Traveller {
	long id;
    String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Traveller() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Traveller(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}