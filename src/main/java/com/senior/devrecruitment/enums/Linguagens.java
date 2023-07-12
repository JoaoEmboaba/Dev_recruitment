package com.senior.devrecruitment.enums;

public enum Linguagens {

    JAVA("BACKEND"), PYTHON("BIGDATA"), JAVASCRIPT("DESENVOLVIMENTO_WEB"),
    SQL("DESENVOLVIMENTO_DESKTOP"), HTML("FRONTEND"), CSS("FRONTEND"), GO("DESENVOLVIMENTO_WEB"),
    C("DESENVOLVIMENTO_DESKTOP"), CPP("DESENVOLVIMENTO_DESKTOP"),
    CS("DESENVOLVIMENTO_DESKTOP"), TYPESCRIPT("DESENVOLVIMENTO_WEB"),
    TS("DESENVOLVIMENTO_WEB"), JS("DESENVOLVIMENTO_WEB"), PHP("DESENVOLVIMENTO_WEB"),
    RUBY(""), SWIFT(""), ELIXIR(""), LUA(""), COBOL(""), ASSEMBLY(""), FORTRAN("");

    private final String role;

    Linguagens(String role) {
        this.role = null;
    }

    public String getRole() {
        return role;
    }
}
