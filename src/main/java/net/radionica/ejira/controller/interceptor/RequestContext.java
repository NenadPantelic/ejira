package net.radionica.ejira.controller.interceptor;

import net.radionica.ejira.model.Role;

public class RequestContext {
    private String _token;
    private Long _userId;
    private String _username;
    private Role role; 

    public Role getRole() {
        return role;
    }

    public String getToken() {
	return _token;
    }

    public Long getUserId() {
	return _userId;
    }

    public String getUsername() {
	return _username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setToken(String authToken) {
	// TODO Auto-generated method stub
	_token = authToken;
    }

    public void setUserId(Long userId) {
	_userId = userId;
    }

    public void setUsername(String username) {
	_username = username;
    }

}
