package com.tgroup.teste.entity.enums;

public enum Profile {

	ADMIN(1, "ROLE_ADMIN"),
	CUSTOMER(2, "ROLE_CUSTOMER");
	
	private int code;
	private String description;

	Profile(int code, String description) {
		this.setCode(code);
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public static Profile toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (Profile x: Profile.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + code);
	}

}
