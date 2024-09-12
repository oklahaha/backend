package com.swim.backend.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LogEventType {
    START("Start"),
    END("End"),
    LIST("List"),
    ADD("Add"),
    EDIT("Edit"),
    DELETE("Delete"),
    ERROR("Error"),
    DEBUG("Debug");

    String displayName;

	private LogEventType(String displayName){
		this.displayName = displayName;
	}

	@JsonValue
	public String value(){
		return displayName;
	}
}
