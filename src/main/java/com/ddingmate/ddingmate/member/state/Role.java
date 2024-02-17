package com.ddingmate.ddingmate.member.state;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    USER("사용자"), ADMIN("운영자");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static Role fromCode(String db) {
        return Arrays.stream(Role.values())
                .filter(v -> v.getValue().equals(db))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("역할 카테고리에 %s가 존재하지 않습니다.", db)));
    }
}
