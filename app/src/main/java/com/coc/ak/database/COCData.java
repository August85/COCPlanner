package com.coc.ak.database;

public class COCData {

	private long id;
	private String clanName;
	private String actualName;
	private String email;
    private String status;

	public COCData() {

	}

	public COCData(long i, String clanName, String actualName, String email, String status) {
		this.id = i;
        this.clanName = clanName;
        this.actualName = actualName;
        this.email = email;
        this.status = status;
	}

	public COCData(String clanName, String actualName, String email, String status) {
		this.clanName = clanName;
		this.actualName = actualName;
        this.email = email;
        this.status = status;
	}

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
