package com.example.core;

public class MessageDto {
  private int id;
  private String email;

  public MessageDto(int id, String email) {
    this.id = id;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", email='" + email + '\'' +
        '}';
  }
}
