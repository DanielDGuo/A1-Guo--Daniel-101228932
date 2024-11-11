package org.example;

public class Controller {
    @PostMapping("/something")
    public String foo(){
        return "foo";
    }
}
