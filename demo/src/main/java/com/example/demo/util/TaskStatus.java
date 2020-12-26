package com.example.demo.util;

//import com.fasterxml.jackson.annotation.JsonProperty;


import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("taskStatus")
public enum TaskStatus {
    Complete,
    UnAssign,
    Assigned,
    Outdated,


}
