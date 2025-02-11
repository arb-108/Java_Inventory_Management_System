
public class user {
	private String userType;
    private String name;
    private String username;

    public String getUsername() {
		return username;
	}

    public user( String name, String username,String userType) {
		super();
		this.userType = userType;
		this.name = name;
		this.username = username;
	}

	public String getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }
}

//04235969836  