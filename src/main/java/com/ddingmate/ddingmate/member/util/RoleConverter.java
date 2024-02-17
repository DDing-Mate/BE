package com.ddingmate.ddingmate.member.util;

import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role attribute) {
            return attribute.getValue();
        }

    @Override
        public Role convertToEntityAttribute(String dbData) {
            return Role.fromCode(dbData);
        }
}
