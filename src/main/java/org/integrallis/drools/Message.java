package org.integrallis.drools;

public class Message {
    private String message;
    private String originalWord;

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getOriginalWord() {
		return originalWord;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}
}
