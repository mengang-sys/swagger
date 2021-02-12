package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class SysPermission implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;//权限名称
    //资源类型
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    private String url;//资源路径

    private String permission;//资源字符串

    private Long parentId;//父编号

    //父编号列表
    private String parentIds;

    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;
}
