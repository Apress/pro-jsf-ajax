package com.apress.projsf.ch1.application;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

public class CredentialsBean
{
  public void onLogin(
    ActionEvent event)
  {
    if (!"duke".equalsIgnoreCase(_username))
      throw new AbortProcessingException("Unrecognized username!");

    // clear out the password, for good measure!
    _password = null;
  }

  public void setUsername(
    String username)
  {
    _username = username;
  }

  public String getUsername()
  {
    return _username;
  }

  public void setPassword(
    String password)
  {
    _password = password;
  }

  public String getPassword()
  {
    return _password;
  }

  private String _username;
  private String _password;
}
